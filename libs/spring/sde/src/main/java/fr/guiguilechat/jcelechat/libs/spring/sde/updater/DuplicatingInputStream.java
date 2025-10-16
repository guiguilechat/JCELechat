package fr.guiguilechat.jcelechat.libs.spring.sde.updater;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * memorize an inpustream to serve it several times
 */
@RequiredArgsConstructor
class DuplicatingInputStream implements Supplier<InputStream> {
	private final InputStream inputStream;

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	@Accessors(fluent = true)
	private final ByteArrayOutputStream baos = loadIS();

	protected ByteArrayOutputStream loadIS() {
		ByteArrayOutputStream newBuffer = new ByteArrayOutputStream();
		try {
			inputStream.transferTo(newBuffer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return newBuffer;
	}

	@Override
	public InputStream get() {
		return new ByteArrayInputStream(baos().toByteArray());
	}
}