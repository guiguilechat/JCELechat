package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_bookmarks_folders;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableMap;

/** Manage the bookmarks of a character */
public class Bookmarks {

	private final ESIAccount con;

	public Bookmarks(ESIAccount con) {
		this.con = con;
	}

	private ObservableMap<Integer, String> foldersById = FXCollections.observableHashMap();

	protected void handleNewBMFolders(List<R_get_characters_character_id_bookmarks_folders> folder) {
		Map<Integer, String> map = folder.stream().collect(Collectors.toMap(f -> f.folder_id, f -> f.name));
		synchronized (foldersById) {
			foldersById.keySet().retainAll(map.keySet());
			foldersById.putAll(map);
		}
	}

	private ObservableMap<Integer, ObservableMap<Integer, M_get_bookmarks_9>> bookmarksByFolderId = FXCollections
			.observableHashMap();

	protected void handleNewBookmarks(List<M_get_bookmarks_9> bms) {
		synchronized (bookmarksByFolderId) {
			for (M_get_bookmarks_9 f : bms) {
				ObservableMap<Integer, M_get_bookmarks_9> m = bookmarksByFolderId.get(f.folder_id);
				if (m == null) {
					m = FXCollections.observableMap(new LinkedHashMap<>());
					bookmarksByFolderId.put(f.folder_id, m);
				}
				m.put(f.bookmark_id, f);
				for (Entry<Integer, ObservableMap<Integer, M_get_bookmarks_9>> e : bookmarksByFolderId
						.entrySet()) {
					if (e.getKey() != f.folder_id) {
						e.getValue().remove(f.bookmark_id);
					}
				}
			}
		}
	}

	private ObservableMap<String, ObservableMap<Integer, M_get_bookmarks_9>> bookmarks = null;

	/**
	 *
	 * @return the cached list of observable bookmarks, by folder->id->bookmark.
	 */
	public ObservableMap<String, ObservableMap<Integer, M_get_bookmarks_9>> getBookmarks() {
		System.err.println("get bms for " + con.characterName());
		if (bookmarks == null) {
			synchronized (this) {
				if (bookmarks == null) {
					bookmarks = FXCollections.observableHashMap();
					foldersById = FXCollections.observableHashMap();
					bookmarksByFolderId = FXCollections.observableHashMap();

					foldersById.addListener(this::onFolderChange);
					bookmarksByFolderId.addListener(this::onBMChange);

					con.raw.cache.addFetchCacheArray(con.characterName() + ".bmFolders",
							(p, h) -> con.raw.get_characters_bookmarks_folders(con.characterId(), p, h),
							this::handleNewBMFolders);
					con.raw.cache.addFetchCacheArray(con.characterName() + ".bms",
							(p, h) -> con.raw.get_characters_bookmarks(con.characterId(), p, h),
							this::handleNewBookmarks);

				}
			}
		}
		return bookmarks;
	}

	/** listener when a folder name is modified */
	private void onFolderChange(Change<? extends Integer, ? extends String> change) {
		synchronized (bookmarks) {
			if (change.wasRemoved() && change.wasAdded()) {
				// folder was renamed
				bookmarks.put(change.getValueAdded(), bookmarks.get(change.getValueRemoved()));
				bookmarks.remove(change.getValueRemoved());
			} else {
				if (change.wasAdded()) {
					ObservableMap<Integer, M_get_bookmarks_9> add = bookmarksByFolderId
							.get(change.getKey());
					if (add == null) {
						add = FXCollections.observableHashMap();
					}
					bookmarks.put(change.getValueAdded(), add);
				} else {
					bookmarks.remove(change.getValueRemoved());
				}
			}
		}
	}

	/** listener when a folder is added */
	private void onBMChange(
			Change<? extends Integer, ? extends ObservableMap<Integer, M_get_bookmarks_9>> change) {
		synchronized (bookmarks) {
			if (change.wasAdded()) {
				String name = foldersById.get(change.getKey());
				if (name != null) {
					bookmarks.put(name, change.getValueAdded());
				}
			}
		}
	}

}
