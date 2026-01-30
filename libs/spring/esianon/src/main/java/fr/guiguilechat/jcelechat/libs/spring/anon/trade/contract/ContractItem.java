package fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.AFetchedListElementAutoId;
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
@Table(name = "esi_trade_contractitem", indexes = {
    @Index(columnList = "fetch_resource_id"),
    @Index(columnList = "typeId,included")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ContractItem extends AFetchedListElementAutoId<ContractItem, ContractInfo> {

	@ManyToOne
	private Type type;

	/**
	 * is_blueprint_copy boolean . true for BPC, false otherwise
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
	 * Material Efficiency Level of the blueprint. 0 for non-bp
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
	 * Number of runs remaining if the blueprint is a copy, 0 if it is an original
	 * or non-bp
	 */
	private int runs;

	/**
	 * Time Efficiency Level of the blueprint. 0 for non-bp
	 */
	private int timeEfficiency;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (quantity > 1) {
			sb.append(quantity).append("× ");
		}
		sb.append(type.name());
		if (blueprintCopy || materialEfficiency > 0 || timeEfficiency > 0) {
			sb.append(" (");
			sb.append(materialEfficiency).append("/").append(timeEfficiency);
			if (runs > 0) {
				sb.append(" ").append(runs).append("runs");
			}
			sb.append(")");
		}
		return sb.toString();
	}

	public static ContractItem of(ContractInfo contract, Type type, R_get_contracts_public_items_contract_id line) {
		ContractItem ret = builder()
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
		ret.setFetchResource(contract);
		return ret;
	}

}
