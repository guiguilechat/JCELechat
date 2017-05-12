package fr.guiguilechat.eveonline.database.esi;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ESINpcCorporations {

	public static class Corporation {
		public int ceo_id;
		public String corporation_description;
		public String corporation_name;
		public int creator_id;
		public String faction;
		public int member_count;
		public double tax_rate;
		public String ticker;
		public String url;
	}

	private final String CORPORATIONS_LIST_URL = "https://esi.tech.ccp.is/latest/corporations/npccorps";

	int[] ids = null;

	public int[] getIDs() {
		if (ids == null) {
			try {
				ids = new ObjectMapper().readValue(new URL(CORPORATIONS_LIST_URL), int[].class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ids;
	}

	private final String CORPORATIONS_DETAIL_URL = "https://esi.tech.ccp.is/latest/corporations/";

	LinkedHashMap<Integer, Corporation> corpos = null;

	public LinkedHashMap<Integer, Corporation> getCorpos() {
		if (corpos == null) {
			corpos = new LinkedHashMap<>();
			for (int corpId : getIDs()) {
				corpos.put(corpId, getCorporation(corpId));
			}
		}
		return corpos;
	}

	public Corporation getCorporation(int id) {
		Corporation ret = null;
		try {
			ret = new ObjectMapper().readValue(new URL(CORPORATIONS_DETAIL_URL + id), Corporation.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) {
		for (Entry<Integer, Corporation> e : new ESINpcCorporations().getCorpos().entrySet()) {
			System.err.println(" " + e.getKey() + " :" + e.getValue().corporation_name);
		}
	}

}
