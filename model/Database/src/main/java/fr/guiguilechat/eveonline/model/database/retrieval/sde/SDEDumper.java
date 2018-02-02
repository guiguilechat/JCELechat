package fr.guiguilechat.eveonline.model.database.retrieval.sde;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** load SDE, convert it into db, store the db, and allow to load it. */
public class SDEDumper {

	private static final Logger logger = LoggerFactory.getLogger(SDEDumper.class);

	protected String rootDir = ".";

	public void setRootDir(String path) {
		rootDir = path;
	}

	protected File dumpDir() {
		return new File(rootDir, "src/main/resources/");
	}

	public File dbDir() {
		return new File(dumpDir(), "SDEDump/");
	}

	public static final String DB_HULLS_RES = "SDEDump/hulls.yaml";

	public File dbHullsFile() {
		return new File(dumpDir(), DB_HULLS_RES);
	}

	public static final String DB_MODULES_RES = "SDEDump/modules.yaml";

	public File dbModulesFile() {
		return new File(dumpDir(), DB_MODULES_RES);
	}

	public static final String DB_ASTEROIDS_RES = "SDEDump/asteroids.yaml";

	public File dbAsteroidsFile() {
		return new File(dumpDir(), DB_ASTEROIDS_RES);
	}

	public static final String DB_BLUEPRINT_RES = "SDEDump/blueprints.yaml";

	public File dbBPsFile() {
		return new File(dumpDir(), DB_BLUEPRINT_RES);
	}

	public static final String DB_METAINF_RES = "SDEDump/metainfs.yaml";

	public File dbMetaInfFile() {
		return new File(dumpDir(), DB_METAINF_RES);
	}

	public static final String DB_LOCATION_RES = "SDEDump/locations.yaml";

	public File dbLocationFile() {
		return new File(dumpDir(), DB_LOCATION_RES);
	}

	public static final String DB_LPOFFERS_RES = "SDEDump/lpoffers.yaml";

	public File dbLPOffersFile() {
		return new File(dumpDir(), DB_LPOFFERS_RES);
	}

	public static final String DB_AGENTS_RES = "SDEDump/agents.yaml";

	public File dbAgentsFile() {
		return new File(dumpDir(), DB_AGENTS_RES);
	}

	public static final String DB_STATIONS_RES = "SDEDump/stations.yaml";

	public File dbStationsFile() {
		return new File(dumpDir(), DB_STATIONS_RES);
	}

	public static void main(String[] args) throws IOException {

		int parallelism = Runtime.getRuntime().availableProcessors() * 10;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parallelism);

		logger.debug("db loaded, writting it");
		SDEDumper dumper = new SDEDumper();
		if (args.length != 0) {
			dumper.setRootDir(args[0]);
		}
	}

}
