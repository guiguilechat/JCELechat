package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.logparser.LogsLoader;

public class ListTypesMain {

	public static void main(String[] args) {
		GameLogsLoader gl = new LogsLoader().gameLogs();
		Set<String> types = new HashSet<>();
		gl.apply(null, log -> {
			LogLine ll = null;
			try {
				while ((ll = log.readLine()) != null) {
					types.add(ll.type);
				}
			} catch (IOException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		});
		for (String type : types) {
			System.out.println(type);
		}
	}

}
