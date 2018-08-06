package fr.guiguilechat.jcelechat.programs.oreworth;

import fr.guiguilechat.jcelechat.esi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.esi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

public class OreWorthController {

	public static class OreData {
		public ObservableValue<String> name;
		public ObservableValue<Double> so;
		public ObservableValue<Double> avg;
		public ObservableValue<Double> bo;
		public ObservableValue<Double> minedVolume;
	}

	@FXML
	private ChoiceBox<Region> regionSelect;

	@FXML
	private TableView<OreData> table;

	private Property<RegionalMarket> marketHolder = new SimpleObjectProperty<>();


	@FXML
	private void initialize(){
		regionSelect.setConverter(new StringConverter<Region>() {
			@Override
			public String toString(Region object) {
				return object.name;
			}

			@Override
			public Region fromString(String string) {
				throw new UnsupportedOperationException("can't transform name into region");
			}
		});
		regionSelect.setItems(FXCollections.observableArrayList(Region.load().values()));
		regionSelect.getSelectionModel().selectedItemProperty().addListener(this::changeRegion);

	}

	protected void changeRegion(ObservableValue<? extends Region> observable, Region oldValue, Region newValue) {
		if (newValue == null) {
			marketHolder.setValue(null);
		} else {
			marketHolder.setValue(ESIAccess.INSTANCE.markets.getMarket(newValue.id));
		}
	}

}
