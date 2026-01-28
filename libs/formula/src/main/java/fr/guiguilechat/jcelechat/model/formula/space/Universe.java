package fr.guiguilechat.jcelechat.model.formula.space;

public enum Universe {
	Eve,
	WormHole,
	Abyssal,
	Void,
	Hidden;

	/**
	 * @see https://developers.eveonline.com/docs/guides/id-ranges/#regions
	 */
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
