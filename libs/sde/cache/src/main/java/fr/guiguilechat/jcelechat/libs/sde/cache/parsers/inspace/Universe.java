package fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace;

public enum Universe {
		Eve,
		WormHole,
		Abyssal,
		Void,
		Hidden
	;

	public static Universe of(int regionId) {
		if (regionId < 11000000) {
			return Eve;
		}
		if (regionId < 12000000) {
			return WormHole;
		}
		if (regionId < 14000000) {
			return Abyssal;
		}
		if (regionId < 19000000) {
			return Void;
		}
		return Hidden;

	}
}
