package fr.guiguilechat.jcelechat.programs.gankhistory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_killmails_killmail_id_killmail_hash;
import fr.lelouet.tools.holders.interfaces.ObjHolder;

public class ShowHistory {

	public static class EZKB {
		public int killmail_id;

		public static class zkb {
			public int locationID;
			public String hash;
			public double fittedValue;
			public double totalValue;
			public int points;
			public boolean npc;
			public boolean solo;
			public boolean awox;
			public int involved;
		}

		public zkb zkb;

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != EZKB.class) {
				return false;
			}
			return ((EZKB) obj).killmail_id == killmail_id;
		}
	}

	protected static InputStream openStreamFor(String uri) throws IOException, InterruptedException, URISyntaxException {
		boolean zip = true;
		URL url = new URI(uri).toURL();
		// System.err.println("url = " + url.toString());
		HttpURLConnection uc = (HttpURLConnection) url.openConnection();
		if (zip) {
			uc.addRequestProperty("Accept-Encoding", "gzip");
		}
		uc.connect();
		int retry = 0;
		while (uc.getResponseCode() != 200) {
			Thread.sleep(2000);
			retry++;
			System.err.println("retry " + retry + " for " + uri + " got response " + uc.getResponseCode());
			uc = (HttpURLConnection) url.openConnection();
			if (zip) {
				uc.addRequestProperty("Accept-Encoding", "gzip");
			}
			uc.connect();
		}
		InputStream is = uc.getInputStream();
		if (zip) {
			is = new GZIPInputStream(is);
		}
		return is;
	}

	private static final ArrayType RETURN_TYPE = TypeFactory.defaultInstance().constructArrayType(EZKB.class);

	public static List<EZKB> lossesForGroup(int groupID, int year, int month)
	    throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {
		List<EZKB> ret = lossesForGroup(groupID, year, month, 1);
		ret.removeIf(km -> km.zkb.awox);
		return ret;
	}

	public static List<EZKB> lossesForGroup(int groupID, int year, int month, int page)
	    throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {
		String uri = "https://zkillboard.com/api/losses/highsec/groupID/" + groupID + "/year/" + year + "/month/" + month
				+ "/npc/0/page/" + page + "/";
		InputStream is = openStreamFor(uri);
		List<EZKB> ret = new ArrayList<>(Arrays.asList(new ObjectMapper().readValue(is, RETURN_TYPE)));
		ret.forEach(ShowHistory::fetch);
		if (ret.size() >= 200) {
			if (page < 10) {
				// System.err.println("fetching next page after page " + page);
				ret.addAll(lossesForGroup(groupID, year, month, page + 1));
			} else {
				System.out.println(uri + " can't load next page");
			}
		}
		return ret;
	}

	public static List<EZKB> killsForType(int typeId, int year, int month)
	    throws IOException, InterruptedException, URISyntaxException {
		return killsForType(typeId, year, month, 1);
	}

	public static List<EZKB> killsForType(int typeId, int year, int month, int page)
	    throws IOException, InterruptedException, URISyntaxException {
		String uri = "https://zkillboard.com/api/kills/highsec/shipTypeID/" + typeId + "/year/" + year
				+ "/month/" + month
				+ "/page/" + page + "/";
		InputStream is = openStreamFor(uri);
		List<EZKB> ret = new ArrayList<>(Arrays.asList(new ObjectMapper().readValue(is, RETURN_TYPE)));
		ret.forEach(ShowHistory::fetch);
		if (ret.size() >= 200) {
			if (page < 10) {
				// System.err.println("fetching next page after page " + page);
				ret.addAll(killsForType(typeId, year, month, page + 1));
			} else {
				System.out.println(uri + " can't load next page");
			}
		}
		return ret;

	}

	private static final DateTimeFormatter ff = DateTimeFormatter.ofPattern("uuuu-MM");

	public static final int FREIGHTERGROUP = 513;
	public static final int BARGEGROUP = 463;
	public static final int EXHUMERGROUP = 543;
	public static final int JFGROUP = 902;
	public static final int EXPEDITIONGROUP = 1283;
	public static final int DSTGROUP = 380;
	public static final int BRGROUP = 1202;

	public static void mainLosses(String[] args) {

		int[] groups = { EXPEDITIONGROUP, FREIGHTERGROUP, JFGROUP, DSTGROUP, BRGROUP };

		LocalDate start = LocalDate.of(2010, 1, 1);
		LocalDate end = LocalDate.now();
		List<LocalDate> dates = Stream.iterate(start, date -> date.isBefore(end), date -> date.plusMonths(1))
				.collect(Collectors.toList());

		Map<LocalDate, Map<Integer, List<EZKB>>> date2Group2Kills = Collections.synchronizedMap(new HashMap<>());

		dates.parallelStream().forEach(d -> {
			Map<Integer, List<EZKB>> group2Kills = new HashMap<>();
			for (int groupId : groups) {
				try {
					List<EZKB> val = lossesForGroup(groupId, d.getYear(), d.getMonthValue());
					group2Kills.put(groupId, val);
				} catch (IOException | InterruptedException | URISyntaxException e) {
					throw new RuntimeException(e);
				}
			}
			date2Group2Kills.put(d, group2Kills);
		});

		for (Map<Integer, List<EZKB>> v1 : date2Group2Kills.values()) {
			for (List<EZKB> v2 : v1.values()) {
				for (EZKB v3 : v2) {
					fetch(v3).get();
				}
			}
		}

		System.err.println("fetched killmails");

		StringBuilder header = new StringBuilder("date");
		for (int group : groups) {
			String gname = ESIRawPublic.INSTANCE.cache().universe.groups(group).get().name;
			header.append("\t").append(gname + " kills").append("\t").append(gname + " systems");
		}
		System.out.println(header + "\tall Kills\tall systems");

		date2Group2Kills.entrySet().stream().sorted(Comparator.comparing((Function<? super Entry<LocalDate, Map<Integer, List<EZKB>>>, ? extends LocalDate>) Entry::getKey)).forEach(e -> {
			Map<Integer, List<EZKB>> group2kills = e.getValue();
			StringBuilder sb = new StringBuilder(e.getKey().format(ff));
			int allKills = 0;
			Set<Integer> allLocations = new HashSet<>();
			for (int groupid : groups) {
				List<EZKB> kills = group2kills.getOrDefault(groupid, Collections.emptyList());
				kills.removeIf(ShowHistory::remove);
				Set<Integer> locations = kills.parallelStream().map(ShowHistory::convertSystem)
						.collect(Collectors.toSet());
				allKills += kills.size();
				allLocations.addAll(locations);
				sb.append("\t").append(kills.size()).append("\t").append(locations.size());
			}
			System.out.println(sb.toString() + "\t" + allKills + "\t" + allLocations.size());
		});
	}

	protected static ObjHolder<R_get_killmails_killmail_id_killmail_hash> fetch(EZKB km) {
		return ESIRawPublic.INSTANCE.cache().killmails.get(km.zkb.hash, km.killmail_id);
	}

	protected static int convertSystem(EZKB km) {
		R_get_killmails_killmail_id_killmail_hash fetched = fetch(km).get();
		return fetched == null ? 0 : fetched.solar_system_id;
	}

	protected static boolean remove(EZKB km) {
		R_get_killmails_killmail_id_killmail_hash fetched = fetch(km).get();
		return fetched.war_id != 0;
	}

	private static final int POLICEDRONES_GROUP = 182;
	private static final int SENTRY_GROUP = 99;
	private static final int CONCORD_GROUP = 301;

	public static void mainKills(String... args) {
		int[] types = IntStream.of(POLICEDRONES_GROUP, SENTRY_GROUP, CONCORD_GROUP)
				.mapToObj(gid -> ESIRawPublic.INSTANCE.cache().universe.groups(gid)).map(ObjHolder::get)
				.flatMapToInt(g -> IntStream.of(g.types)).toArray();
		System.err
		.println("" + types.length + " types : " + IntStream.of(types).sorted().boxed().collect(Collectors.toList()));

		LocalDate start = LocalDate.of(2018, 1, 1);
		LocalDate end = LocalDate.now();
		// start = end.minusMonths(10).minusDays(1);
		List<LocalDate> dates = Stream.iterate(start,
				date -> date.getYear() < end.getYear()
				|| date.getYear() == end.getYear() && date.getMonthValue() < end.getMonthValue(),
				date -> date.plusMonths(1))
				.collect(Collectors.toList());

		Map<LocalDate, Set<EZKB>> date2Kills = Collections.synchronizedMap(new HashMap<>());

		dates.stream().forEach(d -> {
			long startTime = System.currentTimeMillis();
			Set<EZKB> data = new HashSet<>();
			date2Kills.put(d, data);
			IntStream.of(types).parallel().forEach(tid->{
				try {
					List<EZKB> val = killsForType(tid, d.getYear(), d.getMonthValue());
					data.addAll(val);
				} catch (IOException | InterruptedException | URISyntaxException e) {
					throw new RuntimeException(e);
				}
			});
			long endTime = System.currentTimeMillis();
			System.out.println("done " + d.getYear() + "-" + d.getMonthValue() + " in " + (endTime - startTime) / 1000 + "s");
		});

		System.err.println("fetched zkb");

		for (Set<EZKB> v1 : date2Kills.values()) {
			for (EZKB v3 : v1) {
				fetch(v3).get();
			}
		}

		System.err.println("fetched esi");

		System.out.println("date\tkills\tsystems\t10th\t100th\tavg\tginy");

		int systemCount = 1192;
		date2Kills.entrySet().stream().sorted(Comparator.comparing((Function<? super Entry<LocalDate, Set<EZKB>>, ? extends LocalDate>) Entry::getKey)).forEach(e -> {
			StringBuilder sb = new StringBuilder(e.getKey().format(ff));

			Set<EZKB> kills = e.getValue();
			sb.append("\t").append(kills.size());

			Set<Integer> locations = kills.parallelStream().map(ShowHistory::convertSystem)
					.collect(Collectors.toSet());
			sb.append("\t").append(locations.size());

			Map<Integer, List<EZKB>> system2kills = kills.stream().collect(Collectors.groupingBy(ShowHistory::convertSystem));
			int[] ordered = system2kills.entrySet().stream().sorted(Comparator.comparing(e2 -> e2.getValue().size()))
					.mapToInt(e2 -> e2.getValue().size()).toArray();
			int minus10 = ordered.length >= 10 ? ordered[ordered.length - 10] : 0;
			int minus100 = ordered.length >= 100 ? ordered[ordered.length - 100] : 0;
			sb.append("\t").append(minus10).append("\t").append(minus100);

			float average = 1.0f * kills.size() / systemCount;
			sb.append("\t").append(average);
			float sumDiff = 0.0f;
			int sumActual = 0;
			float sumcumulated = 0f;
			for (int i = 0; i < systemCount - 1; i++) {
				float cumulated = (i + 1) * average;
				int actual = i - systemCount + ordered.length > 0 ? ordered[i - systemCount + ordered.length] : 0;
				sumActual += actual;
				sumDiff += cumulated - sumActual;
				sumcumulated += cumulated;
			}
			float giny = sumDiff / sumcumulated;
			sb.append("\t").append(giny);

			System.out.println(sb.toString());
		});
	}

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 10;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);
		mainKills(args);
	}

}
