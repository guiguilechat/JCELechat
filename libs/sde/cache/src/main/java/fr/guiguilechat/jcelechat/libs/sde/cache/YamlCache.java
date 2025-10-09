package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.Channels;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import fr.guiguilechat.jcelechat.libs.sde.cache.SDECacheListener.Ref;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.SdeMeta;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Cached;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Errored;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult.Success;
import fr.lelouet.tools.application.xdg.XDGApp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YamlCache {

	public static final YamlCache INSTANCE = new YamlCache();

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final XDGApp app = new XDGApp("sde.ccp.is");

	protected String format() {
		return "yaml";
	}

	//
	// prepare to download the SDE
	//

	/**
	 * try to fetch SDE is new version avail.
	 *
	 * @param lastReleaseDate last releaseDate received, can be null.
	 * @return a {@link Success} with the channel of the connection to load the SDE
	 *         from if newer SDE avail, a {@link Cached} if no new SDE avail, or an
	 *         {@link Errored} if could not fetch the metadata or the new SDE.
	 */
	public DLResult dl(String lastReleaseDate) {
		RemoteMeta meta = null;
		String url = null;
		try {
			meta = RemoteMeta.fetch();
			String releaseDate = meta.releaseDate;
			if (releaseDate.equals(lastReleaseDate)) {
				return new Cached(meta);
			}
			url = extractURL(meta);
		} catch (Exception e) {
			return new Errored(RemoteMeta.URL, null, e);
		}
		try {
			return new Success(url, meta, Channels.newChannel(new URI(url).toURL().openStream()));
		} catch (Exception e) {
			return new Errored(url, meta, e);
		}
	}

	public DLResult dl() {
		return dl(null);
	}

	//
	// extract
	//

	/**
	 * where we store the files
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractCacheDir = app().cacheFile(format() + "/extracted");

	/**
	 * the file that contains the release date of last downloaded sde , if any.
	 */
	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final File extractMetaFile = new File(extractCacheDir(), "_sde." + format());

	// set to true to avoid downloading the SDE.
	private Instant tryAfter = null;

	@Setter
	private int updateDelayMinutes = 15;
	@Setter
	private int errorDelayMinutes = 5;
	@Setter
	private int successDelayMinutes = 30;

	/**
	 * if needed, download the full yaml from the sde . those files will be
	 * extracted and placed in {@link #extractCacheDir()}
	 */
	public synchronized void donwloadSDE() {
		if (tryAfter == null || tryAfter.isBefore(Instant.now())) {
			updateExtract();
		}
	}

	protected RemoteMeta extractArchiveMeta(File file) throws IOException {
		return new ObjectMapper(new YAMLFactory()).readValue(file, SdeMeta.class).sde;
	}

	/**
	 * check if a new version is avail and extract it
	 */
	protected void updateExtract() {
		String lastReleaseDate = null;
		if (extractMetaFile().exists()) {
			try {
				RemoteMeta lastMeta = extractArchiveMeta(extractMetaFile());
				lastReleaseDate = lastMeta.releaseDate;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			log.debug("no existing download information in file " + extractMetaFile().getAbsolutePath()
					+ ", downloading sde into " + extractCacheDir().getAbsolutePath());
		}
		DLResult result = dl(lastReleaseDate);
		switch (result) {
		case Success s: {
			log.debug("new version of sde to download with releaseDate " + s.meta().releaseDate + " into "
					+ extractCacheDir().getAbsolutePath());
			fr.guiguilechat.jcelechat.libs.sde.cache.tools.FileTools.delDir(extractCacheDir());
			extractCacheDir().mkdirs();
			s.extract(extractCacheDir());
			clearCaches();
			tryAfter = Instant.now().plusSeconds(60 * successDelayMinutes);
			break;
		}
		case Errored e: {
			log.error("failed to download SDE", e);
			tryAfter = Instant.now().plusSeconds(60 * errorDelayMinutes);
			break;
		}
		case Cached c: {
			log.debug("sde already last version: " + c.meta().releaseDate + " in  "
					+ extractCacheDir().getAbsolutePath());
			tryAfter = Instant.now().plusSeconds(60 * updateDelayMinutes);
			break;
		}
		}
	}

	protected String extractURL(RemoteMeta meta) {
		return meta.yamlURL();
	}

	/**
	 * unpack the SDE from a zip url into a folder.
	 *
	 * @param url
	 *                 the sip url
	 * @param cacheDir
	 *                 output folder
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	protected static void unpackSDE(String url, File cacheDir) throws MalformedURLException, IOException {
		InputStream is;
		try {
			is = new URI(url).toURL().openStream();
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
		try (ZipInputStream zis = new ZipInputStream(is)) {
			ZipEntry e;
			while ((e = zis.getNextEntry()) != null) {
				File out = new File(cacheDir, e.getName());
				out.getParentFile().mkdirs();
				FileWriter fw = new FileWriter(out);
				byte[] b = new byte[1000];
				while (zis.available() > 0) {
					int r = zis.read(b, 0, b.length);
					if (r > -1) {
						fw.write(new String(b, 0, r));
					}
				}
				fw.close();
			}
		}
	}

	//
	// download zip
	//

	/**
	 * where we want to place the zipped SDE
	 */
	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final File zipCacheDir = app().cacheFile(format() + "/zipped");

	/**
	 * the downloaded zip file
	 */
	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final File zipTarget = new File(zipCacheDir(), "sde.zip");

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final File zipTemp = new File(zipCacheDir(), "sde.zip.tmp");

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final File zipLastMetaFile = new File(zipCacheDir(), "meta.yaml");

	// set to true to avoid downloading the SDE.
	private boolean zipTriedDL = false;

	/**
	 * if needed, download the full yaml from the sde . those files will be
	 * extracted and placed in {@link #extractCacheDir()}
	 */
	public synchronized void donwloadZipped(boolean returnOnlyNew) {
		if (!zipTriedDL) {
			updateZip(returnOnlyNew);
		}
		zipTriedDL = true;
	}

	/**
	 * check if a new version is avail (using etag) and, when yes, download it
	 *
	 * @param nullOnCached when set to true, only return the file when newer sde
	 * @return null if the file is the same as previous and returnOnlyNew is
	 *         specified, existing file if no update, new file if update.
	 */
	protected File updateZip(boolean nullOnCached) {
		String lastReleaseDate = null;
		if (zipTarget().isFile() && zipLastMetaFile().isFile()) {
			try {
				RemoteMeta lastMeta = extractArchiveMeta(zipLastMetaFile());
				lastReleaseDate = lastMeta.releaseDate;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			log.debug("no existing download information in file " + zipLastMetaFile().getAbsolutePath()
					+ ", downloading sde into " + zipCacheDir().getAbsolutePath());
		}
		DLResult dl = dl(lastReleaseDate);
		switch (dl) {
		case Cached _:
			log.debug("sde already last version " + lastReleaseDate + " in " + zipCacheDir().getAbsolutePath());
			return nullOnCached ? null : zipTarget();
		case Errored _:
			return null;
		case Success s:
			log.debug("sde DL new release " + s.meta().releaseDate + " into " + zipCacheDir().getAbsolutePath());
			try {
				zipCacheDir().mkdirs();
				if (!zipCacheDir().isDirectory()) {
					log.error("can't create dir " + zipCacheDir().getAbsolutePath());
					return null;
				}
				zipTemp().delete();
				s.copyTo(zipTemp());
				zipTemp().renameTo(zipTarget());
				writeZipMeta(s.meta());
				return zipTarget();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected void writeZipMeta(RemoteMeta meta) throws IOException {
		SdeMeta ym = new SdeMeta();
		ym.sde = meta;
		new ObjectMapper(new YAMLFactory()).writeValue(zipLastMetaFile(), ym);
	}

	// caching invalidation

	private final Set<SDECacheListener.Ref> registeredCaches = new HashSet<>();

	public void registerCache(SDECacheListener c) {
		synchronized (registeredCaches) {
			registeredCaches.add(new SDECacheListener.Ref(c));
		}
	}

	protected void clearCaches() {
		synchronized (registeredCaches) {
			log.debug("clearing " + registeredCaches.size() + " caches");
			for (Ref r : registeredCaches) {
				r.clearRefCache();
			}
		}
	}

	// main

	public static void main(String[] args) {
		var cache = new YamlCache();
		cache.donwloadSDE();
		cache.donwloadZipped(true);
	}

}
