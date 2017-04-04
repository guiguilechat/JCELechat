package fr.guiguilechat.eveonline.sde.cache;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SDECache {

	/**
	 * where we want to extract the SDE
	 */
	public static final File CACHEDIR = new File("target/");

	/**
	 * the directory that should be present when we cached the sde
	 */
	public static final File CHECKDIR = new File(CACHEDIR, "sde");

	/**
	 * where we want to download the SDE from
	 */
	public static final String SDE_URL = "https://cdn1.eveonline.com/data/sde/tranquility/sde-20170216-TRANQUILITY.zip";

	/**
	 * if {@link #CHECKDIR} is not a directory, download the full yaml from the
	 * sde . those files will be extracted and placed in {@link #CACHEDIR}
	 */
	public static void donwloadSDE() {
		if (CHECKDIR.isDirectory()) {
			return;
		}
		CACHEDIR.mkdirs();
		try {
			InputStream is = new URL(SDE_URL).openStream();
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
		} catch (IOException e) {
			throw new UnsupportedOperationException("while downloading the SDE", e);
		}
	}

}
