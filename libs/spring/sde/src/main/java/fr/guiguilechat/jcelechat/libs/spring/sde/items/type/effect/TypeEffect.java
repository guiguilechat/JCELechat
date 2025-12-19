package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.effect.Effect;
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
	@ManyToOne(fetch = FetchType.LAZY)
	private Type type;
	@ManyToOne(fetch = FetchType.LAZY)
	private Effect effect;
	private boolean isDefault;

	public static long makeId(int typeId, int effectId) {
		return (long) Integer.MAX_VALUE * typeId + effectId;
	}

	public static TypeEffect of(Type type, Effect effect, boolean isDefault) {
		return builder()
				.id(makeId(type.getId(), effect.getId()))
				.type(type)
				.effect(effect)
				.isDefault(isDefault)
				.build();
	}

}
