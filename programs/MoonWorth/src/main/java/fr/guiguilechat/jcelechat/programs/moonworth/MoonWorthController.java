package fr.guiguilechat.jcelechat.programs.moonworth;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.esi.ConnectedImpl;
import fr.guiguilechat.jcelechat.esi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.esi.disconnected.modeled.market.RegionalMarket;
import fr.guiguilechat.jcelechat.model.sde.items.ItemIndex;
import fr.guiguilechat.jcelechat.model.sde.items.attributes.CompressionQuantityNeeded;
import fr.guiguilechat.jcelechat.model.sde.items.attributes.CompressionTypeID;
import fr.guiguilechat.jcelechat.model.sde.items.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.locations.LocationHelper;
import fr.guiguilechat.jcelechat.model.sde.locations.Region;
import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;
import fr.guiguilechat.tools.JFXTools;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MoonWorthController {

	private static enum Evaluator {
		BO {
			@Override
			public double value(int typeID, RegionalMarket market) {
				ObservableDoubleValue var = market.getBO(typeID, 1);
				ConnectedImpl.waitO(var);
				return var.doubleValue();
			}
		},
		SO {
			@Override
			public double value(int typeID, RegionalMarket market) {
				ObservableDoubleValue var = market.getSO(typeID, 1);
				ConnectedImpl.waitO(var);
				return var.doubleValue();
			}
		},
		MONTH {
			@Override
			public double value(int typeID, RegionalMarket market) {
				ObservableDoubleValue var = market.getHistory(typeID).monthlyAverage();
				ConnectedImpl.waitO(var);
				return var.doubleValue();
			}
		},
		WEEK {
			@Override
			public double value(int typeID, RegionalMarket market) {
				ObservableDoubleValue var = market.getHistory(typeID).weeklyAverage();
				ConnectedImpl.waitO(var);
				return var.doubleValue();
			}
		};

		public abstract double value(int typeID, RegionalMarket market);
	}

	@FXML
	private ChoiceBox<Evaluator> evalSelector;

	@FXML
	private ChoiceBox<Region> regionSelect;

	private Property<RegionalMarket> marketHolder = new SimpleObjectProperty<>();

	@FXML
	private TextArea moondata;

	private LineChart<Number, Number> moonchart;

	@FXML
	private TextField datalimit;

	private IntegerProperty datalimitProperty;

	@FXML
	private BorderPane pane;

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

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("volume");
		yAxis.setLabel("isk");
		moonchart = new LineChart<>(xAxis, yAxis);
		pane.setCenter(moonchart);

		new Thread(this::load).start();
		regionSelect.getSelectionModel().select(Region.getRegion("TheForge"));
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
		System.err.println("data after update :");
		data.values().stream().flatMap(m -> m.values().stream()).flatMap(m -> m.values().stream())
		.forEach(System.err::println);
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
				Asteroid astero = (Asteroid) ItemIndex.getItem(Integer.parseInt(split[3]));
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
			double volumicPrice = eval.value(ast.id, marketHolder.getValue())
					/ ast.volume;
			try {
				Asteroid compressed = (Asteroid) ItemIndex.getItem(ast.attribute(CompressionTypeID.INSTANCE).intValue());
				double compressionRequired = ast.attribute(CompressionQuantityNeeded.INSTANCE).doubleValue();
				double volumicprice2 = eval.value(compressed.id, marketHolder.getValue()) / ast.volume / compressionRequired;
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
			for (Entry<Asteroid, Double> ast : asteroval.entrySet()) {
				double volume = ast.getKey().volume * 100 * ast.getValue();
				volumicPriceToVol.put(volumicPrices.get(ast.getKey()),
						volume + volumicPriceToVol.getOrDefault(volumicPriceToVol, 0.));
			}
			DoubleProperty totalVol = new SimpleDoubleProperty(0);
			DoubleProperty totalIsk = new SimpleDoubleProperty(0);
			volumicPriceToVol.entrySet().stream().sorted((e1, e2) -> (int) Math.signum(e2.getKey() - e1.getKey()))
			.forEach(entry -> {
				if(totalVol.get()==0) {
					seriesToYield.put(series, entry.getKey());
				}
				totalVol.set(totalVol.get() + entry.getValue());
				totalIsk.set(totalIsk.get() + entry.getValue() * entry.getKey());
				double volume = totalVol.get(), isk = totalIsk.get();
				System.err.println("added " + e.getKey() + "\t" + volume + "\t" + isk + "\t" + entry.getKey());
				series.getData().add(new Data<>(volume, isk));
			});
		});
		int limit = datalimitProperty.get();
		if(limit==0) {
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

	protected Stream<Map.Entry<String, Map<Asteroid, Double>>> filteredMoons() {
		return data.entrySet().stream().filter(e -> true).flatMap(e -> e.getValue().entrySet().stream()).filter(e -> true)
				.flatMap(e -> e.getValue().entrySet().stream());
	}

}
