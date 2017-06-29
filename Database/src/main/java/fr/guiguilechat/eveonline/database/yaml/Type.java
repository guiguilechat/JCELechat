package fr.guiguilechat.eveonline.database.yaml;

/**
 * All types in Eve have those fields
 *
 */
public class Type {

	public String name;

	public String groupName;

	public String catName;

	public int id;

	public double volume;

	public int metaLvl;

	public boolean isBlueprint() {
		return "Blueprint".equals(catName);
	}

	public boolean isFaction() {
		return metaLvl > 5 && !name.contains("'");
	}

	public boolean isStoryline() {
		return metaLvl > 5 && name.contains("'");
	}

	public boolean isT2() {
		return metaLvl == 5;
	}

}
