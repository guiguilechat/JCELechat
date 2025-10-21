package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.modifier;

import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaEffects.ModifierInfo;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.Effect;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsDogmaEffectModifier")
@Table(name = "sde_items_dogmaeffectmodifier", indexes = {
    @Index(columnList = "effect_id"),
		@Index(columnList = "modified_id"),
		@Index(columnList = "modifying_id")
})
@Getter
@Setter
@NoArgsConstructor
public class Modifier {

	@Id
	@GeneratedValue
	private Long id;

	private String domain;
	@ManyToOne
	private Effect effect;
	private String func;
	@ManyToOne
	private Group group;
	@ManyToOne
	private Attribute modified;
	@ManyToOne
	private Attribute modifying;
	private int operation;
	@ManyToOne
	private Type skill;

	public static Modifier of(ModifierInfo mi, Effect e,
			Function<Integer, Group> groups,
			Function<Integer, Attribute> attributes,
			Function<Integer, Type> types) {
		Modifier ret = new Modifier();
		ret.setDomain(mi.domain);
		ret.setEffect(e);
		ret.setFunc(mi.func);
		if (mi.groupID > 0) {
			ret.setGroup(groups.apply(mi.groupID));
		}
		if (mi.modifiedAttributeID > 0) {
			ret.setModified(attributes.apply(mi.modifiedAttributeID));
		}
		if (mi.modifyingAttributeID > 0) {
			ret.setModifying(attributes.apply(mi.modifyingAttributeID));
		}
		ret.setOperation(mi.operation);
		if (mi.skillTypeID > 0) {
			ret.setSkill(types.apply(mi.skillTypeID));
		}
		return ret;
	}

}
