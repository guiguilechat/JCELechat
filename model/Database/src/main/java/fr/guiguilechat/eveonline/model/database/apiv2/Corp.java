package fr.guiguilechat.eveonline.model.database.apiv2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.apiv2.Char.BPEntry;
import fr.guiguilechat.eveonline.model.database.apiv2.Char.Content;
import fr.guiguilechat.eveonline.model.database.apiv2.Char.JobEntry;
import fr.guiguilechat.eveonline.model.database.apiv2.Char.OrderEntry;

public class Corp {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(Corp.class);

	private final APIRoot parent;

	public Corp(APIRoot parent) {
		this.parent = parent;
	}

	public final String BASEURL = APIRoot.BASEURL + "corp/";

	public LinkedHashMap<Long, ArrayList<Content>> assetList(long charID) {
		return Char.url2assets(BASEURL + "AssetList.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID);
	}

	public ArrayList<JobEntry> industryJobs(long charID) {
		return Char.url2industryJobs(BASEURL + "IndustryJobs.xml.aspx?keyID=" + parent.key.keyID + "&vCode="
				+ parent.key.code + "&characterID=" + charID);
	}

	public List<OrderEntry> marketOrders(long charID) {
		return Char.url2marketOrders(BASEURL + "MarketOrders.xml.aspx?keyID=" + parent.key.keyID + "&vCode="
				+ parent.key.code + "&characterID=" + charID);
	}

	public List<BPEntry> blueprints(long charID) {
		return Char.url2bps(BASEURL + "Blueprints.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID);
	}

}
