package fr.guiguilechat.jcelechat.libs.sde.model.locations.station;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EstationOperations;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSource;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourceLocalCache;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.DataSourced;
import fr.guiguilechat.jcelechat.libs.sde.model.cache.Mapper;
import fr.guiguilechat.jcelechat.libs.sde.model.items.Type;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class StationOperation extends DataSourced<EstationOperations> {

	public static final Mapper<EstationOperations, StationOperation> CACHE = new Mapper<>(
			EstationOperations.LOADER.yaml(),
			StationOperation::new);

	private final int activityId;
	private final BigDecimal border;
	private final BigDecimal corridor;
	private final BigDecimal fringe;
	private final BigDecimal hub;
	private final BigDecimal manufacturingFactor;
	private final BigDecimal ratio;
	private final BigDecimal researchFactor;

	public StationOperation(DataSource datasource, int id, EstationOperations source) {
		super(datasource, id, source);
		activityId = source.activityID;
		border = source.border;
		corridor = source.corridor;
		fringe = source.fringe;
		hub = source.hub;
		manufacturingFactor = source.manufacturingFactor;
		ratio = source.ratio;
		researchFactor = source.researchFactor;

	}

	protected StationOperation(int id, EstationOperations source) {
		this(DataSourceLocalCache.INSTANCE, id, source);
	}

	@Getter(lazy = true)
	private final String description = source().enDescription();

	@Getter(lazy = true)
	private final String name = source().enOperationName();

	@Getter(lazy = true)
	private final Set<StationService> services = IntStream.of(source().services)
			.mapToObj(i -> datasource().stationServices().of(i))
			.collect(Collectors.toSet());

	private final Set<String> serviceNames = services().stream().map(StationService::serviceName)
			.collect(Collectors.toSet());

	@Getter(lazy = true)
	private final Map<Integer, Type> stationTypes = source().stationTypes.entrySet().stream()
			.collect(Collectors.toMap(Entry::getKey, e -> datasource().types().of(e.getValue())));

}