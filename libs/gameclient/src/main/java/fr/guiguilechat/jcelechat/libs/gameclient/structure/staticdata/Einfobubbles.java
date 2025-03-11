package fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

@SuppressWarnings("serial")
public class Einfobubbles
		extends HashMap<Integer, fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata.Einfobubbles.InfoBubble> {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Einfobubbles> loader = new KeyValTimeLoader<>(
			Einfobubbles.class, "staticdata/infobubbles.static");

	public static class InfoBubble {

		public static class Bonus {

			public BigDecimal bonus;
			public int importance;
			public int nameID;
			public int unitID;

		}

		public static class MiscBonus {

			public BigDecimal bonus;
			public int importance;
			public boolean isPositive;
			public int nameID;
			public int unitID;

		}

		public static class PreReqSkill {

			public static class Skill {

				public int display;
				public int level;

			}

			public HashMap<Integer, Skill> skills = new HashMap<>();

		}

		public int descriptionID;
		public HashMap<Integer, Integer> elements = new HashMap<>();
		public String icon;
		public int iconID;
		public String iconLarge;
		public String iconSmall;
		public String iconSmallNPC;
		// no actual data to find the internal type.
		public ArrayList<MiscBonus> miscBonuses = new ArrayList<>();
		public int nameID;
		public HashMap<Integer, PreReqSkill> preReqSkills = new HashMap<>();
		public ArrayList<Bonus> roleBonuses = new ArrayList<>();
		public HashMap<Integer, List<Bonus>> types = new HashMap<>();

	}

}
