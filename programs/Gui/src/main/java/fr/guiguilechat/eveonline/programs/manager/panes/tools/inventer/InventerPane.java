package fr.guiguilechat.eveonline.programs.manager.panes.tools.inventer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.apiv2.Account;
import fr.guiguilechat.eveonline.model.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.InventionParams;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.inventer.InventionGainAlgorithm.InvProdData;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
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
				setText(InventionGainAlgorithm.formatPrice(value));
			}
		}
	}

	public static final EveChar ALL5 = new Account(null).new EveChar() {

		@SuppressWarnings("serial")
		public java.util.LinkedHashMap<String, Integer> ALL5MAP = new LinkedHashMap<String, Integer>() {
			@Override
			public Integer get(Object key) {
				return 5;
			};

			@Override
			public Integer getOrDefault(Object key, Integer defaultValue) {
				return 5;
			};
		};

		@Override
		public java.util.LinkedHashMap<String, Integer> skillsByName() {
			return ALL5MAP;
		};

	};

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
		sobophCol.setSortType(SortType.DESCENDING);
		table.getSortOrder().add(sobophCol);

		TableColumn<InvProdData, Double> marginCol = new TableColumn<>("margin");
		marginCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleMargin));
		table.getColumns().add(marginCol);

		TableColumn<InvProdData, Double> costSOCol = new TableColumn<>("inSO");
		costSOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleCostSO));
		costSOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(costSOCol);

		TableColumn<InvProdData, Double> sellBOCol = new TableColumn<>("outBO");
		sellBOCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleProductBO));
		sellBOCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(sellBOCol);

		TableColumn<InvProdData, Double> volumeCol = new TableColumn<>("prod/cycle");
		volumeCol
		.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleAvgProd));
		table.getColumns().add(volumeCol);

		TableColumn<InvProdData, String> cycleDuration = new TableColumn<>("cycle dur");
		cycleDuration.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(InventionGainAlgorithm.formatDurationSeconds(
				lo.getValue().copyTime + lo.getValue().inventionTime + lo.getValue().manufacturingTime)));
		table.getColumns().add(cycleDuration);

		for (TableColumn<?, ?> t : new TableColumn<?, ?>[] { sobophCol, costSOCol, sellBOCol, volumeCol }) {
			t.setMaxWidth(60);
		}

		setCenter(table);
		children = new EvePane[] { options };
	}

	protected void compute() {
		updateSettings();
		table.getItems().clear();
		options.computeBtn.setDisable(true);
		Task<Void> task = task();
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	Task<Void> task() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				options.computeBtn.setDisable(true);
				table.getItems().clear();

				EveChar cs = options.characterSkills.getValue();
				Map<String, Integer> skills = cs == null ? new HashMap<>() : cs.skillsByName();
				Pattern nameMatcher = nameLimit == null ? null : Pattern.compile(".*" + nameLimit.toLowerCase() + ".*");

				ObservableList<InvProdData> list = FXCollections.observableArrayList();
				List<Blueprint> bpos = blueprints(skills).collect(Collectors.toList());
				int maxnb = bpos.size() * 2;

				bpos.parallelStream().flatMap(bpo -> bpo.invention.products.stream().parallel()
						.filter(nameMatcher == null ? mat -> true : mat -> nameMatcher.matcher(mat.name.toLowerCase()).matches())
						.flatMap(mat -> InventionGainAlgorithm
								.evalCostInventionProd(bpo, mat, skills, db(), parent().settings.invention,
										options.onlyBest.isSelected())
								.stream()))
				.forEachOrdered(e -> {
					list.add(e);
					updateProgress(list.size(), maxnb);
				});
				Platform.runLater(() -> {
					table.getItems().addAll(list);
					table.sort();
					options.computeBtn.setDisable(false);
				});
				return null;
			}

		};
	}

	protected Stream<Blueprint> blueprints(Map<String, Integer> skills) {
		Stream<Blueprint> ret = db().getBlueprints().values().stream().parallel()
				.filter(bp -> bp != null && bp.invention != null && !bp.invention.products.isEmpty() && bp.copying != null)
				.filter(bp -> InventionGainAlgorithm.haveReqSkills(skills, bp.copying.skills)
						&& InventionGainAlgorithm.haveReqSkills(skills, bp.invention.skills));
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
		if (settings.minCycles != options.minCycles.getValue()) {
			settings.minCycles = options.minCycles.getValue();
			modification = true;
		}
		if (settings.minHours != options.minHours.getValue()) {
			settings.minHours = options.minHours.getValue();
			modification = true;
		}
		if (!options.copystruct.getValue().name().equals(settings.copystruct)) {
			settings.copystruct = options.copystruct.getValue().name();
			modification = true;
		}
		if (!options.inventstruct.getValue().name().equals(settings.inventstruct)) {
			settings.inventstruct = options.inventstruct.getValue().name();
			modification = true;
		}
		if (!options.manufstruct.getValue().name().equals(settings.manufstruct)) {
			settings.manufstruct = options.manufstruct.getValue().name();
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

	public static enum StructBonus {
		none(0, 0, 0), raitaru(15, 1, 3), azbel(20, 1, 4), sotiyo(30, 1, 5);
		public final double me, te, cost;

		private StructBonus(double te, double me, double cost) {
			this.me = me;
			this.te = te;
			this.cost = cost;
		}
	}

}
