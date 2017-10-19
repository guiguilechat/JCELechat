package fr.guiguilechat.eveonline.database.apiv2;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.database.apiv2.Char.Content;
import fr.guiguilechat.eveonline.database.apiv2.Char.JobEntry;

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

}
