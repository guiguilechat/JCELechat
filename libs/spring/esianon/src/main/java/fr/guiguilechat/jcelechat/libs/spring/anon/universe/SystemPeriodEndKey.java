package fr.guiguilechat.jcelechat.libs.spring.anon.universe;

import java.time.Instant;

import jakarta.persistence.Embeddable;

/**
 * common identifier for entities that are retrieved for a system and an end
 * period.
 */
@Embeddable
public record SystemPeriodEndKey(int solarSystemId, Instant periodEnd) {

}
