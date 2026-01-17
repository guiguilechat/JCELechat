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

import fr.guiguilechat.jcelechat.libs.spring.anon.character.information.CharacterInformation;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractInfoService;
import fr.guiguilechat.jcelechat.libs.spring.anon.trade.contract.ContractItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/market/contract")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ContractHTMLController {

	private final ContractInfoService contractInfoService;

	private final ContractItemService contractItemService;

	protected static record Issuer(int id, String name) {

		public static Issuer of(CharacterInformation charInfo) {
			if (charInfo == null) {
				return null;
			}
			if (charInfo.isFetched()) {
				return new Issuer(charInfo.getId(), charInfo.getName());
			}
			if (charInfo.getIdResolution() != null && charInfo.getIdResolution().isFetched()) {
				return new Issuer(charInfo.getId(), charInfo.getIdResolution().getName());
			}
			return new Issuer(charInfo.getId(), "character:" + charInfo.getId());
		}

		public static Issuer of(CorporationInfo corpInfo) {
			if (corpInfo == null) {
				return null;
			}
			if (corpInfo.isFetched()) {
				return new Issuer(corpInfo.getId(), corpInfo.getName());
			}
			if (corpInfo.getIdResolution() != null && corpInfo.getIdResolution().isFetched()) {
				return new Issuer(corpInfo.getId(), corpInfo.getIdResolution().getName());
			}
			return new Issuer(corpInfo.getId(), "corporation:" + corpInfo.getId());
		}
	}

	@GetMapping("{contractId}")
	public String getContract(Model model,
			@PathVariable int contractId) {
		ContractInfo contract = contractInfoService.byId(contractId);
		if (contract == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "contract " + contractId + " does not exist");
		}
		model.addAttribute("contract", contract);
		model.addAttribute("items", contractItemService.loadWithType(contract));
		model.addAttribute("issuer", Issuer.of(contract.getIssuer()));
		model.addAttribute("issuerCorporation", Issuer.of(contract.getIssuerCorporation()));
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
