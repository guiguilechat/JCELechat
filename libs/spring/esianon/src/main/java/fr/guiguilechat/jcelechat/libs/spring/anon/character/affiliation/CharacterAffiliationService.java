package fr.guiguilechat.jcelechat.libs.spring.anon.character.affiliation;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterAffiliationService
    extends
		RemoteEntityService<CharacterAffiliation, Integer, CharacterAffiliationRepository> {

	// auto management

	@Override
	protected CharacterAffiliation create(Integer Id) {
		CharacterAffiliation ret = new CharacterAffiliation();
		ret.setId(Id);
		return ret;
	}

}
