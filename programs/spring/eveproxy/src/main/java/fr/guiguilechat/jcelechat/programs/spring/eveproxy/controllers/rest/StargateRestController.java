package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StargateService.WayPoint;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.services.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/sde/stargate")
public class StargateRestController {

	@Autowired
	private StargateService stargateService;

	@Autowired
	private StationService stationService;

	public static record TravelResult(int startId, int endId, boolean hs, String align, String warpSpeed,
			Duration duration,
			List<WayPoint> waypoints) {

		public TravelResult(int startId, int endId, boolean hs, double align_s, double ws_aups,
				List<WayPoint> waypoints) {
			this(startId, endId, hs, "" + align_s + " s", "" + ws_aups + " AU/s",
					waypoints.stream().map(WayPoint::duration).collect(Collectors.reducing(Duration::plus)).orElse(null),
					waypoints);
		}
	}

	@Operation(summary = "stations route", description = "find the quickest path between two stations, considering ship speed")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "request and result")
	})
	@GetMapping("/travel/{stationFromId}/{stationToId}")
	public ResponseEntity<TravelResult> travel(
			@PathVariable @Parameter(description = "id of station to include travels from") int stationFromId,
			@PathVariable @Parameter(description = "id of station to include travels to from stargates in the same system") int stationToId,
			@RequestParam @Parameter(description = "if set to true, only travel to a stargate in highsecurity. Default false") Optional<Boolean> hs,
			@RequestParam @Parameter(description = "align time, in s,  of the ship. Default 10") Optional<Double> align,
			@RequestParam @Parameter(description = "warp speed, in AU/s, of the ship. Default 4") Optional<Double> ws,
			@RequestParam @Parameter(description = "json or xml. Default json") Optional<String> accept) {
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
						new TravelResult(stationFromId, stationToId, hs.orElse(false), align.orElse(10.0), ws.orElse(4.0), wps),
						accept);
	}

}
