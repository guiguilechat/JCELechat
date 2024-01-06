package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import fr.lelouet.tools.holders.interfaces.ObjHolder;

public class ShowRadius {

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 10;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);
		Universe uni = ESIAccess.INSTANCE.universe;
		List<ObjHolder<R_get_universe_systems_system_id>> systems = uni.cache().systems().get().stream()
				.map(id -> uni.cache().systems(id)).collect(Collectors.toList());
		System.err.println("got list of systems");
		Map<String, Double> map = systems.parallelStream()
				.collect(Collectors.toMap(s -> s.get().name, s -> uni.systemRadiusCelestials(s.get())));
		System.err.println("distances mapped");
		ArrayList<Entry<String, Double>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, (e1, e2) -> Double.compare(e1.getValue(), e2.getValue()));
		for (Entry<String, Double> e : list) {
			System.err.println(e.getKey() + "\t" + e.getValue());
		}
	}

}
