package fr.guiguilechat.jcelechat.libs.spring.npc.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * a corporation id retrieved from
 * https://esi.evetech.net/ui/#/Corporation/get_corporations_npccorps along with
 * its last fetch date and future fetch date for
 * https://esi.evetech.net/ui/#/Loyalty/get_loyalty_stores_corporation_id_offers
 */
@Entity(name = "EsiLPCorporation")
@Table(name = "esi_lp_corporation")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LPStoreCorporation {

	@Id
	private int corporationId;

	/**
	 * last etag received for that corporation lp fetch
	 */
	private String lastEtag;

	/**
	 * after when should we fetch it again ?
	 */
	private Instant nextFetch;

	private Instant lastFetch;

	/**
	 * message of the last error retrieved when response not in (200, 304)
	 */
	@Column(length = 5000)
	private String lastError;

	/**
	 * set to true when the corporations path does not return it anymore.
	 */
	@Builder.Default
	private boolean disabled = false;

}
