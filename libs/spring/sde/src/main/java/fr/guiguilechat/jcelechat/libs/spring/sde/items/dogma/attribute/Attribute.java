package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute;

import java.math.BigDecimal;
import java.util.function.Function;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaAttributes;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.category.AttributeCategory;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.unit.Unit;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsDogmaAttribute")
@Table(name = "sde_items_dogmaattribute", indexes = {
    @Index(columnList = "displayName"),
    @Index(columnList = "name"),
    @Index(columnList = "published") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Attribute extends SdeEntity<Integer> {

	@ManyToOne
	private AttributeCategory category;
	@ManyToOne
	private Attribute chargeRechargeTime;
	private int dataType;
	private BigDecimal defaultValue;
	@Lob
	private String description;
	private String displayName;
	private boolean displayWhenZero;
	private boolean highIsGood;
	private int iconId;
	@ManyToOne
	private Attribute max;
	@ManyToOne
	private Attribute min;
	private String name;
	private boolean published;
	private boolean stackable;
	@ManyToOne
	private Unit unit;

	public void update(EdogmaAttributes source,
			Function<Integer, Attribute> attributes,
			Function<Integer, AttributeCategory> attributecategories,
			Function<Integer, Unit> units) {
		receivedSource();
		setCategory(attributecategories.apply(source.attributeCategoryID));
		setChargeRechargeTime(attributes.apply(source.chargeRechargeTimeID));
		setDataType(source.dataType);
		setDefaultValue(source.defaultValue);
		setDescription(source.description);
		setDisplayName(source.enDisplayName());
		setDisplayWhenZero(source.displayWhenZero);
		setHighIsGood(source.highIsGood);
		setIconId(source.iconID);
		setMax(attributes.apply(source.maxAttributeID));
		setMin(attributes.apply(source.minAttributeID));
		setName(source.name);
		setPublished(source.published);
		setStackable(source.stackable);
		setUnit(units.apply(source.unitID));
	}

}
