package fr.guiguilechat.eveonline.programs.gui.panes.tools.burners;

import fr.guiguilechat.eveonline.programs.EvaluateBurnersAgents;
import fr.guiguilechat.eveonline.programs.EvaluateBurnersAgents.LocalizedLPOffer;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class BurnerPane extends BorderPane implements EvePane {

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

	public BurnerPane(Manager parent) {
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
		stationCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().agent.location));
		table.getColumns().add(stationCol);

		TableColumn<LocalizedLPOffer, String> offerCol = new TableColumn<>("offer");
		offerCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().offer_name));
		table.getColumns().add(offerCol);

		TableColumn<LocalizedLPOffer, Double> soboCol = new TableColumn<>("sobo");
		soboCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().sobogain));
		table.getColumns().add(soboCol);

		TableColumn<LocalizedLPOffer, Double> avgcol = new TableColumn<>("avg");
		avgcol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().avggain));
		table.getColumns().add(avgcol);

		setCenter(table);

		eval = new EvaluateBurnersAgents(parent.db());

		eval.corpEvaluator.withLPAmount(parent.settings.burners.lpQtty);
		eval.corpEvaluator.withBrokerFee(parent.settings.burners.brokerFee);
		eval.corpEvaluator.withMarket(parent.settings.burners.region);
		eval.corpEvaluator.withSaleTax(parent.settings.burners.sellTax);

		eval.systemEvaluator.withMultWeightHub(parent.settings.burners.hubConstelMult);
		eval.systemEvaluator.withWeightAdjConstel(parent.settings.burners.weightOut);
		eval.systemEvaluator.withWeightSameConstel(parent.settings.burners.weightConstel);

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

	protected boolean updateTime() {
		boolean ret = false;
		double newSystemTime = optionBox.systemTime.getValue();
		if (parent.settings.burners.systemTime != newSystemTime) {
			parent.settings.burners.systemTime = newSystemTime;
			eval.sysTravelTime = newSystemTime;
			ret = true;
		}
		double newBurnerTime = optionBox.burnerTime.getValue();
		if (parent.settings.burners.burnerTime != newBurnerTime) {
			parent.settings.burners.burnerTime = newBurnerTime;
			eval.burnerTime = newBurnerTime;
			ret = true;
		}
		return ret;
	}

	protected void compute() {
		boolean upd = updateMarket();
		upd = updateMap() || upd;
		upd = updateTime() || upd;
		if (upd) {
			parent().settings.store();
			table.getItems().clear();
		}
		if (upd || table.getItems().isEmpty()) {
			eval.streamOffers().forEachOrdered(table.getItems()::add);
		}
		table.sort();
	}

}
