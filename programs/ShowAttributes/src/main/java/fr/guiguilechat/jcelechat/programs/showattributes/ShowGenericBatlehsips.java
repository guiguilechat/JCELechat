package fr.guiguilechat.jcelechat.programs.showattributes;

import fr.guiguilechat.jcelechat.programs.showattributes.typeData.EHP;
import fr.guiguilechat.jcelechat.programs.showattributes.typeData.Name;

public class ShowGenericBatlehsips {

	public static void main(String[] args) {
		ShowEntitiesStats.showGroups("EoM .*", new int[] { 816 }, Name.INS, EHP.EM, EHP.TH, EHP.KI, EHP.EX);

	}

}
