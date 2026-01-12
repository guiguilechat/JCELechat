package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.market;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/market/contract")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractHTMLController {

	private final ContractInfoService contractInfoService;

	@GetMapping("{contractId}")
	public String getContract(Model model,
			@PathVariable int contractId) {
		ContractInfo contract = contractInfoService.byId(contractId);
		if (contract == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "contract " + contractId + " does not exist");
		}
		model.addAttribute("contract", contract);
		return "market/contract";
	}

	public String contractUrl(int contractId) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getContract", null, contractId).build()
				.toUri()
				.toString();
	}

	public String contractUrl(ContractInfo contract) {
		return contractUrl(contract.getId());
	}

}
