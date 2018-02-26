package fr.guiguilechat.eveonline.programs.manager.panes.industry.invention;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.apiv2.Account;
import fr.guiguilechat.eveonline.model.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.esi.modeled.Markets.RegionalMarket;
import fr.guiguilechat.eveonline.model.sde.industry.Blueprint;
import fr.guiguilechat.eveonline.model.sde.locations.Region;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.industry.invention.InventionGainAlgorithm.InventionProdData;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
	protected DetailsPane details;

	protected final TableView<InventionProdData> table = new TableView<>();

	protected static class PriceCellFactory extends TableCell<InventionProdData, Double> {
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
		{
			name = "all5";
		}

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

		details = new DetailsPane(parent);
		TitledPane detailstp = new TitledPane("details", details);
		details.setTitledPane(detailstp);
		detailstp.setExpanded(false);
		setBottom(detailstp);

		table.getSelectionModel().selectedItemProperty().addListener((o1, o2, o3) -> {
			detailstp.setExpanded(o3 != null);
			details.setInvention(o3);
		});

		TableColumn<InventionProdData, String> prodCol = new TableColumn<>("product");
		prodCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().productName));
		prodCol.setMinWidth(300);
		table.getColumns().add(prodCol);

		TableColumn<InventionProdData, String> decCol = new TableColumn<>("decryptor");
		decCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().decryptor));
		table.getColumns().add(decCol);

		TableColumn<InventionProdData, Double> sobophCol = new TableColumn<>("sobo/h");
		sobophCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().SOBOph));
		sobophCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(sobophCol);
		sobophCol.setSortType(SortType.DESCENDING);
		table.getSortOrder().add(sobophCol);

		TableColumn<InventionProdData, Integer> maxCycleCol = new TableColumn<>("cycles");
		maxCycleCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().maxCycles));
		table.getColumns().add(maxCycleCol);

		TableColumn<InventionProdData, Double> marginCol = new TableColumn<>("margin");
		marginCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleMargin));
		table.getColumns().add(marginCol);

		TableColumn<InventionProdData, Double> gainCol = new TableColumn<>("gain/ccl");
		gainCol.setCellValueFactory(
				lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleProductBO - lo.getValue().cycleCostSO));
		gainCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(gainCol);

		TableColumn<InventionProdData, Double> itemCostCol = new TableColumn<>("itemcost");
		itemCostCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().itemCost));
		itemCostCol.setCellFactory(col -> new PriceCellFactory());
		table.getColumns().add(itemCostCol);

		TableColumn<InventionProdData, Double> volumeCol = new TableColumn<>("prod/cycle");
		volumeCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue().cycleAvgProd));
		table.getColumns().add(volumeCol);

		TableColumn<InventionProdData, String> cycleDuration = new TableColumn<>("cycle dur");
		cycleDuration.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(InventionGainAlgorithm.formatDurationSeconds(
				lo.getValue().copyTime + lo.getValue().inventionTime + lo.getValue().manufacturingTime)));
		table.getColumns().add(cycleDuration);

		for (TableColumn<?, ?> t : new TableColumn<?, ?>[] { sobophCol, maxCycleCol, marginCol, gainCol, itemCostCol,
			volumeCol, gainCol, }) {
			t.setMaxWidth(60);
		}

		setCenter(table);
		children = new EvePane[] { options };
	}

	protected void compute() {
		if (options.updateSettings()) {
			parent().settings.store();
		}
		debug("start compute invention");
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
				ObservableList<InventionProdData> items = table.getItems();
				EveChar cs = options.characterSkills.getBox().getValue();
				Map<String, Integer> skills = cs == null ? new HashMap<>() : cs.skillsByName();
				String nameLimit = options.bpPattern.getText();
				Pattern nameMatcher = nameLimit == null || nameLimit.length() == 0 ? null
						: Pattern.compile(".*" + nameLimit.toLowerCase() + ".*");

				List<Blueprint> bpos = blueprints(skills).collect(Collectors.toList());

				RegionalMarket market = ESIAccount.DISCONNECTED.markets
						.getMarket(Region.load().get(parent().settings.invention.marketRegion).id);

				bpos.parallelStream().flatMap(bpo -> bpo.invention.products.stream().parallel()
						.filter(nameMatcher == null ? mat -> true : mat -> nameMatcher.matcher(mat.name.toLowerCase()).matches())
						.flatMap(mat -> InventionGainAlgorithm
								.evalCostInventionProd(bpo, mat, skills, parent().settings.invention, market).stream()))
				.forEachOrdered(items::add);

				Platform.runLater(() -> {
					table.sort();
					options.computeBtn.setDisable(false);
					debug("stop compute invention with cached " + market.nbBuyOrders() + " BO and " + market.nbSellOrders()
					+ " SO");
				});
				return null;
			}

		};
	}

	protected Stream<Blueprint> blueprints(Map<String, Integer> skills) {
		Stream<Blueprint> ret = Blueprint.load().values().stream().parallel()
				.filter(bp -> bp != null && bp.invention != null && !bp.invention.products.isEmpty() && bp.copying != null)
				.filter(bp -> InventionGainAlgorithm.haveReqSkills(skills, bp.copying.skills)
						&& InventionGainAlgorithm.haveReqSkills(skills, bp.invention.skills));
		return ret;
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
