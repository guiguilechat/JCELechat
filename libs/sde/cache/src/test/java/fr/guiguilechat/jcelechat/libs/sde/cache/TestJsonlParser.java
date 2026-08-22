package fr.guiguilechat.jcelechat.libs.sde.cache;

import fr.guiguilechat.jcelechat.libs.sde.cache.jsonl.JsonlCache;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EcontrabandTypes;

public class TestJsonlParser {

	public static void main(String[] args) {
		DLResult res = JsonlCache.INSTANCE.donwloadSDE();
		System.out.println(res);
		EcontrabandTypes.LOADER.jsonl().stream().forEach(jkv -> {
			System.out.println("loaded=" + jkv);
		});
	}

}
