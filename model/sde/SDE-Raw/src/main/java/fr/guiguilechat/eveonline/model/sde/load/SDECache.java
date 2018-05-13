package fr.guiguilechat.eveonline.model.sde.load;

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

	/**
	 * where we want to extract the SDE
	 */
	protected File CACHEDIR = new File("target/");

	public File cacheDir() {
		return CACHEDIR;
	}

	/**
	 * the directory that should be present when we cached the sde
	 */
	protected File CHECKDIR = new File(CACHEDIR, "sde");

	public File checkDir() {
		return CHECKDIR;
	}

	protected File LAST_DL = new File(CHECKDIR, "last.txt");

	private boolean triedDL = false;

	/**
	 * if {@link #CHECKDIR} is not a directory, download the full yaml from the
	 * sde . those files will be extracted and placed in {@link #CACHEDIR}
	 */
	@SuppressWarnings("resource")
	public synchronized void donwloadSDE() {
		if (triedDL) {
			return;
		}
		CACHEDIR.mkdirs();
		String url = findLastURL();
		try {
			if (LAST_DL.exists() && url.equals(new BufferedReader(new FileReader(LAST_DL)).readLine())) {
				return;
			}
			if(LAST_DL.exists()) {
				logger.info("new version of sde to download from " + url);
			} else {
				logger.info(
						"no existing download information in file " + LAST_DL.getAbsolutePath() + ", downloading sde from " + url);
			}
		} catch (IOException e1) {
			System.err.println(e1);
		}
		try {
			InputStream is = new URL(url).openStream();
			ZipEntry e;
			try (ZipInputStream zis = new ZipInputStream(is)) {
				while ((e = zis.getNextEntry()) != null) {
					File out = new File(CACHEDIR, e.getName());
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

			FileWriter fw = new FileWriter(LAST_DL);
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
