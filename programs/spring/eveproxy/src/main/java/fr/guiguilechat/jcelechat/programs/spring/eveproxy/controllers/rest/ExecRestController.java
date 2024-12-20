package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateService;
import fr.guiguilechat.jcelechat.libs.spring.trade.contract.ContractInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exec")
@RequiredArgsConstructor
public class ExecRestController {

	private final SdeUpdateService urService;

	private final ContractInfoService contractInfoService;

	@Operation(summary = "force SDE fetch", description = "request next SDE fetch to be forced")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "request accepted")
	})
	@GetMapping("/sde/forcefetch")
	public ResponseEntity<?> fetchSde() {
		urService.forceNext();
		return ResponseEntity.ok("");
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

}
