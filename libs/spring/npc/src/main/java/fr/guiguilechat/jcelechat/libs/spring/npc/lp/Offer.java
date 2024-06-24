package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiNpcLPOffer")
@Table(name = "esi_npc_lpoffer", indexes = {
    @Index(columnList = "type_id")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Offer {

	@Id
	private int id;

	@ManyToOne
	private Type type;

	@OneToMany(mappedBy = "offer", fetch = FetchType.EAGER)
	private List<Requirement> requirements;

	private int akCost;

	private long iskCost;

	private int lpCost;

	private int quantity;

	public static Offer of(R_get_loyalty_stores_corporation_id_offers offer) {
		return builder()
		    .id(offer.offer_id)
		    .akCost(offer.ak_cost)
		    .iskCost(offer.isk_cost)
		    .lpCost(offer.lp_cost)
		    .quantity(offer.quantity)
		    .build();
	}

	public String name() {
		return "[" + (akCost > 0 ? "ak" : "") + (lpCost > 0 ? "lp" : "") + "]"
		    + (getQuantity() > 1 ? getQuantity() + "×" : "") + type.getName();
	}

	public void update(R_get_loyalty_stores_corporation_id_offers co) {
		akCost = co.ak_cost;
		iskCost = co.isk_cost;
		lpCost = co.lp_cost;
		quantity = co.quantity;
	}

}
