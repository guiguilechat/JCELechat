package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.industry;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.IndustryActivity;
import fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.IndustryActivityService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/industry/activity")
@RequiredArgsConstructor
public class ActivityRestController {

	final private IndustryActivityService industryActivityService;

	@Transactional
	@GetMapping("/list")
	public ResponseEntity<List<IndustryActivity>> list(@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				industryActivityService.all().stream()
						.sorted(Comparator.comparing(IndustryActivity::getId))
						.toList(),
				accept);

	}

}
