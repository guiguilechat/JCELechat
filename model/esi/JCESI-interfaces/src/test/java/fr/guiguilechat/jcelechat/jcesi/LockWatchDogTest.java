package fr.guiguilechat.jcelechat.jcesi;

import java.util.concurrent.CountDownLatch;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LockWatchDogTest {

	@Test
	public void testDeadLocks() {
		Object lock = "a";
		LockWatchDog.BARKER.tak(lock);
		LockWatchDog.BARKER.hld(lock);

		try {
			LockWatchDog.BARKER.tak(lock);
			Assert.fail("expected a deadlock");
		} catch (NullPointerException npe) {
		}

		try {
			LockWatchDog.BARKER.tak(lock);
			Assert.fail("expected a deadlock");
		} catch (NullPointerException npe) {
		}

		Object lock2 = "b";
		LockWatchDog.BARKER.tak(lock2);
		LockWatchDog.BARKER.hld(lock2);
		LockWatchDog.BARKER.rel(lock2);

		try {
			LockWatchDog.BARKER.tak(lock);
			Assert.fail("expected a deadlock");
		} catch (NullPointerException npe) {
		}
	}

	@Test
	public void testTripleThreadDeadlock() throws InterruptedException {
		Object a = "a";
		Object b = "b";
		Object c = "c";
		CountDownLatch cdl1 = new CountDownLatch(1);
		new Thread(() -> {
			LockWatchDog.BARKER.tak(a);
			LockWatchDog.BARKER.hld(a);
			LockWatchDog.BARKER.tak(b);
			cdl1.countDown();
		}).start();
		cdl1.await();

		CountDownLatch cdl2 = new CountDownLatch(1);
		new Thread(() -> {
			LockWatchDog.BARKER.tak(b);
			LockWatchDog.BARKER.hld(b);
			LockWatchDog.BARKER.tak(c);
			cdl2.countDown();
		}).start();
		cdl2.await();

		LockWatchDog.BARKER.tak(c);
		LockWatchDog.BARKER.hld(c);
		try {
			LockWatchDog.BARKER.tak(a);
			Assert.fail("expected a deadlock");
		} catch (NullPointerException npe) {

		}
	}

}
