package fr.guiguilechat.jcelechat.libs.everaw;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.everaw.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.everaw.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTimeLoader;
import fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata.*;

public class LoadDb {

	public static void main(String[] args) throws SQLException, JsonMappingException, JsonProcessingException {
		File cacheDir = new File(".evecache");
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		// to get without printing
//		List<KeyValTime<Eblueprints>> list = Eblueprints.getLoader().load(cache);
		for (KeyValTimeLoader<?> loader : List.of(
				Eblueprints.getLoader(),
				Eclonegrades.getLoader(),
				Edbuffcollections.getLoader(),
				Efighterabilities.getLoader(),
				Efighterabilitiesbytype.getLoader(),
				EindustryActivities.getLoader(),
				EindustryActivityModifierSources.getLoader(),
				EindustryActivityTargetFilters.getLoader(),
				EindustryAssemblyLines.getLoader(),
				EindustryInstallationTypes.getLoader()
//
		)) {
			loader.loadPrintCSV(cache, System.out);
		}
	}

}
