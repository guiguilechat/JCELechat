package fr.guiguilechat.jcelechat.programs.oreworth;

import fr.guiguilechat.jcelechat.esi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.esi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.esi.tools.MarketHelpers;
import fr.guiguilechat.jcelechat.model.sde.items.MetaInf;
import fr.guiguilechat.jcelechat.model.sde.items.attributes.CompressionQuantityNeeded;
import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;

public class OreWorthController {

	@FXML
	private ChoiceBox<Region> regionSelect;

	@FXML
	private TableView<Asteroid> table;

	@FXML
	private TableColumn<Asteroid, String> orename;

	@FXML
	private TableColumn<Asteroid, Double> oreso;

	@FXML
	private TableColumn<Asteroid, Double> oreavg;

	@FXML
	private TableColumn<Asteroid, Double> orebo;

	private Property<RegionalMarket> marketHolder = new SimpleObjectProperty<>();

	@FXML
	private void initialize() {
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
		regionSelect.getSelectionModel().selectedItemProperty().addListener(this::changeRegion);

		// make the columns access
		orename.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().name));

		oreso.setCellValueFactory(
				cell -> MarketHelpers.so(cell.getValue().id, marketHolder).divide(mineVolume(cell.getValue())).asObject());

		orebo.setCellValueFactory(
				cell -> MarketHelpers.bo(cell.getValue().id, marketHolder).divide(mineVolume(cell.getValue())).asObject());

		oreavg.setCellValueFactory(cell -> MarketHelpers.monthlyAVG(cell.getValue().id, marketHolder)
				.divide(mineVolume(cell.getValue())).asObject());

		table.setItems(FXCollections.observableArrayList());

		new Thread(this::load).start();
		regionSelect.getSelectionModel().select(Region.getRegion("TheForge"));
	}

	protected void load() {
		regionSelect.setItems(FXCollections.observableArrayList(Region.load().values()));
		Asteroid.METACAT.load().values().stream().filter(a -> a.AsteroidMetaLevel != 0)
		.forEachOrdered(table.getItems()::add);
	}

	protected void changeRegion(ObservableValue<? extends Region> observable, Region oldValue, Region newValue) {
		if (newValue == null) {
			marketHolder.setValue(null);
		} else {
			marketHolder.setValue(ESIAccess.INSTANCE.markets.getMarket(newValue.id));
		}
	}

	protected double mineVolume(Asteroid ore) {
		if (ore.name.startsWith("Compressed ")) {
			Asteroid basic = (Asteroid) MetaInf.getItem(ore.OreBasicType);
			return basic.volume * basic.attribute(CompressionQuantityNeeded.INSTANCE).doubleValue();
		}
		return ore.volume;
	}

}
