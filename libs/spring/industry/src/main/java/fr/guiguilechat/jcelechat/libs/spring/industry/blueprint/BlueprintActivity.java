package fr.guiguilechat.jcelechat.libs.spring.industry.blueprint;

import java.io.Serializable;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.Eblueprints.BPActivities.Activity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
		@Index(columnList = "type_type_id,activity") })
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
	private ACTIVITY_TYPE activity;

	private int maxProductionLimit;


	private int time;
	/**
	 * different possible activities
	 */
	public static enum ACTIVITY_TYPE {
		copying,
		invention,
		manufacturing,
		reaction,
		researchMat,
		researchTime;

		public static ACTIVITY_TYPE from(ActivityType type) {
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

	public static BlueprintActivity of(Eblueprints bp, ActivityType at, Type type) {
		Activity act = bp.activities.activity(at);
		return builder()
				.type(type)
				.activity(ACTIVITY_TYPE.from(at))
				.maxProductionLimit(bp.maxProductionLimit)
				.time(act.time)
				.build();
	}
}
