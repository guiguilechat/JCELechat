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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;
import fr.guiguilechat.jcelechat.model.sde.translate.Invasions.JsonEntry.STATUS;

/**
 * get data about systems being invaded
 *
 */
public class Invasions {

	public static void main(String[] args) throws MalformedURLException, IOException {
		for (SolarSystem e : INSTANCE.getDangerousSystems(false, false)) {
			System.out.println("\t" + e);
		}
		System.out.println("bad sec :");
		for (SolarSystem e : INSTANCE.getBadsecSystems()) {
			System.out.println("\t" + e);
		}
	}

	public static final Invasions INSTANCE = new Invasions();

	private Invasions() {
	}

	public static class JsonEntry {

		public static enum STATUS {
			@JsonProperty("final_liminality")
			FINAL_LIMINALITY(true),
			@JsonProperty("second_liminality")
			SECOND_LIMINALITY(true),
			@JsonProperty("first_liminality")
			FIRST_LIMINALITY(true),
			@JsonProperty("triglavian_minor_victory")
			TRIG_MINOR(false),
			@JsonProperty("stellar_reconnaissance")
			RECON(false),
			@JsonProperty("edencom_minor_victory")
			EDEN_MINOR(false),
			@JsonProperty("redoubt")
			REDOUBT(false),
			@JsonProperty("bulwark")
			BULWARK(false),
			@JsonProperty("fortress")
			FORTRESS(false);

			public final boolean applySecStatus;

			STATUS(boolean applySecStatus) {
				this.applySecStatus = applySecStatus;
			}
		}

		public float derived_security_status;
		public STATUS status;
		public int system_id;
		public String system_name;
		public float system_security;
		public String system_sovereignty;
		public String updated_at;

		public SECSTATUS derivedSec() {
			return SECSTATUS.of(status.applySecStatus ? derived_security_status : system_security);
		}

		public SECSTATUS baseSec() {
			return SECSTATUS.of(system_security);
		}

		public boolean secReduced() {
			return baseSec() != derivedSec();
		}
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

	private Map<String, STATUS> cachedSysName2Status = null;

	/**
	 * fetch the list of systems and their state from
	 * https://kybernaut.space/invasions
	 *
	 * @return the cached map.
	 */
	public Map<String, STATUS> getSysName2Status() {
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

	private Map<SolarSystem, STATUS> cacheSystems2Status = null;

	/**
	 *
	 * @return the mapping of solar system invaded, to their status
	 */
	public Map<SolarSystem, STATUS> getSystems2Status() {
		if (cacheSystems2Status == null) {
			Map<String, STATUS> sysnames = getSysName2Status();
			synchronized (this) {
				if (cacheSystems2Status == null) {
					cacheSystems2Status = sysnames.entrySet().stream()
							.collect(Collectors.toMap(e -> SolarSystem.getSystem(e.getKey()), e -> e.getValue()));
				}
			}
		}
		return cacheSystems2Status;
	}

	@SuppressWarnings("unchecked")
	private Set<SolarSystem> cacheDangerousSystems[] = new Set[4];

	/**
	 * list the solar systems that have been invaded and are in a dangerous state.
	 * if both trig and edencom are allowed, then recon status is allowed.
	 * liminalities are never allowed : even if you side with trigs, players will
	 * make that system dangerous.
	 *
	 * @param allowTrig
	 *          if set to true, allow triglavian minor victory
	 * @param allowEdencom
	 *          if set to true, allow edencom victories
	 *
	 * @return a cached Set of the solar systems.
	 */
	public Set<SolarSystem> getDangerousSystems(boolean allowTrig, boolean allowEdencom) {
		int index = (allowTrig ? 2 : 0) + (allowEdencom ? 1 : 0);
		if (cacheDangerousSystems[index] == null) {
			List<JsonEntry> entries = getEntries();
			synchronized (this) {
				if (cacheDangerousSystems[index] == null) {
					Set<STATUS> dangerous = new HashSet<>(
							Arrays.asList(STATUS.FINAL_LIMINALITY, STATUS.FIRST_LIMINALITY, STATUS.SECOND_LIMINALITY));
					if (!allowEdencom) {
						dangerous.addAll(
								Arrays.asList(STATUS.RECON, STATUS.EDEN_MINOR, STATUS.REDOUBT, STATUS.BULWARK, STATUS.FORTRESS));
					}
					if (!allowEdencom) {
						dangerous.addAll(Arrays.asList(STATUS.RECON, STATUS.TRIG_MINOR));
					}
					cacheDangerousSystems[index] = entries.stream().filter(e -> dangerous.contains(e.status))
							.map(e -> SolarSystem.getSystem(e.system_name)).collect(Collectors.toSet());
				}
			}
		}
		return cacheDangerousSystems[index];
	}
	private Set<SolarSystem> cacheBadsecSystems = null;

	/**
	 * list the solar systems that have been invaded and are in a dangerous state.
	 * Dangerous state being any invasion state since any can have roaming fleet
	 * that scramble
	 *
	 * @return a cached Set of the solar systems.
	 */
	public Set<SolarSystem> getBadsecSystems() {
		if (cacheBadsecSystems == null) {
			List<JsonEntry> entries = getEntries();
			synchronized (this) {
				if (cacheBadsecSystems == null) {
					cacheBadsecSystems = entries.stream().filter(e -> e.secReduced()).map(e -> SolarSystem.getSystem(e.system_id))
							.collect(Collectors.toSet());
				}
			}
		}
		return cacheBadsecSystems;
	}

}
