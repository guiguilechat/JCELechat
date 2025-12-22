package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsTypeEffect")
@Table(name = "sde_items_typeeffect", indexes = {
		@Index(columnList = "type_id"),
		@Index(columnList = "effect_id")
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeEffect {

	@Id
	private Long id;

	private int typeId;

	private int effectId;

	private boolean isDefault;

	public static long makeId(int typeId, int effectId) {
		return (long) Integer.MAX_VALUE * typeId + effectId;
	}

	public static TypeEffect of(int type_id, int effect_id, boolean isDefault) {
		return builder()
				.id(makeId(type_id, effect_id))
				.typeId(type_id)
				.effectId(effect_id)
				.isDefault(isDefault)
				.build();
	}

}
