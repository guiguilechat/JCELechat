package fr.guiguilechat.jcelechat.libs.spring.market.contract;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.market.regional.ObservedRegion;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_contracts_public_region_id_type;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
import lombok.ToString;

@SuppressWarnings("serial")
@Entity(name = "EsiMarketContractInfo")
@Table(name = "esi_market_contractinfo", indexes = {
		@Index(columnList = "region_region_id,removed,fetched"),
		@Index(columnList = "contractId") })
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContractInfo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private ObservedRegion region;

	@ToString.Exclude
	@OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ContractItem> items;

	/**
	 * set to true when the contract only requires one type, and only offers isks.
	 */
	@Builder.Default
	private boolean asksOneTypeForIsks = false;

	@Builder.Default
	private boolean fetched = false;

	@Builder.Default
	private boolean removed = false;

	/**
	 * Buyout price (for Auctions only)
	 */

	private double buyout;
	/**
	 * Collateral price (for Couriers only)
	 */

	private double collateral;
	/**
	 * contract_id integer
	 */

	private long contractId;

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
	@Builder.Default
	private int nbTypesAsked = 0;

	/**
	 * number of distinct types that are included in the contract.
	 */
	@Builder.Default
	private int nbTypesIncluded = 0;

	/**
	 * set to true when the contract only offers one type of blueprint that is a
	 * copy, and only asks for isks.
	 */
	@Builder.Default
	private boolean offersBpcForIsk = false;

	/**
	 * set to true when the contract only offers one type and only asks for isks.
	 */
	@Builder.Default
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

	public static ContractInfo of(ObservedRegion region, R_get_contracts_public_region_id line) {
		return builder()
				.region(region)
				.buyout(line.buyout)
				.collateral(line.collateral)
				.contractId(line.contract_id)
				.dateExpired(ESITools.fieldInstant(line.date_expired))
				.dateIssued(ESITools.fieldInstant(line.date_issued))
				.daysToComplete(line.days_to_complete)
				.endLocationId(line.end_location_id)
				.forCorporation(line.for_corporation)
				.issuerCorporationId(line.issuer_corporation_id)
				.issuerId(line.issuer_id)
				.price(line.price)
				.reward(line.reward)
				.startLocationId(line.start_location_id)
				.title(line.title)
				.type(line.type)
				.volume(line.volume)
				.build();
	}

}
