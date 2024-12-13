package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import fr.guiguilechat.tools.FormatTools;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiTradeContractInfo")
@Table(name = "esi_trade_contractinfo", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "fetched,removed,requestsItem,offersNonBpc,offersItem"),
    @Index(columnList = "region_id"),
    @Index(columnList = "secHigh"),
    @Index(columnList = "secLow"),
    @Index(columnList = "secNull"),

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
	 * text elements of a contract info
	 */
	@Entity(name = "EsiTradeContractInfoText")
	@Table(name = "esi_trade_contractinfotext", indexes = {
	    @Index(columnList = "contract_id")
	})
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	public static class ContractInfoText {

		@Id
		@GeneratedValue
		private Long id;

		@OneToOne
		private ContractInfo contract;

		@Lob
		private String title;

	}

	/**
	 * text elements of the contract, in a separate table to avoid loading
	 * everything.
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
	private ContractInfoText text;

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
	 * set to true when the contract offers at least one BPC
	 */
	@ColumnDefault("false")
	private boolean offersBpc = false;

	/**
	 * set to true when the contract only offers one type of blueprint that is a
	 * copy, and only asks for isks.
	 */
	@ColumnDefault("false")
	private boolean offersBpcForIsk = false;

	/**
	 * set to true when the contract offers at least one item
	 */
	@ColumnDefault("false")
	private boolean offersItem = false;

	/**
	 * set to true when the contract offers at least one non-BPC
	 */
	@ColumnDefault("false")
	private boolean offersNonBpc = false;

	/**
	 * set to true when the contract only offers one type and only asks for isks.
	 */
	@ColumnDefault("false")
	private boolean offersOneTypeForIsk = false;

	/**
	 * Price of contract (for ItemsExchange and Auctions)
	 */
	private double price;

	/**
	 * set to true when the contract requests at least one item
	 */
	@ColumnDefault("false")
	private boolean requestsItem = false;

	/**
	 * Remuneration for contract (for Couriers only)
	 */
	private double reward;

	/**
	 * set to true if the location is a known station in HS
	 */
	@ColumnDefault("false")
	private boolean secHigh = false;

	/**
	 * set to true if the location is a known station in LS
	 */
	@ColumnDefault("false")
	private boolean secLow = false;

	/**
	 * set to true if the location is a known station in NS
	 */
	@ColumnDefault("false")
	private boolean secNull = false;

	/**
	 * Start location ID (for Couriers contract)
	 */
	private long startLocationId;

	/**
	 * Type of the contract
	 */
	@Enumerated(EnumType.STRING)
	private get_contracts_public_region_id_type type;

	/**
	 * Volume of items in the contract
	 */
	private double volume;

	public ContractInfo updateContract(ContractRegion region, R_get_contracts_public_region_id line) {
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
		setText(new ContractInfoText(null, this, line.title));
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
	public void update(R_get_contracts_public_items_contract_id[] retrievedItems) {
		super.update(retrievedItems);
		Set<Integer> askedTypesIds = new HashSet<>();
		Set<Integer> offeredTypesIds = new HashSet<>();
		offersBpc = false;
		offersItem = false;
		offersNonBpc = false;
		requestsItem = false;
		for (R_get_contracts_public_items_contract_id item : retrievedItems) {
			if (item.is_included) {
				offersItem = true;
				if (item.is_blueprint_copy) {
					offersBpc = true;
				} else {

					offersNonBpc = true;
					offeredTypesIds.add(item.type_id);
				}
			} else {
				requestsItem = true;
				askedTypesIds.add(item.type_id);
			}
		}
		setNbTypesAsked(askedTypesIds.size());
		setAsksOneTypeForIsks(!offersBpc && offeredTypesIds.isEmpty() && askedTypesIds.size() == 1);
		setNbTypesIncluded(offeredTypesIds.size());
		setOffersOneTypeForIsk(!offersBpc && offeredTypesIds.size() == 1 && askedTypesIds.isEmpty());
		setOffersBpcForIsk(offersBpc && offeredTypesIds.size() == 1 && askedTypesIds.isEmpty());
	}

	public ContractInfo updateSystem(Map<Long, SolarSystem> stationId2SolarSystem) {
		SolarSystem solSys = stationId2SolarSystem.get(startLocationId);
		if (solSys != null) {
			float ss = solSys.getSecurityStatus();
			secHigh = 0.45 < ss;
			secLow = 0 < ss && ss <= 0.45;
			secHigh = ss <= 0;
		} else {
			secHigh = secLow = secNull = false;
		}
		return this;
	}

	public String name() {
		if (type == get_contracts_public_region_id_type.item_exchange) {
			if (isOffersOneTypeForIsk()) {
				ContractItem item = items.get(0);
				String name = item.getType().name();
				if (item.getMaterialEfficiency() > 0 || item.getTimeEfficiency() > 0) {
					name += " " + item.getMaterialEfficiency() + "/" + item.getTimeEfficiency();
				}
				long quantity = items.stream().mapToLong(ContractItem::getQuantity).sum();
				return "sell " + quantity + "×" + name + " for " + FormatTools.formatPrice(price);
			} else {
				return "sell " + (items == null ? "0" : items.size()) + " items for " + FormatTools.formatPrice(price);

			}
		}
		return "" + type + ":" + getId();
	}

	public String gameLink() {
		return "<url=contract:0//" + getId() + ">contract " + getId() + "</url>";
	}

}
