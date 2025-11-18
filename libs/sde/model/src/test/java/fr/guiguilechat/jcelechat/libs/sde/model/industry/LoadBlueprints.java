package fr.guiguilechat.jcelechat.libs.sde.model.industry;

import fr.guiguilechat.jcelechat.libs.sde.model.industry.BluePrint.Produced;

public class LoadBlueprints {

	public static void main(String[] args) {
		// caracal blueprint
		var caracalbp = BluePrint.CACHE.of(687);
		System.out.println("loaded " + caracalbp.type().toString());
		for (Produced p : caracalbp.manufacturing().products) {
			System.out.println("manufacturing produces " + p);
		}
		for (Produced p : caracalbp.invention().products) {
			System.out.println("invention produces " + p);
		}
	}

}
