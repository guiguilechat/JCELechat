package fr.guiguilechat.jcelechat.libs.spring.connect.character.standings;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStanding.CharacterStandingList;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list.IFetchedListElementRepository;

public interface CharacterStandingRepository
    extends IFetchedListElementRepository<CharacterStandingList, CharacterStanding> {

}
