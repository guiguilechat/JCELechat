package fr.guiguilechat.jcelechat.programs.oreworth;

import fr.guiguilechat.jcelechat.esi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.esi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.items.MetaInf;
import fr.guiguilechat.jcelechat.model.sde.items.attributes.CompressionQuantityNeeded;
import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
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
		regionSelect.setItems(FXCollections.observableArrayList(Region.load().values()));
		regionSelect.getSelectionModel().selectedItemProperty().addListener(this::changeRegion);
		regionSelect.getSelectionModel().select(Region.getRegion("TheForge"));

		// make the columns access
		orename.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().name));

		oreso.setCellValueFactory(cell -> getItemSO(cell.getValue().id).divide(mineVolume(cell.getValue())).asObject());

		orebo.setCellValueFactory(cell -> getItemBO(cell.getValue().id).divide(mineVolume(cell.getValue())).asObject());

		oreavg.setCellValueFactory(cell -> getItemAVG(cell.getValue().id).divide(mineVolume(cell.getValue())).asObject());

		table.setItems(FXCollections.observableArrayList());

		Asteroid.loadCategory().values().stream().filter(a -> a.AsteroidMetaLevel != 0)
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

	protected DoubleProperty getItemSO(int typeID) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		synchronized (marketHolder) {
			if (marketHolder.getValue() != null) {
				ret.bind(marketHolder.getValue().getSO(typeID, 1));
			}
			marketHolder.addListener((ChangeListener<RegionalMarket>) (observable, oldValue, newValue) -> {
				ret.unbind();
				if (newValue != null) {
					ret.bind(newValue.getSO(typeID, 1));
				}
			});
		}
		return ret;
	}

	protected DoubleProperty getItemBO(int typeID) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		synchronized (marketHolder) {
			if (marketHolder.getValue() != null) {
				ret.bind(marketHolder.getValue().getBO(typeID, 1));
			}
			marketHolder.addListener((ChangeListener<RegionalMarket>) (observable, oldValue, newValue) -> {
				ret.unbind();
				if (newValue != null) {
					ret.bind(newValue.getBO(typeID, 1));
				}
			});
		}
		return ret;
	}

	protected DoubleProperty getItemAVG(int typeID) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		synchronized (marketHolder) {
			if (marketHolder.getValue() != null) {
				ret.bind(marketHolder.getValue().getHistory(typeID).dailyAverage());
			}
			marketHolder.addListener((ChangeListener<RegionalMarket>) (observable, oldValue, newValue) -> {
				ret.unbind();
				if (newValue != null) {
					ret.bind(newValue.getHistory(typeID).dailyAverage());
				}
			});
		}
		return ret;
	}

}
