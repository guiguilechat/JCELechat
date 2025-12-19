package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsTypeAttribute")
@Table(name = "sde_items_typeattribute", indexes = {
		@Index(columnList = "type_id"),
		@Index(columnList = "attribute_id")
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeAttribute {

	@Id
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Type type;
	@ManyToOne(fetch = FetchType.LAZY)
	private Attribute attribute;
	private BigDecimal value;

	public static long makeId(int typeId, int attributeId) {
		return (long) Integer.MAX_VALUE * typeId + attributeId;
	}

	public static TypeAttribute of(Type type, Attribute attribute, BigDecimal value) {
		return builder()
				.id(makeId(type.getId(), attribute.getId()))
				.type(type)
				.attribute(attribute)
				.value(value)
				.build();
	}

}
