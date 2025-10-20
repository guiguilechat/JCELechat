package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsTypeAttribute")
@Table(name = "sde_items_typeattribute", indexes = {
    @Index(columnList = "type_id"),
    @Index(columnList = "attribute_id")
})
@NoArgsConstructor
@Getter
@Setter
public class TypeAttribute extends SdeEntity<Long> {

	@ManyToOne
	private Attribute attribute;
	@ManyToOne
	private Type type;
	private BigDecimal value;

	public static long id(int typeId, int attributeId) {
		return (long) Integer.MAX_VALUE * typeId + attributeId;
	}

}
