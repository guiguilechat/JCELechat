package fr.guiguilechat.jcelechat.libs.spring.anon.faction.information;

import java.util.Collection;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.NumberEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.affiliations.faction")
@Order(2)
public class FactionInfoService
		extends NumberEntityService<FactionInfo, Integer, FactionInfoRepository>
		implements IdResolutionListener {

	@Override
	protected FactionInfo create(Integer entityId) {
		FactionInfo ret = new FactionInfo();
		ret.setId(entityId);
		return ret;
	}

	@Override
	public void onNewIdResolutions(Collection<IdResolution> idResolutions) {
		createMissing(
				idResolutions.stream()
						.filter(idr -> idr.getCategory() == post_universe_names_category.faction)
						.map(IdResolution::getId)
						.distinct().toList());
	}

}
