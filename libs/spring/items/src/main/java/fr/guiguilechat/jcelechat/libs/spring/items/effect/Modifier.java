package fr.guiguilechat.jcelechat.libs.spring.items.effect;

import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_effects_effect_id_modifiers;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiConnectItemEffectmodifier")
@Table(name = "esi_connect_itemeffectmodifier", indexes = {
    @Index(columnList = "effect_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Modifier {

	@Id
	@GeneratedValue
	private long id;

	/**
	 * effect_id integer
	 */
	@ManyToOne
	private Effect effect;

	/**
	 * domain string
	 */
	private String domain;

	/**
	 * func string
	 */
	private String func;

	/**
	 * modified_attribute_id integer
	 */
	@ManyToOne
	private Attribute modifiedAttribute;

	/**
	 * modifying_attribute_id integer
	 */
	@ManyToOne
	private Attribute modifyingAttribute;

	/**
	 * operator integer
	 */
	private int operator;

	public static Modifier of(get_dogma_effects_effect_id_modifiers data, Effect effect, Attribute modified,
	    Attribute modifying) {
		return builder()
		    .domain(data.domain)
		    .effect(effect)
		    .func(data.func)
		    .modifiedAttribute(modified)
		    .modifyingAttribute(modifying)
		    .build();
	}

}
