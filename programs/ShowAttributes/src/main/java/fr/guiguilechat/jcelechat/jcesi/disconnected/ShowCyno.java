package fr.guiguilechat.jcelechat.jcesi.disconnected;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import fr.guiguilechat.jcelechat.model.sde.types.module.CynosuralFieldGenerator;

public class ShowCyno {

	public static void main(String[] args) {
		Map<Integer, Ship> id2ship = Ship.METACAT.load().values().stream().collect(Collectors.toMap(e -> e.id, e -> e));
		Map<Integer, IMetaGroup<? extends Ship>> id2group = Ship.METACAT.groups()
				.stream()
				.collect(Collectors.toMap(e -> e.getGroupId(), e -> e));

		for (CynosuralFieldGenerator cfg : CynosuralFieldGenerator.METAGROUP.load().values()) {
			System.out.println(cfg.name);
			List<Ship> allowed = new ArrayList<>();
			for (int groupid : new int[] { cfg.CanFitShipGroup01, cfg.CanFitShipGroup02, cfg.CanFitShipGroup03,
					cfg.CanFitShipGroup04, cfg.CanFitShipGroup05, cfg.CanFitShipGroup06 }) {
				if (groupid != 0) {
					IMetaGroup<? extends Ship> retrieved = id2group.get(groupid);
					if (retrieved != null) {
						allowed.addAll(retrieved.load().values());
					}
				}
			}
			for (int typeId : new int[] { cfg.CanFitShipType1, cfg.CanFitShipType2, cfg.CanFitShipType3 }) {
				if (typeId != 0) {
					Ship retrieved = id2ship.get(typeId);
					if (retrieved != null) {
						allowed.add(retrieved);
					} else {
					}
				}
			}
			Collections.sort(allowed, (s1, s2) -> s1.name.compareTo(s2.name));
			for (Ship s : allowed) {
				System.out.println("\t" + s.name);
			}
		}
	}

}
