package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class IndexedSystems implements Iterable<SolarSystem> {

	private final SolarSystem[] idx2sys;
	private final Map<SolarSystem, Integer> sys2idx;

	public int size() {
		return idx2sys.length;
	}

	public IndexedSystems(Collection<SolarSystem> systems) {
		idx2sys = systems.stream().sorted((s, v) -> s.name.compareTo(v.name)).toArray(SolarSystem[]::new);
		sys2idx = IntStream.range(0, idx2sys.length).boxed().collect(Collectors.toMap(i -> idx2sys[i], i -> i));
	}

	public SolarSystem system(int idx) {
		return idx2sys[idx];
	}

	public int system(SolarSystem sys) {
		return sys2idx.get(sys);
	}

	@Override
	public Iterator<SolarSystem> iterator() {
		return Collections.unmodifiableSet(sys2idx.keySet()).iterator();
	}

}
