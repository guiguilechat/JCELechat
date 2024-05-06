package fr.guiguilechat.jcelechat.libs.spring.connect.character.standings;

import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStanding.CharacterStandingList;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.services.ACharDataRecordListService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterStandingService extends ACharDataRecordListService<
    CharacterStandingList,
    M_get_standings_3,
    CharacterStandingListRepository,
    CharacterStanding,
    CharacterStandingRepository> {

	@Override
	protected CharacterStanding transformRecord(M_get_standings_3 f) {
		return CharacterStanding.from(f);
	}

	@Override
	protected Requested<M_get_standings_3[]> fetchCharacterData(ESIConnected esiConnected,
	    int characterId, Map<String, String> properties) {
		return esiConnected.get_characters_standings(characterId, properties);
	}

	@Override
	protected CharacterStandingList create(Integer characterId) {
		return CharacterStandingList.builder().characterId(characterId).build();
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-characters.read_standings.v1");

}
