package fr.guiguilechat.jcelechat.libs.everaw;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.everaw.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.everaw.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata.Efighterabilities;
import fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata.Efighterabilitiesbytype;

public class LoadDb {

	public static void main(String[] args) throws SQLException, JsonMappingException, JsonProcessingException {
		File cacheDir = new File(".evecache");
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		List<KeyValTime<Efighterabilities>> l = Efighterabilities.getLoader().load(cache);
		System.out.println(
				"loaded " + l.size() + " of type " + Efighterabilities.getLoader().getValueClass().getSimpleName());
		List<?> l1 = Efighterabilitiesbytype.getLoader().load(cache);
		System.out.println("loaded " + l1.size() + " of type "
				+ Efighterabilitiesbytype.getLoader().getValueClass().getSimpleName());
		List<?> l2 = Efighterabilitiesbytype.getLoader().load(cache);
		System.out.println("list 1 has " + l1.size() + " entries and list 2 has " + l2.size() + " entries");
	}

}
