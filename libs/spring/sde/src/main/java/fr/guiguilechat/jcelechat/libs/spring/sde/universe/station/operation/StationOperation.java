package fr.guiguilechat.jcelechat.libs.spring.sde.universe.station.operation;

import java.math.BigDecimal;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EstationOperations;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeUniverseStationOperation")
@Table(name = "sde_universe_station_operation", indexes = {

})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class StationOperation extends SdeEntity<Integer> {

	private int activityId;
	private BigDecimal border;
	private BigDecimal corridor;
	private BigDecimal fringe;
	private BigDecimal hub;
	private BigDecimal manufacturingFactor;
	private String name;
	private BigDecimal ratio;
	private BigDecimal researchFactor;

	public void update(EstationOperations source) {
		receivedSource();
		setActivityId(source.activityID);
		setBorder(source.border);
		setCorridor(source.corridor);
		setFringe(source.fringe);
		setHub(source.hub);
		setManufacturingFactor(source.manufacturingFactor);
		setName(source.enOperationName());
		setRatio(source.ratio);
		setResearchFactor(source.researchFactor);
	}

}
