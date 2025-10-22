package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.effect;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
		@Index(columnList = "type_id")
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeEffect {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private int effectId;
	private int typeId;
	private boolean isDefault;

	public static TypeEffect of(int effectId, int typeId, boolean isDefault) {
		return builder()
				.effectId(effectId)
				.typeId(typeId)
				.isDefault(isDefault)
				.build();
	}

}
