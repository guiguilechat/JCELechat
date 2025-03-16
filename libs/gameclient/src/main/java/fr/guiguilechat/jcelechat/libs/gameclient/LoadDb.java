package fr.guiguilechat.jcelechat.libs.gameclient;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata.*;

public class LoadDb {

	public static void main(String[] args) throws SQLException, JsonMappingException, JsonProcessingException {
		File cacheDir = new File(".evecache");
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		System.err.println(cache.getClientInfo().lastModified());
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
				EindustryInstallationTypes.getLoader(),
				Einfobubbles.getLoader(),
				Eskinlicenses.getLoader(),
				Eskinmaterials.getLoader(),
				Eskins.getLoader()
//
		)) {
			loader.loadPrintCSV(cache, System.out);
		}
	}

}
