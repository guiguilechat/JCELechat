package fr.guiguilechat.jcelechat.libs.spring.connect.character.assets;

import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.connect.generic.CharRecordListUpdater;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import lombok.Getter;

public class CharacterAssetUpdater extends
		CharRecordListUpdater<
				CharacterAssetList,
				R_get_characters_character_id_assets,
				CharacterAssetListRepository,
				CharacterAssetListService,
				CharacterAsset,
				CharacterAssetRepository> {

	@Override
	protected CharacterAsset transformRecord(R_get_characters_character_id_assets f) {
		return CharacterAsset.from(f);
	}

	@Override
	protected Requested<R_get_characters_character_id_assets[]> fetchCharacterData(ESIConnected esiConnected,
			int characterId, Map<String, String> properties) {
		return esiConnected
				.requestGetPages((page, props) -> esiConnected.get_characters_assets(characterId, page, props),
						properties)
				.mapBody(l -> l.toArray(R_get_characters_character_id_assets[]::new));
	}

	@Getter(lazy = true)
	private final Set<String> requiredScopes = Set.of("esi-assets.read_assets.v1");

}
