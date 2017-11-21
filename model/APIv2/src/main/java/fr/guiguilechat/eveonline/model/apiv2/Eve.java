package fr.guiguilechat.eveonline.model.apiv2;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Eve {

	private static final Logger logger = LoggerFactory.getLogger(Eve.class);

	public static class EStation {
		public long stationID, stationTypeID, solarSystemID, corporationID, x, y, z;
		public String stationName, corporationName;
	}

	protected List<EStation> cachedStations = null;

	public synchronized List<EStation> conquerableStationList() {
		if (cachedStations != null) {
			return cachedStations;
		}
		String url = "https://api.eveonline.com/eve/ConquerableStationList.xml.aspx";
		Exception error = null;
		for (int retry = 0; retry < 10; retry++) {
			try {
				if (retry != 0) {
					Thread.sleep(500);
				}
				Document page = Jsoup.connect(url).get();
				Elements el = page.select("result rowset row");
				cachedStations = el.stream().map(e -> APIRoot.convertElement(e, EStation.class)).collect(Collectors.toList());
				return cachedStations;
			} catch (IOException | InterruptedException e) {
				error = e;
			}
		}
		logger.error("while retrieving stations, url " + url, error);
		return null;
	}

	protected Map<Long, EStation> cachedStationsById = null;

	public Map<Long, EStation> stationsByID() {
		if (cachedStationsById != null) {
			return cachedStationsById;
		}
		List<EStation> csl = conquerableStationList();
		synchronized (csl) {
			if (cachedStationsById == null) {
				cachedStationsById = csl.stream().collect(Collectors.toMap(es -> es.stationID, es -> es));
			}
		}
		return cachedStationsById;
	}

	public static void main(String[] args) {
		Eve eve = new Eve();
		System.err.println(eve.stationsByID().get(61000990l).solarSystemID);
	}

}
