package fr.guiguilechat.jcelechat.model.sde.locations;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

public class LocationHelper {

	public static StringConverter<Region> regionConverter(){
		return new StringConverter<>() {
			@Override
			public String toString(Region object) {
				return object.name;
			}

			@Override
			public Region fromString(String string) {
				return Region.load().get(string);
			}
		};
	}

	/**
	 * initialize a choicebox with the possible regions
	 *
	 * @param box
	 */
	public static void initRegion(ChoiceBox<Region> box) {
		box.setConverter(regionConverter());
		box.setItems(FXCollections.observableArrayList(Region.load().values()));
	}
}
