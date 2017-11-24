package fr.guiguilechat.eveonline.programs.gui.panes.tools.inventer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.Settings.InventionParams;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.tools.inventer.ShowInventionLowestCost.InvProdData;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class InventerPane extends BorderPane implements EvePane {

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

	protected OptionsPane options;

	protected final TableView<InvProdData> table = new TableView<>();

	protected static class PriceCellFactory extends TableCell<InvProdData, Double> {
		@Override
		public void updateItem(Double value, boolean empty) {
			super.updateItem(value, empty);
			if (empty) {
				setText(null);
			} else {
				setText(ShowInventionLowestCost.formatPrice(value));
			}
		}
	}

	public InventerPane(Manager parent) {
		this.parent = parent;
		options = new OptionsPane(parent);

		setTop(new TitledPane("options", options));
		options.computeBtn.setOnAction(e -> compute());

		TableColumn<InvProdData, String> prodCol = new TableColumn<>("product");
		prodCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().productName));
		prodCol.setMinWidth(300);
		table.getColumns().add(prodCol);

		TableColumn<InvProdData, String> decCol = new TableColumn<>("decryptor");
		decCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().decryptor));
		table.getColumns().add(decCol);

		TableColumn<InvProdData, Double> sobophCol = new TableColumn<>("sobo/h");
		sobophCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().SOBOph));
		sobophCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(sobophCol);

		TableColumn<InvProdData, Double> bosophCol = new TableColumn<>("boso/h");
		bosophCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().BOSOph));
		bosophCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(bosophCol);

		TableColumn<InvProdData, Double> costBOCol = new TableColumn<>("inBO");
		costBOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleCostBO));
		costBOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(costBOCol);

		TableColumn<InvProdData, Double> costSOCol = new TableColumn<>("inSO");
		costSOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleCostSO));
		costSOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(costSOCol);

		TableColumn<InvProdData, Double> sellBOCol = new TableColumn<>("outBO");
		sellBOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleProductBO));
		sellBOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(sellBOCol);

		TableColumn<InvProdData, Double> sellSOCol = new TableColumn<>("outSO");
		sellSOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleProductSO));
		sellSOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(sellSOCol);

		for (TableColumn<?, ?> t : new TableColumn<?, ?>[] { sobophCol, bosophCol, costBOCol, costSOCol, sellBOCol,
			sellSOCol }) {
			t.setMaxWidth(60);
		}

		setCenter(table);
		children = new EvePane[] { options };
	}

	protected void compute() {

		if (!updateSettings() && table.getItems().size() != 0) {
			return;
		}
		table.getItems().clear();
		EveChar cs = options.characterSkills.getValue();
		Map<String, Integer> skills = cs == null ? new HashMap<>() : cs.skillsByName();
		Pattern nameMatcher = nameLimit == null ? null : Pattern.compile(".*" + nameLimit.toLowerCase() + ".*");

		blueprints()
		.flatMap(bpo -> bpo.invention.products.stream()
				.filter(nameMatcher == null ? mat -> true : mat -> nameMatcher.matcher(mat.name.toLowerCase()).matches())
				.flatMap(mat -> ShowInventionLowestCost
						.evalCostInventionProd(bpo, mat, skills, db(), parent().settings.invention, true).stream()))
		.forEachOrdered(table.getItems()::add);

		table.sort();
	}

	protected Stream<Blueprint> blueprints() {
		Stream<Blueprint> ret = db().getBlueprints().values().stream().parallel()
				.filter(bp -> bp != null && bp.invention != null && !bp.invention.products.isEmpty() && bp.copying != null);
		return ret;
	}

	private String nameLimit = null;

	/**
	 * update the settings from the values in the window
	 *
	 * @return true if settings were modified
	 */
	protected boolean updateSettings() {
		InventionParams settings = parent().settings.invention;
		boolean modification = false;

		if (settings.marketRegion != options.marketRegion.getValue()) {
			settings.marketRegion = options.marketRegion.getValue();
			modification = true;
		}
		if (options.characterSkills.getValue() == null && settings.characterSkills != null
				|| options.characterSkills.getValue() != null
				&& !options.characterSkills.getValue().name.equals(settings.characterSkills)) {
			settings.characterSkills = options.characterSkills.getValue() == null ? null
					: options.characterSkills.getValue().name;
			modification = true;
		}
		if (settings.copyIndex != options.copyIndex.getValue()) {
			settings.copyIndex = options.copyIndex.getValue();
			modification = true;
		}
		if (settings.copyTax != options.copyTax.getValue()) {
			settings.copyTax = options.copyTax.getValue();
			modification = true;
		}
		if (settings.inventIndex != options.inventIndex.getValue()) {
			settings.inventIndex = options.inventIndex.getValue();
			modification = true;
		}
		if (settings.inventTax != options.inventTax.getValue()) {
			settings.inventTax = options.inventTax.getValue();
			modification = true;
		}
		if (settings.manufactureIndex != options.manufIndex.getValue()) {
			settings.manufactureIndex = options.manufIndex.getValue();
			modification = true;
		}
		if (settings.manufactureTax != options.manufTax.getValue()) {
			settings.manufactureTax = options.manufTax.getValue();
			modification = true;
		}
		if (settings.sellTax != options.sellTax.getValue()) {
			settings.sellTax = options.sellTax.getValue();
			modification = true;
		}
		if (settings.brokerFee != options.brokerFee.getValue()) {
			settings.brokerFee = options.brokerFee.getValue();
			modification = true;
		}
		if (settings.nbHours != options.nbHours.getValue()) {
			settings.nbHours = options.nbHours.getValue();
			modification = true;
		}

		if (modification) {
			parent().settings.store();
		}

		String newNameLimit = options.bpPattern.getText();
		if (newNameLimit != null && newNameLimit.length() == 0) {
			newNameLimit = null;
		}
		if (newNameLimit == null && nameLimit != null || newNameLimit != null && !newNameLimit.equals(nameLimit)) {
			nameLimit = newNameLimit;
			modification = true;
		}

		return modification;
	}

}
