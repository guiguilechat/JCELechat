package fr.guiguilechat.jcelechat.libs.exports.common;

import java.io.InputStream;
import java.time.Instant;
import java.util.function.Function;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Accessors(fluent = true)
@Slf4j
public class Archived<T> {

	/**
	 * input stream generator used to load the file when needed.
	 */
	private final Supplier<InputStream> is;

	/**
	 * moment that archive replaced the previous one
	 */
	@Getter
	private final Instant since;

	private final Function<InputStream, T> parser;

	@Getter(lazy = true)
	private final T content = parser.apply(is.get());

}
