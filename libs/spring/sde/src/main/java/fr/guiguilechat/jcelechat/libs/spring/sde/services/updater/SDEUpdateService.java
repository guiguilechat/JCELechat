package fr.guiguilechat.jcelechat.libs.spring.sde.services.updater;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.load.SDECache;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.universe.SolarSystem;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SDEUpdateService {

	private boolean alreadyInserted = false;

	@Transactional
	public void updateSDE() {
		File newFile = SDECache.INSTANCE.updateZip(alreadyInserted);
		if(newFile!=null) {
			log.info("updating DB from new SDE file : " + newFile.getAbsolutePath());
			try {
				updateFromFile(newFile);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			alreadyInserted = true;
		}
	}

	record UpdateContext(Map<Integer, SolarSystem> systemById) {
		public UpdateContext() {
			this(new HashMap<>());
		}
	}

	protected void updateFromFile(File newFile) throws ZipException, IOException {
		UpdateContext context = new UpdateContext();
		try (ZipFile zipFile = new ZipFile(newFile)) {
			for (ZipEntry zipentry : Collections.list(zipFile.entries())) {
				if (!zipentry.isDirectory()) {
					applyZipEntry(context, zipFile, zipentry);
				}
			}
		}
		log.info("post update, have " + context.systemById().size() + " systems");
	}

	static final Pattern ENTRYNAME_SOLARSYSTEM_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/solarsystem\\.staticdata");

	static final Pattern ENTRYNAME_CONSTELLATION_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/constellation\\.staticdata");

	static final Pattern ENTRYNAME_REGION_PATTERN = Pattern.compile(
			"sde/fsd/universe/([a-zA-Z0-9]+)/([- a-zA-Z0-9]+)/region\\.staticdata");

	private void applyZipEntry(UpdateContext context, ZipFile zipFile, ZipEntry zipentry) throws IOException {
		String name = zipentry.getName();
		InputStream is = zipFile.getInputStream(zipentry);
		Matcher m = null;
		m = ENTRYNAME_SOLARSYSTEM_PATTERN.matcher(name);
		if (m.matches()) {
			applySolarSystem(context, is, m.group(1), m.group(2),
					m.group(3), m.group(4));
			return;
		}
		m = ENTRYNAME_CONSTELLATION_PATTERN.matcher(name);
		if (m.matches()) {
			applyConstellation(context, is, m.group(1), m.group(2),
					m.group(3));
			return;
		}
		m = ENTRYNAME_REGION_PATTERN.matcher(name);
		if (m.matches()) {
			applyRegion(context, is, m.group(1), m.group(2));
			return;
		}
		// log.info("ignore entry " + name);
	}

	private void applySolarSystem(UpdateContext context, InputStream is, String uni, String region,
			String constellation, String solarSystem) {
		SolarSystem solsys = new Yaml().loadAs(is, SolarSystem.class);
		context.systemById.put(solsys.solarSystemID, solsys);
	}

	private void applyConstellation(UpdateContext context, InputStream is, String uni, String region,
			String constellation) {
	}

	private void applyRegion(UpdateContext context, InputStream is, String uni, String region) {
	}

}
