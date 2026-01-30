package fr.guiguilechat.jcelechat.libs.spring.anon.character.information;

import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolutionListener;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterInformationService
		extends RemoteNumberEntityService<CharacterInformation, Integer, CharacterInformationRepository>
		implements IdResolutionListener {
	@Override
	protected CharacterInformation create(Integer characterId) {
		CharacterInformation ret = new CharacterInformation();
		ret.setId(characterId);
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
