package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

/**
 * A reindex of a collection of systems. The internal collections is immutable -
 * however the systems are mutable.<br />
 * The reindex is done after sorting of the systems, to ensure successive calls
 * return the same index. Therefore the optional comparator parameter should
 * ONLY return 0 when the systems are the same. For example, comparing using
 * only the system sec status (0.5, 0.6, …) should not be used. To avoid that,
 * the NAME comparator is always appended to the provided comparator.
 */
public class SysIndex implements Iterable<SolarSystem> {

	private final SolarSystem[] idx2sys;
	private final Map<SolarSystem, Integer> sys2idx;

	public int size() {
		return idx2sys.length;
	}

	/**
	 * used to order systems by name ascending.
	 */
	public static final Comparator<SolarSystem> NAME_CMP = (s, v) -> s.name.compareTo(v.name);

	/**
	 * used to order systems by truesec ascending
	 */
	public static final Comparator<SolarSystem> TRUESEC_CMP = (s, v) -> Double.compare(s.truesec, v.truesec);

	/**
	 * index the systems after ordering them by name ascending
	 *
	 * @param systems
	 *          systems to index.
	 */
	public SysIndex(Collection<SolarSystem> systems) {
		this(systems, NAME_CMP);
	}

	/**
	 * index the systems after ordering them with given comparator.
	 *
	 * @param systems
	 *          systems to index
	 * @param compare
	 *          comparator to order the system prior to indexing. Default is by
	 *          name ascending.
	 */
	public SysIndex(Collection<SolarSystem> systems, Comparator<SolarSystem> compare) {
		idx2sys = systems.stream()
				.sorted(compare == null || compare == NAME_CMP ? NAME_CMP : compare.thenComparing(NAME_CMP))
				.toArray(SolarSystem[]::new);
		sys2idx = IntStream.range(0, idx2sys.length).boxed().collect(Collectors.toMap(i -> idx2sys[i], i -> i));
	}

	/**
	 *
	 * @param systems
	 *          systems to index.
	 */
	public SysIndex(SolarSystem... systems) {
		this(Stream.of(systems).collect(Collectors.toSet()));
	}

	/**
	 * @param compare
	 *          comparator to order the systems prior to indexing them.
	 * @param systems
	 *          systems to index.
	 */
	public SysIndex(Comparator<SolarSystem> compare, SolarSystem... systems) {
		this(Stream.of(systems).collect(Collectors.toSet()), compare);
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
