package fr.guiguilechat.eveonline.model.esi.modeled.evecharacter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_bookmarks;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_bookmarks_folders;
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

	private ObservableMap<Integer, ObservableMap<Integer, R_get_characters_character_id_bookmarks>> bookmarksByFolderId = FXCollections
			.observableHashMap();

	protected void handleNewBookmarks(List<R_get_characters_character_id_bookmarks> bms) {
		synchronized (bookmarksByFolderId) {
			for (R_get_characters_character_id_bookmarks f : bms) {
				ObservableMap<Integer, R_get_characters_character_id_bookmarks> m = bookmarksByFolderId.get(f.folder_id);
				if (m == null) {
					m = FXCollections.observableMap(new LinkedHashMap<>());
					bookmarksByFolderId.put(f.folder_id, m);
				}
				m.put(f.bookmark_id, f);
				for (Entry<Integer, ObservableMap<Integer, R_get_characters_character_id_bookmarks>> e : bookmarksByFolderId
						.entrySet()) {
					if (e.getKey() != f.folder_id) {
						e.getValue().remove(f.bookmark_id);
					}
				}
			}
		}
	}

	private ObservableMap<String, ObservableMap<Integer, R_get_characters_character_id_bookmarks>> bookmarks = null;

	/**
	 *
	 * @return the cached list of observable bookmarks, by folder->id->bookmark.
	 */
	public ObservableMap<String, ObservableMap<Integer, R_get_characters_character_id_bookmarks>> getBookmarks() {
		if (bookmarks == null) {
			synchronized (this) {
				if (bookmarks == null) {
					bookmarks = FXCollections.observableHashMap();
					foldersById = FXCollections.observableHashMap();
					bookmarksByFolderId = FXCollections.observableHashMap();

					foldersById.addListener(this::onFolderChange);
					bookmarksByFolderId.addListener(this::onBMChange);

					con.addFetchCacheArray(con.characterName() + ".bmFolders",
							(p, h) -> con.raw.get_characters_character_id_bookmarks_folders(con.characterId(), p, h),
							this::handleNewBMFolders);
					con.addFetchCacheArray(con.characterName() + ".bms",
							(p, h) -> con.raw.get_characters_character_id_bookmarks(con.characterId(), p, h),
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
					ObservableMap<Integer, R_get_characters_character_id_bookmarks> add = bookmarksByFolderId
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
			Change<? extends Integer, ? extends ObservableMap<Integer, R_get_characters_character_id_bookmarks>> change) {
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
