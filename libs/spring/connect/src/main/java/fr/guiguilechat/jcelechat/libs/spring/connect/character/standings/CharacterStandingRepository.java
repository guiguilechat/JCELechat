package fr.guiguilechat.jcelechat.libs.spring.connect.character.standings;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.standings.CharacterStanding.CharacterStandingList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;

public interface CharacterStandingRepository
    extends IFetchedListElementRepository<CharacterStandingList, CharacterStanding> {

	@Override
	@Modifying
	@Query("delete from EsiConnectCharacterStanding where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

}
