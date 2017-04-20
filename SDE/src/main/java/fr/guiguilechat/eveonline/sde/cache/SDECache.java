package fr.guiguilechat.eveonline.sde.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class SDECache {

	/**
	 * where we want to extract the SDE
	 */
	public static final File CACHEDIR = new File("target/");

	/**
	 * the directory that should be present when we cached the sde
	 */
	public static final File CHECKDIR = new File(CACHEDIR, "sde");

	public static final File LAST_DL = new File(CHECKDIR, "last.txt");

	private static boolean triedDL = false;

	/**
	 * if {@link #CHECKDIR} is not a directory, download the full yaml from the
	 * sde . those files will be extracted and placed in {@link #CACHEDIR}
	 */
	@SuppressWarnings("resource")
	public static void donwloadSDE() {
		if (triedDL) {
			return;
		}
		CACHEDIR.mkdirs();
		String url = findLastURL();
		try {
			if (LAST_DL.exists() && url.equals(new BufferedReader(new FileReader(LAST_DL)).readLine())) {
				return;
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
					System.err.println(e.getName());
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
		return "https://cdn1.eveonline.com/data/sde/tranquility/sde-20170330-TRANQUILITY.zip";
	}

}
