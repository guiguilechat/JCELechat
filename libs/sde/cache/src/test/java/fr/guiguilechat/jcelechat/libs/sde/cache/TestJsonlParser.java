package fr.guiguilechat.jcelechat.libs.sde.cache;

import fr.guiguilechat.jcelechat.libs.sde.cache.jsonl.JsonlCache;

public class TestJsonlParser {

	public static void main(String[] args) {
		DLResult res = JsonlCache.INSTANCE.donwloadSDE();
		System.out.println(res);
	}

}
