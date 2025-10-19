package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.io.Serializable;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.BPActivities.ActivityDetails;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * blueprint time per activity and limit data.
 * If an activity time is &le; 0 it means this activity is not supported.
 */
@SuppressWarnings("serial")
@Entity(name = "SdeBlueprintActivity")
@Table(name = "sde_blueprint_activity", indexes = {
    @Index(columnList = "type_id,activity") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BlueprintActivity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** blueprint this activity applies to */
	@ManyToOne
	private Type type;

	@ToString.Exclude
	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Product> products;

	@ToString.Exclude
	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Material> materials;

	@Enumerated(EnumType.STRING)
	private ActivityType activity;

	private int maxProductionLimit;


	private int time;
	/**
	 * different possible activities
	 */
	public enum ActivityType {
		copying,
		invention,
		manufacturing,
		reaction,
		researchMat,
		researchTime;

		public static ActivityType from(
				fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType type) {
			return switch (type) {
				case copying -> copying;
				case invention -> invention;
				case reaction -> reaction;
				case manufacturing -> manufacturing;
				case research_material -> researchMat;
				case research_time -> researchTime;
				default -> throw new UnsupportedOperationException("not handled " + type);
			};
		}
	}

	public static BlueprintActivity of(Eblueprints bp,
			fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType at, Type type) {
		ActivityDetails act = at.of(bp.activities);
		return builder()
				.type(type)
				.activity(ActivityType.from(at))
				.maxProductionLimit(bp.maxProductionLimit)
				.time(act.time)
				.build();
	}
}
