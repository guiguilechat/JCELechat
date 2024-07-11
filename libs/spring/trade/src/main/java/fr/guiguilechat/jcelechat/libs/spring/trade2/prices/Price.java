package fr.guiguilechat.jcelechat.libs.spring.trade2.prices;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.AFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiPrice")
@Table(name = "esi_price")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price extends AFetchedResource<Type> {

	@Id
	@OneToOne
	@JoinColumn(name = "type_id")
	private Type id;

	private double adjustedPrice;

	private double averagePrice;

}
