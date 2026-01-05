package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.multiplier;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class MutaMultiplierService
		extends DeducedEntityService<MutaMultiplier, MutaMultiplierRepository> {

	public Map<Integer, Map<Integer, MutaMultiplier>> byMutaByAttribtute(Iterable<Integer> mutaplasmidIds) {
		return repo().findByMutaplasmidIdIn(mutaplasmidIds).stream()
				.collect(Collectors.groupingBy(mm -> mm.getMutaplasmid().getId()))
				.entrySet().stream()
				.collect(Collectors.toMap(
						Entry::getKey,
						el -> el.getValue().stream()
								.collect(Collectors.toMap(mm -> mm.getAttribute().getId(), mm -> mm))));
	}

}
