package fr.guiguilechat.jcelechat.libs.spring.connect.character.assets;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.assets.CharacterAsset.CharacterAssetList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedListElement;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_flag;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_assets_location_type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterAsset")
@Table(name = "esi_connect_characterasset", indexes = {
    @Index(columnList = "fetch_resource_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterAsset extends AFetchedListElement<CharacterAsset, CharacterAssetList> {

	@Entity(name = "EsiConnectCharacterAssettList")
	@Table(name = "esi_connect_characterassetlist", indexes = {
	    @Index(columnList = "fetch_active,expires")
	})
	@NoArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class CharacterAssetList
	    extends AFetchedList<Integer, R_get_characters_character_id_assets, CharacterAsset> {

	}

	/**
	 * is_blueprint_copy boolean
	 */
	private boolean blueprintCopy;

	private long itemId;

	/**
	 * location_flag string
	 */
	@Enumerated(EnumType.STRING)
	private get_characters_character_id_assets_location_flag locationFlag;
	/**
	 * location_id integer
	 */
	private long locationId;
	/**
	 * location_type string
	 */
	@Enumerated(EnumType.STRING)
	private get_characters_character_id_assets_location_type locationType;
	/**
	 * quantity integer
	 */
	private int quantity;
	/**
	 * is_singleton boolean
	 */
	private boolean singleton;
	/**
	 * type_id integer
	 */
	private int typeId;

	public static CharacterAsset from(R_get_characters_character_id_assets from) {
		return new CharacterAsset(from.is_blueprint_copy, from.item_id, from.location_flag, from.location_id,
		    from.location_type, from.quantity, from.is_singleton, from.type_id);
	}

}
