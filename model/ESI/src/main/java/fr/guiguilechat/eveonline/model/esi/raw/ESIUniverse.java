package fr.guiguilechat.eveonline.model.esi.raw;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

public class ESIUniverse {

	private static final Logger logger = LoggerFactory.getLogger(ESIUniverse.class);

	private ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static class Station {
		public int station_id;
		public String name;
		public int type_id;
		public Object position;
		public int system_id;
		public double reprocessing_efficiency;
		public double reprocessing_stations_take;
		public long max_dockable_ship_volume;
		public long office_rental_cost;
		public String[] services;
		public int owner;
		public int race_id;
	}

	private static final String stationURL = "https://esi.tech.ccp.is/latest/universe/stations/";

	protected HashMap<Integer, Station> cachedStations = new HashMap<>();

	private final ObjectReader stationReader = om.readerFor(Station.class);

	public Station getStation(int id) {
		if (id / 10000000 == 3) {
			return null;
		}
		Station ret = cachedStations.get(id);
		if (ret == null && !cachedStations.containsKey(id)) {
			ret = fetchStation(id);
			cachedStations.put(id, ret);
		}
		return ret;
	}

	protected Station fetchStation(int id) {
		logger.debug("fetching station " + id);
		boolean error = false;
		for (int i = 0; i < 3; i++) {
			try {
				return stationReader.readValue(new URL(stationURL + id));
			} catch (IOException e) {
				if(!error) {
					e.printStackTrace();
				}
				error = true;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return null;
	}

	public void loadStations(int... ids) {
		IntStream.of(ids).parallel().forEach(id -> {
			cachedStations.put(id, fetchStation(id));
		});
	}

	public static class Systems {
		public int star_id;
		public int system_id;
		public String name;
		public Object position;
		public double security_status;
		public int constellation_id;
		public Object[] planets;
		public int[] stargates;
		public String security_class;
		public int[] stations;
	}

	private static final String systemURL = "https://esi.tech.ccp.is/latest/universe/systems/";

	protected HashMap<Integer, Systems> cachedSystems = new HashMap<>();

	private final ObjectReader systemsReader = om.readerFor(Systems.class);

	public Systems getSystem(int id) {
		Systems ret = cachedSystems.get(id);
		if (ret == null && !cachedSystems.containsKey(id)) {
			ret = fetchSystem(id);
			cachedSystems.put(id, ret);
		}
		return ret;
	}

	public void loadSystems(int... ids) {
		IntStream.of(ids).parallel().forEach(id -> {
			cachedSystems.put(id, fetchSystem(id));
		});
	}
	public Systems fetchSystem(int id) {
		logger.debug("fetching system " + id);
		boolean error = false;
		for (int i = 0; i < 3; i++) {
			try {
				return systemsReader.readValue(new URL(systemURL + id));
			} catch (IOException e) {
				if (!error) {
					e.printStackTrace();
				}
				error = true;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new UnsupportedOperationException("catch this", e);
			}
		}
		return null;
	}

}
