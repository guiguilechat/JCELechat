package fr.guiguilechat.jcelechat.programs.showattributes;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;

public class EIVT1T2Ratio {

	public static void main(String[] args) {
		double sumT1 = 0, sumT2 = 0;
		for (Blueprint bp : Blueprint.yaml().load().values()) {

			if (bp.invention != null && bp.invention.products != null && !bp.invention.products.isEmpty()) {
				Blueprint bpi = Blueprint.of(bp.invention.products.get(0).id);
				if (bpi != null) {
					double t1eiv = bp.makeEIV(ESIAccess.INSTANCE.markets::getAdjusted);
					double t2eiv= bpi.makeEIV(ESIAccess.INSTANCE.markets::getAdjusted);
					sumT1+=t1eiv;
					sumT2 += t2eiv;
				}
			}
		}
		System.out.println("sumT1=" + sumT1 + " sumT2=" + sumT2 + " t2/T1=" + sumT2 / sumT1);
	}

}
