package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.universe;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.guiguilechat.jcelechat.libs.spring.universe.stargate.StargateService;
import fr.guiguilechat.jcelechat.libs.spring.universe.stargate.StargateService.WayPoint;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.Station;
import fr.guiguilechat.jcelechat.libs.spring.universe.station.StationService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/universe/travel")
@RequiredArgsConstructor
public class TravelRestController {

	final private StargateService stargateService;

	final private StationService stationService;

	public static record TravelResult(int startId, String startName, int endId, String endName, boolean hs, String align,
			String warpSpeed,
			long duration_s,
			int jumps,
			List<WayPoint> waypoints) {

		public TravelResult(Station from, Station to, boolean hs, double align_s, double ws_aups,
				List<WayPoint> waypoints) {
			this(from.getId(), from.getName(), to.getId(), to.getName(), hs, "" + align_s + " s",
					"" + ws_aups + " AU/s",
					waypoints.stream().mapToLong(WayPoint::duration_s).sum(),
					waypoints.size(),
					waypoints);
		}
	}

	// a few profiles that can be used to make routes.
	@RequiredArgsConstructor enum ShipProfile {
		bc(9.0, 3.5),
		bs(13.0, 3.0),
		cr(7.0, 4.0),
		ds(5.0, 4.5),
		fr(4.0, 5.0),
		sh(1.5, 5.0),
		;

		public final double align;
		public final double warpSpeed;
	}

	@Transactional
	@Operation(summary = "stations route", description = "find the quickest path between two stations, considering ship speed")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "request and result")
	})
	@GetMapping("/from/{stationFromId}/to/{stationToId}")
	public ResponseEntity<TravelResult> travel(
			@PathVariable @Parameter(description = "id of station to include travels from") int stationFromId,
			@PathVariable @Parameter(description = "id of station to include travels to from stargates in the same system") int stationToId,
			@RequestParam @Parameter(description = "if set to true, only travel to a stargate in highsecurity. Default false") Optional<Boolean> hs,
			@RequestParam @Parameter(description = "profile to use for ship align and warp speed. Default cruiser") Optional<ShipProfile> ship,
			@RequestParam @Parameter(description = "align time, in s, of the ship") Optional<Double> align,
			@RequestParam @Parameter(description = "warp speed, in AU/s, of the ship") Optional<Double> ws,
			@RequestParam @Parameter(description = "json or xml. Default json") Optional<ACCEPT_TEXT> accept) {
		Station stationFrom = stationService.byId(stationFromId);
		if (stationFrom == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "station " + stationFromId + " unknown");
		}
		Station stationTo = stationService.byId(stationToId);
		if (stationTo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "station " + stationToId + " unknown");
		}
		double align_s = ship.orElse(ShipProfile.cr).align;
		double ws_aups = ship.orElse(ShipProfile.cr).warpSpeed;

		if (align.isPresent()) {
			align_s = align.get();
		}
		if (ws.isPresent()) {
			ws_aups = ws.get();
		}

		List<WayPoint> wps = stargateService.travel(stationFrom, stationTo, align_s, ws_aups,
				hs.orElse(false));
		return RestControllerHelper
				.makeResponse(
						new TravelResult(stationFrom, stationTo, hs.orElse(false), align_s, ws_aups, wps),
						accept);
	}

}
