package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import java.util.List;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * manage the corporation of the account's bms <br />
 * basically a copypasta of the evecharacter's bookmarks
 */
@RequiredArgsConstructor
public class CorpBookmarks {

	@Getter
	protected final ESIAccount con;

	@Getter(lazy = true)
	private final MapHolder<Integer, M_get_bookmarks_9> bookmarks = makeBookmarks();

	protected MapHolder<Integer, M_get_bookmarks_9> makeBookmarks() {
		return con.connection().cache().corporations.bookmarks(con.corporation.getId()).toMap(bm -> bm.bookmark_id);
	}

	@Getter(lazy = true)
	private final MapHolder<Integer, R_get_corporations_corporation_id_bookmarks_folders> folders = makeFolders();

	protected MapHolder<Integer, R_get_corporations_corporation_id_bookmarks_folders> makeFolders() {
		return con.connection().cache().corporations.bookmarks_folders(con.corporation.getId())
				.toMap(folder -> folder.folder_id);
	}

	/**
	 * the tree of folderName->bookmarks
	 */
	@Getter(lazy = true)
	private final MapHolder<String, List<M_get_bookmarks_9>> tree = getBookmarks()
	.combine(getFolders(),
			(bms, fds) -> bms.values().stream().collect(Collectors.groupingBy(b -> fds.get(b.folder_id).name)))
	.mapMap(m -> m);

}
