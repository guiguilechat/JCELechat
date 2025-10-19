package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiItemsTypeAttribute")
@Table(name = "esi_items_typeattribute", indexes = {
    @Index(columnList = "type_id"),
    @Index(columnList = "attribute_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TypeAttribute {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToOne
	private Attribute attribute;

	@ManyToOne
	private Type type;

	/**
	 * can't use "value" as it bugs
	 */
	@Lob
	private BigDecimal value;

}
