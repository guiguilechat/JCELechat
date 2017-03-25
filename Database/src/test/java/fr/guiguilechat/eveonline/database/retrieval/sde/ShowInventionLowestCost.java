package fr.guiguilechat.eveonline.database.retrieval.sde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import fr.guiguilechat.eveonline.database.EveCentral;
import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.Eblueprints;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.Eblueprints.Material;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.EtypeIDs;
import fr.guiguilechat.eveonline.database.retrieval.sde.model.InventionDecryptor;

public class ShowInventionLowestCost {

	public static void main(String[] args) {
		SDEData sde = new SDEData();
		EveCentral central = new EveCentral();

		ArrayList<InventionDecryptor> decryptors = sde.getInventionDecryptors();
		boolean stophere=false;
		if(stophere) {
			for(InventionDecryptor id : decryptors) {
				System.err.println("cost of "+id.name+" "+central.getSO(id.id)+"(id"+id.id+" "+id.probmult+")");
				return;
			}
		}
		LinkedHashMap<Integer, Eblueprints> bps = Eblueprints.load();
		int skill1 = 3, skill2 = 3, encryptionSkill = 3;
		double multProb = (skill1 + skill2) * 1.0 / 30 + encryptionSkill * 1.0 / 40 + 1;
		for (Eblueprints bpoData : bps.values()) {
			if (!bpoData.activities.invention.products.isEmpty()) {
				EtypeIDs bpoType = sde.getTypeIDs().get(bpoData.blueprintTypeID);
				// only keep bpo with invention and that are found in the itemid table
				if (bpoType != null) {
					double cost = 0;
					for (Material required : bpoData.activities.invention.materials) {
						cost += central.getSO(required.typeID) * required.quantity;
					}
					for (Material bpcProduct : bpoData.activities.invention.products) {
						Eblueprints bpc = bps.get(bpcProduct.typeID);
						boolean error = false;
						for( Material m: bpc.activities.manufacturing.products) {
							EtypeIDs finalProduct = sde.getTypeIDs().get(m.typeID);
							if(finalProduct!=null) {
								System.err.println(bpcProduct.typeID + "\t" + finalProduct.enName() + "\t" + m.quantity);
							} else {
								error=true;
							}
						}
						if(error) {
							continue;
						}
						Map<InventionDecryptor, Double> costPerDecryptor = new HashMap<>();
						for (InventionDecryptor decryptor : decryptors) {
							int run = bpcProduct.quantity + decryptor.maxrun;
							double prob = Math.min(bpcProduct.probability *multProb *  decryptor.probmult, 1);
							int me = 2 + decryptor.me;
							double oneRunInventionCost = (central.getSO(decryptor.id)+cost)/prob/run;
							System.err.println("\t"+decryptor.name+"\t"+oneRunInventionCost+"\t("+decryptor.id+")");
							double allRunsProductionCost=0;
							for (Material m : bpc.activities.manufacturing.materials) {
								int modifiedQtty = m.quantity==1?m.quantity*run:(int) Math.ceil(run*0.01*(100-me)*m.quantity);
								allRunsProductionCost+=modifiedQtty*central.getSO(m.typeID);
							}
							System.err.println("\t\t"+(oneRunInventionCost+allRunsProductionCost/run));
						}
					}
					return;
				} else {
					System.err.println("unknown type with id " + bpoData.blueprintTypeID);
				}
			}
		}
	}

	public HashMap<InventionDecryptor, Double> getInventionCost(SDEData sde, int typeID) {
		// todo
		return null;

	}

}
