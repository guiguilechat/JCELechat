package fr.guiguilechat.eveonline.programs.manager.panes.tools.burners;

import java.util.Map;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.BurnersEvalParams;
import fr.guiguilechat.eveonline.programs.manager.Settings.MissionStats;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.ScrollAdd;
import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class OptionsPane extends BorderPane implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public TextField newMission = new TextField();
	public Button addMissionBtn = new Button("add mission");
	public Button computeBtn = new Button("COMPUTE");

	public ChoiceBox<String> regionMarket = new ChoiceBox<>();
	public TypedField<Double> sellTax, brokerFee;
	public TypedField<Integer> lpQtty;

	public TypedField<Double> weightConst, weightOut, hubConstelMult;

	protected TabPane missionsPane = new TabPane();

	public OptionsPane(Manager parent) {
		this.parent = parent;
	}

	@Override
	public void onIsShown(boolean shown) {
		if (shown) {
			load();
		}
	}

	boolean loaded = false;

	protected void load() {
		if (loaded) {
			return;
		}

		GridPane topPane = new GridPane();

		BurnersEvalParams burnersSettings = parent().settings.burners;

		GridPane computepane = new GridPane();
		computepane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

		computepane.addRow(0, newMission, addMissionBtn);
		addMissionBtn.setOnAction(e -> addNewMission());
		computepane.addRow(2, computeBtn);

		GridPane marketPane = new GridPane();
		marketPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

		// add all the regions to the choicebox
		parent.db().getLocations().entrySet().stream().filter(e -> e.getValue().parentRegion == null).map(Map.Entry::getKey)
		.forEachOrdered(regionMarket.getItems()::add);
		regionMarket.getItems().sort(String::compareToIgnoreCase);
		regionMarket.getSelectionModel().select(parent().settings.burners.region);
		marketPane.addRow(0, new Label("market region"), regionMarket);

		sellTax = TypedField.positivDecimal(burnersSettings.sellTax);
		sellTax.setTooltip(new Tooltip("percentage of the sale that is due as tax"));
		sellTax.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, sellTax));
		marketPane.addRow(1, new Label("sell tax %"), sellTax);

		brokerFee = TypedField.positivDecimal(burnersSettings.brokerFee);
		brokerFee.setTooltip(new Tooltip(
				"when buying at BO value or selling at SO value, the percentage of the transaction that is due as broker fee"));
		brokerFee.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, brokerFee));
		marketPane.addRow(2, new Label("broker fee %"), brokerFee);

		lpQtty = TypedField.positivIntField(burnersSettings.lpQtty);
		lpQtty.setTooltip(new Tooltip("quantity of LP to use. Higher LP quantity means less interesting BO/SO values, "));
		lpQtty.setOnScroll(new ScrollAdd.IntScrollAdd(100000, lpQtty));
		marketPane.addRow(3, new Label("LP quantity"), lpQtty);

		GridPane mapPane = new GridPane();
		mapPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");

		mapPane.addRow(0, new Label("burner dest"));

		weightConst = TypedField.positivDecimal(burnersSettings.weightConstel);
		weightConst.setTooltip(new Tooltip("probability weight of system in same constelation"));
		weightConst.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, weightConst));
		mapPane.addRow(1, new Label("same const"), weightConst);

		weightOut = TypedField.positivDecimal(burnersSettings.weightOut);
		weightOut.setTooltip(new Tooltip("probability weight of system in adjacent constel"));
		weightOut.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, weightOut));
		mapPane.addRow(2, new Label("out const"), weightOut);

		hubConstelMult = TypedField.positivDecimal(burnersSettings.hubConstelMult);
		hubConstelMult.setTooltip(new Tooltip("weight mult of system in hub constel"));
		hubConstelMult.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, hubConstelMult));
		mapPane.addRow(3, new Label("hub mult"), hubConstelMult);

		for (Region tf : new Region[] { regionMarket, sellTax, brokerFee, lpQtty, weightConst, weightOut,
				hubConstelMult }) {
			tf.setMaxWidth(70);
		}

		topPane.addRow(0, computepane, marketPane, mapPane);
		setTop(topPane);

		for (Entry<String, MissionStats> e : parent.settings.burners.missions.entrySet()) {
			missionsPane.getTabs().add(new Tab(e.getKey(), new MissionPane(e.getKey(), e.getValue())));
		}
		missionsPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
		missionsPane.getTabs().addListener(this::handleTabChange);
		setCenter(missionsPane);

		loaded = true;
	}

	public void addNewMission() {
		String missionName = newMission.getText();
		if (missionName == null || missionName.length() == 0) {
			return;
		}
		MissionStats stats = parent.settings.burners.missions.get(missionName);
		if (stats == null) {
			debug("creating mission " + missionName);
			stats = new MissionStats();
			parent.settings.burners.missions.put(missionName, stats);
			parent.settings.store();
			missionsPane.getTabs().add(new Tab(missionName, new MissionPane(missionName, stats)));
			newMission.setText(null);
		} else {
			debug("mission " + missionName + " exists already");
		}
	}

	protected void handleTabChange(Change<? extends Tab> c) {
		while (c.next()) {
			if (c.wasRemoved()) {
				for (Tab t : c.getRemoved()) {
					String missionName = t.getText();
					debug("removing mission" + missionName);
					parent.settings.burners.missions.remove(missionName);
				}
				parent.settings.store();
			}
		}
	}

}
