package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_bookmarks_9;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_bookmarks_folders;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
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

	/**
	 * folder name-> bm id-> bm data
	 */
	private ObservableMap<String, ObservableMap<Integer, M_get_bookmarks_9>> bookmarks = null;

	private ObsMapHolderImpl<String, ObservableMap<Integer, M_get_bookmarks_9>> bookmarksHolder = null;

	/**
	 *
	 * @return the cached list of observable bookmarks, by folder
	 *         name->id->bookmark.
	 */
	public ObsMapHolder<String, ObservableMap<Integer, M_get_bookmarks_9>> getBookmarks() {
		if (bookmarksHolder == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (bookmarksHolder == null) {
					bookmarks = FXCollections.observableHashMap();
					bookmarksHolder = new ObsMapHolderImpl<>(bookmarks);
					bookmarksFolders().followEntries(this::onFolderChange);
					bookmarksFolders().follow(this::dataReceveidFolders);
					bookmarks().followEntries(this::onBmChange);
					bookmarks().follow(this::dataReceveidBM);
				}
			});
		}
		return bookmarksHolder;
	}

	private Map<Integer, R_get_corporations_corporation_id_bookmarks_folders> knownFoldersByRef = new HashMap<>();

	/** listener when a folder name is modified */
	private void onFolderChange(
			Change<? extends Integer, ? extends R_get_corporations_corporation_id_bookmarks_folders> change) {
		LockWatchDog.BARKER.syncExecute(bookmarks, () -> {
			if (change.wasRemoved() && change.wasAdded()) {
				// folder was changed
				bookmarks.remove(change.getValueRemoved().name);
				bookmarks.put(change.getValueAdded().name, bookmarks.remove(change.getValueRemoved().name));
				knownFoldersByRef.put(change.getKey(), change.getValueAdded());
			} else {
				if (change.wasAdded()) {
					knownFoldersByRef.put(change.getKey(), change.getValueAdded());
					ObservableMap<Integer, M_get_bookmarks_9> add = FXCollections.observableHashMap();
					knownBMByRef.entrySet().stream().filter(e -> e.getValue().folder_id == change.getValueAdded().folder_id)
					.forEachOrdered(e -> add.put(e.getKey(), e.getValue()));
					bookmarks.put(change.getValueAdded().name, add);
				} else {
					knownFoldersByRef.remove(change.getKey());
					bookmarks.remove(change.getValueRemoved().name);
				}
			}
		});
	}

	private Map<Integer, M_get_bookmarks_9> knownBMByRef = new HashMap<>();

	/** listener when a bm is modified */
	private void onBmChange(Change<? extends Integer, ? extends M_get_bookmarks_9> c) {
		LockWatchDog.BARKER.syncExecute(bookmarks, () -> {
			if (c.wasRemoved()) {
				M_get_bookmarks_9 removed = c.getValueRemoved();
				bookmarks.values().forEach(om -> om.entrySet().removeIf(e -> e.getValue().bookmark_id == removed.bookmark_id));
				knownBMByRef.remove(c.getKey());
			}
			if (c.wasAdded()) {
				M_get_bookmarks_9 added = c.getValueAdded();
				knownBMByRef.put(c.getKey(), added);
				R_get_corporations_corporation_id_bookmarks_folders folder = knownFoldersByRef.get(added.folder_id);
				if (folder != null) {
					String name = folder.name;
					bookmarks.get(name).put(c.getKey(), c.getValueAdded());
				}
			}
		});
	}

	protected ObsMapHolder<Integer, M_get_bookmarks_9> cacheBookmarks = null;

	/**
	 * get the corp id-> bm raw data
	 *
	 * @return a new map if not cached, or the one cached if already called.
	 */
	protected ObsMapHolder<Integer, M_get_bookmarks_9> bookmarks() {
		if (cacheBookmarks == null) {
			makeCacheBookmarks();
		}
		return cacheBookmarks;
	}

	/**
	 * only preent to be overloaded in character bookmark
	 */
	protected synchronized void makeCacheBookmarks() {
		if (cacheBookmarks == null) {
			cacheBookmarks = ObsMapHolderImpl
					.toMap(con.connection().cache().corporations.bookmarks(con.character.infos.corporationId().get()),
							m -> m.bookmark_id);
		}
	}

	protected ObsMapHolder<Integer, R_get_corporations_corporation_id_bookmarks_folders> cacheFolders = null;

	/**
	 * get the corporation's id-> bm_folder raw data
	 *
	 * @return a new map if not cached, or the one cached if already called.
	 */
	protected ObsMapHolder<Integer, R_get_corporations_corporation_id_bookmarks_folders> bookmarksFolders() {
		if (cacheFolders == null) {
			makeCacheFolders();
		}
		return cacheFolders;
	}

	/**
	 * only preent to be overloaded in character bookmark
	 */
	protected synchronized void makeCacheFolders() {
		if (cacheFolders == null) {
			cacheFolders = ObsMapHolderImpl.toMap(
					con.connection().cache().corporations.bookmarks_folders(con.character.infos.corporationId().get()),
					m -> m.folder_id);
		}
	}

	private boolean datareceivedBM = false;

	protected void dataReceveidBM(Map<Integer, M_get_bookmarks_9> data) {
		datareceivedBM = true;
		synchronized (bookmarks) {
			if (dataReceveidFolders) {
				bookmarksHolder.dataReceived();
			}
		}
	}

	private boolean dataReceveidFolders = false;

	protected void dataReceveidFolders(Map<Integer, R_get_corporations_corporation_id_bookmarks_folders> data) {
		dataReceveidFolders = true;
		synchronized (bookmarks) {
			if (datareceivedBM) {
				bookmarksHolder.dataReceived();
			}
		}
	}

}
