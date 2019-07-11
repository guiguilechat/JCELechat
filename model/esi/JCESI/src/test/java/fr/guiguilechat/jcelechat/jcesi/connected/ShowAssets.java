package fr.guiguilechat.jcelechat.jcesi.connected;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;

/**
 * every 15 min, dumps the assets of a toon's corp in the
 * target/debugcorpassets/ golder
 *
 *
 */
public class ShowAssets {

	public static void main(String[] args) throws InterruptedException, IOException {

		ESIAccount acc = new ESIAccount(
				args[0], args[1]);

		File parentCorp = new File("target/debugcorpassets/");
		parentCorp.mkdirs();
		File parentChar = new File("target/debugcharassets/");
		parentChar.mkdirs();
		while (true) {
			try (PrintWriter corpWritter = new PrintWriter(new FileWriter(
					new File(parentCorp, LocalDateTime.now(Clock.systemUTC()).format(DateTimeFormatter.ISO_DATE_TIME))))) {
				int corpid = ESIStatic.INSTANCE.get_characters(acc.characterId(), null).getOK().corporation_id;
				corpWritter.println("name=" + acc.verify.characterName());
				corpWritter.println("corporation_id=" + corpid);
				corpWritter.println("pages :");
				List<String> errors = new ArrayList<>();
				IntFunction<Requested<R_get_corporations_corporation_id_assets[]>> getPage = page -> {
					Requested<R_get_corporations_corporation_id_assets[]> requestedPage = acc.raw.get_corporations_assets(corpid,
							page, null);
					while (requestedPage.isServerError()) {
						errors.add("page " + page
								+ (requestedPage != null ? "" + requestedPage.getResponseCode() + "-" + requestedPage.getError()
								: "null"));
						requestedPage = acc.raw.get_corporations_assets(corpid, page, null);
					}
					return requestedPage;
				};
				Requested<R_get_corporations_corporation_id_assets[]> page1 = getPage.apply(1);
				if (page1.isOk()) {
					int pages = page1.getNbPages();
					// System.err.println("corporation assets pages=" + pages);
					Set<String> expiries = new HashSet<>();
					HashMap<Long, List<String>> assetId2url = new HashMap<>();
					List<Requested<R_get_corporations_corporation_id_assets[]>> requests = Stream
							.concat(Stream.of(page1), IntStream.range(2, pages + 1).parallel().mapToObj(getPage))
							.collect(Collectors.toList());
					requests.forEach(page -> {
						expiries.add(page.getHeaders().get("Expires").get(0));
						corpWritter.println("\t" + page.getURL() + " Expires=" + page.getHeaders().get("Expires"));
						for (R_get_corporations_corporation_id_assets ass : page.getOK()) {
							corpWritter.println("\t\t" + ass.item_id + " : type=" + ass.type_id + " qtty=" + ass.quantity
									+ " location=" + ass.location_id);
							List<String> list = assetId2url.get(ass.item_id);
							if (list == null) {
								list = new ArrayList<>();
								assetId2url.put(ass.item_id, list);
							} else {
								errors.add("asset " + ass.item_id + " already declared on url " + list);
							}
							list.add(page.getURL());
						}
					});
					corpWritter.println("expiries : " + expiries);
					corpWritter.println("duplicate items :");
					for (Entry<Long, List<String>> e : assetId2url.entrySet()) {
						if (e.getValue().size() > 1) {
							corpWritter.println("" + e.getKey() + " : " + e.getValue());
						}
					}
				}
				corpWritter.println("errors :");
				for (String error : errors) {
					corpWritter.println("\t" + error);
					System.err.println(error);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
			try (PrintWriter charWritter = new PrintWriter(new FileWriter(
					new File(parentChar, LocalDateTime.now(Clock.systemUTC()).format(DateTimeFormatter.ISO_DATE_TIME))))) {
				charWritter.println("name=" + acc.verify.characterName());
				charWritter.println("pages :");
				List<String> errors = new ArrayList<>();
				IntFunction<Requested<R_get_characters_character_id_assets[]>> getPage = page -> {
					Requested<R_get_characters_character_id_assets[]> requestedPage = acc.raw
							.get_characters_assets(acc.characterId(), page, null);
					while (requestedPage.isServerError()) {
						errors.add("page " + page
								+ (requestedPage != null ? "" + requestedPage.getResponseCode() + "-" + requestedPage.getError()
										: "null"));
						requestedPage = acc.raw.get_characters_assets(acc.characterId(), page, null);
					}
					return requestedPage;
				};
				Requested<R_get_characters_character_id_assets[]> page1 = getPage.apply(1);
				if (page1.isOk()) {
					int pages = page1.getNbPages();
					// System.err.println("corporation assets pages=" + pages);
					Set<String> expiries = new HashSet<>();
					HashMap<Long, List<String>> assetId2url = new HashMap<>();
					List<Requested<R_get_characters_character_id_assets[]>> requests = Stream
							.concat(Stream.of(page1), IntStream.range(2, pages + 1).parallel().mapToObj(getPage))
							.collect(Collectors.toList());
					requests.forEach(page -> {
						expiries.add(page.getHeaders().get("Expires").get(0));
						charWritter.println("\t" + page.getURL() + " Expires=" + page.getHeaders().get("Expires"));
						for (R_get_characters_character_id_assets ass : page.getOK()) {
							charWritter
									.println("\t\t" + ass.item_id + " : type=" + ass.type_id + " qtty=" + ass.quantity + " location="
									+ ass.location_id);
							List<String> list = assetId2url.get(ass.item_id);
							if (list == null) {
								list = new ArrayList<>();
								assetId2url.put(ass.item_id, list);
							} else {
								errors.add("asset " + ass.item_id + " already declared on url " + list);
							}
							list.add(page.getURL());
						}
					});
					charWritter.println("expiries : " + expiries);
					charWritter.println("duplicate items :");
					for (Entry<Long, List<String>> e : assetId2url.entrySet()) {
						if (e.getValue().size() > 1) {
							charWritter.println("" + e.getKey() + " : " + e.getValue());
						}
					}
				}
				charWritter.println("errors :");
				for (String error : errors) {
					charWritter.println("\t" + error);
					System.err.println(error);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
			System.out.println(LocalDateTime.now(Clock.systemUTC()));
			Thread.sleep(1000 * 60 * 15);
		}
	}

}
