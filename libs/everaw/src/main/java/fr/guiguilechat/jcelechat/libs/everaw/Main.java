package fr.guiguilechat.jcelechat.libs.everaw;

import fr.guiguilechat.jcelechat.libs.everaw.meta.EveClientInfo;
import fr.guiguilechat.jcelechat.libs.everaw.meta.EveIndex;

public class Main {

	public static void main(String[] args) {
		EveClientInfo ec = EveClientInfo.fetch();
		System.err.println("version " + ec.buildNumber());
		EveIndex idx = ec.rootIndex();
		System.err.println("loaded " + idx.streamResourceNames().size() + " indexes");
	}

}
