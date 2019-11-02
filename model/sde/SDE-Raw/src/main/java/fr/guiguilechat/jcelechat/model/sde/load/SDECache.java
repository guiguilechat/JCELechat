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
import java.net.URL;
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
	@SuppressWarnings("resource")
	public synchronized void donwloadSDE() {
		if (triedDL) {
			return;
		}
		cacheDir().mkdirs();
		String url = findLastURL();
		try {
			if (last_dl().exists() && url.equals(new BufferedReader(new FileReader(last_dl())).readLine())) {
				logger.info("already last version of sde in  " + cacheDir().getAbsolutePath());
				return;
			}
			if (last_dl().exists()) {
				logger.info("new version of sde to download from " + url + " into " + cacheDir().getAbsolutePath());
			} else {
				logger
				.info("no existing download information in file " + last_dl().getAbsolutePath() + ", downloading sde from "
						+ url + " into " + cacheDir().getAbsolutePath());
			}
		} catch (IOException e1) {
			System.err.println(e1);
		}
		try {
			InputStream is = new URL(url).openStream();
			ZipEntry e;
			try (ZipInputStream zis = new ZipInputStream(is)) {
				while ((e = zis.getNextEntry()) != null) {
					File out = new File(cacheDir(), e.getName());
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

			FileWriter fw = new FileWriter(last_dl());
			fw.write(url);
			fw.close();
			triedDL = true;
		} catch (IOException e) {
			throw new UnsupportedOperationException("while downloading the SDE", e);
		}
	}

	public static String findLastURL() {
		try {
			org.jsoup.nodes.Document page = Jsoup.connect("https://developers.eveonline.com/resource/resources").get();
			Elements a = page.select("a[href*=data/sde]");
			return a.attr("href");
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return null;
	}

}
