package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.industry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.modifier.IndustryModifier;
import fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.modifier.IndustryModifierService;
import fr.guiguilechat.jcelechat.libs.spring.anon.industry.activity.modifier.IndustryModifier.Modifiedfield;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.RestControllerHelper.ACCEPT_TEXT;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/industry/modifiers")
@RequiredArgsConstructor
public class ActivityModifierRestController {

	final private IndustryModifierService industryModifierService;

	public static record ActivityModifierDto(
			int typeId,
			int activityId,
			Modifiedfield modifies,
			int modyfingAttributeId,
			List<Integer> productGroupIds,
			List<Integer> productCategoryIds) {

		public static ActivityModifierDto of(IndustryModifier im) {

			return new ActivityModifierDto(
					im.getType().getId(),
					im.getActivity().getId(),
					im.getModifies(),
					im.getModifyingAttribute().getId(),
					im.getProductFilter() == null || im.getProductFilter().getGroupIds().isEmpty() ? null
							: new ArrayList<>(im.getProductFilter().getGroupIds()),
					im.getProductFilter() == null || im.getProductFilter().getCategoryIds().isEmpty() ? null
							: new ArrayList<>(im.getProductFilter().getCategoryIds()));
		}
	}

	@Transactional
	@GetMapping("/list")
	public ResponseEntity<List<ActivityModifierDto>> list(@RequestParam Optional<ACCEPT_TEXT> accept) {
		return RestControllerHelper.makeResponse(
				industryModifierService.all().stream()
						.map(ActivityModifierDto::of)
						.sorted(Comparator.comparing(im -> im.modifies))
						.sorted(Comparator.comparing(im -> im.activityId))
						.sorted(Comparator.comparing(im -> im.typeId))
						.toList(),
				accept);

	}

}
