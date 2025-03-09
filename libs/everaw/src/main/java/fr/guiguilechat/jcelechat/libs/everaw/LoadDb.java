package fr.guiguilechat.jcelechat.libs.everaw;

import java.io.File;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.everaw.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.everaw.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata.Efighterabilities;

public class LoadDb {

	public static void main(String[] args) throws SQLException, JsonMappingException, JsonProcessingException {
		File cacheDir = new File(".evecache");
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		Efighterabilities.load(cache);
	}

}
