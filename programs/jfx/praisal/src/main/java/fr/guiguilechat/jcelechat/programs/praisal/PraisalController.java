package fr.guiguilechat.jcelechat.programs.praisal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.jcesi.tools.MarketHelpers;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.javafx.LocationHelper;
import fr.guiguilechat.tools.javafx.JFXTools;
import fr.guiguilechat.tools.javafx.PriceCellFactory;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
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
	private TableView<Entry<EveType, Integer>> table;

	@FXML
	private TableColumn<Entry<EveType, Integer>, String> itemname;

	@FXML
	private TableColumn<Entry<EveType, Integer>, Double> itembuyback1;

	@FXML
	private TableColumn<Entry<EveType, Integer>, Double> itembuybackr1;

	@FXML
	private TableColumn<Entry<EveType, Integer>, Double> itembuyback2;

	@FXML
	private TableColumn<Entry<EveType, Integer>, Double> itembuybackr2;

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

		// buyback gain = bo *(1-tax/100) - volume*volumicprice
		itembuyback1.setCellValueFactory(cell -> MarketHelpers.bo(cell.getValue().getKey().id, marketHolder1)
				.multiply(bbk1taxProperty.divide(100).negate().add(1.0))
				.subtract(bbk1volumicpriceProperty.multiply(cell.getValue().getKey().volume))
				.multiply(cell.getValue().getValue()).asObject());
		itembuyback1.setCellFactory(col -> new PriceCellFactory<>());
		itembuyback1.visibleProperty().bind(bbk1.selectedProperty());
		itembuyback1.setSortType(SortType.DESCENDING);

		itembuybackr1.setCellValueFactory(
				cell -> reprocessValue(cell.getValue().getKey(), cell.getValue().getValue(), marketHolder1, bbk1taxProperty,
						bbk1volumicpriceProperty)
				.asObject());
		itembuybackr1.setCellFactory(col -> new PriceCellFactory<>());
		itembuybackr1.visibleProperty().bind(bbk1.selectedProperty());

		itembuyback2.setCellValueFactory(cell -> MarketHelpers.bo(cell.getValue().getKey().id, marketHolder1)
				.multiply(bbk2taxProperty.divide(100).negate().add(1.0))
				.subtract(bbk2volumicpriceProperty.multiply(cell.getValue().getKey().volume))
				.multiply(cell.getValue().getValue()).asObject());
		itembuyback2.setCellFactory(col -> new PriceCellFactory<>());
		itembuyback2.visibleProperty().bind(bbk2.selectedProperty());

		itembuybackr2.setCellValueFactory(cell -> reprocessValue(cell.getValue().getKey(), cell.getValue().getValue(),
				marketHolder2, bbk2taxProperty, bbk2volumicpriceProperty).asObject());
		itembuybackr2.setCellFactory(col -> new PriceCellFactory<>());
		itembuybackr2.visibleProperty().bind(bbk2.selectedProperty());

		table.setItems(FXCollections.observableArrayList());

		new Thread(this::load).start();
	}

	protected void load() {
		LocationHelper.initRegion(regionSelect1);
		LocationHelper.initRegion(regionSelect2);
		Region theforge = Region.getRegion("The Forge");
		Platform.runLater(() -> {
			regionSelect1.getSelectionModel().select(theforge);
			regionSelect2.getSelectionModel().select(theforge);
		});
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

	public DoubleBinding reprocessValue(EveType item, int qtty, Property<RegionalMarket> marketholder, DoubleProperty tax,
			DoubleProperty volumicprice) {
		IndustryUsage usage = IndustryUsage.load().get(item.id);
		double totalVol = 0;
		DoubleBinding totalPrice = new DoubleBinding() {

			@Override
			protected double computeValue() {
				return 0;
			}
		};
		if (usage != null) {
			for (Entry<Integer, Double> e : usage.reprocessInto.entrySet()) {
				EveType product = TypeIndex.getType(e.getKey());
				if (product == null) {
					System.err.println("can't find item " + e.getKey());
					continue;
				}
				int qttyProduct = (int) Math.floor(e.getValue() * 0.5);
				// System.err.println(
				// item.name + " * " + qtty + " reprocess into " + qttyProduct + " of "
				// + product.name + " for "
				// + product.volume * qttyProduct + " m³");
				totalVol += product.volume * qttyProduct;
				totalPrice = totalPrice.add(MarketHelpers.bo(product.id, marketholder).multiply(qttyProduct));
			}
		}
		return totalPrice.multiply(tax.divide(100).negate().add(1.0)).subtract(volumicprice.multiply(totalVol));
	}

	public static Map<EveType, Integer> parse(String list) {
		HashMap<EveType, Integer> item2qtty = new HashMap<>();
		for (String line : list.split("\n")) {
			String[] arr = line.split("\t");
			EveType it = TypeIndex.getTypes(arr[0]).get(0);
			if (it != null && arr[1].length() > 0) {
				item2qtty.put(it, Integer.parseInt(arr[1].replaceAll("[^0-9]", "")) + item2qtty.getOrDefault(it, 0));
			}
		}
		return item2qtty;
		// return Stream.of(list.split("\n")).map(arr ->
		// MetaInf.getItem(arr.split("\t")[0])).filter(i -> i != null);
	}


}
