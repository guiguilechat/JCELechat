package fr.guiguilechat.eveonline.database.apiv2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.database.apiv2.Char.BPEntry;
import fr.guiguilechat.eveonline.database.apiv2.Char.Content;
import fr.guiguilechat.eveonline.database.apiv2.Char.JobEntry;
import fr.guiguilechat.eveonline.database.apiv2.Char.OrderEntry;

public class Account {

	private final Logger logger = LoggerFactory.getLogger(Account.class);

	private final APIRoot parent;

	public Account(APIRoot parent) {
		this.parent = parent;
	}

	public final String BASEURL = APIRoot.BASEURL + "account/";

	public class Character {
		public String name;
		public long characterID;
		public String corporationName;
		public int corporationID;
		public int allianceID;
		public String allianceName;
		public int factionID;
		public String factionName;

		@Override
		public String toString() {
			return name;
		}

		public ArrayList<JobEntry> industryJobs() {
			return parent.chars.industryJobs(characterID);
		}

		public ArrayList<OrderEntry> marketOrders() {
			return parent.chars.marketOrders(characterID);
		}

		public LinkedHashMap<String, Integer> skillsByName() {
			return parent.chars.skillsByName(characterID);
		}

		public LinkedHashMap<Long, ArrayList<Content>> assetList() {
			return parent.chars.assetList(characterID);
		}

		public LinkedHashMap<Long, ArrayList<Content>> corpAssets() {
			return parent.corp.assetList(characterID);
		}

		public ArrayList<BPEntry> blueprints() {
			return parent.chars.blueprints(characterID);
		}

	}

	public ArrayList<Character> characters() {
		String url = BASEURL + "characters.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code;
		ArrayList<Character> ret = new ArrayList<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result rowset row");
			for (Element el : elements) {
				Character chara = new Character();
				ret.add(chara);
				chara.name = el.attr("name");
				chara.characterID = Long.parseLong(el.attr("characterID"));
				chara.corporationName = el.attr("corporationName");
				chara.corporationID = Integer.parseInt(el.attr("corporationID"));
				chara.allianceID = Integer.parseInt(el.attr("allianceID"));
				chara.allianceName = el.attr("allianceName");
				chara.factionID = Integer.parseInt(el.attr("factionID"));
				chara.factionName = el.attr("factionName");
			}
		} catch (IOException e) {
			logger.error("while getting characters for api key " + parent.key.keyID, e);
			return new ArrayList<>();
		}
		return ret;
	}

}
