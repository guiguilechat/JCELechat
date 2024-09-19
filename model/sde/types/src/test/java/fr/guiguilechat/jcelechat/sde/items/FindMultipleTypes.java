package fr.guiguilechat.jcelechat.sde.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;

public class FindMultipleTypes {

	public static void main(String[] args) {
		Requested<Integer[]> firstPage = ESIRawPublic.INSTANCE.get_universe_types(null, null);
		Map<Integer, Requested<Integer[]>> page2Request = IntStream.rangeClosed(2, firstPage.getNbPages()).parallel()
		    .boxed()
		    .collect(Collectors.toMap(
		        p -> p,
		        p -> ESIRawPublic.INSTANCE.get_universe_types(p, null)));
		Map<Integer, List<Integer>> typeId2Pages = new HashMap<>();
		page2Request.entrySet().forEach(e -> {
			if (!e.getValue().isOk()) {
				throw new RuntimeException("page " + e.getKey() + " error" + e.getValue().getResponseCode());
			}
			for (Integer typeId : e.getValue().getOK()) {
				typeId2Pages.computeIfAbsent(typeId, i -> new ArrayList<>()).add(e.getKey());
			}
		});
		typeId2Pages.entrySet().forEach(e -> {
			if (e.getValue().size() != 1) {
				System.err.println("type " + e.getKey() + " present in pages " + e.getValue());
			}
		});
		System.out.println("loaded total " + typeId2Pages.size() + " types");
	}

}
