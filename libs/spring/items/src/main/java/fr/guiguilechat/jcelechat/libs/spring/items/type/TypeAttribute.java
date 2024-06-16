package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
	private long id;

	@ManyToOne
	private Attribute attribute;

	@ManyToOne
	private Type type;

	/**
	 * can't use "value" as it bugs
	 */
	@Column(name = "value_")
	private BigDecimal value;

}
