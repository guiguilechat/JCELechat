package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService;
import fr.guiguilechat.jcelechat.libs.spring.market.services.RegionLineService.OfferLocation;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.programs.spring.eveproxy.services.BpService2;

@RestController
@RequestMapping("/api/blueprint")
public class BPRestController {

	@Autowired
	private BpService2 bpService2;

	@Autowired
	private TypeService typeService;

	@Autowired
	private RegionLineService regionLineService;

	public static record BpInfo(int typeId, String name, double eiv, List<OfferLocation> seeds) {

	}

	@GetMapping("/{typeFiltering}/{typeFilter}")
	public ResponseEntity<?> showInformation(@PathVariable String typeFiltering,
			@PathVariable String typeFilter,
			@RequestParam Optional<String> accept) throws IOException {
		List<BpInfo> ret = RestControllerHelper.typesFilter(typeService, typeFiltering, typeFilter)
				.stream().map(type -> {
					double eiv = bpService2.eiv(type.getTypeId());
					List<OfferLocation> seeds = regionLineService.seedLocations(type.getTypeId());
					return new BpInfo(type.getTypeId(), type.getName(), eiv, seeds);
				}).toList();
		return RestControllerHelper.makeResponse(ret, accept);
	}

}
