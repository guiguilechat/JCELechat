package fr.guiguilechat.eveonline.model.database.yaml;

import java.util.ArrayList;

/**
 * lpstore offering
 *
 */
public class LPOffer {

	public static class ItemRef {
		public int type_id;
		public int quantity;
	}

	public ItemRef product = new ItemRef();

	public static class Requirements {
		public ArrayList<ItemRef> items = new ArrayList<>();
		public int isk;
		public int lp;
	}

	public Requirements requirements = new Requirements();

	public String corporation;
	public String offer_name;
	public int id;

}
