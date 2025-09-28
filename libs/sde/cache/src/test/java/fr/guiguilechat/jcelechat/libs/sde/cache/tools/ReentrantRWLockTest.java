package fr.guiguilechat.jcelechat.libs.sde.cache.tools;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantRWLockTest {

	// does not work, since it can't prevent deadlocking if two threads run that at
	// the same time : the will both acquire read, but can't acquire write since the
	// other one is still in read.
	// @Test(timeOut = 1000)
	public void testRWlock() {
		ReentrantReadWriteLock test = new ReentrantReadWriteLock();
		test.readLock().lock();
		test.writeLock().lock();
	}

}
