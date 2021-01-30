package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_bookmarks_folders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;

/** Manage the bookmarks of a character */
public class CharBookmarks extends fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.CorpBookmarks {

	public CharBookmarks(ESIAccount con) {
		super(con);
	}

	@Override
	protected ObsMapHolder<Integer, M_get_bookmarks_9> makeBookmarks() {
		return con.connection().cache().characters.bookmarks(con.characterId()).toMap(bm -> bm.bookmark_id);
	}

	@Override
	protected ObsMapHolder<Integer, R_get_corporations_corporation_id_bookmarks_folders> makeFolders() {
		return con.connection().cache().characters.bookmarks_folders(con.characterId()).mapItems(this::convert)
				.toMap(folder -> folder.folder_id);
	}

	protected R_get_corporations_corporation_id_bookmarks_folders convert(
			R_get_characters_character_id_bookmarks_folders origin) {
		R_get_corporations_corporation_id_bookmarks_folders ret = new R_get_corporations_corporation_id_bookmarks_folders();
		ret.folder_id = origin.folder_id;
		ret.name = origin.name;
		ret.creator_id = con.characterId();
		return ret;
	}

}
