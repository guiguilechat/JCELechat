package fr.guiguilechat.jcelechat.model.sde.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.lelouet.tools.application.xdg.XDGApp;

/**
 * store the SDE locally
 *
 */
public class SDECache {

	private static final Logger logger = LoggerFactory.getLogger(SDECache.class);

	public static final SDECache INSTANCE = new SDECache();

	public static InputStreamReader fileReader(String fileName) throws FileNotFoundException {
		return fileReader(new File(fileName));
	}

	public static InputStreamReader fileReader(File file) throws FileNotFoundException {
		return new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8"));
	}

	private XDGApp app = new XDGApp("sde.ccp.is");

	/**
	 * where we want to extract the SDE
	 */
	public File cacheDir() {
		return app.cacheFile();
	}

	/**
	 * the directory that should be present when we cached the sde
	 */
	public File checkDir() {
		return new File(cacheDir(), "sde");
	}

	/**
	 * the file that contains the etag of last downloaded sde , if any.
	 *
	 * @return
	 */
	protected File etagFile() {
		return new File(checkDir(), "etag.txt");
	}

	// set to true to avoid downloading the SDE.
	private boolean triedDL = false;

	/**
	 * if needed, download the full yaml from the sde . those files will be
	 * extracted and placed in {@link #cacheDir()}
	 */
	public synchronized void donwloadSDE() {
		if (!triedDL) {
			downloadSDE_NOCHECK();
		}
		triedDL = true;
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

	/**
	 * unpack the SDE from a zip url into a folder.
	 *
	 * @param url
	 *          the sip url
	 * @param cacheDir
	 *          output folder
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	protected static void unpackSDE(String url, File cacheDir) throws MalformedURLException, IOException {
		InputStream is = new URL(url).openStream();
		ZipEntry e;
		try (ZipInputStream zis = new ZipInputStream(is)) {
			while ((e = zis.getNextEntry()) != null) {
				File out = new File(cacheDir, e.getName());
				out.getParentFile().mkdirs();
				FileWriter fw = new FileWriter(out);
				byte[] b = new byte[100];
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

	protected String getEtag(String url) {
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

	protected synchronized void downloadSDE_NOCHECK() {
		File dir = cacheDir();
		String url = findURL();
		String etag = getEtag(url);
		if (etagFile().exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(etagFile()))) {
				if (etag.equals(br.readLine())) {
					logger.info("already last version of sde in  " + cacheDir().getAbsolutePath());
					return;
				}
				logger.info("new version of sde to download with etag " + etag + " into " + cacheDir().getAbsolutePath());
			} catch (IOException e1) {
				e1.printStackTrace(System.err);
			}
		} else {
			logger.info("no existing download information in file " + etagFile().getAbsolutePath()
					+ ", downloading sde into " + cacheDir().getAbsolutePath());
		}
		try {
			FileTools.delDir(dir);
			dir.mkdirs();
			unpackSDE(url, cacheDir());
			FileWriter fw = new FileWriter(etagFile());
			fw.write(etag);
			fw.close();
		} catch (IOException ioe) {
			throw new UnsupportedOperationException(ioe);
		}
	}

	public static void main(String[] args) {
		SDECache.INSTANCE.donwloadSDE();
	}

}
