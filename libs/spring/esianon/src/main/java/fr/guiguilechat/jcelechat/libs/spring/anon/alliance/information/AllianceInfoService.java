package fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information;

import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AllianceInfoService extends
		RemoteEntityService<AllianceInfo, Integer, AllianceInfoRepository>
		implements IdResolutionListener {

	@Override
	protected AllianceInfo create(Integer entityId) {
		AllianceInfo ret = new AllianceInfo();
		ret.setId(entityId);
		return ret;
	}

	@Override
	public void onNewIdResolutions(Collection<IdResolution> idResolutions) {
		createMissing(
				idResolutions.stream()
						.filter(idr -> idr.getCategory() == post_universe_names_category.alliance)
						.map(IdResolution::getId)
						.distinct().toList());
	}

}
