package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** a sell order using contract exchange that is fetched */
@Getter
@RequiredArgsConstructor
public class ContractOrder {

	private final int contractId;

	/**
	 * sum of runs for BPC, sum of quantities for non-bpc
	 */
	private final long quantity;

	/**
	 * value requested for the contract
	 */
	private final double value;

	@Getter(lazy = true)
	private final double unitPrice = value / quantity;

	@Getter(lazy = true)
	private final String gameLink = "<url=contract:0//" + contractId + ">contract " + contractId + "</url>";

	/**
	 * should only be done using contract type exchange with fetched true
	 * 
	 * @param info
	 */
	public ContractOrder(ContractInfo info) {
		this(info.getId(), info.getOfferedCopy() ? info.getOfferedRuns() : info.getOfferedQuantity(), info.getPrice());
		if (!info.isFetched()) {
			throw new RuntimeException("contract id "+info.getId()+" is not fetched, can't make an order out of it");
		}
		if (info.getType() != get_contracts_public_region_id_type.item_exchange) {
			throw new RuntimeException("contract id "+info.getId()+" is not exchange :"+info.getType()+" , can't make an order out of it");
		}
		if (!info.isOffersItem()) {
			throw new RuntimeException(
			    "contract id " + info.getId() + " does not offer item, can't make a sell order out of it");
		}
		if (info.isRequestsItem()) {
			throw new RuntimeException("contract id " + info.getId() + " requests item, can't make a sell order out of it");
		}
		if (info.getNbTypesIncluded() != 1) {
			throw new RuntimeException("contract id " + info.getId() + " offers " + info.getNbTypesIncluded()
			    + " different items, can't make an order out of it");
		}
	}

}
