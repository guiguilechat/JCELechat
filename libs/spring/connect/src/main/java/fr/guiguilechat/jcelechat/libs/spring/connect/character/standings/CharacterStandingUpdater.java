package fr.guiguilechat.jcelechat.libs.spring.connect.character.standings;

import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.generic.CharRecordListUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import lombok.Getter;

public class CharacterStandingUpdater
	extends CharRecordListUpdater<
		CharacterStandingList,
		M_get_standings_3,
		CharacterStandingListRepository,
		CharacterStandingListService,
		CharacterStanding,
		CharacterStandingRepository>  {

	@Override
	protected CharacterStanding transformRecord(M_get_standings_3 f) {
		return CharacterStanding.from(f);
	}

	@Override
	protected Requested<M_get_standings_3[]> fetchCharacterData(ESIConnected esiConnected,
			int Id, Map<String, String> properties) {
		return esiConnected.get_characters_standings(Id, properties);
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-characters.read_standings.v1");

}
