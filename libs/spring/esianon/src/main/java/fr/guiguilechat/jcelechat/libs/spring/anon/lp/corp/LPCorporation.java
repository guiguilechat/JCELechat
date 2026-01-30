package fr.guiguilechat.jcelechat.libs.spring.anon.lp.corp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer.LinkCorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporation;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LP corporation listed from esi's npc corporation
 * {@link ESIRawPublic#get_corporations_npccorps(java.util.Map)}
 */
@Entity(name = "EsiNpcLpCorporation")
@Table(name = "esi_npc_lpcorporation", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires"),
		@Index(columnList = "corporationId")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LPCorporation extends RemoteNumberEntity<Integer, List<R_get_loyalty_stores_corporation_id_offers>> {

	/** should have same id as this */
	@OneToOne
	private NpcCorporation corporation;

	private int nbOffers = 0;

	@OneToMany(mappedBy = "lpCorp")
	private List<LinkCorporationOffer> offers = new ArrayList<>();

	@Override
	public void update(List<R_get_loyalty_stores_corporation_id_offers> data) {
		nbOffers = data.size();
	}

}
