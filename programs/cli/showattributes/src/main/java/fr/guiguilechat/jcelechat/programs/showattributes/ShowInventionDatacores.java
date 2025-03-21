package fr.guiguilechat.jcelechat.programs.showattributes;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.Activity;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint.MaterialReq;
import fr.guiguilechat.jcelechat.model.sde.types.commodity.Datacores;

public class ShowInventionDatacores {
	public static void main(String[] args) {
		Datacores[] datacores = Datacores.METAGROUP.load().values().toArray(Datacores[]::new);
		Blueprint[] bps = Blueprint.yaml().load().values().stream().filter(bp -> bp.invention != null)
				.toArray(Blueprint[]::new);
		System.out.print("bp");
		System.out.println(
				"\t" + Stream.of(datacores).map(d -> d.name.replaceAll("Datacore - ", "")).collect(Collectors.joining("\t")));
		for (Blueprint bp : bps) {
			Activity inv = bp.invention;
			int[] reqDatacores = new int[datacores.length];
			for (MaterialReq<?> m : inv.materials) {
				for (int i = 0; i < datacores.length; i++) {
					Datacores d = datacores[i];
					if (d.id == m.id) {
						reqDatacores[i] = m.quantity;
					}
				}
			}
			System.out.print(bp.name());
			System.out.println(
					"\t" + IntStream.of(reqDatacores).mapToObj(i -> i != 0 ? "" + i : "").collect(Collectors.joining("\t")));
		}
	}

}
