package fr.guiguilechat.eveonline.database.retrieval;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.database.Database;
import fr.guiguilechat.eveonline.database.Parser;
import fr.guiguilechat.eveonline.database.elements.Hull;
import fr.guiguilechat.eveonline.database.elements.Module;

public class SDEDumper {

	/**
	 * where we want to extract the SDE
	 */
	public static final File CACHEDIR = new File("target/");

	/**
	 * the directory that should be present when we cached the sde
	 */
	public static final File CHECKDIR = new File(CACHEDIR, "sde");

	public static final File DB_DIR = new File("src/main/resources/SDEDump/");

	public static final File DB_FILE = new File(DB_DIR, "dump.yaml");

	/**
	 * where we want to download the SDE from
	 */
	public static final String SDE_URL = "https://cdn1.eveonline.com/data/sde/tranquility/sde-20170216-TRANQUILITY.zip";

	public static void donwloadSDE() {
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
			} catch (Exception ex) {
				throw new UnsupportedOperationException("", ex);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		if (!CHECKDIR.isDirectory()) {
			System.err.println("can't find " + CHECKDIR + " directory, loading it");
			donwloadSDE();
		}

		Database db = loadDb();
		DB_DIR.mkdirs();
		Parser.write(db, DB_FILE);
	}

	@SuppressWarnings({ "unchecked" })

	public static Database loadDb() throws FileNotFoundException {
		Database db = new Database();

		// find category for modules and ships
		int shipCat = -1, moduleCat = -1;
		File CategoryYaml = new File(CHECKDIR, "fsd/categoryIDs.yaml");
		HashMap<Integer, Map<String, ?>> categorys = (HashMap<Integer, Map<String, ?>>) new Yaml()
				.load(new FileReader(CategoryYaml));
		for (Entry<Integer, Map<String, ?>> e : categorys.entrySet()) {
			String catName = getElemName(e.getValue());
			if ("Module".equals(catName)) {
				moduleCat = e.getKey();
			}
			if ("Ship".equals(catName)) {
				shipCat = e.getKey();
			}
		}
		// System.err.println("module cat is " + moduleCat + ", ship cat is " +
		// shipCat);

		// find groups in modules and ships category
		Set<Integer> shipGroups = new LinkedHashSet<>();
		Set<Integer> modulesGroups = new LinkedHashSet<>();
		File groupYaml = new File(CHECKDIR, "fsd/groupIDs.yaml");
		HashMap<Integer, Map<String, ?>> groups = (HashMap<Integer, Map<String, ?>>) new Yaml()
				.load(new FileReader(groupYaml));
		for (Entry<Integer, Map<String, ?>> e : groups.entrySet()) {
			if (Boolean.TRUE.equals(e.getValue().get("published"))) {
				Integer groupCat = (Integer) e.getValue().get("categoryID");
				if (groupCat == shipCat) {
					shipGroups.add(e.getKey());
				}
				if (groupCat == moduleCat) {
					modulesGroups.add(e.getKey());
				}
			} else {
				// System.err.println("unpublished " + ((Map<?, ?>)
				// e.getValue().get("name")).get("en"));
			}
		}
		// System.err.println("ship groups are " + shipGroups + ", module groups are
		// " + modulesGroups);

		File typesYaml = new File(CHECKDIR, "fsd/typeIDs.yaml");
		HashMap<Integer, Map<String, ?>> types = (HashMap<Integer, Map<String, ?>>) new Yaml()
				.load(new FileReader(typesYaml));
		for( Entry<Integer, Map<String, ?>> e : types.entrySet()) {
			Map<String, ?> elem = e.getValue();
			if (Boolean.TRUE.equals(elem.get("published"))) {
				Integer groupId = (Integer) e.getValue().get("groupID");
				if(shipGroups.contains(groupId)) {
					Hull h = new Hull();
					db.hulls.put(e.getKey(), h);
					h.name=getElemName(elem);
					h.attributes.mass = getelemDouble(elem, "mass").longValue();
					h.attributes.volume = getelemDouble(elem, "volume").longValue();
					// System.err.println("hull : " + h.name);
				}else if(modulesGroups.contains(groupId)) {
					Module m =new Module();
					db.modules.put(e.getKey(), m);
					m.name=getElemName(e.getValue());
					// System.err.println("module : " + m.name);
				} else {
					// System.err.println("group " + groupId + " skipped");
				}
			}
		}

		return db;
	}

	@SuppressWarnings("unchecked")
	public static final String getElemName(Map<String, ?> elem) {
		return ((Map<String, String>) elem.get("name")).get("en");
	}

	public static final Double getelemDouble(Map<String, ?> elem, String att) {
		Object ret = elem.get(att);
		if(ret==null) {
			return null;
		}
		if(ret instanceof Double) {
			return (Double) ret;
		}
		if(ret instanceof Float) {
			return (double)(float)ret;
		}
		if(ret instanceof Integer ) {
			return (double)(int)ret;
		}
		if(ret instanceof Long) {
			return (double)(long)ret;
		}
		System.err.println("can't cast " + ret.getClass() + " to a double");
		return null;
	}

}
