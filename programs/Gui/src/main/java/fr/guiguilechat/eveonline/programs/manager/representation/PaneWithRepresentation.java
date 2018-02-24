package fr.guiguilechat.eveonline.programs.manager.representation;

import java.util.Collection;

/**
 * a pane that represents attributes through GUI
 *
 */
public interface PaneWithRepresentation {

	public Collection<Representation<?>> GetRepresentations();

	/**
	 * update the setting with each representation's value
	 *
	 * @return true if at least one representation had a different gui value than
	 *         the parameter it represents (meaning the user modified it).
	 */
	public default boolean updateSettings() {
		return GetRepresentations().stream().filter(Representation::updateValue).count() > 0;
	}

	public default boolean reloadSettings() {
		return GetRepresentations().stream().filter(Representation::reload).count() > 0;
	}

}
