package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.net.URI;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractItemService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.Region;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.region.RegionService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.ContractEvalService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.ContractEvalService.ContractEval;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.ContractEvalService.EvalParams;
import fr.guiguilechat.tools.FormatTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/contracts")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractEvalController {

	private final ContractEvalService contractEvalService;

	private final ContractItemService contractItemService;

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
		model.addAttribute("params", params);

		model.addAttribute("contracts",
		    contractEvalService.evaluate(params).stream().map(this::LinkedContractEval).toList());
		long postEvaluate = System.currentTimeMillis();

		List<Category> categories = contractItemService.categories();
		long postCategories = System.currentTimeMillis();
		log.trace("fetched {} categories in {} ms", categories.size(), postCategories - postEvaluate);
		model.addAttribute("categories",
		    categories.stream()
		        .sorted(Comparator.comparing(Category::name))
						.collect(Collectors.toMap(Category::getId, Category::name, (_, y) -> y, LinkedHashMap::new)));

		LinkedHashMap<String, String> locationFilters = new LinkedHashMap<>();
		locationFilters.put("HS", "High Security");
		locationFilters.put("LS", "Low Security");
		locationFilters.put("NS", "Null Security");
		regionService.allById().values().stream()
		    .sorted(Comparator.comparing(Region::name))
		    .forEach(r -> locationFilters.put("" + r.getId(), r.name()));
		model.addAttribute("locationFilters", locationFilters);

		return "market/contracts";
	}

}
