package fr.guiguilechat.jcelechat.jcesi.connected.modeled.assets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Assets;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_type;

/**
 * an {@link R_get_corporations_corporation_id_assets} that also knows its
 * children
 *
 */
public class ItemNode extends R_get_corporations_corporation_id_assets {

	public Map<get_corporations_corporation_id_assets_location_flag, List<ItemNode>> contained = new HashMap<>();
	public int groupId;
	public String typeName;

	public ItemNode(boolean is_blueprint_copy, boolean is_singleton, long item_id,
			get_corporations_corporation_id_assets_location_flag location_flag,
			long location_id,
			get_corporations_corporation_id_assets_location_type location_type,
			int quantity,
			int type_id,
			String typeName,
			int groupId) {
		this.is_blueprint_copy = is_blueprint_copy;
		this.is_singleton = is_singleton;
		this.item_id = item_id;
		this.location_flag = location_flag;
		this.location_id = location_id;
		this.location_type = location_type;
		this.quantity = quantity;
		this.type_id = type_id;
		this.typeName = typeName;
		this.groupId = groupId;
	}

	public ItemNode(R_get_characters_character_id_assets source, IntUnaryOperator tid2gid,
			IntFunction<String> tid2tname) {
		this(source.is_blueprint_copy, source.is_singleton, source.item_id, Assets.convert(source.location_flag), source.location_id,
				Assets.convert(source.location_type), source.quantity, source.type_id,
				tid2tname.apply(source.type_id),
				tid2gid.applyAsInt(source.type_id));
	}

	public ItemNode(R_get_corporations_corporation_id_assets source, IntUnaryOperator tid2gid,
			IntFunction<String> tid2tname) {
		this(source.is_blueprint_copy, source.is_singleton, source.item_id, source.location_flag, source.location_id,
				source.location_type, source.quantity, source.type_id,
				tid2tname.apply(source.type_id),
				tid2gid.applyAsInt(source.type_id));
	}

	private String optional = null;

	public ItemNode withOptional(String optional) {
		this.optional = optional;
		// set to null to lazyly force recompute
		name = null;
		return this;
	}

	private transient String name = null;

	public String name() {
		if (name == null) {
			if (optional == null) {
				name = typeName;
			} else {
				name = "[" + optional + "] " + typeName;
			}
		}
		return name;
	}

	private transient Double priceAverage = null;

	public double priceAverage() {
		if (priceAverage == null) {
			if (is_blueprint_copy) {
				priceAverage = 0.0;
			} else {
				priceAverage = quantity * ESIAccess.INSTANCE.markets.getAverage(type_id);
			}
		}
		return priceAverage;
	}

	private transient Double recPriceAverage = null;

	public double recPriceAverage() {
		if (recPriceAverage == null) {
			double total = priceAverage();
			for (List<ItemNode> list : contained.values()) {
				for (ItemNode itemnode : list) {
					total += itemnode.recPriceAverage();
				}
			}
			recPriceAverage = total;
		}
		return recPriceAverage;
	}

	public void print(StringBuilder sb, String prefix, String spacing, String newline) {
		sb.append(prefix).append(name());
		if (quantity != 1) {
			sb.append(" ×").append(quantity);
		}
		// sb.append('[').append(item_id).append('@').append(location_id).append(']');
		prefix = prefix + spacing;
		for (Entry<get_corporations_corporation_id_assets_location_flag, List<ItemNode>> e : contained.entrySet()) {
			if (!e.getValue().isEmpty()) {
				sb.append(newline).append(prefix).append(e.getKey());
				for (ItemNode i : e.getValue()) {
					sb.append(newline);
					i.print(sb, prefix + spacing, spacing, newline);
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		print(sb, "", "\t", "\n");
		return sb.toString();
	}
}