package fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.libs.spring.anon.character.information.CharacterInformation;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.AFetchedList;
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
	@Index(columnList = "fetch_active,fetch_priority,expires"),
    @Index(columnList = "fetched,removed,requestsItem,offersNonBpc,offersItem"),
    @Index(columnList = "region_id"),
    @Index(columnList = "secHigh"),
    @Index(columnList = "secLow"),
    @Index(columnList = "secNull"),
    @Index(columnList = "offersOneTypeForIsk,offeredTypeId,offeredCopy,offeredMe,offeredTe,offeredQuantity,offeredRuns"),
    @Index(columnList = "removedBefore"),

})
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ContractInfo extends AFetchedList<Integer, R_get_contracts_public_items_contract_id, ContractItem> {

	@ManyToOne(fetch = FetchType.LAZY)
	private ContractRegion region;

	@ToString.Exclude
	@OneToMany(mappedBy = "fetchResource", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ContractItem> items;

	//
	// retrieved from region listing
	//

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
	 * Character ID for the issuer
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CharacterInformation issuer;

	/**
	 * Character's corporation ID for the issuer
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporationInfo issuerCorporation;

	/**
	 * Price of contract (for ItemsExchange and Auctions)
	 */
	private double price;

	/**
	 * Remuneration for contract (for Couriers only)
	 */
	private double reward;

	public enum STATUS {
		created,
		created_need_fetch,
		fetched,
		removed_need_fetch,
		expired,
		completed,
		canceled
	}

	/**
	 * status of the fetch of the contract
	 */
	@Enumerated(EnumType.STRING)
	private STATUS status = STATUS.created;

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

	public ContractInfo updateContract(ContractRegion region,
			R_get_contracts_public_region_id fetched,
			Function<Integer, CharacterInformation> getCharacter,
			Function<Integer, CorporationInfo> getCorporation) {
		setRegion(region);

		setBuyout(fetched.buyout);
		setCollateral(fetched.collateral);
		setDateExpired(ESIDateTools.fieldInstant(fetched.date_expired));
		setDateIssued(ESIDateTools.fieldInstant(fetched.date_issued));
		setDaysToComplete(fetched.days_to_complete);
		setEndLocationId(fetched.end_location_id);
		setForCorporation(fetched.for_corporation);
		setIssuerCorporation(getCorporation.apply(fetched.issuer_corporation_id));
		setIssuer(getCharacter.apply(fetched.issuer_id));
		setPrice(fetched.price);
		setReward(fetched.reward);
		setStartLocationId(fetched.start_location_id);
		setText(new ContractInfoText(null, this, fetched.title));
		setType(fetched.type);
		setVolume(fetched.volume);

		if (fetched.type != get_contracts_public_region_id_type.auction
		    && fetched.type != get_contracts_public_region_id_type.item_exchange) {
			setFetched(true);
			setFetchActive(false);
		}
		return this;
	}

	public void updateStatus() {
		setStatus(makeStatus());
	}

	/**
	 * @return the new status to set, based on the flags
	 */
	protected STATUS makeStatus() {
		if (!isFetched()) {
			return getType().equals(get_contracts_public_region_id_type.item_exchange)
			    ? STATUS.created_need_fetch
			    : STATUS.created;
		}
		if (!isRemoved()) {
			return STATUS.fetched;
		}
		if (isFetchActive()) {
			return STATUS.removed_need_fetch;
		}
		if (isCanceled()) {
			return STATUS.canceled;
		}
		if (expired) {
			return STATUS.expired;
		} else {
			return STATUS.completed;
		}
	}

	//
	// post region retrieval analyzis
	//

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

	public ContractInfo updateSystem(Map<Long, SolarSystem> stationId2SolarSystem) {
		SolarSystem solSys = stationId2SolarSystem.get(startLocationId);
		if (solSys != null) {
			float ss = solSys.getSecurityStatus().floatValue();
			secHigh = 0.45 < ss;
			secLow = 0 < ss && ss <= 0.45;
			secHigh = ss <= 0;
		} else {
			secHigh = secLow = secNull = false;
		}
		return this;
	}

	//
	// post item retrieval analyzis
	//

	/**
	 * short for !offersItem && nbTypesAsked==1
	 */
	@ColumnDefault("false")
	private boolean asksOneTypeForIsk = false;

	/**
	 * when the contract requests only one type, the quantity requested
	 */
	private Long askedQuantity = null;

	/**
	 * when the contract only requests one type, the id of that type
	 */
	private Integer askedTypeId;

	/**
	 * number of distinct types that are asked by the contract creator
	 */
	private int nbTypesAsked = 0;

	/**
	 * number of distinct types that are included in the contract. those with
	 * different (iscopy, me, te) counts as different types
	 */
	private int nbTypesIncluded = 0;

	/**
	 * when the contract provides only one type, is the item provided a copy? false
	 * for non-bpc
	 */
	private Boolean offeredCopy = null;

	/**
	 * when the contract provides only one type, the ME of the item provided. 0 for
	 * non-BP
	 */
	private Integer offeredMe = null;

	/**
	 * when the contract provides only one type, the quantity provided
	 */
	private Long offeredQuantity = null;

	/**
	 * when the contract provides only one type, the runs of the item provided. 0 if
	 * non-bp or BPO
	 */
	private Long offeredRuns = null;

	/**
	 * when the contract provides only one type, the TE of the item provided. 0 for
	 * non-bp
	 */
	private Integer offeredTe = null;

	/**
	 * when the contract provides only one type, the type id provided
	 */
	private Integer offeredTypeId = null;

	/**
	 * set to true when the contract contains at least one provided item that is a
	 * copy
	 */
	@ColumnDefault("false")
	private boolean offersBpc = false;

	/**
	 * short for !requestsItem && nbTypesIncluded==1 && offersBPC
	 */
	@ColumnDefault("false")
	private boolean offersBpcForIsk = false;

	/**
	 * set to true when the contract contains at least one provided item
	 */
	@ColumnDefault("false")
	private boolean offersItem = false;

	/**
	 * set to true when the contract contains at least one provided non-BPC item
	 */
	@ColumnDefault("false")
	private boolean offersNonBpc = false;

	/**
	 * short for !requestsItem && nbTypesIncluded==1 && !offersBPC
	 */
	@ColumnDefault("false")
	private boolean offersOneTypeForIsk = false;

	/**
	 * set to true when the contract requests at least one item
	 */
	@ColumnDefault("false")
	private boolean requestsItem = false;

	public static record ItemHash(int typeId, boolean iscopy, int me, int te) {
		public static ItemHash of(R_get_contracts_public_items_contract_id item) {
			return new ItemHash(item.type_id, item.is_blueprint_copy, item.material_efficiency, item.time_efficiency);
		}

		public static ItemHash of(ContractItem item) {
			return new ItemHash(item.getType().getId(), item.isBlueprintCopy(), item.getMaterialEfficiency(),
			    item.getTimeEfficiency());
		}
	}

	@Override
	public void update(R_get_contracts_public_items_contract_id[] retrievedItems) {
		super.update(retrievedItems);
		Map<Integer, Long> askedItemQuantity = new HashMap<>();
		Map<ItemHash, Long> includedItemQuantity = new HashMap<>();
		boolean offersBpc = false;
		boolean offersNonBpc = false;
		long offeredRuns = 0L;
		for (R_get_contracts_public_items_contract_id item : retrievedItems) {
			if (item.is_included) {
				ItemHash itemHash = ItemHash.of(item);
				includedItemQuantity.put(itemHash, item.quantity + includedItemQuantity.getOrDefault(itemHash, 0L));
				if (item.is_blueprint_copy) {
					offersBpc = true;
					offeredRuns += item.runs;
				} else {
					offersNonBpc = true;
				}
			} else {
				askedItemQuantity.put(item.type_id, item.quantity + askedItemQuantity.getOrDefault(item.type_id, 0L));
			}
		}
		updateFromAnalyzis(includedItemQuantity, askedItemQuantity, offersBpc, offersNonBpc, offeredRuns);
	}

	protected void updateFromAnalyzis(
	    Map<ItemHash, Long> includedItemQuantity,
	    Map<Integer, Long> askedItemQuantity,
	    boolean offersBpc,
	    boolean offersNonBpc,
	    long offeredRuns) {
		setNbTypesIncluded(includedItemQuantity.size());
		setOffersOneTypeForIsk(!offersBpc && includedItemQuantity.size() == 1 && askedItemQuantity.isEmpty());
		if (includedItemQuantity.size() == 1) {
			ItemHash itemHash = includedItemQuantity.keySet().stream().findAny().get();
			setOfferedCopy(itemHash.iscopy());
			setOfferedMe(itemHash.me());
			setOfferedQuantity(includedItemQuantity.values().stream().findAny().get());
			setOfferedRuns(offeredRuns < 0 ? -1 : offeredRuns);
			setOfferedTe(itemHash.te);
			setOfferedTypeId(itemHash.typeId());
		}
		setOffersBpc(offersBpc);
		setOffersBpcForIsk(offersBpc && !offersNonBpc && includedItemQuantity.size() == 1 && askedItemQuantity.isEmpty());
		setOffersItem(!includedItemQuantity.isEmpty());
		setOffersNonBpc(offersNonBpc);

		setNbTypesAsked(askedItemQuantity.size());
		setAsksOneTypeForIsk(includedItemQuantity.isEmpty() && askedItemQuantity.size() == 1);
		if (askedItemQuantity.size() == 1) {
			Entry<Integer, Long> e = askedItemQuantity.entrySet().stream().findAny().get();
			setAskedTypeId(e.getKey());
			setAskedQuantity(e.getValue());
		}
		setRequestsItem(!askedItemQuantity.isEmpty());
	}

	public void reAnalize() {
		Map<Integer, Long> askedItemQuantity = new HashMap<>();
		Map<ItemHash, Long> includedItemQuantity = new HashMap<>();
		boolean offersBpc = false;
		boolean offersNonBpc = false;
		long offeredRuns = 0L;
		for (ContractItem item : getItems()) {
			if (item.isIncluded()) {
				ItemHash itemHash = ItemHash.of(item);
				includedItemQuantity.put(itemHash, item.getQuantity() + includedItemQuantity.getOrDefault(itemHash, 0L));
				if (item.isBlueprintCopy()) {
					offersBpc = true;
					offeredRuns += item.getRuns();
				} else {
					offersNonBpc = true;
				}
			} else {
				askedItemQuantity.put(item.getType().getId(),
				    item.getQuantity() + askedItemQuantity.getOrDefault(item.getType().getId(), 0L));
			}
		}
		updateFromAnalyzis(includedItemQuantity, askedItemQuantity, offersBpc, offersNonBpc, offeredRuns);

	}

	//
	// post removal analyzis
	//

	/**
	 * first time that contract was removed. It may have been accepted or canceled
	 * before that.
	 */
	private Instant removedBefore;

	/**
	 * set to true once the contract is 404(canceled)
	 */
	@ColumnDefault("false")
	private boolean canceled = false;

	/**
	 * set to true once the contract is 403 and dateexpired is not elapsed
	 * (accepted)
	 */
	@ColumnDefault("false")
	private boolean completed = false;

	/**
	 * set to true once the contract is 403 and dateexpired is elapsed (expired)
	 */
	@ColumnDefault("false")
	private boolean expired = false;

	//
	//

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
