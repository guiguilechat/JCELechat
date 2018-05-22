package fr.guiguilechat.eveonline.programs.guimutaplasmids;

import java.util.HashMap;
import java.util.Map;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.modeled.market.RegionalMarket;
import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.locations.Region;
import fr.guiguilechat.eveonline.programs.guimutaplasmids.MutaplasmidFamily.ModifiedItem;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MutaEvals extends Application {

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 50;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		launch(args);
	}

	private ChoiceBox<MutaplasmidFamily> familychoice;
	private BorderPane attributesPane = new BorderPane();
	private TableView<ModifiedItemCost> table = new TableView<>();
	private TextField qttyField;
	private SimpleIntegerProperty qttyVal;
	private ChoiceBox<String> regionMarket;
	private RegionalMarket market;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("mutaplasmids evaluations");

		familychoice = new ChoiceBox<>(FXCollections.observableArrayList(MutaplasmidFamily.INSTANCES));
		familychoice.getSelectionModel().selectedItemProperty().addListener(
				(ChangeListener<MutaplasmidFamily>) (observable, oldValue, newValue) -> updateAttributes(newValue));

		regionMarket = new ChoiceBox<>(FXCollections.observableArrayList(Region.load().keySet()));
		regionMarket.getSelectionModel().select("TheForge");
		regionMarket.getSelectionModel().selectedItemProperty()
		.addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
			updateTableItems();
		});

		qttyField = new TextField("10");
		qttyVal = new SimpleIntegerProperty(10);
		qttyField.textProperty().addListener((ob, old, now) -> {
			try {
				qttyVal.set(Integer.parseInt(now));
			} catch (Exception e) {
				qttyField.setText("" + qttyVal.get());
			}
		});

		GridPane topPane = new GridPane();
		topPane.addRow(0, regionMarket, familychoice, qttyField);

		TableColumn<ModifiedItemCost, String> itemCol = new TableColumn<>("item");
		itemCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().item.item().name));
		table.getColumns().add(itemCol);

		TableColumn<ModifiedItemCost, String> mutaCol = new TableColumn<>("muta");
		mutaCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(
				lo.getValue().item.strength() == null ? "none" : lo.getValue().item.strength().toString()));
		table.getColumns().add(mutaCol);

		TableColumn<ModifiedItemCost, Number> probaCol = new TableColumn<>("probability");
		probaCol.setCellValueFactory(lo -> lo.getValue().probability());
		table.getColumns().add(probaCol);

		TableColumn<ModifiedItemCost, Number> priceCol = new TableColumn<>("price");
		priceCol.setCellValueFactory(lo -> lo.getValue().price());
		table.getColumns().add(priceCol);
		priceCol.setSortType(SortType.ASCENDING);
		priceCol.setCellFactory(col -> new PriceCellFactory());
		table.getSortOrder().add(priceCol);

		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		SplitPane mainLayout = new SplitPane(topPane, attributesPane, table);
		mainLayout.setDividerPositions(0.0, 0.0);
		mainLayout.setOrientation(Orientation.VERTICAL);
		Scene scene = new Scene(mainLayout, 800, 900);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private HashMap<MutaplasmidFamily, FamilySliders> familyAttributes = new HashMap<>();
	private FamilySliders presentSliders = null;

	private void updateAttributes(MutaplasmidFamily newValue) {
		FamilySliders atts = familyAttributes.get(newValue);
		if (atts == null) {
			atts = new FamilySliders(newValue);
			int rowidx = 0;
			for (Attribute a : newValue.modifiedAttributes()) {
				double minValue = newValue.results().stream().mapToDouble(mod -> mod.minvalues().get(a)).min().getAsDouble();
				double maxvalue = newValue.results().stream().mapToDouble(mod -> mod.maxvalues().get(a)).max().getAsDouble();
				// System.err.println("attribute " + a.getClass().getSimpleName() + "
				// from " + minValue + " to " + maxvalue);
				Slider sl = new Slider(minValue, maxvalue, a.getHighIsGood() ? minValue : maxvalue);
				sl.setPrefWidth(250);
				sl.setShowTickLabels(true);
				sl.valueProperty().addListener((o, old, now) -> sortLater());
				atts.attValues.put(a, sl.valueProperty());
				Label valueLbl = new Label();
				valueLbl.textProperty().bind(Bindings.createStringBinding(() -> "" + sl.getValue(), sl.valueProperty()));
				atts.addRow(rowidx, new Label(a.getClass().getSimpleName()), sl, valueLbl);

				rowidx++;
			}
			familyAttributes.put(newValue, atts);
		}
		presentSliders = atts;
		attributesPane.setCenter(presentSliders);
		updateTableItems();
	}

	/**
	 * shows the attribute of a family, as well as a slider to select minimum
	 * value.
	 *
	 */
	protected static class FamilySliders extends GridPane {

		public final MutaplasmidFamily family;

		public FamilySliders(MutaplasmidFamily family) {
			this.family = family;
			setHgap(10);
		}

		public HashMap<Attribute, ObservableDoubleValue> attValues = new HashMap<>();
	}

	protected static class ModifiedItemCost {
		public ModifiedItemCost(ModifiedItem item2, RegionalMarket market2, Map<Attribute, ObservableDoubleValue> attValues,
				SimpleIntegerProperty qttyVal) {
			item = item2;
			market = market2;
			this.attValues = attValues;
			this.qttyVal = qttyVal;
		}

		public ModifiedItem item;
		public RegionalMarket market;
		public Map<Attribute, ObservableDoubleValue> attValues;
		public SimpleIntegerProperty qttyVal;

		private ObservableNumberValue probability;

		public ObservableNumberValue probability() {
			if (probability == null) {
				probability = item.probability(attValues);
			}
			return probability;
		}

		private ObservableNumberValue price;

		public ObservableNumberValue price() {
			if (price == null) {
				DoubleBinding qtty = Bindings.createDoubleBinding(() -> {
					double ret = qttyVal.get() / probability().getValue().doubleValue();
					// System.err
					// .println("new required quantity of item " + item.item().name + "
					// with " + item.strength() + " is " + ret);
					return ret;
				}, probability(), qttyVal);
				ObjectBinding<ObservableDoubleValue> holder = Bindings.createObjectBinding(() -> {
					ObservableDoubleValue ret = qtty.get() == Double.POSITIVE_INFINITY
							? new SimpleDoubleProperty(Double.POSITIVE_INFINITY)
									: market.getMarketOrders(item.item().id).getPrice(false, (int) Math.ceil(qtty.get()));
							// System.err.println("price of " + qtty.get() + " items of " +
							// item.item().name + " is " + ret.get());
							return ret;
				}, qtty);
				SimpleDoubleProperty oprice = new SimpleDoubleProperty();
				oprice.bind(holder.get());
				price = oprice;
				holder.addListener((ob, old, now) -> {
					oprice.unbind();
					oprice.bind(now);
					System.err.println(
							"price of " + item.item().name + " - " + item.strength() + " bound to qtty " + qttyVal.get()
							+ " and value " + now.doubleValue());
				});
			}
			return price;
		}

	}

	protected void updateTableItems() {
		table.getItems().clear();
		if (presentSliders == null) {
			return;
		}
		market = ESIAccount.DISCONNECTED.markets
				.getMarket(Region.load().get(regionMarket.getSelectionModel().getSelectedItem()).id);
		for (ModifiedItem item : presentSliders.family.results()) {
			ModifiedItemCost added = new ModifiedItemCost(item, market, presentSliders.attValues, qttyVal);
			table.getItems().add(added);
		}
		sortLater();
	}

	PauseTransition pause = new PauseTransition(Duration.seconds(1));

	protected void sortLater() {
		pause.setOnFinished(event -> table.sort());
		pause.playFromStart();
	}

	protected static class PriceCellFactory extends TableCell<ModifiedItemCost, Number> {
		@Override
		public void updateItem(Number value, boolean empty) {
			System.err.println("update cell to number " + value);
			super.updateItem(value, empty);
			if (empty) {
				setText(null);
			} else {
				setText(formatPrice(value.doubleValue()));
			}
		}
	}

	static final long[] unitSuffixValue = { 1000000000000l, 1000000000l, 1000000l, 1000l };
	static final String[] unitSuffix = { "T", "B", "M", "k" };

	public static String formatPrice(double value) {
		if (value == Double.MAX_VALUE || value == Double.MIN_VALUE || value == Double.POSITIVE_INFINITY
				|| value == Double.NEGATIVE_INFINITY) {
			return "" + value;
		}
		double prefix = value;
		String suffix = null;
		for (int i = 0; i < unitSuffix.length && suffix == null; i++) {
			if (value >= unitSuffixValue[i]) {
				prefix = value / unitSuffixValue[i];
				suffix = unitSuffix[i];
			}
		}
		if (suffix == null) {
			suffix = "";
		}
		String rets = "" + prefix;
		return (rets.length() > 4 ? rets.substring(0, 4).replaceAll("\\.$", "") : rets) + suffix;
	}

}
