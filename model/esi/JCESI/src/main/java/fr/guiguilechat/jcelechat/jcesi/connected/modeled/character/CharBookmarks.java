package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_bookmarks_folders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;

/** Manage the bookmarks of a character */
public class CharBookmarks extends fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation.CorpBookmarks {

	public CharBookmarks(ESIAccount con) {
		super(con);
	}


	@Override
	protected synchronized void makeCacheBookmarks() {
		if (cacheBookmarks == null) {
			cacheBookmarks = ObsMapHolderImpl.toMap(con.raw.cache().characters.bookmarks(con.characterId()),
					m -> m.bookmark_id);
		}
	}

	@Override
	protected synchronized void makeCacheFolders() {
		if (cacheFolders == null) {
			cacheFolders = ObsMapHolderImpl.map(
					ObsMapHolderImpl.toMap(con.raw.cache().characters.bookmarks_folders(con.characterId()), m -> m.folder_id),
					this::convert);
		}
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
