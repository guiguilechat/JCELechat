package fr.guiguilechat.jcelechat.libs.spring.trade.contract;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list.AFetchedListElement;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_contracts_public_items_contract_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiMarketContractItem")
@Table(name = "esi_market_contractitem", indexes = {
		@Index(columnList = "contract_id"),
		@Index(columnList = "typeId") })
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ContractItem extends AFetchedListElement<ContractItem, ContractInfo> {

	@ManyToOne
	private ContractInfo contract;

	@ManyToOne
	private Type type;

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

	public static ContractItem of(ContractInfo contract, Type type, R_get_contracts_public_items_contract_id line) {
		return builder()
				.contract(contract)
				.type(type)
				.blueprintCopy(line.is_blueprint_copy)
				.included(line.is_included)
				.itemId(line.item_id)
				.materialEfficiency(line.material_efficiency)
				.quantity(line.quantity)
				.recordId(line.record_id)
				.runs(line.runs)
				.timeEfficiency(line.time_efficiency)
				.build();
	}

}
