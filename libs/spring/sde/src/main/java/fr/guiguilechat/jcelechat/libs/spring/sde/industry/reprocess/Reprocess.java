package fr.guiguilechat.jcelechat.libs.spring.sde.industry.reprocess;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
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
import lombok.ToString;

@SuppressWarnings("serial")
@Entity(name = "SdeIndustryReprocess")
@Table(name = "sde_industry_reprocess", indexes = {
    @Index(columnList = "reprocessed_id"),
    @Index(columnList = "product_id") })
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reprocess implements Serializable {

	@Id
	private Long id;

	public static long makeId(int reprocessedTypeId, int productTypeId) {
		return (long) Integer.MAX_VALUE * reprocessedTypeId + productTypeId;
	}

	@ManyToOne
	private Type reprocessed;

	@ManyToOne
	private Type product;

	private int quantity;

	public static Reprocess of(Type reprocessed, Type product, int quantity) {
		return builder()
				.id(makeId(reprocessed.getId(), product.getId()))
				.reprocessed(reprocessed)
				.product(product)
				.quantity(quantity)
				.build();
	}

}
