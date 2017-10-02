package fr.guiguilechat.eveonline.programs.gui.panes;

import java.util.Map;

import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.gui.Manager;

/**
 * panes to react to the modification of parameters.
 * <p>
 * events are handled, then propagated to the children.The default handler does
 * nothing.
 * </p>
 *
 */
public interface EvePane {

	public Manager parent();

	public static final EvePane[] emptyPaneArray = new EvePane[] {};

	public default EvePane[] subEvePanes() {
		return emptyPaneArray;
	}

	//// debug

	public default void debug(String message) {
		parent().printDebug(getClass(), message);
	}

	// access to the database

	public default YamlDatabase db() {
		return parent().db();
	}

	//// API Modifications

	/** do not override this */
	public default void propagateNewAPI(int key, String code) {
		onNewAPI(key, code);
		for (EvePane p : subEvePanes()) {
			p.propagateNewAPI(key, code);
		}
	}

	/** override this to handle a new api */
	public default void onNewAPI(int key, String code) {
	}

	/** do not override this */
	public default void propagateDelAPI(int key) {
		for (EvePane p : subEvePanes()) {
			p.propagateDelAPI(key);
		}
		onDelAPI(key);
	}

	/** override this to handle an api deletion */
	public default void onDelAPI(int key) {
	}

	//// Team modifications

	/** do not override this */
	public default void propagateNewTeam(String name) {
		onNewTeam(name);
		for (EvePane p : subEvePanes()) {
			p.propagateNewTeam(name);
		}
	}

	/** override this to handle a new team */
	public default void onNewTeam(String name) {
	}

	/** do not override this */
	public default void propagateDelTeam(String name) {
		for (EvePane p : subEvePanes()) {
			p.propagateDelTeam(name);
		}
		onDelTeam(name);
	}

	/** override this to handle a team deletion */
	public default void onDelTeam(String name) {
	}

	/** do not override this */
	public default void propagateRenameTeam(String old, String now) {
		onRenameTeam(old, now);
		for (EvePane e : subEvePanes()) {
			e.propagateRenameTeam(old, now);
		}
	}

	/**
	 * Override this to handle a team rename. this is called after the parents
	 * have been notified, and in most case should only replace occurences of old
	 * by now.
	 *
	 * @param old
	 *          old team name
	 * @param now
	 *          new team name
	 */
	public default void onRenameTeam(String old, String now) {
	}

	/** do not override this */
	public default void propagateTeamAddLoc(String team, long locID) {
		onTeamAddLoc(team, locID);
		for (EvePane e : subEvePanes()) {
			e.propagateTeamAddLoc(team, locID);
		}
	}

	/**
	 * override this to handle a new location to a team . This is called after the
	 * parent are notified, so all parents values should be updated to reflect
	 * this new location
	 */
	public default void onTeamAddLoc(String team, long locID) {
	}

	/** do not override this */
	public default void propagateTeamDelLoc(String team, long locID) {
		onTeamDelLoc(team, locID);
		for (EvePane e : subEvePanes()) {
			e.propagateTeamDelLoc(team, locID);
		}
	}

	/**
	 * override this to handle removal of location to a team . This is called
	 * after the parent are notified, so all parents values should be updated to
	 * reflect this removal
	 */
	public default void onTeamDelLoc(String team, long locID) {
	}

	/** do not override this */
	public default void propagateAdd2Team(String team, String character) {
		onAdd2Team(team, character);
		for (EvePane p : subEvePanes()) {
			p.propagateAdd2Team(team, character);
		}
	}

	/** override this to handle a new team addition */
	public default void onAdd2Team(String team, String character) {
	}

	/** do not override this */
	public default void propagateDel2Team(String team, String character) {
		for (EvePane p : subEvePanes()) {
			p.propagateDel2Team(team, character);
		}
		onDel2Team(team, character);
	}

	/** override this to handle a new team addition */
	public default void onDel2Team(String team, String character) {
	}

	/** do not override this */
	public default void propagateFocusedTeam(String teamName) {
		for (EvePane p : subEvePanes()) {
			p.propagateFocusedTeam(teamName);
		}
		onFocusedTeam(teamName);
	}

	/** override this to handle a modification in the focused team */
	public default void onFocusedTeam(String teamName) {
	}

	/** do not override this */
	public default void propagateFocusedTeamNewItems(Map<Integer, Long> itemsDiff) {
		onFocusedTeamNewItems(itemsDiff);
		for (EvePane p : subEvePanes()) {
			p.propagateFocusedTeamNewItems(itemsDiff);
		}
	}

	/**
	 * override this to handle modification of focused team's assets. This is
	 * called with the items quantities updated in the manager.
	 *
	 * @param itemsDiff
	 *          the map of modification in number owned for each item id
	 */
	public default void onFocusedTeamNewItems(Map<Integer, Long> itemsDiff) {
	}

	// provision

	public default void propagateNewProvision(int itemID, int qtty) {
		for (EvePane p : subEvePanes()) {
			p.propagateNewProvision(itemID, qtty);
		}
		onNewProvision(itemID, qtty);
	}

	/**
	 * override this to handle when an item provision is modified. This is called
	 * before the value is set in the settings, so the settings still hold the old
	 * value.
	 *
	 * @param itemID
	 * @param qtty
	 */
	public default void onNewProvision(int itemID, int qtty) {
	}

}
