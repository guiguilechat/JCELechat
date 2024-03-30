package fr.guiguilechat.jcelechat.libs.spring.market.model;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Entity(name = "EsiMarketRegionContractItem")
@Table(name = "esi_market_regioncontractitem", indexes = {
		@Index(columnList = "contract_id"),
		@Index(columnList = "typeId") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class RegionContractItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private RegionContract contract;

	/**
	 * is_blueprint_copy boolean
	 */
	private boolean blueprintCopy;

	/**
	 * true if the contract issuer has submitted this item with the contract, false
	 * if the isser is asking for this item in the contract
	 */
	private boolean included;

	/**
	 * Unique ID for the item being sold. Not present if item is being requested by
	 * contract rather than sold with contract
	 */
	private long itemId;

	/**
	 * Material Efficiency Level of the blueprint
	 */
	private int materialEfficiency;

	/**
	 * Number of items in the stack
	 */
	private int quantity;

	/**
	 * Unique ID for the item, used by the contract system
	 */
	private long recordId;

	/**
	 * Number of runs remaining if the blueprint is a copy, -1 if it is an original
	 */
	private int runs;

	/**
	 * Time Efficiency Level of the blueprint
	 */
	private int timeEfficiency;

	/**
	 * Type ID for item
	 */
	private int typeId;

	public static RegionContractItem of(RegionContract contract, R_get_contracts_public_items_contract_id line) {
		return builder()
				.contract(contract)
				.blueprintCopy(line.is_blueprint_copy)
				.included(line.is_included)
				.itemId(line.item_id)
				.materialEfficiency(line.material_efficiency)
				.quantity(line.quantity)
				.recordId(line.record_id)
				.runs(line.runs)
				.timeEfficiency(line.time_efficiency)
				.typeId(line.type_id)
				.build();
	}

}
