package fr.guiguilechat.jcelechat.libs.spring.npc.model;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EsiLPOffer")
@Table(name = "esi_lp_offer")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CorporationOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	LPStoreCorporation corporation;

	@ManyToOne
	private Type type;

	private int akCost;

	private long iskCost;

	private int lpCost;

	private int offerId;

	private int quantity;

	@OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
	private List<OfferRequirement> requirements;

	public static CorporationOffer of(R_get_loyalty_stores_corporation_id_offers offer, LPStoreCorporation corporation,
			Type type) {
		return builder()
				.corporation(corporation)
				.type(type)
				.akCost(offer.ak_cost)
				.iskCost(offer.isk_cost)
				.lpCost(offer.lp_cost)
				.offerId(offer.offer_id)
				.quantity(offer.quantity)
				.build();
	}

}
