package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.LockWatchDog;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.impl.ObsMapHolderImpl;
import fr.guiguilechat.jcelechat.jcesi.interfaces.ObsMapHolder;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener.Change;
import javafx.collections.ObservableMap;

/**
 * manage the corporation of the account's bms <br />
 * basically a copypasta of the evecharacter's bookmarks
 */
public class CorpBookmarks {

	protected final ESIAccount con;

	public CorpBookmarks(ESIAccount con) {
		this.con = con;
	}

	private ObservableMap<String, ObservableMap<String, M_get_bookmarks_9>> bookmarks = null;

	private ObsMapHolder<String, ObservableMap<String, M_get_bookmarks_9>> bookmarksHolder = null;

	/**
	 *
	 * @return the cached list of observable bookmarks, by folder
	 *         name->id->bookmark.
	 */
	public ObsMapHolder<String, ObservableMap<String, M_get_bookmarks_9>> getBookmarks() {
		if (bookmarksHolder == null) {
			LockWatchDog.BARKER.tak(this);
			synchronized (this) {
				LockWatchDog.BARKER.hld(this);
				if (bookmarksHolder == null) {
					LockWatchDog.BARKER.hld(this);
					bookmarks = FXCollections.observableHashMap();
					bookmarksHolder = new ObsMapHolderImpl<>(bookmarks);
					bookmarksFolders().follow(this::onFolderChange);
					bookmarks().follow(this::onBmChange);
				}
			}
			LockWatchDog.BARKER.rel(this);
		}
		return bookmarksHolder;
	}

	private Map<String, R_get_corporations_corporation_id_bookmarks_folders> knownFoldersByRef = new HashMap<>();

	/** listener when a folder name is modified */
	private void onFolderChange(
			Change<? extends String, ? extends R_get_corporations_corporation_id_bookmarks_folders> change) {
		LockWatchDog.BARKER.tak(bookmarks);
		synchronized (bookmarks) {
			LockWatchDog.BARKER.hld(bookmarks);
			if (change.wasRemoved() && change.wasAdded()) {
				// folder was changed
				bookmarks.remove(change.getValueRemoved().name);
				bookmarks.put(change.getValueAdded().name, bookmarks.remove(change.getValueRemoved().name));
				knownFoldersByRef.put(change.getKey(), change.getValueAdded());
			} else {
				if (change.wasAdded()) {
					ObservableMap<String, M_get_bookmarks_9> add = FXCollections.observableHashMap();
					knownBMByRef.entrySet().stream().filter(e -> e.getValue().folder_id == change.getValueAdded().folder_id)
					.forEachOrdered(e -> add.put(e.getKey(), e.getValue()));
					bookmarks.put(change.getValueAdded().name, add);
					knownFoldersByRef.put(change.getKey(), change.getValueAdded());
				} else {
					bookmarks.remove(change.getValueRemoved().name);
					knownFoldersByRef.remove(change.getKey());
				}
			}
		}
		LockWatchDog.BARKER.rel(bookmarks);
	}

	private Map<String, M_get_bookmarks_9> knownBMByRef = new HashMap<>();

	/** listener when a bm is modified */
	private void onBmChange(Change<? extends String, ? extends M_get_bookmarks_9> c) {
		LockWatchDog.BARKER.tak(bookmarks);
		synchronized (bookmarks) {
			LockWatchDog.BARKER.hld(bookmarks);
			if (c.wasRemoved()) {
				M_get_bookmarks_9 removed = c.getValueRemoved();
				bookmarks.values().forEach(om -> om.entrySet().removeIf(e -> e.getValue().bookmark_id == removed.bookmark_id));
				knownBMByRef.remove(c.getKey());
			}
			if (c.wasAdded()) {
				M_get_bookmarks_9 added = c.getValueAdded();
				R_get_corporations_corporation_id_bookmarks_folders folder = knownFoldersByRef.get("" + added.folder_id);
				if (folder != null) {
					String name = folder.name;
					if (name != null && bookmarks.containsKey(name)) {
						bookmarks.get(name).put("", c.getValueAdded());
					}
				}
				knownBMByRef.put(c.getKey(), c.getValueAdded());
			}
		}
		LockWatchDog.BARKER.rel(bookmarks);
	}

	protected ObsMapHolder<String, M_get_bookmarks_9> cacheBookmarks = null;

	/**
	 * get the corp id-> bm raw data
	 *
	 * @return a new map if not cached, or the one cached if already called.
	 */
	protected ObsMapHolder<String, M_get_bookmarks_9> bookmarks() {
		if (cacheBookmarks == null) {
			makeCacheBookmarks();
		}
		return cacheBookmarks;
	}

	protected synchronized void makeCacheBookmarks() {
		if (cacheBookmarks == null) {
			cacheBookmarks = ObsMapHolderImpl.toMap(
					con.raw.cache.corporations.bookmarks(con.character.infos.corporationId().get()), m -> "" + m.bookmark_id);
		}
	}

	protected ObsMapHolder<String, R_get_corporations_corporation_id_bookmarks_folders> cacheFolders = null;

	/**
	 * get the corp id-> bm_folder raw data
	 *
	 * @return a new map if not cached, or the one cached if already called.
	 */
	protected ObsMapHolder<String, R_get_corporations_corporation_id_bookmarks_folders> bookmarksFolders() {
		if (cacheFolders == null) {
			makeCacheFolders();
		}
		return cacheFolders;
	}

	protected synchronized void makeCacheFolders() {
		if (cacheFolders == null) {
			cacheFolders = ObsMapHolderImpl.toMap(
					con.raw.cache.corporations.bookmarks_folders(con.character.infos.corporationId().get()),
					m -> "" + m.folder_id);
		}
	}

}
