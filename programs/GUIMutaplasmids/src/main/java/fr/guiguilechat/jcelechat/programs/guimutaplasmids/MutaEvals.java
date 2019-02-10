package fr.guiguilechat.jcelechat.programs.guimutaplasmids;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.MutaplasmidFamily.ModifiedItem;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.MutaplasmidFamily.MutaStr;
import fr.guiguilechat.tools.PriceCellFactory;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MutaEvals extends Application {

	public static void start(String[] args) {
		MutaplasmidFamily.searchESI();

		int parrallelism = Runtime.getRuntime().availableProcessors() * 50;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		launch(args);
	}

	private ChoiceBox<MutaplasmidFamily> familychoice;
	private BorderPane attributesPane = new BorderPane();
	private TableView<ModifiedItemCost> table = new TableView<>();
	private TextField qttyField = new TextField("1");
	private ObservableIntegerValue qttyVal = intEval(qttyField);
	private ChoiceBox<String> regionMarket;
	private RegionalMarket market;
	private TextField decayedPriceField = new TextField("10");
	private ObservableDoubleValue decayedPriceVal = doubleEval(decayedPriceField);
	private TextField gravidPriceField = new TextField("100");
	private ObservableDoubleValue gravidPriceVal = doubleEval(gravidPriceField);
	private TextField unstablePriceField = new TextField("1000");
	private ObservableDoubleValue unstablePriceVal = doubleEval(unstablePriceField);
	private TextField abnormalPriceField = new TextField("100");
	private ObservableDoubleValue abnormalPriceVal = doubleEval(abnormalPriceField);

	/**
	 * get the price, in M isk, of a mutaplasmid strength
	 *
	 * @param strength
	 * @return
	 */
	public ObservableDoubleValue getPrice(MutaStr strength) {
		if (strength == null) {
			return new SimpleDoubleProperty(00);
		}
		switch (strength) {
		case DECAYED:
			return decayedPriceVal;
		case GRAVID:
			return gravidPriceVal;
		case ABNORMAL:
			return abnormalPriceVal;
		case UNSTABLE:
			return unstablePriceVal;
		default:
			throw new UnsupportedOperationException("doesnt handle " + strength);
		}
	}

	@SuppressWarnings("unchecked")
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

		VBox topPane = new VBox(5);
		HBox row1 = new HBox(10, regionMarket, new Label("item:"), familychoice, new Label("qtty:"), qttyField);
		row1.setAlignment(Pos.BASELINE_CENTER);
		topPane.getChildren().add(row1);
		HBox row2 = new HBox(10,
				new VBox(new Label("mutaplasmid :"), new Label("price (M) :")),
				new VBox(new Label("decayed"), decayedPriceField),
				new VBox(	new Label("gravid"),gravidPriceField),
				new VBox(new Label("unstable"), unstablePriceField),
				new VBox(	new Label("abnormal"), abnormalPriceField));
		row2.setAlignment(Pos.BASELINE_CENTER);
		topPane.getChildren().add(row2);

		for (TextField tf : new TextField[] { abnormalPriceField, decayedPriceField, gravidPriceField, qttyField,
				unstablePriceField }) {
			tf.setMaxWidth(70);
			tf.textProperty().addListener((o, old, now) -> sortLater());
		}

		TableColumn<ModifiedItemCost, String> itemCol = new TableColumn<>("item");
		itemCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().item.item().name));
		itemCol.setMinWidth(300);
		table.getColumns().add(itemCol);

		TableColumn<ModifiedItemCost, String> mutaCol = new TableColumn<>("muta");
		mutaCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(
				lo.getValue().item.strength() == null ? "none" : lo.getValue().item.strength().toString()));
		table.getColumns().add(mutaCol);

		TableColumn<ModifiedItemCost, Number> probaCol = new TableColumn<>("probability");
		probaCol.setCellValueFactory(lo -> lo.getValue().probability());
		table.getColumns().add(probaCol);

		TableColumn<ModifiedItemCost, Number> qttyCol = new TableColumn<>("qtty");
		qttyCol.setCellValueFactory(lo -> lo.getValue().qtty());
		table.getColumns().add(qttyCol);

		TableColumn<ModifiedItemCost, Number> priceCol = new TableColumn<>("price");
		priceCol.setCellValueFactory(lo -> lo.getValue().price());
		table.getColumns().add(priceCol);
		priceCol.setSortType(SortType.ASCENDING);
		priceCol.setCellFactory(PriceCellFactory::create);
		table.getSortOrder().addAll(priceCol, qttyCol);

		// table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
				boolean negRange = newValue.results().iterator().next().highRangeValues().get(a).doubleValue() < 0;
				double lowRangeValue = (negRange
						? newValue.results().stream().mapToDouble(mod -> mod.lowRangeValues().get(a)).max()
								: newValue.results().stream().mapToDouble(mod -> mod.lowRangeValues().get(a)).min()).getAsDouble();
				double highRangeValue = (negRange
						? newValue.results().stream().mapToDouble(mod -> mod.highRangeValues().get(a)).min()
								: newValue.results().stream().mapToDouble(mod -> mod.highRangeValues().get(a)).max()).getAsDouble();
				System.err
				.println("attribute " + a.getClass().getSimpleName() + " from " + lowRangeValue + " to " + highRangeValue);

				// if both values are negative, we set them to positive.
				Slider sl = !negRange
						? new Slider(lowRangeValue, highRangeValue, a.getHighIsGood() ? lowRangeValue : highRangeValue)
								: new Slider(-lowRangeValue, -highRangeValue, a.getHighIsGood() ? -lowRangeValue : -highRangeValue);
						sl.setBlockIncrement(0.1);
						sl.setPrefWidth(250);
						sl.setShowTickLabels(true);
						sl.valueProperty().addListener((o, old, now) -> sortLater());
						atts.attValues.put(a, negRange ? sl.valueProperty().negate() : sl.valueProperty());
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

	protected class ModifiedItemCost {
		public ModifiedItemCost(ModifiedItem item2, RegionalMarket market2, Map<Attribute, ObservableDoubleValue> attValues,
				ObservableIntegerValue qttyVal2, Function<MutaStr, ObservableDoubleValue> strengthPrice) {
			item = item2;
			market = market2;
			this.attValues = attValues;
			qttyVal = qttyVal2;
			strengthPrices = strengthPrice;
		}

		public ModifiedItem item;
		public RegionalMarket market;
		public Function<MutaStr, ObservableDoubleValue> strengthPrices;
		public Map<Attribute, ObservableDoubleValue> attValues;
		public ObservableIntegerValue qttyVal;

		private ObservableNumberValue probability;

		public ObservableNumberValue probability() {
			if (probability == null) {
				probability = item.probability(attValues);
			}
			return probability;
		}

		private DoubleBinding qtty;

		public DoubleBinding qtty() {
			if (qtty == null) {
				qtty = Bindings.createDoubleBinding(() -> {
					double ret = qttyVal.get() / probability().getValue().doubleValue();
					// System.err
					// .println("new required quantity of item " + item.item().name + "
					// with " + item.strength() + " is " + ret);
					return ret;
				}, probability(), qttyVal);
			}
			return qtty;
		}

		private ObservableNumberValue price;

		public ObservableNumberValue price() {
			if (price == null) {
				ObservableDoubleValue mutraprice = strengthPrices.apply(item.strength());
				price = Bindings
						.createDoubleBinding(
								() -> Double.isFinite(qtty().get())
								? market.getMarketOrders(item.item().id).getPrice(false, (int) Math.ceil(qtty().get())).get()
										+ qtty.get() * mutraprice.get() * 1000000
										: Double.POSITIVE_INFINITY,
										qtty(), regionMarket.getSelectionModel().selectedItemProperty(), mutraprice);
			}
			return price;
		}

	}

	protected void updateTableItems() {
		table.getItems().clear();
		if (presentSliders == null) {
			return;
		}
		market = ESIAccess.INSTANCE.markets
				.getMarket(Region.load().get(regionMarket.getSelectionModel().getSelectedItem()).id);
		for (ModifiedItem item : presentSliders.family.results()) {
			ModifiedItemCost added = new ModifiedItemCost(item, market, presentSliders.attValues, qttyVal, this::getPrice);
			table.getItems().add(added);
		}
		sortLater();
	}

	PauseTransition pause = new PauseTransition(Duration.seconds(1));

	protected void sortLater() {
		pause.setOnFinished(event -> table.sort());
		pause.playFromStart();
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

	public static ObservableDoubleValue doubleEval(TextField t) {
		SimpleDoubleProperty ret = new SimpleDoubleProperty();
		t.textProperty().addListener((ob, old, now) -> {
			try {
				ret.set(Double.parseDouble(now));
			} catch (Exception e) {
				t.textProperty().set(old);
			}
		});
		ret.set(Double.parseDouble(t.getText()));
		return ret;
	}

	public static ObservableIntegerValue intEval(TextField t) {
		SimpleIntegerProperty ret = new SimpleIntegerProperty();
		t.textProperty().addListener((ob, old, now) -> {
			try {
				ret.set(Integer.parseInt(now));
			} catch (Exception e) {
				t.textProperty().set(old);
			}
		});
		ret.set(Integer.parseInt(t.getText()));
		return ret;
	}

}
