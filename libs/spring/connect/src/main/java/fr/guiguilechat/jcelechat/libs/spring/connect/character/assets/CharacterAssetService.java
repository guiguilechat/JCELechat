package fr.guiguilechat.jcelechat.libs.spring.connect.character.assets;

import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.assets.CharacterAsset.CharacterAssetList;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.ACharDataRecordListService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterAssetService extends ACharDataRecordListService<
	CharacterAssetList, 
   R_get_characters_character_id_assets,
   CharacterAssetListRepository,
   CharacterAsset,
   CharacterAssetRepository> {

	@Override
	protected CharacterAsset transformRecord(R_get_characters_character_id_assets f) {
		return CharacterAsset.from(f);
	}

	@Override
	protected Requested<R_get_characters_character_id_assets[]> fetchCharacterData(ESIConnected esiConnected,
	    int characterId, Map<String, String> properties) {
		return esiConnected.requestGetPages((page, props) -> esiConnected.get_characters_assets(characterId, page, props),
		    properties).mapBody(l -> l.toArray(R_get_characters_character_id_assets[]::new));
	}

	@Override
	protected CharacterAssetList create(Integer characterId) {
		CharacterAssetList ret = new CharacterAssetList();
		ret.setRemoteId(characterId);
		return ret;
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-assets.read_assets.v1");

	// service usage

}
