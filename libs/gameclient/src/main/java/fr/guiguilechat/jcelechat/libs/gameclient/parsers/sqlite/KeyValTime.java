package fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class KeyValTime<T> {

	private int key;
	private T val;
	private Instant time;

	public static <T> KeyValTime<T> ofRow(int key, T val, double timeD) {
		Instant time = Instant.ofEpochMilli((long) (timeD * 1000));
		return new KeyValTime<>(key, val, time);
	}

}
