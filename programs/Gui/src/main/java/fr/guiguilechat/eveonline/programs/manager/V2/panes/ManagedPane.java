package fr.guiguilechat.eveonline.programs.manager.V2.panes;

import fr.guiguilechat.eveonline.programs.manager.V2.DataHandler;

public interface ManagedPane {

	public DataHandler getDataHandler();

	public default void setShown(boolean shown) {
	}
}
