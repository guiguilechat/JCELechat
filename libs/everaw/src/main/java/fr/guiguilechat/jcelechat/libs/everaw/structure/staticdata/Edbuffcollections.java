package fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTimeLoader;
import fr.guiguilechat.jcelechat.libs.everaw.structure.common.Modifier;
import fr.guiguilechat.jcelechat.libs.everaw.structure.common.OperationName;
import lombok.Getter;

public class Edbuffcollections {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<Edbuffcollections> loader = new KeyValTimeLoader<>(
			Edbuffcollections.class, "staticdata/dbuffcollections.static");

	public enum AggregateMode {
		Maximum,
		Minimum

		;
	}

	public static class ItemModifier extends Modifier {

	}

	public static class locationGroupModifier extends Modifier {

		public int groupID;

	}

	public static class locationModifier extends Modifier {
	}

	public static class locationRequiredSkillModifier extends Modifier {

		public int skillID;

	}

	public enum ShowOutputValueInUI {
		Hide,
		ShowInverted,
		ShowNormal

		;
	}

	public AggregateMode aggregateMode;

	public String developerDescription;

	public int displayNameID;

	public List<ItemModifier> itemModifiers = new ArrayList<>();

	public List<locationGroupModifier> locationGroupModifiers = new ArrayList<>();

	public List<locationModifier> locationModifiers = new ArrayList<>();

	public List<locationRequiredSkillModifier> locationRequiredSkillModifiers = new ArrayList<>();

	public OperationName operationName;

	public ShowOutputValueInUI showOutputValueInUI;

}
