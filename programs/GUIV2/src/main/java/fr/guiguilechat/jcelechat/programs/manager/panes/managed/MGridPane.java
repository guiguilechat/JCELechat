package fr.guiguilechat.jcelechat.programs.manager.panes.managed;

import java.util.HashSet;

import fr.guiguilechat.jcelechat.programs.manager.DataHandler;
import fr.guiguilechat.jcelechat.programs.manager.MPane;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class MGridPane extends GridPane implements MPane {

	private final DataHandler handler;

	public MGridPane(DataHandler handler) {
		this.handler = handler;
	}

	@Override
	public DataHandler getDataHandler() {
		return handler;
	}

	HashSet<MPane> children = new HashSet<>();

	@Override
	public void setShown(boolean shown) {
		for (MPane c : children) {
			c.setShown(shown);
		}
	}

	public <T extends Node & MPane> void put(T child, int column, int row) {
		super.add(child, column, row);
		children.add(child);
	}

}
