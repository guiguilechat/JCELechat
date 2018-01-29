package fr.guiguilechat.eveonline.programs.manager;

public interface MPane {

	public DataHandler getDataHandler();

	public default void setShown(boolean shown) {
	}
}
