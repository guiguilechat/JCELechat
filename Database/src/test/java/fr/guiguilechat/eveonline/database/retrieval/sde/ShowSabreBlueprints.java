package fr.guiguilechat.eveonline.database.retrieval.sde;

import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;

public class ShowSabreBlueprints {

	public static void main(String[] args) {
		SDEData sde = new SDEData();
		int sabreID = sde.getDico().get("sabre");
		sde.getParentManufacturing(sabreID).forEach(bp->{
			System.err.println(sde.getType(bp.blueprintTypeID).enName() + "\t" + bp.blueprintTypeID);
			sde.getParentInvention(bp.blueprintTypeID).forEach(bp2 -> {
				System.err.println("\tinvented by " + sde.getType(bp2.blueprintTypeID).enName() + "\t" + bp2.blueprintTypeID);
			});
		});
	}

}
