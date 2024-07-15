package fr.guiguilechat.jcelechat.libs.spring.trade.prices;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.basic.AFetchedResource;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiTradePrice")
@Table(name = "esi_trade_price")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price extends AFetchedResource<Integer> {

	@OneToOne
	private Type type;

	private double adjustedPrice;

	private double averagePrice;

	public static Price of(Type type, double adjustedPrice, double averagePrice) {
		Price ret = new Price(type, adjustedPrice, averagePrice);
		ret.setId(type.getId());
		return ret;
	}

}
