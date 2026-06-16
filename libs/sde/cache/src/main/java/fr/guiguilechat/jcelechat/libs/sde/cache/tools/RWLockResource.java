package fr.guiguilechat.jcelechat.libs.sde.cache.tools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import lombok.RequiredArgsConstructor;

/// make a try-with-resource resource out of a ReadWriteLock. This ensures the
/// readlock/writelock is unlocked after a call
///
///
/// usage is
/// ```java
/// var myRWLockResource = new RWLockResource<>(myReadWriteLock);
/// try (var _ = myRWLockResource.readLock()) {
/// 	// code using the lock here
/// }
/// ```
/// You can call the constructor as much as you want for the same object, it's
/// only delegation into a resource way.
public class RWLockResource<T extends ReadWriteLock> {

	@RequiredArgsConstructor
	public static class CloseableLock implements AutoCloseable {

		private final Lock lock;

		@Override
		public void close() {
			lock.unlock();
		}

	}

	private final T lock;

	private final CloseableLock closeableRLock;
	private final CloseableLock closeableWLock;

	public RWLockResource(T lock) {
		this.lock = lock;
		closeableRLock = new CloseableLock(lock.readLock());
		closeableWLock = new CloseableLock(lock.writeLock());
	}

	public CloseableLock readLock() {
		lock.readLock().lock();
		return closeableRLock;
	}

	public CloseableLock writeLock() {
		lock.writeLock().lock();
		return closeableWLock;
	}

}
