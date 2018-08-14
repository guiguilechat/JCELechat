package fr.guiguilechat.jcelechat.programs.soboplace;

import fr.guiguilechat.jcelechat.esi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.esi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.tools.PriceCellFactory;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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

public class SoBoPlaceController {

	@FXML
	private ChoiceBox<Region> regionSelect;

	@FXML
	private TableView<Item> table;

	@FXML
	private TableColumn<Item, String> itemname;

	@FXML
	private TableColumn<Item, Double> itemDayVol;

	@FXML
	private TableColumn<Item, Double> itemDayGain;

	@FXML
	private TableColumn<Item, Double> itemWeekVol;

	@FXML
	private TableColumn<Item, Double> itemWeekGain;

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
		itemname.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().name));

		itemDayVol.setCellValueFactory(cell -> getItemDailyVol(cell.getValue().id).asObject());

		itemDayGain.setCellValueFactory(cell -> getItemDailyGain(cell.getValue().id).asObject());
		itemDayGain.setCellFactory(PriceCellFactory::create);

		itemWeekVol.setCellValueFactory(cell -> getItemWeeklyVol(cell.getValue().id).asObject());

		itemWeekGain.setCellValueFactory(cell -> getItemWeeklyGain(cell.getValue().id).asObject());
		itemWeekGain.setCellFactory(PriceCellFactory::create);

		table.setItems(FXCollections.observableArrayList());

		new Thread(this::load).start();
		regionSelect.getSelectionModel().select(Region.getRegion("TheForge"));
	}

	protected void load() {
		regionSelect.setItems(FXCollections.observableArrayList(Region.load().values()));
		fr.guiguilechat.jcelechat.model.sde.items.types.Module.METACAT.load().values().stream()
		.filter(a -> a.marketGroup != 0)
		.forEachOrdered(table.getItems()::add);
	}

	protected void changeRegion(ObservableValue<? extends Region> observable, Region oldValue, Region newValue) {
		if (newValue == null) {
			marketHolder.setValue(null);
		} else {
			marketHolder.setValue(ESIAccess.INSTANCE.markets.getMarket(newValue.id));
		}
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

	/**
	 * get the gain when buyingan item at BO, and selling it at BO
	 *
	 * @param typeid
	 * @return
	 */
	protected DoubleBinding getItemGain(int typeID) {
		return getItemSO(typeID).multiply(0.97).subtract(getItemBO(typeID).multiply(1.02));
	}

	protected DoubleBinding getItemDailyGain(int typeID) {
		return getItemGain(getItemDailyVol(typeID), getItemGain(typeID));
	}

	protected DoubleBinding getItemGain(DoubleProperty vol, DoubleBinding gain) {
		return Bindings.createDoubleBinding(() -> {
			if (vol.get() == 0 || !Double.isFinite(gain.get())) {
				return 0.0;
			}
			return vol.get() / (20 + vol.get()) * vol.get() * gain.get();
		}, vol, gain);
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

	protected DoubleProperty getItemDailyAVG(int typeID) {
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

	protected DoubleProperty getItemDailyVol(int typeID) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		synchronized (marketHolder) {
			if (marketHolder.getValue() != null) {
				ret.bind(marketHolder.getValue().getHistory(typeID).dailyVolume());
			}
			marketHolder.addListener((ChangeListener<RegionalMarket>) (observable, oldValue, newValue) -> {
				ret.unbind();
				if (newValue != null) {
					ret.bind(newValue.getHistory(typeID).dailyVolume());
				}
			});
		}
		return ret;
	}

	protected DoubleProperty getItemWeeklyVol(int typeID) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		synchronized (marketHolder) {
			if (marketHolder.getValue() != null) {
				ret.bind(marketHolder.getValue().getHistory(typeID).weeklyVolume());
			}
			marketHolder.addListener((ChangeListener<RegionalMarket>) (observable, oldValue, newValue) -> {
				ret.unbind();
				if (newValue != null) {
					ret.bind(newValue.getHistory(typeID).weeklyVolume());
				}
			});
		}
		return ret;
	}

	protected DoubleBinding getItemWeeklyGain(int typeID) {
		return getItemGain(getItemWeeklyVol(typeID), getItemGain(typeID));
	}

}
