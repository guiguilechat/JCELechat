package fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer;

import fr.guiguilechat.jcelechat.libs.spring.anon.lp.corp.LPCorporation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * a corporation id retrieved from
 * https://esi.evetech.net/ui/#/Corporation/get_corporations_npccorps along with
 * its last fetch date and future fetch date for
 * https://esi.evetech.net/ui/#/Loyalty/get_loyalty_stores_corporation_id_offers
 */
@Entity(name = "EsiNpcLPCorporationOffer")
@Table(name = "esi_npc_lpcorporationoffer", indexes = {
		@Index(columnList = "lp_corp_id"),
		@Index(columnList = "offer_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LinkCorporationOffer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	/** */
	@ManyToOne
	private LPCorporation lpCorp;

	@ManyToOne
	private Offer offer;

}
