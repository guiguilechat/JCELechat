package fr.guiguilechat.jcelechat.libs.spring.universe.statistics.jumps;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class DailyJumps {

	private final int ssId;
	private final int jumps;
	private final String date;
}