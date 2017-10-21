package fr.guiguilechat.eveonline.database.apiv2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

	public static class APIKeyInfo {
		int accessMask;
		public String type;
		String expire;
	}

	public APIKeyInfo apiKeyInfo() {
		String url = BASEURL + "APIKeyInfo.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code;
		try {
			Document page = Jsoup.connect(url).get();
			Element el = page.select("result key").first();
			APIKeyInfo ret = APIRoot.convertElement(el, APIKeyInfo.class);
			return ret;
		} catch (IOException e) {
			logger.error("while getting api key info for " + parent.key.keyID, e);
			return null;
		}
	}

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
			return parent.isCorp() ? parent.corp.industryJobs(characterID) : parent.chars.industryJobs(characterID);
		}

		public List<OrderEntry> marketOrders() {
			return parent.isCorp() ? parent.corp.marketOrders(characterID) : parent.chars.marketOrders(characterID);
		}

		public LinkedHashMap<String, Integer> skillsByName() {
			return parent.isCorp() ? new LinkedHashMap<>() : parent.chars.skillsByName(characterID);
		}

		public LinkedHashMap<Long, ArrayList<Content>> assetList() {
			return parent.isCorp() ? parent.corp.assetList(characterID) : parent.chars.assetList(characterID);
		}

		public List<BPEntry> blueprints() {
			return parent.isCorp() ? parent.corp.blueprints(characterID) : parent.chars.blueprints(characterID);
		}

	}

	public ArrayList<Character> characters() {
		String url = BASEURL + "characters.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code;
		ArrayList<Character> ret = new ArrayList<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result rowset row");
			for (Element el : elements) {
				ret.add(APIRoot.convertElement(el, Character.class, this));
			}
		} catch (IOException e) {
			logger.error("while getting characters for api key " + parent.key.keyID, e);
			return new ArrayList<>();
		}
		if (parent.isCorp()) {
			for (Character c : ret) {
				c.name = "corp:" + c.name;
			}
		}
		return ret;
	}

}
