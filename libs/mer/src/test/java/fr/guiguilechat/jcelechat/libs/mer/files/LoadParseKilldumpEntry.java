package fr.guiguilechat.jcelechat.libs.mer.files;

import fr.guiguilechat.jcelechat.libs.mer.MER;
import fr.guiguilechat.jcelechat.libs.mer.MERFetcher;
import fr.guiguilechat.jcelechat.libs.mer.MERFetcher.MERFetch;

public class LoadParseKilldumpEntry {

	public static void main(String[] args) {
		MERFetch merfetch = MERFetcher.INSTANCE.forDate(MERFetcher.DATE_DEFAULTSTART);
		MER mer = new MER(merfetch).load();
		KillDumpEntry kde = mer.getKillDumpEntries().get(0);
		System.out.println(kde.getKillDate());
	}

}
