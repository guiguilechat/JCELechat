package fr.guiguilechat.eveonline.programs.gui.panes.provision;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.SelectTeamPane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ProvisionPane extends BorderPane implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected GridPane selectPane = new GridPane();

	protected ChoiceBox<String> provisionModeChoice = new ChoiceBox<>();

	protected ProvisionLPStorePane lpstore;

	protected SelectTeamPane selectTeam;

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	public ProvisionPane(Manager parent) {
		this.parent = parent;
		selectPane.add(new Label("team :"), 0, 0);
		selectTeam = new SelectTeamPane(parent);
		selectPane.add(selectTeam, 1, 0);
		selectPane.add(new Label(" "), 2, 0);
		selectPane.add(new Label("provision mode :"), 3, 0);
		selectPane.add(provisionModeChoice, 4, 0);
		setTop(selectPane);
		provisionModeChoice.getItems().addAll("lpstore");
		provisionModeChoice.setOnAction(ev -> adaptProvisionMode());

		lpstore = new ProvisionLPStorePane(parent);
		children = new EvePane[] { selectTeam, lpstore };

	}

	protected void adaptProvisionMode() {
		switch(provisionModeChoice.getValue()) {
		case "lpstore":
			showLPStore();
			break;
		}
	}

	public void showLPStore() {
		lpstore.load();
		setCenter(lpstore);
	}

}
