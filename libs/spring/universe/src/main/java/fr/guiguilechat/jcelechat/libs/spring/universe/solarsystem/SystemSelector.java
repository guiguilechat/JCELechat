package fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem;

import java.util.List;

/**
 * select a list of system ids for a parameter
 *
 * @param <T> type of the parameter
 */
public interface SystemSelector<T> {

	List<Integer> apply(SolarSystemRepository repo, Iterable<T> filter);
}
