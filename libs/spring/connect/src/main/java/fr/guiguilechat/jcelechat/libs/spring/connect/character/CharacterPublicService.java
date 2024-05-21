package fr.guiguilechat.jcelechat.libs.spring.connect.character;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterAffiliationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.informations.CharacterInformationService;
import fr.guiguilechat.jcelechat.libs.spring.connect.resolve.IdResolutionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * service to regroup character public data
 */
@Getter
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterPublicService {

	private final CharacterAffiliationService characterAffiliationService;

	private final CharacterInformationService characterInformationService;

	private final IdResolutionService idResolutionService;

	public void observe(Iterable<Integer> characterIds) {
		// TODO
	}

}
