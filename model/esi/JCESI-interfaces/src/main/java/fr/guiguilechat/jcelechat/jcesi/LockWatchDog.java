package fr.guiguilechat.jcelechat.jcesi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.IdentityHashMap;
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

		public int remaining = 0;

		public ArrayList<List<StackTraceElement>> stacks = new ArrayList<>();
		public ArrayList<Date> dates = new ArrayList<>();

		public List<StackTraceElement> hold;

	}

	private final IdentityHashMap<Object, AquireData> aquisitions = new IdentityHashMap<>();

	public void tak(Object lock) {
		synchronized (aquisitions) {
			AquireData data = aquisitions.get(lock);
			if (data == null) {
				data = new AquireData();
				aquisitions.put(lock, data);
			}
			data.dates.add(new Date());
			List<StackTraceElement> l = Arrays.asList(Thread.currentThread().getStackTrace());
			data.stacks.add(l.subList(2, l.size()));
			data.remaining++;
		}
	}

	public void rel(Object lock) {
		synchronized (aquisitions) {
			AquireData data = aquisitions.get(lock);
			if (data == null || data.remaining == 0) {
				logger.warn("releasing a lock not acquired " + lock, new NullPointerException());
				return;
			}
			data.remaining--;
			if (data.remaining == 0) {
				data.dates.clear();
				data.stacks.clear();
			}
		}
	}

	public void hld(Object lock) {
		synchronized (aquisitions) {
			AquireData data = aquisitions.get(lock);
			if (data == null || data.remaining == 0) {
				logger.warn("holding a lock not acquired " + lock, new NullPointerException());
				return;
			}
			List<StackTraceElement> l = Arrays.asList(Thread.currentThread().getStackTrace());
			data.hold = l.subList(2, l.size());
		}

	}

	public void logLocks() {
		Date now = new Date();
		synchronized (aquisitions) {
			logger.trace("watchdog locks logging");
			boolean nolock = true;
			for( Entry<Object, AquireData> e : aquisitions.entrySet()) {
				AquireData val = e.getValue();
				if (val.remaining == 0) {
					continue;
				}
				nolock = false;
				logger.debug("" + val.remaining + " " + e.getKey());
				Date firstdate = val.dates.stream().sorted((d1, d2)->(int)Math.signum(d2.getTime()-d1.getTime())).findFirst().orElse(null);
				logger.debug("  acquired " + (now.getTime() - firstdate.getTime()) / 1000 + " s ago, hold by "
						+ val.hold.toString().replace('[', ' '));
				for (List<StackTraceElement> l : val.stacks) {
					logger.debug("    " + l.toString().replace('[', ' '));
				}
			}
			if (nolock) {
				logger.trace("watchdog no lock");
			}
		}
	}

	private LockWatchDog() {
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::logLocks, 10, 10, TimeUnit.SECONDS);
	}

	public static final LockWatchDog BARKER = new LockWatchDog();

}
