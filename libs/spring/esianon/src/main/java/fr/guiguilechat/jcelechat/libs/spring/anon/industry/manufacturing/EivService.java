package fr.guiguilechat.jcelechat.libs.spring.anon.industry.manufacturing;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.prices.PriceService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.prices.PriceService.PriceListener;
import fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material.BlueprintMaterialService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EivService implements PriceListener {

//	private final EntityManager entityManager;

	private final EivRepository eivRepository;

	/** only need for the entities, as we do the query */
	@SuppressWarnings("unused")
	private final BlueprintMaterialService materialService;

	/** only need for the entities, as we do the query */
	@SuppressWarnings("unused")
	private final PriceService priceService;

	@Cacheable("eivs")
	public Map<Integer, Long> eivs() {
		return eivRepository.fetchEivs(ActivityType.manufacturing)
				.stream()
				.collect(Collectors.toMap(a -> (int) a[0], a -> ((Number) a[1]).longValue()));
	}

	@Getter(lazy = true)
	private final List<String> cacheList = List.of(
			"eivs");

}
