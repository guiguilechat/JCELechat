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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_assets;

/**
 * every 15 min, dumps the assets of a toon's corp in the
 * target/debugcorpassets/ golder
 *
 *
 */
public class ShowCorporationAssets {

	public static void main(String[] args) throws InterruptedException, IOException {

		ESIAccount acc = new ESIAccount(
				args[0], args[1]);

		File parent = new File("target/debugcorpassets/");
		parent.mkdirs();
		while (true) {
			try (PrintWriter pw = new PrintWriter(new FileWriter(
					new File(parent, LocalDateTime.now(Clock.systemUTC()).format(DateTimeFormatter.ISO_DATE_TIME))))) {
				int corpid = ESIStatic.INSTANCE.get_characters(acc.characterId(), null).getOK().corporation_id;
				pw.println("name=" + acc.verify.characterName());
				pw.println("corporation_id=" + corpid);
				pw.println("pages :");
				List<String> errors = new ArrayList<>();
				IntFunction<Requested<R_get_corporations_corporation_id_assets[]>> getPage = page -> {
					Requested<R_get_corporations_corporation_id_assets[]> requestedPage = acc.raw.get_corporations_assets(corpid,
							page, null);
					while (requestedPage.isServerError()) {
						errors.add("page " + page
								+ (requestedPage != null ? "" + requestedPage.getResponseCode() + "-" + requestedPage.getError()
								: "null"));
						requestedPage = acc.raw.get_corporations_assets(corpid, 1, null);
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
						pw.println("\t" + page.getURL() + " Expires=" + page.getHeaders().get("Expires"));
						for (R_get_corporations_corporation_id_assets ass : page.getOK()) {
							pw.println("\t\t" + ass.item_id + " : type=" + ass.type_id + " qtty=" + ass.quantity + " location="
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
					pw.println("expiries : " + expiries);
					pw.println("duplicate items :");
					for (Entry<Long, List<String>> e : assetId2url.entrySet()) {
						if (e.getValue().size() > 1) {
							pw.println("" + e.getKey() + " : " + e.getValue());
						}
					}
				}
				pw.println("errors :");
				for (String error : errors) {
					pw.println("\t" + error);
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
