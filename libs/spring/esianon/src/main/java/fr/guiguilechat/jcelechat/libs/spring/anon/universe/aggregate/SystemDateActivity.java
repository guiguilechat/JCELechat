package fr.guiguilechat.jcelechat.libs.spring.anon.universe.aggregate;

import java.time.OffsetDateTime;

public record SystemDateActivity(int sysId, OffsetDateTime date, double activity, double hourly) {


}
