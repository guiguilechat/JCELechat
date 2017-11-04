package fr.guiguilechat.eveonline.programs.gui.panes.tools.burners;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class BurnerPane extends BorderPane implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	protected OptionsPane optionBox;

	public BurnerPane(Manager parent) {
		this.parent = parent;
		optionBox = new OptionsPane(parent);
		setTop(new TitledPane("options", optionBox));
		optionBox.modifMarket.setOnAction(e -> updateMarket());
		optionBox.modifTime.setOnAction(e -> updateTime());
		optionBox.modifMap.setOnAction(e -> updateMap());

		children = new EvePane[] { optionBox };
	}

	protected void updateMarket() {

	}

	protected void updateMap() {

	}

	protected void updateTime() {

	}

	protected void compute() {

	}

}
