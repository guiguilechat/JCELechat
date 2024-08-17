package fr.guiguilechat.jcelechat.model.sde.locations;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.jcelechat.model.sde.locations.Invasions.JsonEntry.STATUS;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem.SECSTATUS;

/**
 * get data about systems being invaded
 *
 */
public class Invasions {

	public static void main(String[] args) throws MalformedURLException, IOException {
		for (SolarSystem e : INSTANCE.getPointSystems(false, false)) {
			System.out.println("\t" + e);
		}
		System.out.println("bad sec :");
		for (SolarSystem e : INSTANCE.getReducedSystems()) {
			System.out.println("\t" + e);
		}
	}

	public static final Invasions INSTANCE = new Invasions();

	private Invasions() {
	}

	public static class JsonEntry {

		public static enum STATUS {
			@JsonProperty("final_liminality")
			FINAL_LIMINALITY(true, true, false),
			@JsonProperty("second_liminality")
			SECOND_LIMINALITY(true, true, false),
			@JsonProperty("first_liminality")
			FIRST_LIMINALITY(true, true, false),
			@JsonProperty("triglavian_minor_victory")
			TRIG_MINOR(false, true, false),
			@JsonProperty("stellar_reconnaissance")
			RECON(false, true, true),
			@JsonProperty("edencom_minor_victory")
			EDEN_MINOR(false, false, true),
			@JsonProperty("redoubt")
			REDOUBT(false, false, true),
			@JsonProperty("bulwark")
			BULWARK(false, false, true),
			@JsonProperty("fortress")
			FORTRESS(false, false, true);

			public final boolean applySecStatus;
			public final boolean hasTrigPoint;
			public final boolean hasEdenPoint;

			STATUS(boolean applySecStatus, boolean trigPoint, boolean edenPoint) {
				this.applySecStatus = applySecStatus;
				hasTrigPoint = trigPoint;
				hasEdenPoint = edenPoint;
			}

			/**
			 * tell if the status makes roaming fleet point you at gate
			 *
			 * @param friendTriglavian
			 *          true if we are friend with triglavian
			 * @param friendEdencom
			 *          true if we are friend with edencom
			 * @return true if a faction not friend with can point.
			 */
			public boolean hasPoint(boolean friendTriglavian, boolean friendEdencom) {
				return hasTrigPoint && !friendTriglavian || hasEdenPoint && !friendEdencom;
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
						cachedEntries = mapper.readValue(new URI("https://kybernaut.space/invasions.json").toURL(),
								new TypeReference<List<JsonEntry>>() {
						});
					} catch (IOException | URISyntaxException e) {
						throw new RuntimeException(e);
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
							.collect(Collectors.toMap(e -> SolarSystem.getSystem(e.getKey()), (Function<? super Entry<String, STATUS>, ? extends STATUS>) Entry::getValue));
				}
			}
		}
		return cacheSystems2Status;
	}

	@SuppressWarnings("unchecked")
	private Set<SolarSystem> cachePointSystems[] = new Set[4];

	/**
	 * list the solar systems that have been invaded and are in a dangerous state.
	 * if both trig and edencom are allowed, then recon status is allowed.
	 *
	 * @param friendTrig
	 *          if set to true, allow system that have triglavian points
	 * @param friendEden
	 *          if set to true, allow system with edencom points
	 *
	 * @return a cached Set of the solar systems.
	 */
	public Set<SolarSystem> getPointSystems(boolean friendTrig, boolean friendEden) {
		int index = (friendTrig ? 2 : 0) + (friendEden ? 1 : 0);
		if (cachePointSystems[index] == null) {
			List<JsonEntry> entries = getEntries();
			synchronized (cachePointSystems) {
				if (cachePointSystems[index] == null) {
					cachePointSystems[index] = entries.stream().filter(e -> e.status.hasPoint(friendTrig, friendEden))
							.map(e -> SolarSystem.getSystem(e.system_name)).collect(Collectors.toSet());
				}
			}
		}
		return cachePointSystems[index];
	}

	private Set<SolarSystem> cacheReducedSystems = null;

	/**
	 * list the solar systems that have been invaded and are in a lower sec status
	 *
	 * @return a cached Set of the solar systems.
	 */
	public Set<SolarSystem> getReducedSystems() {
		if (cacheReducedSystems == null) {
			List<JsonEntry> entries = getEntries();
			synchronized (this) {
				if (cacheReducedSystems == null) {
					cacheReducedSystems = entries.stream().filter(JsonEntry::secReduced)
							.map(e -> SolarSystem.getSystem(e.system_id))
							.collect(Collectors.toSet());
				}
			}
		}
		return cacheReducedSystems;
	}

	@SuppressWarnings("unchecked")
	private Set<SolarSystem> cacheDangerousHS[] = new Set[4];

	/**
	 * get the HS systems that have been either converted to non HS or have
	 * roaming fleet that can point on gate
	 *
	 * @param friendTrig
	 *          true if we are friend to trig
	 * @param friendEden
	 *          true if we are friend to eden
	 * @return a cached internal set
	 */
	public Set<SolarSystem> getDangerousHSSystems(boolean friendTrig, boolean friendEden) {
		int index = (friendTrig ? 2 : 0) + (friendEden ? 1 : 0);
		if (cacheDangerousHS[index] == null) {
			List<JsonEntry> entries = getEntries();
			synchronized (cacheDangerousHS) {
				if (cacheDangerousHS[index] == null) {
					cacheDangerousHS[index] = entries.stream()
							.filter(e -> e.baseSec() == SECSTATUS.HS
							&& (e.derivedSec() != e.baseSec() || e.status.hasPoint(friendTrig, friendEden)))
							.map(e -> SolarSystem.getSystem(e.system_id))
							.collect(Collectors.toSet());
				}
			}
		}
		return cacheDangerousHS[index];
	}

}
