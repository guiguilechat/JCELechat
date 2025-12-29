package fr.guiguilechat.jcelechat.libs.exports.industry.translate;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.exports.industry.AssemblyLine;
import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryAssemblyLines;

public class AssemblyLineTranslator {

	public void translate(ClientCache cc, LinkedHashMap<Integer, AssemblyLine> container)
			throws JsonMappingException, JsonProcessingException, SQLException {
		List<KeyValTime<EindustryAssemblyLines>> loaded = EindustryAssemblyLines.getLoader().load(cc);
		loaded.stream()
				.sorted(Comparator.comparing(kvt -> kvt.getVal().id))
				.map(this::translate)
				.forEach(al -> container.put(al.id, al));
	}

	AssemblyLine translate(KeyValTime<EindustryAssemblyLines> source) {
		return null;

	}

}
