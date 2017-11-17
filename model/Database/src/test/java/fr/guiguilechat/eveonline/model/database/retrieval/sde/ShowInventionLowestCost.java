package fr.guiguilechat.eveonline.model.database.retrieval.sde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import fr.guiguilechat.eveonline.model.database.EveCentral;
import fr.guiguilechat.eveonline.model.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.model.esi.ESIBasePrices;
import fr.guiguilechat.eveonline.model.sde.fsd.Eblueprints;
import fr.guiguilechat.eveonline.model.sde.fsd.EtypeIDs;
import fr.guiguilechat.eveonline.model.sde.fsd.Eblueprints.Material;
import fr.guiguilechat.eveonline.model.sde.model.InventionDecryptor;

public class ShowInventionLowestCost {

	public static void main(String[] args) {
		SDEData sde = new SDEData();
		EveCentral central = new EveCentral();
		ESIBasePrices emp = new ESIBasePrices();

		ArrayList<InventionDecryptor> decryptors = sde.getInventionDecryptors();
		central.cache(decryptors.stream().mapToInt(id -> id.id).toArray());
		LinkedHashMap<Integer, Eblueprints> bps = Eblueprints.load();
		int skill1 = 5, skill2 = 5, encryptionSkill = 5;
		double multProb = (skill1 + skill2) * 1.0 / 30 + encryptionSkill * 1.0 / 40 + 1;
		for (Eblueprints bpoData : bps.values()) {
			if (!bpoData.activities.invention.products.isEmpty()) {
				EtypeIDs bpoType = sde.getTypeIDs().get(bpoData.blueprintTypeID);
				// only keep bpo with invention and that are found in the itemid table
				if (bpoType != null) {
					double cost = 0;
					// always cache the data before
					central.cache(bpoData.activities.invention.materials.stream().mapToInt(m -> m.typeID).toArray());
					for (Material required : bpoData.activities.invention.materials) {
						cost += central.getSO(required.typeID) * required.quantity;
					}
					for (Material bpcProduct : bpoData.activities.invention.products) {
						Eblueprints bpc = bps.get(bpcProduct.typeID);
						if (bpc.activities.manufacturing.products.size() != 1) {
							System.err.println("too many porducts for item id " + bpc.blueprintTypeID);
							continue;
						}
						EtypeIDs finalProduct = sde.getTypeIDs().get(bpc.activities.manufacturing.products.get(0).typeID);
						if (finalProduct == null) {
							System.err.println("cancel bpc " + bpoType.enName());
							continue;
						}
						double eiv = 0.0;// estimated item value
						for (Material m : bpc.activities.manufacturing.materials) {
							eiv += emp.getAdjusted(m.typeID) * m.quantity;
						}
						// System.err.println("\testimated item value " + eiv);
						central.cache(bpc.activities.manufacturing.materials.stream().mapToInt(m -> m.typeID).toArray());
						InventionDecryptor bestdecryptor = null;
						double lowestCost = Double.POSITIVE_INFINITY;
						for (InventionDecryptor decryptor : decryptors) {
							int run = bpcProduct.quantity + decryptor.maxrun;
							double prob = Math.min(bpcProduct.probability * multProb * decryptor.probmult, 1);
							int me = 2 + decryptor.me;
							double oneRunInventionCost = (central.getSO(decryptor.id) + cost) / prob / run;
							// System.err.println("\t"+decryptor.name+"\t"+oneRunInventionCost+"\t("+decryptor.id+")");
							double allRunsProductionCost = eiv;
							for (Material m : bpc.activities.manufacturing.materials) {
								int modifiedQtty = m.quantity == 1 ? m.quantity * run
										: (int) Math.ceil(run * 0.01 * (100 - me) * m.quantity);
								allRunsProductionCost += modifiedQtty * central.getSO(m.typeID);
							}
							double finalUnitCost = oneRunInventionCost + allRunsProductionCost / run;
							if (finalUnitCost < lowestCost || bestdecryptor == null) {
								lowestCost = finalUnitCost;
								bestdecryptor = decryptor;
							}
						}
						System.out.println(bpcProduct.typeID + "\t" + finalProduct.enName() + "\t" + bestdecryptor.name + "\t"
								+ lowestCost / 1000000);
					}
				} else {
					System.err.println("unknown type with id " + bpoData.blueprintTypeID);
				}
				return;
			}
		}
	}

	public HashMap<InventionDecryptor, Double> getInventionCost(SDEData sde, int typeID) {

		// todo
		return null;

	}

}
