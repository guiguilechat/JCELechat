package fr.guiguilechat.jcelechat.libs.spring.universe.statistics;

import java.time.OffsetDateTime;

public record SystemDateActivity(int sysId, OffsetDateTime date, double activity, double hourly) {


}
