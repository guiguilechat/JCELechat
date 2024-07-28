package fr.guiguilechat.jcelechat.libs.spring.npc.lp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
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

@Entity(name = "EsiNpcLpCorporation")
@Table(name = "esi_npc_lpcorporation", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "corporationId")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObservedCorporation extends ARemoteEntity<Integer, List<R_get_loyalty_stores_corporation_id_offers>> {

	@OneToOne(optional = false)
	private CorporationInfo corporation;

	private int nbOffers = 0;

	@OneToMany(mappedBy = "observed")
	private List<LinkCorporationOffer> offers = new ArrayList<>();

	@Override
	public void update(List<R_get_loyalty_stores_corporation_id_offers> data) {
		nbOffers = data.size();
	}

}
