package fr.guiguilechat.jcelechat.model.sde.load;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.sde.RemoteMeta;
import fr.guiguilechat.jcelechat.model.sde.Resolve;
import fr.lelouet.tools.application.xdg.XDGApp;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * store the SDE locally
 */
@Slf4j
public class SDECache {

	private static final Logger logger = LoggerFactory.getLogger(SDECache.class);

	public static final SDECache INSTANCE = new SDECache();

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

	/**
	 * where we want to extract the SDE
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractCacheDir = app().cacheFile("extracted");

	/**
	 * the directory that should be present when we cached the sde
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractCheckDir = new File(extractCacheDir(), "meta");

	/**
	 * the file that contains the etag of last downloaded sde , if any.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractEtagFile = new File(extractCheckDir(), "etag.txt");

	/**
	 * the file that contains the last-modified of last downloaded sde , if any.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractLastModifiedFile = new File(extractCheckDir(), "lastmodified.txt");

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

	/**
	 * check if a new version is avail (using stored etag and remote ) and extract
	 * it if needed
	 */
	public synchronized void updateExtract() {
		String url = Resolve.findURL();
		RemoteMeta meta = RemoteMeta.forUrl(url);
		String etag = meta.etag();
		if (extractEtagFile().exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(extractEtagFile()))) {
				if (etag.equals(br.readLine())) {
					logger.info("already last version of sde in  " + extractCacheDir().getAbsolutePath());
					return;
				}
				logger.info("new version of sde to download with etag " + etag + " into "
						+ extractCacheDir().getAbsolutePath());
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		} else {
			logger.info("no existing download information in file " + extractEtagFile().getAbsolutePath()
					+ ", downloading sde into " + extractCacheDir().getAbsolutePath());
		}
		try {
			FileTools.delDir(extractCacheDir());
			extractCacheDir().mkdirs();
			unpackSDE(url, extractCacheDir());
			writExtractedeMeta(meta);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	protected void writExtractedeMeta(RemoteMeta meta) throws IOException {
		extractLastModifiedFile().getParentFile().mkdirs();
		try (FileWriter fw = new FileWriter(extractLastModifiedFile())) {
			fw.write(DateTimeFormatter.RFC_1123_DATE_TIME.format(meta.lastModified().atOffset(ZoneOffset.UTC)));
		}
		extractEtagFile().getParentFile().mkdirs();
		try (FileWriter fw = new FileWriter(extractEtagFile())) {
			fw.write(meta.etag());
		}
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
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File zipCacheDir = app().cacheFile("zipped");

	/**
	 * the downloaded zip file
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File zipTarget = new File(zipCacheDir(), "sde.zip");

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File zipTemp = new File(zipCacheDir(), "sde.zip.tmp");

	/**
	 * the file that contains the etag of last downloaded sde , if any.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File zipEtagFile = new File(zipCacheDir(), "etag.txt");

	/**
	 * the file that contains the last-modified of last downloaded sde , if any.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File zipLastModifiedFile = new File(zipCacheDir(), "lastmodified.txt");

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
	public synchronized File updateZip(boolean nullOnCached) {
		String lastEtag = null;
		if (zipTarget().isFile() && zipEtagFile().isFile()) {
			try (BufferedReader br = new BufferedReader(new FileReader(zipEtagFile()))) {
				lastEtag = br.readLine();
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		} else {
			logger.info("no existing download information in file " + zipEtagFile().getAbsolutePath()
					+ ", downloading sde into " + zipCacheDir().getAbsolutePath());
		}
		SDEDownload res = getSDE(lastEtag);
		if (res.cached()) {
			logger.info("already last version of sde in  " + zipCacheDir().getAbsolutePath());
			return nullOnCached ? null : zipTarget();
		}
		if (res.error() != null) {
			return null;
		}
		logger.info(
				"new version of sde to download with etag " + res.etag() + " into " + zipCacheDir().getAbsolutePath());

		try {
			zipCacheDir().mkdirs();
			if (!zipCacheDir().isDirectory()) {
				log.error("can't create dir " + zipCacheDir().getAbsolutePath());
				return null;
			}
			zipTemp().delete();
			copySDE(res.channel(), zipTemp());
			zipTemp().renameTo(zipTarget());
			writeZipMeta(res);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		return zipTarget();
	}

	protected void writeZipMeta(SDEDownload sdeDL) throws IOException {
		try (FileWriter fw = new FileWriter(zipLastModifiedFile())) {
			fw.write(DateTimeFormatter.RFC_1123_DATE_TIME.format(sdeDL.lastModified().atOffset(ZoneOffset.UTC)));
		}
		try (FileWriter fw = new FileWriter(zipEtagFile())) {
			fw.write(sdeDL.etag());
		}

	}

	public static record SDEDownload(String url, String etag, Instant lastModified, ReadableByteChannel channel,
			Exception error,
			boolean cached) {
		static SDEDownload ok(String url, String etag, Instant lastModified, ReadableByteChannel channel) {
			return new SDEDownload(url, etag, lastModified, channel, null, false);
		}

		static SDEDownload error(String url, Exception error) {
			return new SDEDownload(url, null, null, null, error, false);
		}

		static SDEDownload cached(String url, String etag, Instant lastModified) {
			return new SDEDownload(url, etag, lastModified, null, null, true);
		}

		public File toTempFile() throws IOException {
			File created = File.createTempFile("SDE", null);
			copySDE(channel, created);
			return created;
		}
	}

	public static SDEDownload getSDE(String lastEtag) {
		String url = Resolve.findURL();
		RemoteMeta meta = RemoteMeta.forUrl(url);
		String etag = meta.etag();
		if (lastEtag != null && lastEtag.equals(etag)) {
			return SDEDownload.cached(url, etag, meta.lastModified());
		}
		try {
			return SDEDownload.ok(url, etag, meta.lastModified(),
					Channels.newChannel(new URI(url).toURL().openStream()));
		} catch (IOException | URISyntaxException e) {
			logger.error("while downloading " + url, e);
			return SDEDownload.error(url, e);
		}
	}

	static void copySDE(ReadableByteChannel readableByteChannel, File targetDir)
			throws FileNotFoundException, IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(targetDir)) {
			fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
		}
	}

	//
	// main to test
	//

	public static void main(String[] args) {
		SDECache.INSTANCE.donwloadSDE();
		System.out.println(SDECache.INSTANCE.updateZip(false));
		System.out.println(SDECache.INSTANCE.updateZip(true));
		System.out.println(SDECache.INSTANCE.updateZip(false));
	}

}
