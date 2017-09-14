package fr.guiguilechat.eveonline.database.esi;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ESIUniverse {

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

	public Station getStation(int id) {
		if (id / 10000000 == 3) {
			return null;
		}
		Station ret = cachedStations.get(id);
		if (ret == null && !cachedStations.containsKey(id)) {
			try {
				ret = om.readValue(new URL(stationURL + id), Station.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cachedStations.put(id, ret);
		}
		return ret;
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

	public Systems getSystem(int id) {
		Systems ret = cachedSystems.get(id);
		if (ret == null && !cachedSystems.containsKey(id)) {
			try {
				ret = om.readValue(new URL(systemURL + id), Systems.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cachedSystems.put(id, ret);
		}
		return ret;
	}

}
