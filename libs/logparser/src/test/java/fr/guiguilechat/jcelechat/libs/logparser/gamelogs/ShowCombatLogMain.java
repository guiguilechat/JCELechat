package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.io.IOException;

import fr.guiguilechat.jcelechat.libs.logparser.LogsLoader;
import fr.guiguilechat.jcelechat.libs.logparser.gamelogs.LogLine.LOGTYPE;
import fr.guiguilechat.jcelechat.libs.logparser.gamelogs.types.CombatLine;

public class ShowCombatLogMain {

	public static void main(String[] args) {
		GameLogsLoader gl = new LogsLoader().gameLogs();
		gl.apply(null, log -> {
			LogLine ll = null;
			try {
				while ((ll = log.readLine()) != null) {
					if (ll.type == LOGTYPE.combat) {
						((CombatLine) ll).subtype();
						// System.out.println(ll.time);
					}
				}
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		});

	}

}
