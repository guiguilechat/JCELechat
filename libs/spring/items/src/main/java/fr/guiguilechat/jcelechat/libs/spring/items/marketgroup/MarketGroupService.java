package fr.guiguilechat.jcelechat.libs.spring.items.marketgroup;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.AFetchedResourceService.EntityUpdateListener;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_groups_market_group_id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.marketgroup")
@Order(1)
public class MarketGroupService
    extends ARemoteEntityService<MarketGroup, Integer, R_get_markets_groups_market_group_id, MarketGroupRepository>
    implements EntityUpdateListener {

	@Lazy
	private final TypeService typeService;

	@Override
	protected MarketGroup create(Integer entityId) {
		MarketGroup ret = new MarketGroup();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_markets_groups_market_group_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_markets_groups(id, properties);
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_markets_groups(p).mapBody(List::of);
	}

	@Override
	protected void updateResponseOk(Map<MarketGroup, R_get_markets_groups_market_group_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, Type> idtoType = typeService.createIfAbsent(
		    responseOk.values().stream()
		    .flatMap(mg -> IntStream.of(mg.types).boxed())
		    .distinct()
		        .toList());
		Map<Integer, MarketGroup> idToMarketGroup = createIfAbsent(
		    responseOk.values().stream()
		        .map(mg -> mg.parent_group_id)
		        .distinct()
		        .filter(i -> i != 0)
		        .toList());
		for (Entry<MarketGroup, R_get_markets_groups_market_group_id> e : responseOk.entrySet()) {
			MarketGroup mg = e.getKey();
			R_get_markets_groups_market_group_id data = e.getValue();
			mg.setParent(idToMarketGroup.get(data.parent_group_id));
			for (int tid : data.types) {
				Type type = idtoType.get(tid);
				if (type.getMarketGroup() != null && !mg.getId().equals(type.getMarketGroup().getId())) {
					log.warn("type {} marketgroup changed from {}({}) to {}({})", type.name(), type.getMarketGroup().name(),
					    type.getMarketGroup().getId(), mg.name(), mg.getId());
				}
				type.setMarketGroup(mg);
			}
		}
		typeService.saveAll(idtoType.values());
	}

	// use

	/** list of market groups that have no parent (the roots) */
	@Cacheable("MarketGroupRoot")
	public List<MarketGroup> roots() {
		return repo().findByParentNull();
	}

	// cache

	public static interface MarketGroupListener extends EntityUpdateListener {
	}

	@Getter
	@Lazy
	private final Optional<List<MarketGroupListener>> listeners;

	@Getter(lazy = true)
	private final boolean selfInvalidate = true;

	@Getter(lazy = true)
	private final List<String> cacheList = List.of(
	    "MarketGroupRoot");

}
