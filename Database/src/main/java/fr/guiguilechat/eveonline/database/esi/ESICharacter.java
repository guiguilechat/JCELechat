package fr.guiguilechat.eveonline.database.esi;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ESICharacter {

	private static final String baseURL = "https://esi.tech.ccp.is/latest/characters/names/?character_ids=";
	private static final int maxIDs = 500;

	private ObjectMapper om = new ObjectMapper();

	public Map<Integer, String> getNames(int... ids) {
		if (ids == null || ids.length == 0) {
			return Collections.emptyMap();
		}
		LinkedHashMap<Integer, String> ret = new LinkedHashMap<>();
		for (int start = 0; start < ids.length; start += maxIDs) {
			StringBuilder idBuilder = null;
			for (int i = start; i < start + maxIDs && i < ids.length; i++) {
				int id = ids[i];
				if (idBuilder == null) {
					idBuilder = new StringBuilder("" + id);
				} else {
					idBuilder.append("," + id);
				}
			}
			try {
				Name[] names = om.readValue(new URL(baseURL + idBuilder.toString()), Name[].class);
				for (Name name : names) {
					ret.put(name.character_id, name.character_name);
				}
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return ret;
	}

	public static class Name {
		public int character_id;
		public String character_name;
	}

}
