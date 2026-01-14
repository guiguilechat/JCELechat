package fr.guiguilechat.jcelechat.libs.spring.connect.character.assets;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterAssetListService
		extends RemoteEntityService<CharacterAssetList, Integer, CharacterAssetListRepository> {

	@Override
	protected CharacterAssetList create(Integer characterId) {
		CharacterAssetList ret = new CharacterAssetList();
		ret.setId(characterId);
		return ret;
	}

}
