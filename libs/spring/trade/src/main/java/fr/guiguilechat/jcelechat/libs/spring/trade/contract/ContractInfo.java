package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiTradeContractInfo")
@Table(name = "esi_trade_contractinfo", indexes = {
    @Index(columnList = "region_id")
})
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ContractInfo extends AFetchedList<Integer, R_get_contracts_public_items_contract_id, ContractItem> {

	@ManyToOne
	private ContractRegion region;

	@ToString.Exclude
	@OneToMany(mappedBy = "fetchResource", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ContractItem> items;

	/**
	 * set to true when the contract only requires one type, and only offers isks.
	 */
	private boolean asksOneTypeForIsks = false;

	/**
	 * set to true once the contract is 404
	 */
	private boolean canceled = false;

	/**
	 * set to true once the contract is 403
	 */
	private boolean completed = false;

	/**
	 * Buyout price (for Auctions only)
	 */

	private double buyout;
	/**
	 * Collateral price (for Couriers only)
	 */

	private double collateral;

	/**
	 * Expiration date of the contract
	 */
	private Instant dateExpired;

	/**
	 * Сreation date of the contract
	 */
	private Instant dateIssued;

	/**
	 * Number of days to perform the contract
	 */
	private int daysToComplete;

	/**
	 * End location ID (for Couriers contract)
	 */
	private long endLocationId;

	/**
	 * true if the contract was issued on behalf of the issuer's corporation
	 */
	private boolean forCorporation;

	/**
	 * Character's corporation ID for the issuer
	 */
	private int issuerCorporationId;

	/**
	 * Character ID for the issuer
	 */
	private int issuerId;

	/**
	 * number of distinct types that are asked by the contract creator
	 */
	private int nbTypesAsked = 0;

	/**
	 * number of distinct types that are included in the contract.
	 */
	private int nbTypesIncluded = 0;

	/**
	 * set to true when the contract only offers one type of blueprint that is a
	 * copy, and only asks for isks.
	 */
	private boolean offersBpcForIsk = false;

	/**
	 * set to true when the contract only offers one type and only asks for isks.
	 */
	private boolean offersOneTypeForIsk = false;

	/**
	 * Price of contract (for ItemsExchange and Auctions)
	 */
	private double price;

	/**
	 * Remuneration for contract (for Couriers only)
	 */
	private double reward;

	/**
	 * Start location ID (for Couriers contract)
	 */
	private long startLocationId;

	/**
	 * Title of the contract
	 */
	private String title;

	/**
	 * Type of the contract
	 */
	@Enumerated(EnumType.STRING)
	private get_contracts_public_region_id_type type;

	/**
	 * Volume of items in the contract
	 */
	private double volume;

	public ContractInfo update(ContractRegion region, R_get_contracts_public_region_id line) {
		setRegion(region);

		setBuyout(line.buyout);
		setCollateral(line.collateral);
		setDateExpired(ESITools.fieldInstant(line.date_expired));
		setDateIssued(ESITools.fieldInstant(line.date_issued));
		setDaysToComplete(line.days_to_complete);
		setEndLocationId(line.end_location_id);
		setForCorporation(line.for_corporation);
		setIssuerCorporationId(line.issuer_corporation_id);
		setIssuerId(line.issuer_id);
		setPrice(line.price);
		setReward(line.reward);
		setStartLocationId(line.start_location_id);
		setTitle(line.title);
		setType(line.type);
		setVolume(line.volume);

		if (line.type != get_contracts_public_region_id_type.auction
		    && line.type != get_contracts_public_region_id_type.item_exchange) {
			setFetched(true);
			setFetchActive(false);
		}
		return this;
	}

	@Override
	public void update(R_get_contracts_public_items_contract_id[] data) {
		super.update(data);

		Set<Integer> askedTypesIds = new HashSet<>();
		Set<Integer> offeredTypesIds = new HashSet<>();
		boolean hasBPC = false;
		for (R_get_contracts_public_items_contract_id item : data) {
			if (item.is_included) {
				if (!item.is_blueprint_copy) {
					offeredTypesIds.add(item.type_id);
				}
			} else {
				askedTypesIds.add(item.type_id);
			}
			if (item.is_blueprint_copy) {
				hasBPC = true;
			}
		}
		setNbTypesAsked(askedTypesIds.size());
		setAsksOneTypeForIsks(!hasBPC && offeredTypesIds.isEmpty() && askedTypesIds.size() == 1);
		setNbTypesIncluded(offeredTypesIds.size());
		setOffersOneTypeForIsk(!hasBPC && offeredTypesIds.size() == 1 && askedTypesIds.isEmpty());
		setOffersBpcForIsk(hasBPC && offeredTypesIds.size() == 1 && askedTypesIds.isEmpty());
	}

}
