package fr.guiguilechat.jcelechat.libs.spring.anon.industry.indices;

import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.anon.universe.SystemPeriodEndKey;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems_cost_indices;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiIndustryCostIndices")
@Table(name = "esi_industry_costindices", indexes = {
		@Index(columnList = "solar_system_id, date"),
		@Index(columnList = "periodEnd")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@IdClass(SystemPeriodEndKey.class)
public class SystemCostIndices {

	@Id
	private int solarSystemId;

	/**
	 * the last-modified of the fetched resource
	 */
	@Id
	private Instant periodEnd;

	/**
	 * deduced from last-modified and expire as lm-(expired-lm)
	 */
	private Instant periodStart;

	/**
	 * median of last-modified and previous date
	 */
	private Instant date;

	private float copying;

	private float duplicating;

	private float invention;

	private float manufacturing;

	private float reaction;

	private float me;

	private float te;

	private float tech;

	private float engineering;

	public static SystemCostIndices of(R_get_industry_systems source, Instant start, Instant mid, Instant end) {
		SystemCostIndicesBuilder builder =
				builder()
				.solarSystemId(source.solar_system_id)
				.periodStart(start)
				.date(mid)
				.periodEnd(end)
		;
		for (R_get_industry_systems_cost_indices i : source.cost_indices) {
			switch (i.activity) {
			case copying:
				builder.copying(i.cost_index);
				break;
			case duplicating:
				builder.duplicating(i.cost_index);
				break;
			case invention:
				builder.invention(i.cost_index);
				break;
			case manufacturing:
				builder.manufacturing(i.cost_index);
				break;
			case reaction:
				builder.reaction(i.cost_index);
				break;
			case researching_material_efficiency:
				builder.me(i.cost_index);
				break;
			case researching_technology:
				builder.tech(i.cost_index);
				break;
			case researching_time_efficiency:
				builder.te(i.cost_index);
				break;
			case reverse_engineering:
				builder.engineering(i.cost_index);
				break;
			default: // do nothing
			}
		}
		return builder.build();
	}

}
