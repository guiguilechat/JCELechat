package fr.guiguilechat.jcelechat.jcesi.holders.common;

import java.io.Closeable;
import java.util.concurrent.locks.Lock;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LockResource<T extends Lock> {

	@Getter
	private final T lock;

	/**
	 * sub class to remove the throws ioexception
	 */
	public class Closer implements Closeable {
		@Override
		public void close() {
			lock.unlock();
		}
	}

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	private final Closer closer = new Closer();

	public Closer lock() {
		return getCloser();
	}

}
