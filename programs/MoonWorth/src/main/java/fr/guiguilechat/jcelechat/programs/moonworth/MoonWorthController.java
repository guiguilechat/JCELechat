package fr.guiguilechat.jcelechat.programs.moonworth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.attributes.CompressionQuantityNeeded;
import fr.guiguilechat.jcelechat.model.sde.attributes.CompressionTypeID;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.jcelechat.model.sde.locations.javafx.LocationHelper;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.Material;
import fr.guiguilechat.jcelechat.model.sde.types.material.IceProduct;
import fr.guiguilechat.jcelechat.model.sde.types.material.Mineral;
import fr.guiguilechat.jcelechat.model.sde.types.material.MoonMaterials;
import fr.guiguilechat.tools.JFXTools;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MoonWorthController {

	private static enum Evaluator {
		BO {
			@Override
			public double value(int typeID, MoonWorthController controller) {
				return controller.market().getBO(typeID, 1).get();
			}
		},
		// SO {
		// @Override
		// public double value(int typeID, MoonWorthController controller) {
		// ObservableDoubleValue var = controller.market().getSO(typeID, 1);
		// System.err.println("so value of " + typeID + " is " + var.doubleValue());
		// return var.doubleValue();
		// }
		// },
		MONTH {
			@Override
			public double value(int typeID, MoonWorthController controller) {
				return controller.market().getHistory(typeID).monthly.getAverage().get();
			}
		},
		WEEK {
			@Override
			public double value(int typeID, MoonWorthController controller) {
				return controller.market().getHistory(typeID).weekly.getAverage().get();
			}
		},
		REPROBO {
			@Override
			public double value(int typeID, MoonWorthController controller) {
				double ret = 0;
				for (Entry<Integer, Double> e : IndustryUsage.load().get(typeID).reprocessInto.entrySet()) {
					Material mat = (Material) TypeIndex.getType(e.getKey());
					double matval = controller.matReprocess(mat) * BO.value(mat.id, controller);
					ret += matval;
				}
				return ret;
			}
		},
		REPROMONTH {
			@Override
			public double value(int typeID, MoonWorthController controller) {
				double ret = 0;
				for (Entry<Integer, Double> e : IndustryUsage.load().get(typeID).reprocessInto.entrySet()) {
					Material mat = (Material) TypeIndex.getType(e.getKey());
					double matval = controller.matReprocess(mat) * MONTH.value(mat.id, controller);
					ret += matval;
				}
				return ret;
			}
		};

		public abstract double value(int typeID, MoonWorthController controller);
	}

	@FXML
	private ChoiceBox<Evaluator> evalSelector;

	@FXML
	private ChoiceBox<Region> regionSelect;

	private Property<RegionalMarket> marketHolder = new SimpleObjectProperty<>();

	public RegionalMarket market() {
		return marketHolder.getValue();
	}

	@FXML
	private TextArea moondata;

	private LineChart<Number, Number> moonchart;

	@FXML
	private TextField datalimit;

	private IntegerProperty datalimitProperty;

	@FXML
	private GridPane optionsPane;

	@FXML
	private BorderPane pane;

	private HashMap<Material, DoubleProperty> matReprocess = new HashMap<>();

	public double matReprocess(Material mat) {
		DoubleProperty obs = matReprocess.get(mat);
		if (obs == null) {
			System.err.println("no observable for " + mat);
		}
		double ret = obs != null ? obs.doubleValue() : 0.0;
		// System.err.println("reprocess mult of " + mat + " is " + ret);
		return ret;
	}

	@FXML
	private void initialize() {
		evalSelector.getItems().addAll(Evaluator.values());
		evalSelector.getSelectionModel().select(Evaluator.BO);
		evalSelector.getSelectionModel().selectedItemProperty()
		.addListener((ChangeListener<Evaluator>) (observable, oldValue, newValue) -> updateChart());
		regionSelect.getSelectionModel().selectedItemProperty().addListener(this::changeRegion);
		moondata.textProperty().addListener(this::onMoonUpdate);

		datalimitProperty = JFXTools.convertInt(datalimit.textProperty(), i -> i >= 0);
		datalimitProperty.addListener((ChangeListener<Number>) (observable, oldValue, newValue) -> updateChart());

		int row = 3;
		ArrayList<Material> materials = new ArrayList<>();
		materials.addAll(Mineral.METAGROUP.load().values());
		materials.addAll(MoonMaterials.METAGROUP.load().values());
		materials.addAll(IceProduct.METAGROUP.load().values());
		for (Material mat : materials) {
			Label lbl = new Label(mat.name);
			TextField reproc = new TextField("0.5");
			reproc.setMinHeight(20);
			reproc.setMaxHeight(25);
			reproc.setMinWidth(40);
			// lbl.setMinWidth(120);
			DoubleProperty prop = JFXTools.convertDouble(reproc.textProperty(), d -> d >= 0);
			matReprocess.put(mat, prop);
			optionsPane.addRow(row, lbl, reproc);
			row++;
		}

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("volume (m^3)");
		yAxis.setLabel("isk (M)");
		moonchart = new LineChart<>(xAxis, yAxis);
		pane.setCenter(moonchart);
		moonchart.setLegendVisible(false);
		moonchart.getStyleClass().add("thick-chart");

		new Thread(this::load).start();
		regionSelect.getSelectionModel().select(Region.getRegion("The Forge"));
	}

	protected void load() {
		LocationHelper.initRegion(regionSelect);
	}

	protected void changeRegion(ObservableValue<? extends Region> observable, Region oldValue, Region newValue) {
		if (newValue == null) {
			marketHolder.setValue(null);
		} else {
			marketHolder.setValue(ESIAccess.INSTANCE.markets.getMarket(newValue.id));
			Asteroid.METACAT.load().values().stream().filter(astero -> astero.marketGroup != 0)
			.forEach(astero -> marketHolder.getValue().getBO(astero.id, 1));
		}
	}

	Map<Region, Map<SolarSystem, Map<String, Map<Asteroid, Double>>>> data = new HashMap<>();

	protected void onMoonUpdate(Object o, String previous, String now) {
		Map<Region, Map<SolarSystem, Map<String, Map<Asteroid, Double>>>> map = parse(now);
		boolean replace = false;
		if (replace) {
			data = map;
		} else {
			for (Entry<Region, Map<SolarSystem, Map<String, Map<Asteroid, Double>>>> e : map.entrySet()) {
				if (data.containsKey(e.getKey())) {
					Map<SolarSystem, Map<String, Map<Asteroid, Double>>> present = data.get(e.getKey());
					for (Entry<SolarSystem, Map<String, Map<Asteroid, Double>>> e2 : e.getValue().entrySet()) {
						if (present.containsKey(e2.getKey())) {
							Map<String, Map<Asteroid, Double>> present2 = present.get(e2.getKey());
							for (Entry<String, Map<Asteroid, Double>> e3 : e2.getValue().entrySet()) {
								present2.put(e3.getKey(), e3.getValue());
							}
						} else {
							present.put(e2.getKey(), e2.getValue());
						}
					}
				} else {
					data.put(e.getKey(), e.getValue());
				}
			}
		}
		updateChart();
	}

	public static final Pattern moonDescPattern = Pattern.compile("(.+) ([IVX]+) - Moon ([0-9]+)");

	protected Map<Region, Map<SolarSystem, Map<String, Map<Asteroid, Double>>>> parse(String data) {
		// region > system > moon > asteroid > quantity
		Map<Region, Map<SolarSystem, Map<String, Map<Asteroid, Double>>>> result = new HashMap<>();
		Map<Asteroid, Double> lastMoon = null;
		for (String line : data.split("\n")) {
			Matcher moonName = moonDescPattern.matcher(line);
			if (moonName.matches()) {
				SolarSystem syst = SolarSystem.getSystem(moonName.group(1));
				Region r = Region.getRegion(syst.region);
				Map<SolarSystem, Map<String, Map<Asteroid, Double>>> regionmap = result.get(r);
				if (regionmap == null) {
					regionmap = new HashMap<>();
					result.put(r, regionmap);
				}
				Map<String, Map<Asteroid, Double>> systmap = regionmap.get(syst);
				if (systmap == null) {
					systmap = new HashMap<>();
					regionmap.put(syst, systmap);
				}
				String moon = syst.name + " " + moonName.group(2) + "-" + moonName.group(3);
				lastMoon = systmap.get(moon);
				if (lastMoon == null) {
					lastMoon = new HashMap<>();
					systmap.put(moon, lastMoon);
				}
				continue;
			}
			String[] split = line.split("\t");
			if (split.length == 7 && split[0].length() == 0) {
				Asteroid astero = (Asteroid) TypeIndex.getType(Integer.parseInt(split[3]));
				Double qtty = Double.parseDouble(split[2]);
				lastMoon.put(astero, qtty);
			}
		}
		return result;
	}

	protected void updateChart() {
		moonchart.getData().clear();
		Evaluator eval = evalSelector.getSelectionModel().getSelectedItem();
		if (data.isEmpty() || eval == null) {
			return;
		}
		HashMap<Asteroid, Double> volumicPrices = new HashMap<>();
		Asteroid.METACAT.load().values().stream().filter(ast -> ast.marketGroup != 0).forEach(ast -> {
			double volumicPrice = eval.value(ast.id, this) / ast.volume;
			try {
				Asteroid compressed = (Asteroid) TypeIndex.getType(ast.valueSet(CompressionTypeID.INSTANCE).intValue());
				double compressionRequired = ast.valueSet(CompressionQuantityNeeded.INSTANCE).doubleValue();
				double volumicprice2 = eval.value(compressed.id, this) / ast.volume / compressionRequired;
				if (volumicprice2 > volumicPrice) {
					volumicPrice = volumicprice2;
				}
			} catch (Exception e) {
			}
			volumicPrices.put(ast, volumicPrice);
		});
		HashMap<Series<Number, Number>, Double> seriesToYield = new HashMap<>();
		filteredMoons().forEach(e -> {
			Map<Asteroid, Double> asteroval = e.getValue();
			XYChart.Series<Number, Number> series = new XYChart.Series<>();
			series.getData().add(new Data<>(0.0, 0.0));
			series.setName(e.getKey());
			Map<Double, Double> volumicPriceToVol = new HashMap<>();
			for (Entry<Asteroid, Double> astEntry : asteroval.entrySet()) {
				double volume = 100 * 50000 * astEntry.getValue();
				Double volumicPrice = volumicPrices.get(astEntry.getKey());
				volumicPriceToVol.put(volumicPrice, volume + volumicPriceToVol.getOrDefault(volumicPrice, 0.0));
			}
			DoubleProperty totalVol = new SimpleDoubleProperty(0);
			DoubleProperty totalIsk = new SimpleDoubleProperty(0);
			volumicPriceToVol.entrySet().stream().sorted((e1, e2) -> (int) Math.signum(e2.getKey() - e1.getKey()))
			.forEach(entry -> {
				if (totalVol.get() == 0) {
					seriesToYield.put(series, entry.getKey());
				}
				totalVol.set(totalVol.get() + entry.getValue());
				totalIsk.set(totalIsk.get() + entry.getValue() * entry.getKey());
				double volume = totalVol.get();
				double isk = totalIsk.get();
				Data<Number, Number> added = new Data<>(volume, isk / 1000000);
				added.setNode(new HoveredThresholdNode(e.getKey(), isk));
				series.getData().add(added);
			});
		});
		int limit = datalimitProperty.get();
		if (limit == 0) {
			moonchart.getData().addAll(seriesToYield.keySet());
		} else {
			double minValue = seriesToYield.values().stream().sorted((a, b) -> (int) Math.signum(b - a)).limit(limit)
					.mapToDouble(d -> d).min().getAsDouble();
			for (Entry<Series<Number, Number>, Double> e : seriesToYield.entrySet()) {
				if (e.getValue() >= minValue) {
					moonchart.getData().add(e.getKey());
				}
			}
		}
	}

	/** a node which displays a value on hover, but is otherwise empty */
	class HoveredThresholdNode extends StackPane {
		HoveredThresholdNode(String seriesName, double value) {
			setPrefSize(10, 10);

			final Label label = new Label(seriesName + " " + JFXTools.formatPrice(value));
			label.getStyleClass().addAll("chart-line-symbol", "chart-series-line");
			label.setStyle("-fx-font-size: 10; -fx-font-weight: bold;");
			label.setMinWidth(200);

			setOnMouseEntered(mouseEvent -> {
				getChildren().setAll(label);
				setCursor(Cursor.NONE);
				toFront();
			});
			setOnMouseExited(mouseEvent -> {
				getChildren().clear();
				setCursor(Cursor.CROSSHAIR);
			});
		}
	}

	protected Stream<Map.Entry<String, Map<Asteroid, Double>>> filteredMoons() {
		return data.entrySet().stream().filter(e -> true).flatMap(e -> e.getValue().entrySet().stream()).filter(e -> true)
				.flatMap(e -> e.getValue().entrySet().stream());
	}

}
