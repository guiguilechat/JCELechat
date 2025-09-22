package fr.guiguilechat.jcelechat.model.sde2.yaml;

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
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.jcelechat.model.FileTools;
import fr.guiguilechat.jcelechat.model.sde2.RemoteMeta;
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

	protected String formatDir() {
		return "yaml/";
	}

	/**
	 * where we want to extract the SDE
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractCacheDir = app().cacheFile(formatDir() + "extracted");

	/**
	 * the directory that should be present when we cached the sde
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractCheckDir = new File(extractCacheDir(), "meta");

	/**
	 * the file that contains the release date of last downloaded sde , if any.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractLastReleaseFile = new File(extractCheckDir(), "lastRelease.txt");

	/**
	 * the file that contains the buildnumber of last downloaded sde , if any.
	 */
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File extractLastBuildFile = new File(extractCheckDir(), "buildnumber.txt");

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
	protected void updateExtract() {
		RemoteMeta meta = RemoteMeta.fetch();
		String lastRelease = meta.releaseDate;
		if (extractLastReleaseFile().exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(extractLastReleaseFile()))) {
				if (lastRelease.equals(br.readLine())) {
					log.info("already last version of sde in  " + extractCacheDir().getAbsolutePath());
					return;
				}
				log.info("new version of sde to download with lastRelease " + lastRelease + " into "
						+ extractCacheDir().getAbsolutePath());
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		} else {
			log.info("no existing download information in file " + extractLastReleaseFile().getAbsolutePath()
					+ ", downloading sde into " + extractCacheDir().getAbsolutePath());
		}
		try {
			FileTools.delDir(extractCacheDir());
			extractCacheDir().mkdirs();
			unpackSDE(extractURL(meta), extractCacheDir());
			writExtractedeMeta(meta);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	protected String extractURL(RemoteMeta meta) {
		return meta.yamlURL();
	}

	protected void writExtractedeMeta(RemoteMeta meta) throws IOException {
		extractLastReleaseFile().getParentFile().mkdirs();
		try (FileWriter fw = new FileWriter(extractLastReleaseFile())) {
			fw.write(meta.releaseDate);
		}
		extractLastBuildFile().getParentFile().mkdirs();
		try (FileWriter fw = new FileWriter(extractLastBuildFile())) {
			fw.write("" + meta.buildNumber);
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

	public static void main(String[] args) {
		new YamlCache().donwloadSDE();
	}

}
