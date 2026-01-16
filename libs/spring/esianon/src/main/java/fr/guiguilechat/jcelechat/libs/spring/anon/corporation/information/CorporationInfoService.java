package fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CorporationInfoService extends
		RemoteEntityService<CorporationInfo, Integer, CorporationInfoRepository>
implements IdResolutionListener {

	@Override
	protected CorporationInfo create(Integer entityId) {
		CorporationInfo ret = new CorporationInfo();
		ret.setId(entityId);
		return ret;
	}

	// create new entries when needed

	@Override
	public void onNewIdResolution(IdResolution idResolution) {
		if (idResolution.getCategory() == post_universe_names_category.corporation) {
			createIfAbsent(idResolution.getId());
		}
	}

}
