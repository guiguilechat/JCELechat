package fr.guiguilechat.eveonline.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.IntPredicate;

import fr.guiguilechat.eveonline.database.retrieval.sde.cache.SDEData;
import fr.guiguilechat.eveonline.database.retrieval.sde.fsd.EtypeIDs;

public class ShowJitaOrePrice {

	public static class AsteroidData {
		EtypeIDs type;
		double bo;
		double so;
		double volume = 1;

		public double boPerVolume() {
			return bo / volume;
		}
	}

	public static void main(String[] args) {
		SDEData sde = new SDEData();
		IntPredicate isCorrectAsteroid = i -> {
			EtypeIDs data = sde.getType(i);
			// group 465 is ice
			return data.groupID != 465 && !data.enName().toLowerCase().contains("compressed");
		};
		// cat 25 is asteroid cat
		int[] typeIDs = sde.getTypeIDsForCategory(25).filter(isCorrectAsteroid).toArray();
		sde.central().cache(typeIDs);

		ArrayList<AsteroidData> datas = new ArrayList<>();
		for (int id : typeIDs) {
			AsteroidData data = new AsteroidData();
			EtypeIDs type = sde.getType(id);
			data.type = type;
			data.volume = type.volume;
			data.bo = sde.central().getBO(id);
			data.so = sde.central().getSO(id);
			if (data.bo != 0) {
				datas.add(data);
			}
		}

		Collections.sort(datas, (d1, d2) -> (int) Math.signum(d2.boPerVolume() - d1.boPerVolume()));
		for (AsteroidData data : datas) {
			System.err.println(data.type.enName() + "\t" + data.boPerVolume() + "\t(" + data.type.groupID + ")");
		}
	}

}
