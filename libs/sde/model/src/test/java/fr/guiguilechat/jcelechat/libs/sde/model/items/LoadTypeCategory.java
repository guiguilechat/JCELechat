package fr.guiguilechat.jcelechat.libs.sde.model.items;

public class LoadTypeCategory {

	public static void main(String[] args) {
		Category ships = Category.CACHE.of("Ship");
		System.err.println("groups of " + ships + " are " + ships.groups());
	}

}
