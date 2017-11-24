package fr.guiguilechat.eveonline.programs.gui.panes.tools.inventer;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.programs.gui.Manager;
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

	protected OptionsPane optionBox;

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
		optionBox = new OptionsPane(parent);

		setTop(new TitledPane("options", optionBox));
		optionBox.computeBtn.setOnAction(e -> compute());

		TableColumn<InvProdData, String> prodCol = new TableColumn<>("product");
		prodCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().productName));
		table.getColumns().add(prodCol);

		TableColumn<InvProdData, String> decCol = new TableColumn<>("decryptor");
		decCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().decryptor));
		table.getColumns().add(decCol);

		TableColumn<InvProdData, Double> sobophCol = new TableColumn<>("sobo / h");
		sobophCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().SOBOph));
		sobophCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(sobophCol);

		TableColumn<InvProdData, Double> bosophCol = new TableColumn<>("boso / h");
		bosophCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().BOSOph));
		bosophCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(bosophCol);

		TableColumn<InvProdData, Double> costBOCol = new TableColumn<>("cost BO");
		costBOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleCostBO));
		costBOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(costBOCol);

		TableColumn<InvProdData, Double> costSOCol = new TableColumn<>("cost SO");
		costSOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleCostSO));
		costSOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(costSOCol);

		TableColumn<InvProdData, Double> sellBOCol = new TableColumn<>("sell BO");
		sellBOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleProductBO));
		sellBOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(sellBOCol);

		TableColumn<InvProdData, Double> sellSOCol = new TableColumn<>("sell SO");
		sellSOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleProductSO));
		sellSOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(sellSOCol);

		setCenter(table);
		children = new EvePane[] { optionBox };
	}

	protected void compute() {
		table.getItems().clear();

		updateSettings();
		Map<String, Integer> skills = new HashMap<>();
		String namelimit = null;
		Pattern nameMatcher = namelimit == null ? null : Pattern.compile(".*" + namelimit.toLowerCase() + ".*");

		blueprints()
		.flatMap(bpo -> bpo.invention.products.stream()
				.filter(nameMatcher == null ? mat -> true : mat -> nameMatcher.matcher(mat.name.toLowerCase()).matches())
				.flatMap(mat -> ShowInventionLowestCost
						.evalCostInventionProd(bpo, mat, skills, db(), parent().settings.invention, true).stream()))
		.forEachOrdered(table.getItems()::add);
	}

	protected Stream<Blueprint> blueprints() {
		Stream<Blueprint> ret = db().getBlueprints().values().stream().parallel()
				.filter(bp -> bp != null && bp.invention != null && !bp.invention.products.isEmpty() && bp.copying != null);
		return ret;
	}

	protected void updateSettings() {

	}

}
