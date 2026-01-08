package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.html;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLineService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.MutaplasmidRepository.AttributeRange;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.MutaplasmidService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.MutaplasmidService.MutatedRange;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.attribute.TypeAttributeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.station.Station;
import fr.guiguilechat.tools.FormatTools;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/html/mutation")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MutationHTMLController {

	private final MarketLineService marketLineService;

	private final MutaplasmidService mutaplasmidService;

	private final TypeAttributeService typeAttributeService;

	private final TypeService typeService;

	// list products

	@Transactional
	@GetMapping("/products")
	public String products(Model model) {
		return "mutation/products";
	}

	public String productsUrl() {
		return MvcUriComponentsBuilder.fromMethodName(
				getClass(),
				"products",
				(Model) null).build()
				.toUri()
				.toString();
	}

	// list possible mutations for a product and attribute filters

	public static record ProductFilter(
			Map<Integer, Number> filter) {
	}

	public static record AttRangeLimit(int attributeId, String attributeName, boolean highIsGood, Number min,
			Number max, Number limit) {

		public double step() {
			if (min.doubleValue() > 0) {
				return min.doubleValue() / 10000;
			} else {
				return -max.doubleValue() / 10000;
			}
		}
	}

	public static record MutatedChance(MutatedRange mutation, double chance, double rollCost) {

		public String type() {
			return mutation().sourceType().name();
		}

		public String muta() {
			return mutation().mutaplasmid() == null ? "" : mutation().mutaplasmid().name();
		}

		public String formatRollCost() {
			return FormatTools.formatPrice(rollCost());
		}

		public double unitCost() {
			return rollCost() / chance;
		}

		public String formatUnitCost() {
			return FormatTools.formatPrice(unitCost());
		}

		public String formatChance() {
			return String.format("%.3f%%", chance() * 100);
		}
	}

	@Transactional
	@GetMapping("/product/{productTypeId}")
	public String product(
			Model model,
			@PathVariable int productTypeId,
			ProductFilter attFilter,
			Optional<Integer> baseTypeId) {
		Type productType = typeService.ofId(productTypeId);
		if (productType == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"type with id " + productTypeId + " not found");
		}
		model.addAttribute("type", productType);

		Map<Integer, Number> requiredAttributeValue = new HashMap<>();
		Map<Integer, AttributeRange> attrId2Range = mutaplasmidService.listAttributesRanges(productTypeId)
				.stream()
				.collect(Collectors.toMap(AttributeRange::attributeId, o -> o));
		if (baseTypeId != null && baseTypeId.isPresent()) {
			typeAttributeService.byTypeId(List.of(baseTypeId.get())).stream()
					.filter(ta -> attrId2Range.containsKey(ta.getAttributeId()))
					.forEach(ta -> {
				requiredAttributeValue.put(ta.getAttributeId(), ta.getValue());
			});
		}
		if (!attrId2Range.isEmpty()) {
			List<AttRangeLimit> attParams = attrId2Range.values().stream()
					.map(r -> {
						Number limit = r.highIsGood() ? r.min() : r.max();
						if (requiredAttributeValue.containsKey(r.attributeId())) {
							limit = requiredAttributeValue.get(r.attributeId());
						} else if (attFilter != null
								&& attFilter.filter() != null
								&& attFilter.filter().containsKey(r.attributeId())) {
							limit = attFilter.filter().get(r.attributeId());
							requiredAttributeValue.put(r.attributeId(), limit);
						}
						return new AttRangeLimit(r.attributeId(), r.attributeName(), r.highIsGood(), r.min(), r.max(),
								limit);
					})
					.toList();
			model.addAttribute("attParams", attParams);
		}
//		System.err.println("required attributes : " + requiredAttributeValue);

		List<MutatedRange> possibleMutations = mutaplasmidService.listOutputs(productTypeId);
		Set<Integer> purchasebleTypes = possibleMutations.stream().flatMap(mr -> mr.mutaplasmid() == null
				? Stream.of(mr.sourceType().getId())
				: Stream.of(mr.sourceType().getId(), mr.mutaplasmid().getId()))
				.collect(Collectors.toSet());
		var typeId2Price = marketLineService.locationSoPrices(List.of(Station.JITA_HUB_ID), 0, purchasebleTypes);
		List<MutatedChance> evaluations = possibleMutations.stream()
				.map(mr -> {
					double chance = mr.chance(requiredAttributeValue);
//					System.out.println(mr.sourceType() + " + " + mr.mutaplasmid() + " : " + chance);
					double price = typeId2Price.getOrDefault(mr.sourceType().getId(), Double.POSITIVE_INFINITY);
					if (mr.mutaplasmid() != null) {
						price += typeId2Price.getOrDefault(mr.mutaplasmid().getId(), Double.POSITIVE_INFINITY);
					}
					return new MutatedChance(mr, chance, price);
				})
				.filter(mc -> mc.chance() > 0)
				.sorted(Comparator.comparing(MutatedChance::unitCost))
				.toList();
		model.addAttribute("evaluations", evaluations);
		return "mutation/product";
	}

	public String productUrl(int productTypeId) {
		return MvcUriComponentsBuilder.fromMethodName(
				getClass(),
				"product",
				null, // no model
				productTypeId,
				null, // no filter
				null).build()
				.toUri()
				.toString();
	}

	public String productUrl(int productTypeId, int sourceTypeId) {
		return MvcUriComponentsBuilder.fromMethodName(
				getClass(),
				"product",
				null, // no model
				productTypeId,
				null, // no filter
				sourceTypeId).build()
				.toUri()
				.toString();
	}

}