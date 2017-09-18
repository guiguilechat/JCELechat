package fr.guiguilechat.eveonline.programs.gui.panes;

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

	public default EvePane[] children() {
		return emptyPaneArray;
	}

	//// debug

	public default void debug(String message) {
		parent().printDebug(getClass(), message);
	}

	//// API Modifications

	/** do not override this */
	public default void propagateNewAPI(int key, String code) {
		onNewAPI(key, code);
		for (EvePane p : children()) {
			p.propagateNewAPI(key, code);
		}
	}

	/** override this to handle a new api */
	public default void onNewAPI(int key, String code) {
	}

	/** do not override this */
	public default void propagateDelAPI(int key) {
		for (EvePane p : children()) {
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
		for (EvePane p : children()) {
			p.propagateNewTeam(name);
		}
	}

	/** override this to handle a new team */
	public default void onNewTeam(String name) {
	}

	/** do not override this */
	public default void propagateDelTeam(String name) {
		for (EvePane p : children()) {
			p.propagateDelTeam(name);
		}
		onDelTeam(name);
	}

	/** override this to handle a team deletion */
	public default void onDelTeam(String name) {
	}

	/** do not override this */
	public default void propagateAdd2Team(String team, String character) {
		onAdd2Team(team, character);
		for (EvePane p : children()) {
			p.propagateAdd2Team(team, character);
		}
	}

	/** override this to handle a new team addition */
	public default void onAdd2Team(String team, String character) {
	}

	/** do not override this */
	public default void propagateDel2Team(String team, String character) {
		for (EvePane p : children()) {
			p.propagateDel2Team(team, character);
		}
		onDel2Team(team, character);
	}

	/** override this to handle a new team addition */
	public default void onDel2Team(String team, String character) {
	}

	public default void propagateFocusedTeam(String teamName) {
		for (EvePane p : children()) {
			p.propagateFocusedTeam(teamName);
		}
		onFocusedTeam(teamName);
	}

	public default void onFocusedTeam(String teamName) {
	}

}
