package fr.guiguilechat.jcelechat.programs.routemaker;

import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class RouteMakerController {

	@FXML
	private ChoiceBox<Region> fromregion;

	@FXML
	private ChoiceBox<System> fromsystem;

	public void initialize() {

	}

}
