package fr.guiguilechat.jcelechat.libs.spring.sde.industry.blueprint.activity.material;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Eblueprints.ActivityType;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.DeducedEntityService;

@Service
public class BlueprintMaterialService extends DeducedEntityService<BlueprintMaterial, BlueprintMaterialRepository> {

	public List<BlueprintMaterial> forBPActivity(int blueprintId, ActivityType activity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public List<BlueprintMaterial> findUsages(Integer typeId, ActivityType activity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public List<BlueprintMaterial> forBPActivity(Iterable<Integer> bpIds, List<ActivityType> activities) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	public Set<Type> allActivityMaterialsInCategory(ActivityType manufacturing, int categoryId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
