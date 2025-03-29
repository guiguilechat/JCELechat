package fr.guiguilechat.jcelechat.libs.gameclient;

import java.io.File;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;

public class TestDB {

	public static void main(String[] args) throws SQLException, JsonMappingException, JsonProcessingException {
		File cacheDir = new File(".evecache");
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		System.err.println(cache.getClientInfo().lastModified());
//		for (KeyValTimeLoader<?> loader : List.of(
//				Eblueprints.getLoader(),
//				Eclonegrades.getLoader(),
//				Edbuffcollections.getLoader(),
//				Efighterabilities.getLoader(),
//				Efighterabilitiesbytype.getLoader(),
//				EindustryActivities.getLoader(),
//				EindustryActivityModifierSources.getLoader(),
//				EindustryActivityTargetFilters.getLoader(),
//				EindustryAssemblyLines.getLoader(),
//				EindustryInstallationTypes.getLoader(),
//				Einfobubbles.getLoader(),
//				Eskinlicenses.getLoader(),
//				Eskinmaterials.getLoader(),
//				Eskins.getLoader()
////
//		)) {
//			loader.loadPrintCSV(cache, System.out);
//		}

//		cache.getAllIndexes().stream()
//				.flatMap(idx -> idx.getLines().stream())
////				.peek(md -> cache.file(md))
//				.sorted(Comparator.comparing(ResourceMetaData::resName))
//				.map(ResourceMetaData::resName)
//				.forEach(System.out::println);
//		System.out.println("loaders :");
//		cache.getAllIndexes().parallelStream()
//				.flatMap(idx -> idx.getLines().stream())
//				.filter(md -> md.resName().endsWith("Loader.pyd"))
//				.peek(md -> cache.file(md))
//				.sorted(Comparator.comparing(ResourceMetaData::resName))
//				.map(ResourceMetaData::resName)
//				.forEach(System.out::println);
//		cache.getAllIndexes().parallelStream()
//				.flatMap(idx -> idx.getLines().stream())
//				.filter(md -> md.resName().matches("data/.*\\.fsdbinary"))
//				.peek(md -> cache.file(md))
//				.sorted(Comparator.comparing(ResourceMetaData::resName))
//				.map(ResourceMetaData::resName)
//				.forEach(System.out::println);

		cache.getAllIndexes().forEach(idx -> {
			System.out.println(idx);
			idx.getLines().stream()
					.filter(line -> line.resName().contains("currencybuckets"))
					.forEach(line -> {
						System.out.println(line);
					});
		});
	}

}
