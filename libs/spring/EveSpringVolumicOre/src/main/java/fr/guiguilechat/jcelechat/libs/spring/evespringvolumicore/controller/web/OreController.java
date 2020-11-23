package fr.guiguilechat.jcelechat.libs.spring.evespringvolumicore.controller.web;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.guiguilechat.jcelechat.libs.spring.evespringvolumicore.controller.api.OreAPI;
import fr.guiguilechat.jcelechat.libs.spring.evespringvolumicore.controller.api.OreAPI.Orderer;
import fr.guiguilechat.jcelechat.libs.spring.evespringvolumicore.controller.api.OreAPI.Security;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;

@Controller
@RequestMapping("/web/ore")
public class OreController {

	@Autowired
	OreAPI oreapi;

	public static class OreParams {

		private Float reprocessingEfficiency = 0.5f;

		public Float getReprocessingEfficiency() {
			return reprocessingEfficiency;
		}

		public void setReprocessingEfficiency(Float reprocessingEfficiency) {
			this.reprocessingEfficiency = reprocessingEfficiency;
		}

		private Security security = Security.all;

		public Security getSecurity() {
			return security;
		}

		public void setSecurity(Security security) {
			this.security = security;
		}

		private Long minVolume = 1l;

		public Long getMinVolume() {
			return minVolume;
		}

		public void setMinVolume(Long minVolume) {
			this.minVolume = minVolume;
		}

		private int regionID = 10000002;

		public int getRegionID() {
			return regionID;
		}

		public void setRegionID(int regionID) {
			this.regionID = regionID;
		}

		private String filter = "";

		public String getFilter() {
			return filter;
		}

		public void setFilter(String filter) {
			this.filter = filter;
		}

		private Orderer order = Orderer.compr_bo;

		public Orderer getOrder() {
			return order;
		}

		public void setOrder(Orderer order) {
			this.order = order;
		}

		private boolean allowNoOffer = false;

		public boolean isAllowNoOffer() {
			return allowNoOffer;
		}

		public void setAllowNoOffer(boolean allowNoOffer) {
			this.allowNoOffer = allowNoOffer;
		}

	}

	private Collection<Region> allowedRegions = Region.load().values().stream().filter(r -> r.isKS && !r.isWormhole)
			.sorted(Comparator.comparing(r -> r.name)).collect(Collectors.toList());

	@GetMapping("/volumic")
	public String volumic(Model model, OreParams params) {

		model.addAttribute("volumes",
				oreapi.volumic(Optional.of(params.getSecurity()), Optional.of(params.getRegionID()),
						Optional.of(params.getMinVolume()),
						Optional.of(params.getFilter()), Optional.of(params.getOrder()),
						Optional.of(params.getReprocessingEfficiency()),
						params.isAllowNoOffer() ? Optional.of("") : Optional.empty()));
		model.addAttribute("params", params);
		model.addAttribute("regions", allowedRegions);
		model.addAttribute("securities", Security.values());
		model.addAttribute("orders", Orderer.values());
		return "oreVolumic";
	}

	@GetMapping("/**")
	@ResponseBody
	public String index() {
		return "welcome to eve ore";
	}

}
