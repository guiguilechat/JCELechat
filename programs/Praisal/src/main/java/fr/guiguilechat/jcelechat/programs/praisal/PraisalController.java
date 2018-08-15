package fr.guiguilechat.jcelechat.programs.praisal;

import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.esi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.esi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.esi.tools.MarketHelpers;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaInf;
import fr.guiguilechat.jcelechat.model.sde.locations.LocationHelper;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.tools.JFXTools;
import fr.guiguilechat.tools.PriceCellFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PraisalController {

	@FXML
	private ChoiceBox<Region> regionSelect;

	@FXML
	private TextArea copyfield;

	@FXML
	private TextField volumicprice;

	private DoubleProperty volumicpriceproperty;

	@FXML
	private TextField buybacktax;

	private DoubleProperty buybacktaxproperty;


	@FXML
	private TableView<Item> table;

	@FXML
	private TableColumn<Item, String> itemname;

	@FXML
	private TableColumn<Item, Double> itembo;

	@FXML
	private TableColumn<Item, Double> itemavgvol;

	@FXML
	private TableColumn<Item, Double> itembovol;

	@FXML
	private TableColumn<Item, Double> itembuyback;

	private Property<RegionalMarket> marketHolder = new SimpleObjectProperty<>();

	@FXML
	private void initialize() {
		regionSelect.getSelectionModel().selectedItemProperty().addListener(this::changeRegion);

		buybacktaxproperty = JFXTools.convertDouble(buybacktax.textProperty(), d -> d >= 0);
		buybacktaxproperty.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> table.sort());

		volumicpriceproperty = JFXTools.convertDouble(volumicprice.textProperty(), d -> d >= 0);
		volumicpriceproperty.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> table.sort());

		copyfield.textProperty().addListener(this::onCopyUpdate);

		// make the columns access
		itemname.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().name));

		itembo.setCellValueFactory(cell -> MarketHelpers.bo(cell.getValue().id, marketHolder).asObject());
		itembo.setCellFactory(col -> new PriceCellFactory<>());

		itemavgvol.setCellValueFactory(
				cell -> MarketHelpers.monthlyAVG(cell.getValue().id, marketHolder).divide(cell.getValue().volume).asObject());
		itemavgvol.setCellFactory(col -> new PriceCellFactory<>());

		itembovol.setCellValueFactory(
				cell -> MarketHelpers.bo(cell.getValue().id, marketHolder).divide(cell.getValue().volume).asObject());
		itembovol.setCellFactory(col -> new PriceCellFactory<>());

		// buyback gain = bo *(1-tax/100) - volume*volumicprice
		itembuyback.setCellValueFactory(cell -> MarketHelpers.bo(cell.getValue().id, marketHolder)
				.multiply(buybacktaxproperty.divide(100).negate().add(1.0))
				.subtract(volumicpriceproperty.multiply(cell.getValue().volume)).asObject());
		itembuyback.setCellFactory(col -> new PriceCellFactory<>());

		itembuyback.setSortType(SortType.DESCENDING);
		table.getSortOrder().add(itembuyback);
		table.setItems(FXCollections.observableArrayList());

		new Thread(this::load).start();
	}

	protected void load() {
		LocationHelper.initRegion(regionSelect);
		regionSelect.getSelectionModel().select(Region.getRegion("TheForge"));
	}

	protected void changeRegion(ObservableValue<? extends Region> observable, Region oldValue, Region newValue) {
		if (newValue == null) {
			marketHolder.setValue(null);
		} else {
			marketHolder.setValue(ESIAccess.INSTANCE.markets.getMarket(newValue.id));
		}
	}

	protected void onCopyUpdate(Object o, String previous, String now) {
		table.getItems().clear();
		parse(now).forEach(table.getItems()::add);
		table.sort();
	}

	public static Stream<Item> parse(String list) {
		System.err.println("parsint items : \n" + list);
		return Stream.of(list.split("\n")).map(arr -> MetaInf.getItem(arr.split("\t")[0])).filter(i -> i != null);
	}

}
