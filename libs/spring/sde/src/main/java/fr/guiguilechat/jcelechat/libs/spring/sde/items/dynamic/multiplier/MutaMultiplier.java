package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.multiplier;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.Attribute;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.Mutaplasmid;
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

@Entity(name = "SdeItemsMutaMultiplier")
@Table(name = "sde_items_mutamultiplier", indexes = {
		@Index(columnList = "mutaplasmid_id"),
		@Index(columnList = "attribute_id")
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MutaMultiplier {

	@Id
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Attribute attribute;

	private Boolean highIsGood;

	private BigDecimal min;

	private BigDecimal max;

	@ManyToOne(fetch = FetchType.LAZY)
	private Mutaplasmid mutaplasmid;

	public static long makeId(int typeId, int attributeId) {
		return (long) Integer.MAX_VALUE * typeId + attributeId;
	}

	public static MutaMultiplier of(Mutaplasmid mutaplasmid, Attribute attribute, Boolean highIsGood, BigDecimal min,
			BigDecimal max) {
		return builder()
				.id(makeId(mutaplasmid.getId(), attribute.getId()))
				.attribute(attribute)
				.highIsGood(highIsGood)
				.min(min)
				.max(max)
				.mutaplasmid(mutaplasmid)
				.build();
	}

}
