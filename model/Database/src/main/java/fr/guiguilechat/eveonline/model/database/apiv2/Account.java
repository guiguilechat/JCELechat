package fr.guiguilechat.eveonline.model.database.apiv2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.apiv2.Char.BPEntry;
import fr.guiguilechat.eveonline.model.database.apiv2.Char.Content;
import fr.guiguilechat.eveonline.model.database.apiv2.Char.JobEntry;
import fr.guiguilechat.eveonline.model.database.apiv2.Char.OrderEntry;

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
		Exception error = null;
		for (int retry = 0; retry < 10; retry++) {
			try {
				if (retry != 0) {
					Thread.sleep(500);
				}
				Document page = Jsoup.connect(url).get();
				Element el = page.select("result key").first();
				APIKeyInfo ret = APIRoot.convertElement(el, APIKeyInfo.class);
				return ret;
			} catch (IOException | InterruptedException e) {
				error = e;
			}
		}
		logger.error("while retrieving api key, url " + url, error);
		return null;
	}

	public class EveChar {
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

	protected List<EveChar> cachedChars = null;

	public List<EveChar> characters() {
		if (cachedChars != null) {
			return cachedChars;
		}
		String url = BASEURL + "characters.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code;
		Exception error = null;
		List<EveChar> ret = null;
		for (int retry = 0; retry < 10 && ret == null; retry++) {
			try {
				error = null;
				if (retry != 0) {
					Thread.sleep(500);
				}
				Document page = Jsoup.connect(url).get();
				Elements elements = page.select("result rowset row");
				ret = elements.stream().map(el -> APIRoot.convertElement(el, EveChar.class, this)).collect(Collectors.toList());
			} catch (IOException | InterruptedException e) {
				logger.debug("while fetching characters for api key " + parent.key.keyID, e);
				error = e;
			}
		}
		if (error != null) {
			logger.error("while fetching characters " + url, error);
			return new ArrayList<>();
		}
		if (parent.isCorp()) {
			for (EveChar c : ret) {
				c.name = "corp:" + c.name;
			}
		}
		cachedChars = ret;
		return ret;
	}

}
