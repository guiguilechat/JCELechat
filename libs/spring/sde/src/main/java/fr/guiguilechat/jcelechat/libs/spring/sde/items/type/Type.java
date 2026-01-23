package fr.guiguilechat.jcelechat.libs.spring.sde.items.type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping.MutaMapping;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup.MarketGroup;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.metagroup.MetaGroup;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.description.TypeDescription;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect.TypeEffect;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsType")
@Table(name = "sde_items_type", indexes = {
		@Index(columnList = "group_id"),
		@Index(columnList = "market_group_id"),
		@Index(columnList = "meta_group_id"),
		@Index(columnList = "published"),
		@Index(columnList = "name"),
		@Index(columnList = "variation_type_id") })
@Getter
@Setter
@NoArgsConstructor
public class Type extends SdeEntity<Integer> {


	public static final int LARGE_SKILL_INJECTOR_ID = 40520;
	public static final int PLEX_ID = 44992;
	public static final int SMALL_SKILL_INJECTOR_ID = 45635;

	@OneToMany(mappedBy = "typeId", fetch = FetchType.LAZY)
	private List<TypeAttribute> attributes = new ArrayList<>();
	private BigDecimal basePrice;
	private BigDecimal capacity;
	@OneToOne(mappedBy = "type", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private TypeDescription description;
	@OneToMany(mappedBy = "typeId", fetch = FetchType.LAZY)
	private List<TypeEffect> effects = new ArrayList<>();
	@ColumnDefault("0")
	private int factionId;
	@ColumnDefault("0")
	private int graphicId;
	@ManyToOne(fetch = FetchType.LAZY)
	private Group group;
	@ColumnDefault("0")
	private int iconId;
	@ManyToOne(fetch = FetchType.LAZY)
	private MarketGroup marketGroup;
	private BigDecimal mass;
	@ManyToOne(fetch = FetchType.LAZY)
	private MetaGroup metaGroup;
	private String name;
	@ColumnDefault("0")
	private int portionSize;
	@ColumnDefault("true")
	private boolean published;
	@ColumnDefault("0")
	private int raceId;
	private BigDecimal radius;
	@ColumnDefault("0")
	private int soundId;
	@ManyToOne(fetch = FetchType.LAZY)
	private Type variationType;
	private BigDecimal volume;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private Set<MutaMapping> mutationOf = new HashSet<>();

	/**
	 * @return the name if exists ; otherwise id description
	 */
	public String name() {
		if (name != null) {
			return name;
		}
		return "type:" + getId();
	}

	public String nameId() {
		StringBuilder ret = new StringBuilder(name == null ? "unknown" : name);
		ret.append(" (").append(getId()).append(")");
		return ret.toString();
	}

	@Override
	public String toString() {
		return name == null ? "type:" + getId() : name + "(" + getId() + ")";
	}

	public void update(Etypes source,
			Function<Integer, Type> types,
			Function<Integer, Group> groups,
			Function<Integer, MarketGroup> marketGroups,
			Function<Integer, MetaGroup> metaGroups) {
		receivedSource();
		setBasePrice(source.basePrice);
		setCapacity(source.capacity);
		setDescription(new TypeDescription(null, this, source.enDescription()));
		setFactionId(source.factionID);
		setGraphicId(source.graphicID);
		if (source.groupID > 0) {
			setGroup(groups.apply(source.groupID));
		}
		setIconId(source.iconID);
		if (source.marketGroupID > 0) {
			setMarketGroup(marketGroups.apply(source.marketGroupID));
		}
		setMass(source.mass);
		if (source.metaGroupID > 0) {
			setMetaGroup(metaGroups.apply(source.metaGroupID));
		}
		setName(source.enName());
		setPortionSize(source.portionSize);
		setPublished(source.published);
		setRaceId(source.raceID);
		setRadius(source.radius);
		setSoundId(source.soundID);
		if (source.variationParentTypeID != null && source.variationParentTypeID > 0) {
			setVariationType(types.apply(source.variationParentTypeID));
		}
		setVolume(source.volume);
	}

}
