package fr.guiguilechat.jcelechat.programs.praisal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.jcesi.tools.MarketHelpers;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.ItemIndex;
import fr.guiguilechat.jcelechat.model.sde.locations.LocationHelper;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.tools.JFXTools;
import fr.guiguilechat.tools.PriceCellFactory;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PraisalController {

	@FXML
	private TextArea copyfield;

	@FXML
	private TextField bbk1volumicprice, bbk2volumicprice;

	private DoubleProperty bbk1volumicpriceProperty, bbk2volumicpriceProperty;

	@FXML
	private TextField bbk1tax, bbk2tax;

	private DoubleProperty bbk1taxProperty, bbk2taxProperty;

	@FXML
	private CheckBox bbk1, bbk2;

	@FXML
	private ChoiceBox<Region> regionSelect1, regionSelect2;

	private Property<RegionalMarket> marketHolder1 = new SimpleObjectProperty<>(),
			marketHolder2 = new SimpleObjectProperty<>();

	@FXML
	private TableView<Entry<Item, Integer>> table;

	@FXML
	private TableColumn<Entry<Item, Integer>, String> itemname;

	@FXML
	private TableColumn<Entry<Item, Integer>, Double> itembo;

	@FXML
	private TableColumn<Entry<Item, Integer>, Double> itemavgvol;

	@FXML
	private TableColumn<Entry<Item, Integer>, Double> itembovol;

	@FXML
	private TableColumn<Entry<Item, Integer>, Double> itembuyback1;

	@FXML
	private TableColumn<Entry<Item, Integer>, Double> itembuyback2;

	@FXML
	private void initialize() {
		regionSelect1.getSelectionModel().selectedItemProperty().addListener(this::changeRegion1);
		regionSelect2.getSelectionModel().selectedItemProperty().addListener(this::changeRegion2);

		bbk1taxProperty = JFXTools.convertDouble(bbk1tax.textProperty(), d -> d >= 0);
		bbk1taxProperty.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> table.sort());

		bbk1volumicpriceProperty = JFXTools.convertDouble(bbk1volumicprice.textProperty(), d -> d >= 0);
		bbk1volumicpriceProperty.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> table.sort());

		bbk2taxProperty = JFXTools.convertDouble(bbk2tax.textProperty(), d -> d >= 0);
		bbk2taxProperty.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> table.sort());

		bbk2volumicpriceProperty = JFXTools.convertDouble(bbk2volumicprice.textProperty(), d -> d >= 0);
		bbk2volumicpriceProperty.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> table.sort());

		copyfield.textProperty().addListener(this::onCopyUpdate);
		copyfield.focusedProperty().addListener(
				(ChangeListener<Boolean>) (observable, oldValue, newValue) -> Platform.runLater(() -> copyfield.selectAll()));

		// make the columns access
		itemname.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getKey().name));

		itembo.setCellValueFactory(cell -> MarketHelpers.bo(cell.getValue().getKey().id, marketHolder1)
				.multiply(cell.getValue().getValue()).asObject());
		itembo.setCellFactory(col -> new PriceCellFactory<>());

		itemavgvol.setCellValueFactory(cell -> MarketHelpers.monthlyAVG(cell.getValue().getKey().id, marketHolder1)
				.divide(cell.getValue().getKey().volume).asObject());
		itemavgvol.setCellFactory(col -> new PriceCellFactory<>());

		itembovol.setCellValueFactory(cell -> MarketHelpers.bo(cell.getValue().getKey().id, marketHolder1)
				.divide(cell.getValue().getKey().volume).asObject());
		itembovol.setCellFactory(col -> new PriceCellFactory<>());

		// buyback gain = bo *(1-tax/100) - volume*volumicprice
		itembuyback1.setCellValueFactory(cell -> MarketHelpers.bo(cell.getValue().getKey().id, marketHolder1)
				.multiply(bbk1taxProperty.divide(100).negate().add(1.0))
				.subtract(bbk1volumicpriceProperty.multiply(cell.getValue().getKey().volume))
				.multiply(cell.getValue().getValue()).asObject());
		itembuyback1.setCellFactory(col -> new PriceCellFactory<>());
		itembuyback1.visibleProperty().bind(bbk1.selectedProperty());
		itembuyback1.setSortType(SortType.DESCENDING);

		itembuyback2.setCellValueFactory(cell -> MarketHelpers.bo(cell.getValue().getKey().id, marketHolder1)
				.multiply(bbk2taxProperty.divide(100).negate().add(1.0))
				.subtract(bbk2volumicpriceProperty.multiply(cell.getValue().getKey().volume))
				.multiply(cell.getValue().getValue()).asObject());
		itembuyback2.setCellFactory(col -> new PriceCellFactory<>());
		itembuyback2.visibleProperty().bind(bbk2.selectedProperty());

		table.getSortOrder().add(itembo);
		table.setItems(FXCollections.observableArrayList());

		new Thread(this::load).start();
	}

	protected void load() {
		LocationHelper.initRegion(regionSelect1);
		regionSelect1.getSelectionModel().select(Region.getRegion("TheForge"));
		LocationHelper.initRegion(regionSelect2);
		regionSelect2.getSelectionModel().select(Region.getRegion("TheForge"));
	}

	protected void changeRegion1(ObservableValue<? extends Region> observable, Region oldValue, Region newValue) {
		if (newValue == null) {
			marketHolder1.setValue(null);
		} else {
			marketHolder1.setValue(ESIAccess.INSTANCE.markets.getMarket(newValue.id));
		}
	}

	protected void changeRegion2(ObservableValue<? extends Region> observable, Region oldValue, Region newValue) {
		if (newValue == null) {
			marketHolder2.setValue(null);
		} else {
			marketHolder2.setValue(ESIAccess.INSTANCE.markets.getMarket(newValue.id));
		}
	}

	protected void onCopyUpdate(Object o, String previous, String now) {
		table.getItems().clear();
		parse(now).entrySet().forEach(table.getItems()::add);
		table.sort();
	}

	public static Map<Item, Integer> parse(String list) {
		HashMap<Item, Integer> item2qtty = new HashMap<>();
		for (String line : list.split("\n")) {
			String[] arr = line.split("\t");
			Item it = ItemIndex.getItem(arr[0]);
			if (it != null && arr[1].length() > 0) {
				item2qtty.put(it, Integer.parseInt(arr[1].replaceAll("[^0-9]", "")) + item2qtty.getOrDefault(it, 0));
			}
		}
		return item2qtty;
		// return Stream.of(list.split("\n")).map(arr ->
		// MetaInf.getItem(arr.split("\t")[0])).filter(i -> i != null);
	}

}
