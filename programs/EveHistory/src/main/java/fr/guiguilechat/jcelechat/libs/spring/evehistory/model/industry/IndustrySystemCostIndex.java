package fr.guiguilechat.jcelechat.libs.spring.evehistory.model.industry;

import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_industry_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_industry_systems_activity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class IndustrySystemCostIndex {

	public static Stream<IndustrySystemCostIndex> streamOf(R_get_industry_systems systemIndexes,
			IndustryFetchResult fetchResult) {
		return Stream.of(systemIndexes.cost_indices).map(
				ci -> builder()
						.activity(ci.activity)
						.cost_index(ci.cost_index)
						.fetchResult(fetchResult)
						.solar_system_id(systemIndexes.solar_system_id)
						.build()
				);
	}

	@Id
	// using sequence is supposed to be faster for insert batch
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private get_industry_systems_activity activity;

	private float cost_index;

	private int solar_system_id;

	@ManyToOne
	private IndustryFetchResult fetchResult;

}
