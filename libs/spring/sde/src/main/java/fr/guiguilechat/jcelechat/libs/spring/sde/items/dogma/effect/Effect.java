package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaEffects;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaEffects.ModifierInfo;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.modifier.Modifier;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsDogmaEffect")
@Table(name = "sde_items_dogmaeffect", indexes = {
		@Index(columnList = "discharge_id"),
		@Index(columnList = "display_name"),
		@Index(columnList = "duration_id"),
		@Index(columnList = "falloff_id"),
		@Index(columnList = "name"),
		@Index(columnList = "published"),
		@Index(columnList = "range_id"),
		@Index(columnList = "tracking_speed_id") })
@Getter
@Setter
@NoArgsConstructor
public class Effect extends SdeEntity<Integer> {

	private boolean assistance;
	private String description;
	private boolean disallowAutoRepeat;
	@ManyToOne
	private Attribute discharge;
	private String displayName;
	private int distribution;
	@ManyToOne
	private Attribute duration;
	private int effectCategoryId;
	private boolean electronicChance;
	@ManyToOne
	private Attribute falloff;
	@ManyToOne
	private Attribute fittingUsageChance;
	private String guid;
	private int iconId;
	private String name;
	@ManyToOne
	private Attribute npcActivationChance;
	@ManyToOne
	private Attribute npcUsageChance;
	private boolean offensive;
	private boolean propulsionChance;
	private boolean published;
	@ManyToOne
	private Attribute range;
	private boolean rangeChance;
	@ManyToOne
	private Attribute resistance;
	@ManyToOne
	private Attribute trackingSpeed;
	private boolean warpSafe;

	/**
	 * modifiers array
	 */
	@OneToMany(mappedBy = "effect")
	private List<Modifier> modifiers;

	public void update(EdogmaEffects source,
			Function<Integer, Attribute> attributes,
			BiConsumer<Effect, ModifierInfo> infos) {
		super.receivedSource();
		setAssistance(source.isAssistance);
		setDescription(source.enDescription());
		setDisallowAutoRepeat(source.disallowAutoRepeat);
		if (source.dischargeAttributeID > 0) {
			setDischarge(attributes.apply(source.dischargeAttributeID));
		}
		setDisplayName(source.enDisplayName());
		setDistribution(source.distribution);
		if (source.durationAttributeID > 0) {
			setDuration(attributes.apply(source.durationAttributeID));
		}
		setEffectCategoryId(source.effectCategoryID);
		setElectronicChance(source.electronicChance);
		if (source.falloffAttributeID > 0) {
			setFalloff(attributes.apply(source.falloffAttributeID));
		}
		if (source.fittingUsageChanceAttributeID > 0) {
			setFittingUsageChance(attributes.apply(source.fittingUsageChanceAttributeID));
		}
		setGuid(source.guid);
		setIconId(source.iconID);
		setName(source.name);
		if (source.npcActivationChanceAttributeID > 0) {
			setNpcActivationChance(attributes.apply(source.npcActivationChanceAttributeID));
		}
		if (source.npcUsageChanceAttributeID > 0) {
			setNpcUsageChance(attributes.apply(source.npcUsageChanceAttributeID));
		}
		setOffensive(source.isOffensive);
		setPropulsionChance(source.propulsionChance);
		setPublished(source.published);
		if (source.rangeAttributeID > 0) {
			setRange(attributes.apply(source.rangeAttributeID));
		}
		setRangeChance(source.rangeChance);
		if (source.resistanceAttributeID > 0) {
			setResistance(attributes.apply(source.resistanceAttributeID));
		}
		if (source.trackingSpeedAttributeID > 0) {
			setTrackingSpeed(attributes.apply(source.trackingSpeedAttributeID));
		}
		setWarpSafe(source.isWarpSafe);
		if (source.modifierInfo != null) {
			for (ModifierInfo mi : source.modifierInfo) {
				infos.accept(this, mi);
			}
		}

	}

}
