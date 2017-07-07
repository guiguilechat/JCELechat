package fr.guiguilechat.eveonline.database.apiv2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fr.guiguilechat.eveonline.database.EveCentral;
import fr.guiguilechat.eveonline.database.apiv2.Account.Character;
import fr.guiguilechat.eveonline.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.database.yaml.Blueprint.Material;
import fr.guiguilechat.eveonline.database.yaml.Type;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;

public class Char {

	private final APIRoot parent;

	public Char(APIRoot parent) {
		this.parent = parent;
	}

	public final String BASEURL = APIRoot.BASEURL + "char/";

	public static class Content {
		public long itemID;
		public int typeID;
		public int quantity = 1;
		public int flag = 0;
		public boolean singleton = false;
		public int rawQuantity;
	}

	public LinkedHashMap<Long, ArrayList<Content>> assetList(long charID) {
		String url = BASEURL + "AssetList.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID;
		LinkedHashMap<Long, ArrayList<Content>> ret = new LinkedHashMap<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result > rowset > row");
			for (Element el : elements) {
				long locId = Long.parseLong(el.attr("locationID"));
				ArrayList<Content> locCtt = ret.get(locId);
				if (locCtt == null) {
					locCtt = new ArrayList<>();
					ret.put(locId, locCtt);
				}
				for (Element ct : el.select("rowset row")) {
					locCtt.add(extractContent(ct));
					// is this needed ?
					for (Element ct2 : el.select("rowset row")) {
						locCtt.add(extractContent(ct2));
					}
				}
			}
		} catch (IOException ex) {
			return ret;
		}
		return ret;
	}

	protected static Content extractContent(Element ct) {
		Content c = new Content();
		c.itemID = APIRoot.getLong(ct, "itemID", 0);
		c.typeID = APIRoot.getInt(ct, "typeID", 0);
		c.quantity = APIRoot.getInt(ct, "quantity", 1);
		c.flag = APIRoot.getInt(ct, "flag", 0);
		c.singleton = APIRoot.getBool(ct, "singleton", false);
		c.rawQuantity = APIRoot.getInt(ct, "rawQuantity", 0);
		return c;
	}

	public static class BPEntry {
		public long itemID;
		public long locationID;
		public long typeID;
		public String typeName;
		public int flagID;
		public int quantity;
		public int timeEfficiency;
		public int materialEfficiency;
		public int runs;
	}

	public ArrayList<BPEntry> blueprints(long charID) {
		String url = BASEURL + "Blueprints.xml.aspx?keyID=" + parent.key.keyID + "&vCode=" + parent.key.code
				+ "&characterID=" + charID;
		ArrayList<BPEntry> ret = new ArrayList<>();
		try {
			Document page = Jsoup.connect(url).get();
			Elements elements = page.select("result rowset row");
			for (Element el : elements) {
				ret.add(extractBPEntry(el));
			}
		} catch (IOException e) {
			throw new UnsupportedOperationException("catch this", e);
		}
		return ret;
	}

	protected static BPEntry extractBPEntry(Element el) {
		BPEntry bpc = new BPEntry();
		bpc.itemID = APIRoot.getLong(el, "itemID", 0);
		bpc.locationID = APIRoot.getLong(el, "locationID", 0);
		bpc.typeID = APIRoot.getInt(el, "typeID", 0);
		bpc.typeName = el.attr("typeName");
		bpc.flagID = APIRoot.getInt(el, "flagID", 0);
		bpc.quantity = APIRoot.getInt(el, "quantity", 1);
		bpc.timeEfficiency = APIRoot.getInt(el, "timeEfficiency", 1);
		bpc.materialEfficiency = APIRoot.getInt(el, "materialEfficiency", 1);
		bpc.runs = APIRoot.getInt(el, "runs", 1);
		return bpc;
	}

	public static class BPCEval {
		String name;
		LinkedHashMap<String, Integer> required = new LinkedHashMap<>();
		String outName;
		int outNb;
		double outValue, inValue;
		double mult;
	}

	public static void main(String[] args) {
		APIRoot r = new APIRoot(Integer.parseInt(args[0]), args[1]);
		EveCentral central = new EveCentral();
		YamlDatabase db = new YamlDatabase();
		double tax = 03;
		// first pass we copy the bpcs to get the required and produced amount of
		// materials
		ArrayList<BPCEval> evaluations = new ArrayList<>();
		for (Character chara : r.account.characters()) {
			for (BPEntry bp : r.chars.blueprints(chara.characterID)) {
				// skip bpo
				if (bp.runs <= 0) {
					continue;
				}
				// blueprint unknown ??
				Blueprint bpt = db.getBlueprints().get(bp.typeName);
				if (bpt == null) {
					continue;
				}
				Type output = db.getTypeByName(bpt.manufacturing.products.get(0).name);
				// out type unknown? (not in database yet)
				if (output == null) {
					continue;
				}
				BPCEval eval = new BPCEval();
				eval.name = bp.typeName;
				for (Material required : bpt.manufacturing.materials) {
					int modifiedQtty = required.quantity == 1 ? required.quantity * bp.runs
							: (int) Math.ceil(bp.runs * 0.01 * (100 - bp.materialEfficiency) * required.quantity);
					eval.required.put(required.name, modifiedQtty);
				}
				for (Material mout : bpt.manufacturing.products) {
					eval.outName = mout.name;
					eval.outNb = mout.quantity * bp.runs;
				}
				evaluations.add(eval);
			}
		}
		// then we cache the materials
		int[] itemIDs = evaluations.stream()
				.flatMap(eval -> Stream.concat(Stream.of(eval.outName), eval.required.keySet().stream())).distinct()
				.mapToInt(s -> db.getMetaInfs().get(s).id).toArray();
		central.cache(itemIDs);
		// then we retrieve sell / buy value of items and compute the mult value of
		// each bpc
		for (BPCEval eval : evaluations) {
			for (Entry<String, Integer> e : eval.required.entrySet()) {
				int id = db.getMetaInfs().get(e.getKey()).id;
				eval.inValue += central.getSO(id) * e.getValue();
			}
			int outId = db.getMetaInfs().get(eval.outName).id;
			eval.outValue += central.getBO(outId) * eval.outNb * (1.0 - tax / 100);
			eval.mult = eval.outValue - eval.inValue;
		}

		Collections.sort(evaluations, (e1, e2) -> (int) Math.signum(e2.mult - e1.mult));
		evaluations.removeIf(e -> e.mult <= 0);
		HashMap<String, Integer> toBuy = new HashMap<>();
		for (BPCEval e : evaluations) {
			System.out.println("" + e.name + " : " + e.mult);
			for (Entry<String, Integer> m : e.required.entrySet()) {
				toBuy.put(m.getKey(), m.getValue() + toBuy.getOrDefault(m.getKey(), 0));
			}
		}
		System.out.println();
		System.out.println("to buy:");
		toBuy.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())).forEach(e -> {
			System.out.println(e.getKey() + "\t" + e.getValue());
		});
	}

}
