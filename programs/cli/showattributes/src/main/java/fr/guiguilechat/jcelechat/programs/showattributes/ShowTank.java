package fr.guiguilechat.jcelechat.programs.showattributes;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;

public class ShowTank {

	public static void main(String[] args) {
		// irregular frigates, destroyers, cruisers, battlecruisers, battleships,
		// capsule, carrier
		showGroup(".*(Jarognik).*", 1568, 1664, 1665, 1666, 1667, 4053, 1726);
	}

	public static void showGroup(String nameFilter, int... groupIds) {
		// preload the group
		IntStream.of(groupIds).parallel()
				.flatMap(groupId -> IntStream.of(ESIRawPublic.INSTANCE.cache().universe.groups(groupId).get().types))
				.forEach(i -> ESIRawPublic.INSTANCE.cache().universe.types(i));
		System.out.println("name\tid\tgroup\tEMEHP\tTHEHP\tKIEHP\tEXEHP");
		for (int groupId : groupIds) {
			R_get_universe_groups_group_id group = ESIRawPublic.INSTANCE.cache().universe.groups(groupId).get();
			for (int typeId : group.types) {
				showEntity(nameFilter, typeId);
			}
		}
	}

	private static final DecimalFormat nf = new DecimalFormat("#", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

	public static void showEntity(String nameFilter, int typeId) {
		R_get_universe_types_type_id type = ESIRawPublic.INSTANCE.cache().universe.types(typeId).get();
		if (nameFilter != null && !type.name.matches(nameFilter)) {
			return;
		}
		R_get_universe_groups_group_id group = ESIRawPublic.INSTANCE.cache().universe.groups(type.group_id).get();

		Map<Integer, Double> attIdToValue = Stream
				.of(type.dogma_attributes != null ? type.dogma_attributes
						: new get_dogma_dynamic_items_type_id_item_id_dogma_attributes[0])
				.collect(Collectors.toMap(att -> att.attribute_id, att -> (double) att.value));
		// shield
		double shieldEMResonnance = attIdToValue.getOrDefault(271, 1.0);
		double shieldTHResonnance = attIdToValue.getOrDefault(274, 1.0);
		double shieldKIResonnance = attIdToValue.getOrDefault(273, 1.0);
		double shieldEXResonnance = attIdToValue.getOrDefault(272, 1.0);
		double shieldHP = attIdToValue.getOrDefault(263, 0.0);
		// armor
		double armorEMResonnance = attIdToValue.getOrDefault(267, 1.0);
		double armorTHResonnance = attIdToValue.getOrDefault(270, 1.0);
		double armorKIResonnance = attIdToValue.getOrDefault(269, 1.0);
		double armorEXResonnance = attIdToValue.getOrDefault(268, 1.0);
		double armorHP = attIdToValue.getOrDefault(265, 0.0);
		// hull
		double hullEMResonnance = attIdToValue.getOrDefault(113, 1.0);
		double hullTHResonnance = attIdToValue.getOrDefault(110, 1.0);
		double hullKIResonnance = attIdToValue.getOrDefault(109, 1.0);
		double hullEXResonnance = attIdToValue.getOrDefault(111, 1.0);
		double hullHP = attIdToValue.getOrDefault(9, 0.0);

		// total ehp
		double EMEHP = shieldHP / shieldEMResonnance + armorHP / armorEMResonnance + hullHP / hullEMResonnance;
		double THEHP = shieldHP / shieldTHResonnance + armorHP / armorTHResonnance + hullHP / hullTHResonnance;
		double KIEHP = shieldHP / shieldKIResonnance + armorHP / armorKIResonnance + hullHP / hullKIResonnance;
		double EXEHP = shieldHP / shieldEXResonnance + armorHP / armorEXResonnance + hullHP / hullEXResonnance;

		System.out.println(type.name + "\t" + typeId + "\t" + group.name + "\t" + nf.format(EMEHP) + "\t" + nf.format(THEHP)
		+ "\t" + nf.format(KIEHP) + "\t" + nf.format(EXEHP));

	}

}
