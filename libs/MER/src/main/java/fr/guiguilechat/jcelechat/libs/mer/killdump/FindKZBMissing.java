package fr.guiguilechat.jcelechat.libs.mer.killdump;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;

public class FindKZBMissing {

	public static class Diff {
		public int excessZK = 0;
		public int missingZK = 0;
	}

	public static void main(String[] args) throws InterruptedException {
		int year = 2022;
		int month = 12;
		LinkedHashMap<Integer, EtypeIDs> types = EtypeIDs.load();
		Map<Integer, Map<Integer, Map<Integer, List<KDEntry>>>> grouped = KDParser.INSTANCE
				.groupMonthBySysTypeDay(
						kde -> kde.getSolarSystem() != null && kde.getSolarSystem().isHS()
						&& kde.getSolarSystem().constellation().region.equals("Domain"),
						year, month);
		Map<Integer, Diff> diffByType = new HashMap<>();
		for (Entry<Integer, Map<Integer, Map<Integer, List<KDEntry>>>> e : grouped.entrySet()) {
			int sys = e.getKey();
			fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem ss = fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem
					.getSystem(sys);
			for (Entry<Integer, Map<Integer, List<KDEntry>>> e2 : e.getValue().entrySet()) {
				int type_id = e2.getKey();
				EtypeIDs etype = types.get(type_id);
				ZKEntry[] zkills = fetchKills(year, month, sys, type_id);
				Thread.sleep(1000);
				long nbkills = e2.getValue().values().stream().flatMap(l -> l.stream()).count();
				if (nbkills != zkills.length) {
					int excess = (int) (zkills.length - nbkills);
					System.out
							.println("" + year + "-" + month + "/" + ss.name + "/" + (etype == null ? "t_" + type_id : etype.enName())
									+ "\tmer=" + nbkills + "\tzk=" + zkills.length + "\turl="
							+ makeZKURL(year, month, sys, type_id));
					Diff diff = diffByType.computeIfAbsent(type_id, i -> new Diff());
					if (excess > 0) {
						diff.excessZK += excess;
					} else {
						diff.missingZK -= excess;
					}
				}
			}
		}
		System.out.println("type\tzk\tmer");
		for (Entry<Integer, Diff> e : diffByType.entrySet()) {
			int type_id = e.getKey();
			EtypeIDs etype = types.get(type_id);
			Diff diff = e.getValue();
			System.out.println(
					(etype == null ? "t_" + type_id : etype.enName()) + "\t" + diff.excessZK + "\t"
							+ diff.missingZK);
		}
	}

	public static class ZKEntry {
		public int killmail_id;

		public static class ZKB {
			public long locationID;
			public String hash;
			public double fittedValue;
			public double droppedValue;
			public double destroyedValue;
			public double totalValue;
			public double points;
			public boolean npc, solo, awox;
		}

		public ZKB zkb;
	}

	public static ZKEntry[] fetchKills(int year, int month, int systemid, int typeid) {
		String url = String.format(
				"https://zkillboard.com/api/losses/shipTypeID/%d/year/%04d/month/%02d/solarSystemID/%d/npc/0/",
				typeid, year, month, systemid);
		try {
			return new ObjectMapper().readValue(new URL(url), ZKEntry[].class);
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public static String makeZKURL(int year, int month, int systemid, int typeid) {
		return String.format("https://zkillboard.com/system/%d/losses/shipTypeID/%d/year/%04d/month/%02d/", systemid,
				typeid, year, month);
	}

}
