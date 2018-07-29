package fr.guiguilechat.jcelechat.programs.manager;

public interface MPane {

	public DataHandler getDataHandler();

	public default void setShown(boolean shown) {
	}
}
