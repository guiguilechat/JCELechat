package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.inventory;

import java.util.Comparator;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/html/inventory")
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class InventoryHTMLController {

	@Lazy
	private final TypeHTMLController typeHTMLController;

	private final TypeService typeService;


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

	@GetMapping("/search/{typeFiltering}")
	public String getSearchQuery(Model model, @PathVariable String typeFiltering,
			String filter) {
		return getSearch(model, typeFiltering, filter);
	}

	@GetMapping("/search")
	public String getSearch() {
		return "inventory/typesearch";
	}

	public String rootUrl() {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getSearch").build()
				.toUri()
				.toString();
	}

}
