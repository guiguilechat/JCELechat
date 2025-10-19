package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;

import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * A region is observed to list its contracts.
 */
@Entity(name = "EsiTradeContractRegion")
@Table(name = "esi_trade_contractregion", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "region_id")

})
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Order(2) // depends on regions
public class ContractRegion extends ARemoteEntity<Integer, List<R_get_contracts_public_region_id>> {

	@OneToOne
	private Region region;

	@OneToMany(mappedBy = "region")
	private List<ContractInfo> contracts = new ArrayList<>();

	/**
	 * number of contracts active on last fetch
	 */
	private int nbContracts = 0;

	@Override
	public void update(List<R_get_contracts_public_region_id> data) {
		nbContracts = data.size();
	}

}
