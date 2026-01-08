package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.inventory;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.CategoryService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html.MarketHtmlController;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/html/inventory/category")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class CategoryHTMLController {

	private final CategoryService categoryService;

	@Lazy
	private final MarketHtmlController marketHtmlController;

	@Transactional
	@GetMapping("/{categoryId}")
	public String getCategory(Model model, @PathVariable int categoryId) {
		Category c = categoryService.ofId(categoryId);
		if (c == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category " + categoryId + " does not exist");
		}
		model.addAttribute("cat", c);

		Category prvCat = categoryService.prevGroup(c);
		if (prvCat != null) {
			model.addAttribute("prvCat", prvCat);
		}
		Category nxtCat = categoryService.nextGroup(c);
		if (nxtCat != null) {
			model.addAttribute("nxtCat", nxtCat);
		}

		model.addAttribute("jitaRanking", marketHtmlController.linkedRanking(c, List.of(Station.JITA_HUB_ID)));

		return "inventory/category";
	}

	public String categoryUrl(Category category) {
		return MvcUriComponentsBuilder.fromMethodName(getClass(), "getCategory", null,
				category.getId()).build()
				.toUri()
				.toString();
	}

}
