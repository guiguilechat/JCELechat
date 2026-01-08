package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping.MutaMappingRepository.MutaProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;
import jakarta.transaction.Transactional;

@Service
public class MutaMappingService extends DeducedEntityService<MutaMapping, MutaMappingRepository> {

	public List<MutaProduct> listProducts() {
		return repo().listProducts();
	}

	public record MutaProductGroup(String catName, String groupName, List<MutaProduct> products) {
	}

	@Transactional
	public List<MutaProductGroup> listProductGroups() {
		List<MutaProductGroup> ret = new ArrayList<>();
		Integer lastGroupId = null;
		List<MutaProduct> lastProducts = null;
		for (MutaProduct mp : listProducts()) {
			if (lastGroupId == null || lastGroupId != mp.groupId()) {
				lastProducts = null;
				lastGroupId = mp.groupId();
			}
			if (lastProducts == null) {
				lastProducts = new ArrayList<>();
				ret.add(new MutaProductGroup(mp.categoryName(), mp.groupName(), lastProducts));

			}
			lastProducts.add(mp);
		}
		return ret;
	}

	@Transactional
	public List<Type> sourcesFor(int abyssalTypeId) {
		return repo().sourcesFor(abyssalTypeId);
	}

}
