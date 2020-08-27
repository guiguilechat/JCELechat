package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

/**
 * get data about systems being invaded
 *
 */
public class Invasions {

	public static void main(String[] args) throws MalformedURLException, IOException {
		for (SolarSystem e : INSTANCE.getDangerousSystems()) {
			System.out.println(e);
		}
	}

	public static final Invasions INSTANCE = new Invasions();

	private Invasions() {
	}

	public static class JsonEntry {
		public double derived_security_status;
		public String status;
		public int system_id;
		public String system_name;
		public double system_security;
		public String system_sovereignty;
		public String updated_at;
	}

	private List<JsonEntry> cachedEntries = null;

	/**
	 *
	 * @return the cached json entries from the site
	 */
	public List<JsonEntry> getEntries() {
		if (cachedEntries == null) {
			synchronized (this) {
				if (cachedEntries == null) {
					try {
						ObjectMapper mapper = new ObjectMapper();
						cachedEntries = mapper.readValue(new URL("https://kybernaut.space/invasions.json"),
								new TypeReference<List<JsonEntry>>() {
						});
					} catch (IOException e) {
						throw new UnsupportedOperationException("catch this", e);
					}
				}
			}

		}
		return cachedEntries;
	}

	private Map<String, String> cachedSysName2Status = null;

	/**
	 * fetch the list of systems and their state from
	 * https://kybernaut.space/invasions
	 *
	 * @return the cached map.
	 */
	public Map<String, String> getSysName2Status() {
		if (cachedSysName2Status == null) {
			List<JsonEntry> entries = getEntries();
			synchronized (this) {
				if (cachedSysName2Status == null) {
					cachedSysName2Status = entries.stream().collect(Collectors.toMap(e -> e.system_name, e -> e.status));
				}
			}
		}
		return cachedSysName2Status;
	}

	//
	// trying through the invasion page with JSOUP
	//
	// private Map<String, String> fetchSysName2Status() {
	// Document document;
	// try {
	// document = Jsoup.connect("https://kybernaut.space/invasions").get();
	// } catch (IOException e) {
	// throw new UnsupportedOperationException("catch this", e);
	// }
	// return
	// document.select("div[class=info-block]").stream().collect(Collectors
	// .toMap(element -> element.selectFirst("h2").text(), element ->
	// element.selectFirst("li[class=status]").text()));
	// }

	private Map<SolarSystem, String> cachedSystems = null;

	/**
	 *
	 * @return
	 */
	public Map<SolarSystem, String> getSystems() {
		if (cachedSystems == null) {
			Map<String, String> sysnames = getSysName2Status();
			synchronized (this) {
				if (cachedSystems == null) {
					cachedSystems = sysnames.entrySet().stream()
							.collect(Collectors.toMap(e -> SolarSystem.getSystem(e.getKey()), e -> e.getValue()));
				}
			}
		}
		return cachedSystems;
	}

	/**
	 * set of state that are dangerous to navigate withing, basically all the
	 * states besides Fortress
	 */
	private final Set<String> DANGEROUS_STATES = new HashSet<>(
			Arrays.asList("edencom_minor_victory", "Edencom Minor Victory", "final_liminality", "Final Liminality",
					"stellar_reconnaissance", "Stellar Reconnaissance", "triglavian_minor_victory", "Triglavian Minor Victory"));

	private Set<SolarSystem> cacheDangerousSystems = null;

	/**
	 * list the solar systems that have been invaded and are in a dangerous state.
	 * Dangerous state being any other than fortress
	 *
	 * @return a cached Set of the solar systems.
	 */
	public Set<SolarSystem> getDangerousSystems() {
		if (cacheDangerousSystems == null) {
			List<JsonEntry> entries = getEntries();
			synchronized (this) {
				if (cacheDangerousSystems == null) {
					cacheDangerousSystems = entries.stream().filter(e -> DANGEROUS_STATES.contains(e.status))
							.map(e -> SolarSystem.getSystem(e.system_name)).collect(Collectors.toSet());
				}
			}
		}
		return cacheDangerousSystems;
	}
}
