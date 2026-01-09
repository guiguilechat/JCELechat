package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.util.Comparator;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.inventory.TypeHTMLController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/html/inventory")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class InventoryHtmlController {

	@Lazy
	private final TypeHTMLController typeHTMLController;

	private final TypeService typeService;


	@Transactional
	@GetMapping("/search/{typeFiltering}/{typeFilter}")
	public String getSearch(Model model, @PathVariable String typeFiltering,
			@PathVariable String typeFilter) {
		List<Type> types = typeService.matching(typeFiltering, typeFilter);
		if (types.size() == 1) {
			return "redirect:" + typeHTMLController.typeUrl(types.get(0));
		}
		model.addAttribute("types",
				types.stream().sorted(Comparator.comparing(Type::name)).toList());
		return "inventory/typesearch";
	}

	@Transactional
	@GetMapping("/search/{typeFiltering}")
	public String getSearchQuery(Model model, @PathVariable String typeFiltering,
			String filter) {
		return getSearch(model, typeFiltering, filter);
	}

	@Transactional
	@GetMapping("/search")
	public String getSearch(Model model) {
		return "inventory/typesearch";
	}

}
