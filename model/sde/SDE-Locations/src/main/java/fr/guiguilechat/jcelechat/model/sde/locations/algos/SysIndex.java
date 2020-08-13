package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

/**
 * A reindex of a collection of systems. The internal collections is immutable -
 * however the systems are mutable.
 */
public class SysIndex implements Iterable<SolarSystem> {

	private final SolarSystem[] idx2sys;
	private final Map<SolarSystem, Integer> sys2idx;

	public int size() {
		return idx2sys.length;
	}

	public SysIndex(Collection<SolarSystem> systems) {
		idx2sys = systems.stream().sorted((s, v) -> s.name.compareTo(v.name)).toArray(SolarSystem[]::new);
		sys2idx = IntStream.range(0, idx2sys.length).boxed().collect(Collectors.toMap(i -> idx2sys[i], i -> i));
	}

	public SysIndex(SolarSystem systems) {
		this(Stream.of(systems).collect(Collectors.toSet()));
	}

	/**
	 *
	 * @param idx
	 *          an integer between 0 and {@link #size()} excluded.
	 * @return the system at that index.
	 */
	public SolarSystem system(int idx) {
		return idx2sys[idx];
	}

	/**
	 *
	 * @param sys
	 *          a system indexed
	 * @return the index of that system.
	 */
	public int index(SolarSystem sys) {
		return sys2idx.get(sys);
	}

	@Override
	public Iterator<SolarSystem> iterator() {
		return Collections.unmodifiableSet(sys2idx.keySet()).iterator();
	}

}
