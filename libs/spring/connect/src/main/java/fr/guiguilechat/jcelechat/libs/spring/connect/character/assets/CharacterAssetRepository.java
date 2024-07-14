package fr.guiguilechat.jcelechat.libs.spring.connect.character.assets;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.assets.CharacterAsset.CharacterAssetList;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list.IFetchedListElementRepository;

public interface CharacterAssetRepository extends IFetchedListElementRepository<CharacterAssetList, CharacterAsset> {

	@Override
	@Modifying
	@Query("delete from EsiConnectCharacterAsset where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

}
