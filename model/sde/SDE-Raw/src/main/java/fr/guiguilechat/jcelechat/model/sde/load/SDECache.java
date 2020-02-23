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

import fr.lelouet.tools.settings.xdg.XDGApp;

/**
 * store the SDE locally
 *
 */
public class SDECache {

	private static final Logger logger = LoggerFactory.getLogger(SDECache.class);

	public static final SDECache INSTANCE = new SDECache();

	public static InputStreamReader fileReader(String fileName) throws FileNotFoundException {
		return new InputStreamReader(new FileInputStream(fileName), Charset.forName("UTF-8"));
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
	 * the file that contains the information about last downloaded sde, if any.
	 *
	 * @return
	 */
	protected File last_dl() {
		return new File(checkDir(), "last.txt");
	}

	private boolean triedDL = false;

	/**
	 * if {@link #checkDir()} is not a directory, download the full yaml from the
	 * sde . those files will be extracted and placed in {@link #cacheDir()}
	 */
	public synchronized void donwloadSDE() {
		downloadSDE_STATICURL();
	}

	@SuppressWarnings("resource")
	protected synchronized void donwloadSDE_DYNAMICURL() {
		if (triedDL) {
			return;
		}
		cacheDir().mkdirs();
		String url = findDynamicURL();
		try {
			if (last_dl().exists() && url.equals(new BufferedReader(new FileReader(last_dl())).readLine())) {
				logger.info("already last version of sde in  " + cacheDir().getAbsolutePath());
				triedDL = true;
				return;
			}
			if (last_dl().exists()) {
				logger.info("new version of sde to download from " + url + " into " + cacheDir().getAbsolutePath());
			} else {
				logger.info("no existing download information in file " + last_dl().getAbsolutePath()
						+ ", downloading sde from " + url + " into " + cacheDir().getAbsolutePath());
			}
		} catch (IOException e1) {
			System.err.println(e1);
		}
		try {
			unpackSDE(url, cacheDir());
			FileWriter fw = new FileWriter(last_dl());
			fw.write(url);
			fw.close();
		} catch (IOException e) {
			throw new UnsupportedOperationException("while downloading the SDE", e);
		} finally {
			triedDL = true;
		}
	}

	public static String findDynamicURL() {
		try {
			org.jsoup.nodes.Document page = Jsoup.connect("https://developers.eveonline.com/resource/resources").get();
			Elements a = page.select("a[href*=data/sde]");
			return a.attr("href");
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return null;
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

	public static final String URL_STATIC = "https://eve-static-data-export.s3-eu-west-1.amazonaws.com/tranquility/sde.zip";

	public String getEtag() {
		HttpRequest request = HttpRequest.newBuilder(URI.create(URL_STATIC)).method("HEAD", HttpRequest.BodyPublishers.noBody())
				.build();
		try {
			return HttpClient.newHttpClient().send(request, BodyHandlers.discarding()).headers().firstValue(
					"Etag")
					.orElse(null);
		} catch (IOException | InterruptedException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
	}

	/**
	 * the file that contains the etag of last downloaded sde , if any.
	 *
	 * @return
	 */
	protected File last_Etag() {
		return new File(checkDir(), "etag.txt");
	}


	protected synchronized void downloadSDE_STATICURL() {
		if (triedDL) {
			return;
		}
		cacheDir().mkdirs();
		String etag = getEtag();
		if (last_Etag().exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(last_Etag()))) {
				if (etag.equals(br.readLine())) {
					logger.info("already last version of sde in  " + cacheDir().getAbsolutePath());
					triedDL = true;
					return;
				}
				logger.info("new version of sde to download with etag " + etag + " into " + cacheDir().getAbsolutePath());
			} catch (IOException e1) {
				System.err.println(e1);
			}
		} else {
			logger.info("no existing download information in file " + last_Etag().getAbsolutePath()
					+ ", downloading sde into " + cacheDir().getAbsolutePath());
		}
		try {
			unpackSDE(URL_STATIC, cacheDir());
			FileWriter fw = new FileWriter(last_Etag());
			fw.write(etag);
			fw.close();
		} catch (IOException ioe) {
			throw new UnsupportedOperationException(ioe);
		}

		triedDL = true;
	}

	public static void main(String[] args) {
		SDECache.INSTANCE.donwloadSDE();
	}

}
