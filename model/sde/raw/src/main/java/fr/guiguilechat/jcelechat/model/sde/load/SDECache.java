package fr.guiguilechat.jcelechat.model.sde.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.lelouet.tools.application.xdg.XDGApp;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * store the SDE locally
 *
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

	private static final String URL_STATIC = "https://eve-static-data-export.s3-eu-west-1.amazonaws.com/tranquility/sde.zip";
	private final static String URL_DYNAMIC = "https://developers.eveonline.com/resource/resources";

	/**
	 * find the url to download the SDE from.
	 *
	 * @return
	 */
	protected static String findURL() {
		try {
			org.jsoup.nodes.Document page = Jsoup.connect(URL_DYNAMIC).get();
			Elements a = page.select("a[href*=sde.zip]");
			String ret = a == null ? null : a.attr("href");
			if (ret == null || ret.length() == 0) {
				logger.debug("can't find URL for SDE, fallback to static " + URL_STATIC);
			} else {
				logger.debug("dynamic URL for SDE is " + ret);
				return ret;
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return URL_STATIC;
	}

	protected static String getEtag(String url) {
		HttpRequest request = HttpRequest.newBuilder(URI.create(
				url))
				.method("HEAD", HttpRequest.BodyPublishers.noBody())
				.build();
		try {
			return HttpClient.newHttpClient().send(request, BodyHandlers.discarding()).headers().firstValue(
					"Etag")
					.orElse(null);
		} catch (IOException | InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
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
	private final File extractCheckDir = new File(extractCacheDir(), "sde");

	/**
	 * the file that contains the etag of last downloaded sde , if any.
	 *
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractEtagFile = new File(extractCheckDir(), "etag.txt");

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
	 * check if a new version is avail (using etag) and, when yes, extract it
	 */
	public synchronized void updateExtract() {
		String url = findURL();
		String etag = getEtag(url);
		if (extractEtagFile().exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(extractEtagFile()))) {
				if (etag.equals(br.readLine())) {
					logger.info("already last version of sde in  " + extractCacheDir().getAbsolutePath());
					return;
				}
				logger
						.info("new version of sde to download with etag " + etag + " into " + extractCacheDir().getAbsolutePath());
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
			FileWriter fw = new FileWriter(extractEtagFile());
			fw.write(etag);
			fw.close();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
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
	 * @return null if the file is the same as previous and returnOnlyNew is
	 *           specified, existing file if no update, new file if update.
	 */
	public synchronized File updateZip(boolean returnOnlyNew) {
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
			return returnOnlyNew ? null : zipTarget();
		}
		if (res.error() != null) {
			return null;
		}
		logger.info("new version of sde to download with etag " + res.etag() + " into " + zipCacheDir().getAbsolutePath());

		try {
			zipCacheDir().mkdirs();
			if (!zipCacheDir().isDirectory()) {
				log.error("can't create dir " + zipCacheDir().getAbsolutePath());
				return null;
			}
			zipTemp().delete();
			copySDE(res.channel(), zipTemp());
			zipTemp().renameTo(zipTarget());
			FileWriter fw = new FileWriter(zipEtagFile());
			fw.write(res.etag());
			fw.close();
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		return zipTarget();
	}

	public static record SDEDownload(String url, String etag, ReadableByteChannel channel, Exception error,
			boolean cached) {
		static SDEDownload ok(String url, String etag, ReadableByteChannel channel) {
			return new SDEDownload(url, etag, channel, null, false);
		}

		static SDEDownload error(String url, Exception error) {
			return new SDEDownload(url, null, null, error, false);
		}

		static SDEDownload cached(String url, String etag) {
			return new SDEDownload(url, etag, null, null, true);
		}

		public File toTempFile() throws IOException {
			File created = File.createTempFile("SDE", null);
			copySDE(channel, created);
			return created;
		}
	}

	public static SDEDownload getSDE(String lastEtag) {
		String url = findURL();
		String etag = getEtag(url);
		if (lastEtag != null && lastEtag.equals(etag)) {
			return SDEDownload.cached(url, etag);
		}
		try {
			return SDEDownload.ok(url, etag, Channels.newChannel(new URI(url).toURL().openStream()));
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
