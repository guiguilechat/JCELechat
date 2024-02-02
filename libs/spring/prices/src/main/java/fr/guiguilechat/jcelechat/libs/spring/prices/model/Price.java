package fr.guiguilechat.jcelechat.libs.spring.prices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EsiPrice")
@Table(name = "esi_price")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Price {

	@Id
	private int typeId;

	private double adjustedPrice;

	private double averagePrice;

}
