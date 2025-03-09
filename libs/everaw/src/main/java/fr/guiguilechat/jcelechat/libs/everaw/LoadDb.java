package fr.guiguilechat.jcelechat.libs.everaw;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.everaw.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.everaw.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata.Eblueprints;
import fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata.Efighterabilities;
import fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata.Efighterabilitiesbytype;

public class LoadDb {

	public static void main(String[] args) throws SQLException, JsonMappingException, JsonProcessingException {
		File cacheDir = new File(".evecache");
		ClientCache cache = new ClientCache(cacheDir, ClientInfo.fetch());
		List<KeyValTime<Eblueprints>> list = Eblueprints.getLoader().load(cache);
		System.out.println(Eblueprints.getLoader().loadPrint(cache));
		System.out.println(Efighterabilities.getLoader().loadPrint(cache));
		System.out.println(Efighterabilitiesbytype.getLoader().loadPrint(cache));
		// do it twice to check if same size or not.
		System.out.println(Efighterabilitiesbytype.getLoader().loadPrint(cache));

	}

}
