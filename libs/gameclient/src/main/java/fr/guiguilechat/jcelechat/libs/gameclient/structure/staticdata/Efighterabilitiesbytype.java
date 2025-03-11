package fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata;

import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

public class Efighterabilitiesbytype {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Efighterabilitiesbytype> loader = new KeyValTimeLoader<>(
			Efighterabilitiesbytype.class, "staticdata/fighterabilitiesbytype.static");

	public static class AbilitySlot {

		public static class Charges {

			public int chargeCount;
			public int rearmTimeSeconds;

		}

		public int abilityID;
		public Charges charges;
		public int cooldownSeconds;

	}

	public AbilitySlot abilitySlot0;
	public AbilitySlot abilitySlot1;
	public AbilitySlot abilitySlot2;
	public int displayNameID;
}
