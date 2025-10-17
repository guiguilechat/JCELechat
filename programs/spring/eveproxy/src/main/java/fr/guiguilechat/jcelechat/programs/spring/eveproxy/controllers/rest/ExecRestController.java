package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.gameclient.updater.GameClientUpdateService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdater;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService;
import fr.guiguilechat.jcelechat.libs.spring.trade.history.HistoryReqService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/exec")
@RequiredArgsConstructor
@Slf4j
public class ExecRestController {

	private final ContractInfoService contractInfoService;

	private final GameClientUpdateService gameClientUpdateService;

	private final HistoryReqService historyReqService;

	private final SdeUpdater sdeUpdater;

	@Operation(summary = "force SDE fetch", description = "request next SDE fetch to be forced")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "request accepted")
	})
	@GetMapping("/sde/forcefetch")
	public ResponseEntity<?> fetchSde() {
		sdeUpdater.forceNext();
		return ResponseEntity.ok("");
	}

	@Operation(summary = "force game client update", description = "request next game client fetch to be forced")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "request accepted")
	})
	@GetMapping("/gameclient/forcefetch")
	public ResponseEntity<?> fetchGameClient() {
		gameClientUpdateService.forceNext();
		return ResponseEntity.ok("");
	}

	@Operation(summary = "last game client fetch", description = "get details on the last game client fetch")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "request accepted")
	})
	@GetMapping("/gameclient/last")
	public ResponseEntity<?> lastGameClient(@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(gameClientUpdateService.findLastSuccess(), accept);
	}

	@Operation(summary = "request contracts re analyzis", description = "request the contract info service to start re analyzing fetched contracts")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "request accepted")
	})
	@GetMapping("/contract/reanalyze")
	public ResponseEntity<?> analizeContracts() {
		contractInfoService.requestAnalize();
		return ResponseEntity.ok("");
	}

	@Operation(summary = "request deduplicate of market history", description = "request the history updated to perform deduplication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "request accepted")
	})
	@GetMapping("/history/deduplicate")
	public ResponseEntity<?> deduplicateHistory(boolean deduplicate) {
		log.info("history deduplicate set to " + deduplicate);
		historyReqService.setDeduplicate(deduplicate);
		return ResponseEntity.ok("");
	}

}
