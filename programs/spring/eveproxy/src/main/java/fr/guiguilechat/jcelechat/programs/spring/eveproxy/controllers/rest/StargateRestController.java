package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Station;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService.TravelTime;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService.WarpDist;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService.WarpJumpDist;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService.WayPoint;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StationService;

@RestController
@RequestMapping("/api/sde/stargate")
public class StargateRestController {

	@Autowired
	private StargateService stargateService;

	@Autowired
	private StationService stationService;

	@GetMapping("/g2g")
	public ResponseEntity<List<WarpJumpDist>> listG2G(
			@RequestParam Optional<Boolean> hs,
			@RequestParam Optional<String> accept) {
		return RestControllerHelper.makeResponse(stargateService.warpJumpsG2G(hs.orElse(false)), accept);
	}

	@GetMapping("/s2g/{stationId}")
	public ResponseEntity<List<WarpJumpDist>> fromStation(
			@PathVariable int stationId,
			@RequestParam Optional<String> accept) {
		Station station = stationService.findById(stationId);

		if (station == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "station " + stationId + " unknown");
		}
		return RestControllerHelper.makeResponse(stargateService.warpJumpsFrom(station), accept);
	}

	@GetMapping("/g2s/{stationId}")
	public ResponseEntity<List<WarpDist>> toStation(
			@PathVariable int stationId,
			@RequestParam Optional<String> accept) {
		Station station = stationService.findById(stationId);

		if (station == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "station " + stationId + " unknown");
		}
		return RestControllerHelper.makeResponse(stargateService.warpJumpsTo(station), accept);
	}

	@GetMapping("/traveltimes/{stationFromId}/{stationToId}")
	public ResponseEntity<List<TravelTime>> travelTimes(
			@PathVariable int stationFromId,
			@PathVariable int stationToId,
			@RequestParam Optional<Boolean> hs,
			@RequestParam Optional<Double> align,
			@RequestParam Optional<Double> ws,
			@RequestParam Optional<String> accept) {
		Station stationFrom = stationService.findById(stationFromId);
		if (stationFrom == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "station " + stationFromId + " unknown");
		}
		Station stationTo = stationService.findById(stationToId);
		if (stationTo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "station " + stationToId + " unknown");
		}
		return RestControllerHelper
				.makeResponse(
						stargateService.travelTimes(stationFrom, stationTo, ws.orElse(4.0), align.orElse(10.0), hs.orElse(false)),
						accept);
	}

	public static record TravelResult(int startId, int endId, boolean hs, double align_s, double ws_aups,
			long time_s,
			List<WayPoint> waypoints) {

	}

	@GetMapping("/travel/{stationFromId}/{stationToId}")
	public ResponseEntity<TravelResult> travel(
			@PathVariable int stationFromId,
			@PathVariable int stationToId,
			@RequestParam Optional<Boolean> hs,
			@RequestParam Optional<Double> align,
			@RequestParam Optional<Double> ws,
			@RequestParam Optional<String> accept) {
		Station stationFrom = stationService.findById(stationFromId);
		if (stationFrom == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "station " + stationFromId + " unknown");
		}
		Station stationTo = stationService.findById(stationToId);
		if (stationTo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "station " + stationToId + " unknown");
		}
		List<WayPoint> wps = stargateService.travel(stationFrom, stationTo, align.orElse(10.0), ws.orElse(4.0),
				hs.orElse(false));
		return RestControllerHelper
				.makeResponse(
						new TravelResult(stationFromId, stationToId, hs.orElse(false), align.orElse(10.0), ws.orElse(4.0),
								(long) wps.stream().mapToDouble(WayPoint::time_s).sum(), wps),
						accept);
	}

}
