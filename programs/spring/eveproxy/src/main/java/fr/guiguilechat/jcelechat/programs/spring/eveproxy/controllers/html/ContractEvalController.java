package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.universe.region.RegionService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.ContractEvalService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.ContractEvalService.ContractEval;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.ContractEvalService.EvalParams;
import fr.guiguilechat.tools.FormatTools;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/html/contracts")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractEvalController {

	private final ContractEvalService contractEvalService;

	private final RegionService regionService;

	@Transactional
	@GetMapping("/id/{contractid}")
	public String getContractEval(Model model, @PathVariable int contractid, EvalParams params) {
		return "market/contract";

	}

	public URI uri(int contractId, EvalParams params) {
		return MvcUriComponentsBuilder
		    .fromMethodName(getClass(), "getContractEval", null, contractId, params).build()
		    .toUri();
	}

	public URI uri(int contractId) {
		return uri(contractId, new EvalParams());
	}

	public static record LinkedContract(String url, String name) {

	}

	public LinkedContract LinkedContract(int contractId) {
		return new LinkedContract(uri(contractId).toString(), null);
	}

	public static record LinkedContractEval(String url, ContractEval eval, String region) {
		public String name() {
			return eval.getContract().name();
		}

		public String gameLink() {
			return eval.getContract().gameLink();
		}

		public String gain() {
			return FormatTools.formatPrice(eval.gain());
		}

		public String cost() {
			return FormatTools.formatPrice(eval.getValueRequired());
		}

		public String value() {
			return FormatTools.formatPrice(eval.getValueProvided());
		}

	}

	public LinkedContractEval LinkedContractEval(ContractEval eval) {
		return new LinkedContractEval(uri(eval.getContract().getId()).toString(), eval,
		    regionService.byId(eval.getContract().getRegion().getId()).name());
	}

	@Transactional
	@GetMapping("/evaluate")
	public String getEvaluatedContracts(Model model, EvalParams params) {
		model.addAttribute("contracts",
		    contractEvalService.evaluate(params).stream().map(this::LinkedContractEval).toList());
		model.addAttribute("params", params);
		return "market/contracts";
	}

}
