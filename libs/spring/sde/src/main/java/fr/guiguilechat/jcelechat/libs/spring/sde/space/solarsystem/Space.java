package fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@RequiredArgsConstructor
@Slf4j
public enum Space {

	AS("abyss", "triglavian (abyss) space, reachable using filaments or WH"),
	HS("high sec", "hgih-security known space"),
	LS("low sec", "low-security known space"),
	NS("null sec", "null-security known space"),
	US("unknown space", "space not handled yet"),
	VS("void space", "jovian space"),
	WS("wormhole space", "wormhole reachable space");

	private final String name;

	private final String description;

	public static Space of(float truesec, String universe) {
		if (truesec > 0.45) {
			return HS;
		}
		if (truesec >= 0.0) {
			return LS;
		}
		if (universe != null) {
			switch (universe) {
			case "abyssal":
				return AS;
			case "eve":
				return NS;
			case "void":
				return VS;
			case "wormhole":
				return WS;
			}
		}
		log.error("can't find space for truesec {} and universe {}", truesec, universe);
		return US;
	}

}
