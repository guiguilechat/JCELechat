package fr.guiguilechat.jcelechat.libs.spring.npc.model;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers_required_items;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EsiLPOfferRequirement")
@Table(name = "esi_lp_offer_requirement", indexes = {
		@Index(columnList = "type_type_id"),
		@Index(columnList = "offer_id")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OfferRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private CorporationOffer offer;

	@ManyToOne
	private Type type;

	private int quantity;

	public static OfferRequirement of(R_get_loyalty_stores_corporation_id_offers_required_items item,
			CorporationOffer offer, Type type) {
		return builder()
				.offer(offer)
				.quantity(item.quantity)
				.type(type)
				.build();
	}

}
