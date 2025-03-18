package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.attributes.OreBasicType;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.industry.InventionDecryptor;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.GenericDecryptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndustryTranslater {

	private static final Logger logger = LoggerFactory.getLogger(IndustryTranslater.class);

	/**
	 * @param args
	 *             should be [database destination root], typically
	 *             src/generated/resources/
	 * @throws IOException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, SQLException {

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length < 1 ? "src/generated/resources/" : args[0]);
		folderOut.mkdirs();

		ClientInfo ci = ClientInfo.fetch();
		ClientCache cc = new ClientCache(ci);

		LinkedHashMap<Integer, Blueprint> blueprints = new LinkedHashMap<>();
		LinkedHashMap<Integer, InventionDecryptor> decryptors = new LinkedHashMap<>();
		LinkedHashMap<Integer, IndustryUsage> usages = new LinkedHashMap<>();

		new ClientCacheBlueprintTranslator(cc).translateBlueprints(cc, blueprints, usages);
//		new SDEBlueprintTranslator().translateBlueprints(blueprints, usages);
		translateDecryptors(decryptors);
		translateCompression(usages);

		// sort decryptors and blueprints by typeId

		Stream.of(blueprints, decryptors).forEach(m -> {
			ArrayList<Entry<Integer, ? extends TypeRef<?>>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, Comparator.comparing(Entry<Integer, ? extends TypeRef<?>>::getKey));
			m.clear();
			for (Entry<Integer, ? extends TypeRef<?>> e : list) {
				((Map<Integer, TypeRef<?>>) m).put(e.getKey(), e.getValue());
			}
		});

		// save

		File bpFile = Blueprint.export(blueprints, folderOut);
		copyBPIfDiff(bpFile, cc, folderOut);
		InventionDecryptor.export(decryptors, folderOut);
		IndustryUsage.export(usages, folderOut);

		log.info("exported industry in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");

	}

	//
	// decryptor translation from sde
	//

	private static void translateDecryptors(LinkedHashMap<Integer, InventionDecryptor> decryptors) {
		for (Entry<Integer, GenericDecryptor> e : GenericDecryptor.METAGROUP.load().entrySet()) {
			decryptors.put(e.getKey(), convertDecryptor(e.getValue()));
		}
		InventionDecryptor nullDecryptor = new InventionDecryptor();
		decryptors.put(0, nullDecryptor);
	}

	public static InventionDecryptor convertDecryptor(GenericDecryptor dec) {
		InventionDecryptor ret = new InventionDecryptor();
		ret.id = dec.id;
		return ret;
	}

	private static void translateCompression(LinkedHashMap<Integer, IndustryUsage> usages) {
		for (Asteroid compressed : Asteroid.METACAT.load().values()) {
			if (compressed.getAttributes().contains(OreBasicType.INSTANCE) && !compressed.name.startsWith("Batch ")) {
				Integer compressIntoId = compressed.valueSet(OreBasicType.INSTANCE).intValue();
				if (compressIntoId != 0 && compressIntoId != compressed.id) {
					EveType compressInto = TypeIndex.getType(compressIntoId);
					if (compressInto == null) {
						logger.debug("can't find asteroid from id " + compressIntoId);
					} else {
						usages.computeIfAbsent(compressed.id, o -> new IndustryUsage()).compressTo = compressIntoId;
						usages.computeIfAbsent(compressIntoId, o -> new IndustryUsage()).compressFrom = compressed.id;
					}
				}
			}
		}
	}

	private static void copyBPIfDiff(File newFile, ClientCache cc, File archiveFolder) throws IOException {
		File archiveDir = new File(archiveFolder, "SDE/industry/blueprints");
		archiveDir.mkdirs();
		File lastCopy = null;
		Instant lastTime = null;
		for (File archive : archiveDir.listFiles()) {
			Instant archiveTime = Blueprint.archiveName2Instant(archive.getName());
			if (archiveTime == null) {
				continue;
			}
			if (lastTime == null || lastTime.isBefore(archiveTime)) {
				lastCopy = archive;
				lastTime = archiveTime;
			}
		}
		if (lastCopy != null && Files.mismatch(lastCopy.toPath(), newFile.toPath()) == -1) {
			return;
		}
		// actual copy
		File newArchive = new File(archiveDir, Blueprint.instant2ArchiveName(cc.getClientInfo().lastModified()));
		log.info("copying existing bp file " + newFile + " to archive file " + newArchive
				+ " with oldest archive found being " + lastCopy);
		Files.copy(newFile.toPath(), newArchive.toPath());
		// then append the file name to thelist
		File listFile = new File(archiveFolder, "list");
		try (BufferedWriter writer = Files.newBufferedWriter(listFile.toPath(), StandardOpenOption.CREATE,
				StandardOpenOption.APPEND)) {
			writer.append(newArchive.getName());
			writer.newLine();
		}
	}
}
