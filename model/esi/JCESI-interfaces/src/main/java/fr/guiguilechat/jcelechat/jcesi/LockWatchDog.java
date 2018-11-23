package fr.guiguilechat.jcelechat.jcesi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** lockwatchdog to watch what acquires and releases synchronization items*/
public class LockWatchDog {

	private static final Logger logger = LoggerFactory.getLogger(LockWatchDog.class);

	public static class AquireData {

		public HashMap<Thread, List<StackTraceElement>> takerTraces = new HashMap<>();
		public ArrayList<Date> dates = new ArrayList<>();

		public Thread holder;

	}

	private final IdentityHashMap<Object, AquireData> aquisitions = new IdentityHashMap<>();
	private final HashMap<Thread, IdentityHashMap<Object, Object>> threadsSyncs = new HashMap<>();

	public static boolean skip = System.getProperties().contains("nowatchdog");

	public void tak(Object lock) {
		if (skip) {
			return;
		}
		Thread th = Thread.currentThread();
		synchronized (aquisitions) {
			AquireData data = aquisitions.get(lock);
			if (data == null) {
				data = new AquireData();
				aquisitions.put(lock, data);
			}
			data.dates.add(new Date());
			List<StackTraceElement> l = Arrays.asList(Thread.currentThread().getStackTrace());
			data.takerTraces.put(th, l.subList(2, l.size()));
			IdentityHashMap<Object, Object> threadSets = threadsSyncs.get(th);
			if (threadSets == null) {
				threadSets = new IdentityHashMap<>();
				threadsSyncs.put(th, threadSets);
			} else {
				// search for deadlocks if this thread already own objects
				// check that the owner of the object we are asking to acquire does not
				// try to acquire an object we acquired, directly or resursively.
				/** used as a set */
				IdentityHashMap<Object, Object> holdedLocks = new IdentityHashMap<>();
				holdedLocks.put(lock, null);
				List<Object> nextLock = new LinkedList<>();
				nextLock.addAll(threadSets.keySet());
				do {
					Object loopLock = nextLock.remove(0);
					AquireData ad = aquisitions.get(loopLock);
					if (ad.holder != null) {
						for (Object otherLock : threadsSyncs.get(ad.holder).keySet()) {
							if (otherLock != loopLock) {
								if (holdedLocks.containsKey(otherLock)) {
									logger.error(
											"deadloclk as thread " + ad.holder + " holds the object " + otherLock + " which is being taken");
									logLocks();
									throw new NullPointerException("deadlocked");
								}
								nextLock.add(otherLock);
							}
						}
					}
					holdedLocks.put(loopLock, null);
				} while (!nextLock.isEmpty());
			}
			threadSets.put(lock, null);
		}
	}

	public void rel(Object lock) {
		if (skip) {
			return;
		}
		Thread th = Thread.currentThread();
		synchronized (aquisitions) {
			AquireData data = aquisitions.get(lock);
			if (data == null || data.takerTraces.size() == 0) {
				logger.warn("releasing a lock not acquired " + lock, new NullPointerException());
				return;
			}
			data.takerTraces.remove(th);
			data.holder = null;
			if (data.takerTraces.size() == 0) {
				data.dates.clear();
			}
			IdentityHashMap<Object, Object> threadSets = threadsSyncs.get(th);
			threadSets.remove(lock);
			if (threadSets.isEmpty()) {
				threadsSyncs.remove(th);
			}
		}
	}

	public void hld(Object lock) {
		if (skip) {
			return;
		}
		Thread th = Thread.currentThread();
		synchronized (aquisitions) {
			AquireData data = aquisitions.get(lock);
			if (data == null || data.takerTraces.size() == 0) {
				logger.warn("holding a lock not acquired " + lock, new NullPointerException());
				return;
			}
			data.holder = th;
		}

	}

	public void logLocks() {
		Date now = new Date();
		synchronized (aquisitions) {
			boolean nolock = true;
			for( Entry<Object, AquireData> e : aquisitions.entrySet()) {
				AquireData val = e.getValue();
				if (val.takerTraces.size() == 0) {
					continue;
				}
				nolock = false;
				logger.debug("" + val.takerTraces.size() + " " + e.getKey());
				Date firstdate = val.dates.stream().sorted((d1, d2)->(int)Math.signum(d2.getTime()-d1.getTime())).findFirst().orElse(null);
				logger.debug("  acquired " + (now.getTime() - firstdate.getTime()) / 1000 + " s ago, hold by "
						+ val.holder.toString());
				for (Entry<Thread, List<StackTraceElement>> l : val.takerTraces.entrySet()) {
					logger.debug("    " + l.getKey() + " : " + l.getValue().toString().replace('[', ' '));
				}
			}
			if (nolock) {
				logger.trace("watchdog no lock");
			}
		}
	}

	private LockWatchDog() {
		if (!skip) {
			Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::logLocks, 30, 30, TimeUnit.SECONDS);
		}
	}

	public static final LockWatchDog BARKER = new LockWatchDog();

}
