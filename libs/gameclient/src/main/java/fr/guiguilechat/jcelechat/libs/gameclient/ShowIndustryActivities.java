package fr.guiguilechat.jcelechat.libs.gameclient;

import java.io.File;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivities;

public class ShowIndustryActivities {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, SQLException {
		File cacheDir = new File(".evecache");
		ClientCache cc = new ClientCache(cacheDir, ClientInfo.fetch());

		System.out.println("build " + cc.getClientInfo().getBuild() + "\t" + cc.getClientInfo().lastModified());
		System.out.println("activityId\tname\tdescription");
		List<KeyValTime<EindustryActivities>> loaded = EindustryActivities.getLoader().load(cc);
		loaded.stream().sorted(Comparator.comparing(KeyValTime::getKey)).forEach(e -> {
			System.out.println(e.getVal().activityID + "\t" + e.getVal().activityName + "\t" + e.getVal().description);
		});

	}

}
