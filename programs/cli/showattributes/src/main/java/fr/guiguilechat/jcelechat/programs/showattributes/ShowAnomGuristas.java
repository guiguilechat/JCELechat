package fr.guiguilechat.jcelechat.programs.showattributes;

import fr.guiguilechat.jcelechat.programs.showattributes.typeData.EHP;
import fr.guiguilechat.jcelechat.programs.showattributes.typeData.Name;
import fr.guiguilechat.jcelechat.programs.showattributes.typeData.OrbitVelocity;
import fr.guiguilechat.jcelechat.programs.showattributes.typeData.SigRadius;

public class ShowAnomGuristas {

	public static void main(String[] args) {

		ShowEntitiesStats.showGroups(null, new int[] { 613, 614, 615 }, Name.INS, SigRadius.INS, OrbitVelocity.INS, EHP.KI);
	}

}
