package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory/type")
@RequiredArgsConstructor
public class TypeRestController {

	final private TypeService typeService;

	@GetMapping("/id/{typeId}/variations")
	public ResponseEntity<List<Integer>> listVariations(
			@PathVariable int typeId,
			@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				typeService.listVariationIds(typeId),
				accept);
	}

}
