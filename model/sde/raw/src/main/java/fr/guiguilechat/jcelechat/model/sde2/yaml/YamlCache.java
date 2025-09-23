package fr.guiguilechat.jcelechat.model.sde2.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.sde2.RemoteMeta;
import fr.guiguilechat.jcelechat.model.sde2.parsers.SdeMeta;
import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache.SDEDownload.Cached;
import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache.SDEDownload.Errored;
import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache.SDEDownload.Success;
import fr.lelouet.tools.application.xdg.XDGApp;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YamlCache {

	public static final YamlCache INSTANCE = new YamlCache();

	public static InputStreamReader fileReader(File file) throws FileNotFoundException {
		return new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));
	}

	public static Yaml yaml(Constructor cons) {
		LoaderOptions options = new LoaderOptions();
		options.setCodePointLimit(Integer.MAX_VALUE);
		DumperOptions dumperOptions = new DumperOptions();
		Representer representer = new Representer(dumperOptions);
		return new Yaml(cons, representer, dumperOptions, options);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final XDGApp app = new XDGApp("sde.ccp.is");

	//
	// extract
	//

	protected String format() {
		return "yaml";
	}

	/**
	 * where we want to extract the SDE
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractCacheDir = app().cacheFile(format() + "/extracted");

	/**
	 * the file that contains the release date of last downloaded sde , if any.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractMetaFile = new File(extractCacheDir(), "_sde." + format());

	// set to true to avoid downloading the SDE.
	private boolean extractTriedDL = false;

	/**
	 * if needed, download the full yaml from the sde . those files will be
	 * extracted and placed in {@link #extractCacheDir()}
	 */
	public synchronized void donwloadSDE() {
		if (!extractTriedDL) {
			updateExtract();
		}
		extractTriedDL = true;
	}

	protected RemoteMeta extractArchiveMeta(File file) throws IOException {
		return new ObjectMapper(new YAMLFactory()).readValue(file, SdeMeta.class).sde;
	}

	/**
	 * check if a new version is avail (using stored etag and remote ) and extract
	 * it if needed
	 */
	protected void updateExtract() {
		RemoteMeta meta = RemoteMeta.fetch();
		String lastRelease = meta.releaseDate;
		if (extractMetaFile().exists()) {
			try {
				RemoteMeta lastMeta = extractArchiveMeta(extractMetaFile());
				if (lastRelease.equals(lastMeta.releaseDate)) {
					log.info(
							"sde already last version: " + lastRelease + " in  " + extractCacheDir().getAbsolutePath());
					return;
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			log.info("new version of sde to download with lastRelease " + lastRelease + " into "
					+ extractCacheDir().getAbsolutePath());
		} else {
			log.info("no existing download information in file " + extractMetaFile().getAbsolutePath()
					+ ", downloading sde into " + extractCacheDir().getAbsolutePath());
		}
		try {
			FileTools.delDir(extractCacheDir());
			extractCacheDir().mkdirs();
			unpackSDE(extractURL(meta), extractCacheDir());
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
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

	public sealed interface SDEDownload {
		RemoteMeta meta();

		public record Success(String url, RemoteMeta meta, ReadableByteChannel channel) implements SDEDownload {

			void copyTo(File targetDir)
					throws FileNotFoundException, IOException {
				try (FileOutputStream fileOutputStream = new FileOutputStream(targetDir)) {
					fileOutputStream.getChannel().transferFrom(channel(), 0, Long.MAX_VALUE);
				}
			}

			public File toTempFile() throws IOException {
				File created = File.createTempFile("SDE", null);
				copyTo(created);
				return created;
			}
		}

		public record Cached(RemoteMeta meta) implements SDEDownload {
		}

		public record Errored(String url, RemoteMeta meta, Exception error) implements SDEDownload {
		}
	}

	public SDEDownload getSDE(String lastReleaseDate) {
		RemoteMeta meta = RemoteMeta.fetch();
		String releaseDate = meta.releaseDate;
		if (releaseDate.equals(lastReleaseDate)) {
			return new Cached(meta);
		}
		String url = extractURL(meta);
		try {
			return new Success(url, meta, Channels.newChannel(new URI(url).toURL().openStream()));
		} catch (IOException | URISyntaxException ioe) {
			return new Errored(url, meta, ioe);
		}
	}

	/**
	 * where we want to place the zipped SDE
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File zipCacheDir = app().cacheFile(format() + "/zipped");

	/**
	 * the downloaded zip file
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File zipTarget = new File(zipCacheDir(), "sde.zip");

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File zipTemp = new File(zipCacheDir(), "sde.zip.tmp");

	@Getter(lazy = true)
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
			log.info("no existing download information in file " + zipLastMetaFile().getAbsolutePath()
					+ ", downloading sde into " + zipCacheDir().getAbsolutePath());
		}
		SDEDownload dl = getSDE(lastReleaseDate);
		switch (dl) {
		case Cached _:
			log.info("sde already last version " + lastReleaseDate + " in " + zipCacheDir().getAbsolutePath());
			return nullOnCached ? null : zipTarget();
		case Errored _:
			return null;
		case Success s:
			log.info("sde DL new release " + s.meta().releaseDate + " into " + zipCacheDir().getAbsolutePath());
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
			}catch (Exception e){
				throw new RuntimeException(e);
			}
		}
	}

	protected void writeZipMeta(RemoteMeta meta) throws IOException {
		SdeMeta ym = new SdeMeta();
		ym.sde = meta;
		new ObjectMapper(new YAMLFactory()).writeValue(zipLastMetaFile(), ym);
	}

	public static void main(String[] args) {
		var cache = new YamlCache();
		cache.donwloadSDE();
		cache.donwloadZipped(true);
	}

}
