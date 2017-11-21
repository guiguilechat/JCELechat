package fr.guiguilechat.eveonline.model.apiv2;

import java.util.ArrayList;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.model.apiv2.APIRoot;
import fr.guiguilechat.eveonline.model.apiv2.Char.Content;

public class CharMain {

	public static void main(String[] args) {
		int keyID = Integer.parseInt(args[0]);
		String keyCode = args[1];
		Long charID = Long.parseLong(args[2]);
		APIRoot api = new APIRoot(keyID, keyCode);
		for (Entry<Long, ArrayList<Content>> e : api.chars.assetList(charID).entrySet()) {
			System.err.println(e.getKey());
			for (Content c : e.getValue()) {
				System.err.println(" " + c.itemID + " : " + c.quantity);
			}
		}
	}

}
