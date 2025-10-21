package fr.guiguilechat.jcelechat.libs.spring.sde.items.type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Etypes;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup.MarketGroup;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.metagroup.MetaGroup;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttribute;
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

	@OneToMany(mappedBy = "type")
	private List<TypeAttribute> attributes = new ArrayList<>();
	private BigDecimal basePrice;
	private BigDecimal capacity;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
	private TypeDescription description;
	private int factionId;
	private int graphicId;
	@ManyToOne(fetch = FetchType.LAZY)
	private Group group;
	private int iconId;
	@ManyToOne
	private MarketGroup marketGroup;
	private BigDecimal mass;
	@ManyToOne
	private MetaGroup metaGroup;
	private String name;
	private int portionSize;
	private boolean published;
	private int raceId;
	private BigDecimal radius;
	private int soundId;
	@ManyToOne
	private Type variationType;
	private BigDecimal volume;

	public String name() {
		if (name != null) {
			return name;
		}
		return "type:" + getId();
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
