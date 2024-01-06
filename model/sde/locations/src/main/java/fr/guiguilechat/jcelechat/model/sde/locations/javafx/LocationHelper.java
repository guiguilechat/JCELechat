package fr.guiguilechat.jcelechat.model.sde.locations.javafx;

import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

public class LocationHelper {

	public static StringConverter<Region> regionConverter(){
		return new StringConverter<>() {
			@Override
			public String toString(Region object) {
				return object != null ? object.name : "ANY";
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
		box.getItems().addAll(Region.load().values());
	}
}
