package fr.guiguilechat.eveonline.programs.manager.panes.tools.burners;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.burners.algorithms.EvaluateBurnersAgents;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.burners.algorithms.EvaluateBurnersAgents.LocalizedLPOffer;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.inventer.InventionGainAlgorithm;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class BurnersToolPane extends BorderPane implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	protected OptionsPane optionBox;

	EvaluateBurnersAgents eval;

	protected final TableView<LocalizedLPOffer> table = new TableView<>();

	public static class PriceCellFactory extends TableCell<LocalizedLPOffer, Double> {
		@Override
		public void updateItem(Double value, boolean empty) {
			super.updateItem(value, empty);
			if (empty) {
				setText(null);
			} else {
				setText(InventionGainAlgorithm.formatPrice(value));
			}
		}
	}

	public BurnersToolPane(Manager parent) {
		this.parent = parent;

		optionBox = new OptionsPane(parent);
		setTop(new TitledPane("options", optionBox));
		optionBox.computeBtn.setOnAction(e -> compute());

		children = new EvePane[] { optionBox };

		TableColumn<LocalizedLPOffer, String> agentCol = new TableColumn<>("agent");
		agentCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().agent.name));
		table.getColumns().add(agentCol);

		TableColumn<LocalizedLPOffer, String> corpCol = new TableColumn<>("corporation");
		corpCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().agent.corporation));
		table.getColumns().add(corpCol);

		TableColumn<LocalizedLPOffer, String> stationCol = new TableColumn<>("station");
		stationCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().agent.station));
		table.getColumns().add(stationCol);

		TableColumn<LocalizedLPOffer, String> offerCol = new TableColumn<>("offer");
		offerCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().name));
		table.getColumns().add(offerCol);

		TableColumn<LocalizedLPOffer, Double> soboCol = new TableColumn<>("soboph");
		soboCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().sobogain));
		soboCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(soboCol);

		TableColumn<LocalizedLPOffer, Double> avgcol = new TableColumn<>("avg");
		avgcol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().avggain));
		avgcol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(avgcol);

		setCenter(table);

		eval = new EvaluateBurnersAgents();

		eval.corpEvaluator.withLPAmount(parent.settings.burners.lpQtty);
		eval.corpEvaluator.withBrokerFee(parent.settings.burners.brokerFee);
		eval.corpEvaluator.withMarket(parent.settings.burners.region);
		eval.corpEvaluator.withSaleTax(parent.settings.burners.sellTax);

		eval.systemEvaluator.withMultWeightHub(parent.settings.burners.hubConstelMult);
		eval.systemEvaluator.withWeightAdjConstel(parent.settings.burners.weightOut);
		eval.systemEvaluator.withWeightSameConstel(parent.settings.burners.weightConstel);
		eval.missions = parent.settings.burners.missions.values();

	}

	protected boolean updateMarket() {
		boolean ret = false;
		String newregion = optionBox.regionMarket.getValue();
		if (!parent.settings.burners.region.equals(newregion)) {
			parent.settings.burners.region = newregion;
			eval.corpEvaluator.withMarket(newregion);
			ret = true;
		}
		double newSaleTax = optionBox.sellTax.getValue();
		if (parent.settings.burners.sellTax != newSaleTax) {
			parent.settings.burners.sellTax = newSaleTax;
			eval.corpEvaluator.withSaleTax(newSaleTax);
			ret = true;
		}
		double newBrokerFee = optionBox.brokerFee.getValue();
		if (parent.settings.burners.brokerFee != newBrokerFee) {
			parent.settings.burners.brokerFee = newBrokerFee;
			eval.corpEvaluator.withBrokerFee(newBrokerFee);
			ret = true;
		}
		int newLPQtty = optionBox.lpQtty.getValue();
		if (parent.settings.burners.lpQtty != newLPQtty) {
			parent.settings.burners.lpQtty = newLPQtty;
			eval.corpEvaluator.withLPAmount(newLPQtty);
			ret=true;
		}
		return ret;
	}

	protected boolean updateMap() {
		boolean ret = false;
		double newWeightConstel = optionBox.weightConst.getValue();
		if (parent.settings.burners.weightConstel != newWeightConstel) {
			parent.settings.burners.weightConstel = newWeightConstel;
			eval.systemEvaluator.withWeightSameConstel(newWeightConstel);
			ret = true;
		}
		double newWeightOut = optionBox.weightOut.getValue();
		if (parent.settings.burners.weightOut != newWeightOut) {
			parent.settings.burners.weightOut = newWeightOut;
			eval.systemEvaluator.withWeightAdjConstel(newWeightOut);
			ret = true;
		}
		double newHubMult = optionBox.hubConstelMult.getValue();
		if (parent.settings.burners.hubConstelMult != newHubMult) {
			parent.settings.burners.hubConstelMult = newHubMult;
			eval.systemEvaluator.withMultWeightHub(newHubMult);
			ret = true;
		}
		return ret;
	}

	protected boolean updateMissions() {
		// on all tabs, we check if there was a modification, return true if number
		// of modified tabs is >0
		return optionBox.missionsPane.getTabs().stream()
				.map(t -> ((MissionPane) t.getContent()))
				.filter(MissionPane::updateSettings)
				.count() > 0;

	}

	protected void compute() {
		if (updateMarket() | updateMap() | updateMissions()) {
			parent().settings.store();
		}
		table.getItems().clear();
		optionBox.computeBtn.setDisable(true);
		Task<Void> task = task();
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	Task<Void> task() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				ObservableList<LocalizedLPOffer> list = FXCollections.observableArrayList();
				eval.streamOffers().forEachOrdered(list::add);
				Platform.runLater(() -> {
					table.getItems().addAll(list);
					table.sort();
					optionBox.computeBtn.setDisable(false);
				});
				return null;
			}

		};
	}

}
