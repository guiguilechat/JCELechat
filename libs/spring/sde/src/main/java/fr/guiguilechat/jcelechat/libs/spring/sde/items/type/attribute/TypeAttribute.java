package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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

	private int typeId;

	private int attributeId;

	private BigDecimal value;

	public static long makeId(int typeId, int attributeId) {
		return (long) Integer.MAX_VALUE * typeId + attributeId;
	}

	public static TypeAttribute of(int type_id, int attribute_id, BigDecimal value) {
		return builder()
				.id(makeId(type_id, attribute_id))
				.typeId(type_id)
				.attributeId(attribute_id)
				.value(value)
				.build();
	}

}
