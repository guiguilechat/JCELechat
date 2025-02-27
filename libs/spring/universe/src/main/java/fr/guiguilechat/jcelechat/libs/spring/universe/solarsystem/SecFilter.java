package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * accept a solar system based on its truesec
 */
@RequiredArgsConstructor
@Getter
public enum SecFilter {
	HS(0.45f, 1f),
	LS(0f, 0.45f),
	NS(-1f, 0f),
	ALL(-1f, 1f)
	;

	public final float lowerSS;
	public final float higherSS;

	public boolean accept(SolarSystem ss) {
		return lowerSS <= ss.getSecurityStatus() && higherSS >= ss.getSecurityStatus();
	}
}