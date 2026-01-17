package fr.guiguilechat.jcelechat.libs.spring.anon.character.affiliation;

import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterAffiliationService
		extends RemoteEntityService<CharacterAffiliation, Integer, CharacterAffiliationRepository>
		implements IdResolutionListener {

	// auto management

	@Override
	protected CharacterAffiliation create(Integer Id) {
		CharacterAffiliation ret = new CharacterAffiliation();
		ret.setId(Id);
		return ret;
	}

	@Override
	public void onNewIdResolutions(Collection<IdResolution> idResolutions) {
		createMissing(
				idResolutions.stream()
						.filter(idr -> idr.getCategory() == post_universe_names_category.character)
						.map(IdResolution::getId)
						.distinct().toList());
	}

}
