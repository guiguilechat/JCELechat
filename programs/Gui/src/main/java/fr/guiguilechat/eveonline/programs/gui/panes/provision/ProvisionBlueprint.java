package fr.guiguilechat.eveonline.programs.gui.panes.provision;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.apiv2.Char.BPEntry;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.Settings.ProvisionType;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.animation.PauseTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class ProvisionBlueprint extends BorderPane implements EvePane {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProvisionBlueprint.class);

	private final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ProvisionBlueprint(Manager parent) {
		this.parent = parent;
	}

	protected boolean loaded = false;
	protected boolean shown = false;
	protected LinkedHashMap<String, Blueprint> blueprints;

	protected HBox filterPane;
	protected TableView<BPRow> bpsPane;

	protected static enum BpSubset {

		my_bpos(true, true), my_bps(false, true), all_bps(true, false);

		public final boolean forceOriginal;
		public final boolean forceOwned;

		private BpSubset(boolean forceOriginal, boolean forceOwned) {
			this.forceOriginal = forceOriginal;
			this.forceOwned = forceOwned;
		}

	}

	protected ChoiceBox<BpSubset> subset = new ChoiceBox<>();
	protected ChoiceBox<String> allowedGroups = new ChoiceBox<>();
	protected TextField filterNames = new TextField();

	public void load() {
		if (loaded) {
			return;
		}
		if (blueprints == null) {
			blueprints = db().getBlueprints();
		}
		filterPane = new HBox(10);

		subset.getItems().addAll(BpSubset.values());
		subset.getSelectionModel().select(BpSubset.all_bps);
		subset.getSelectionModel().selectedItemProperty().addListener((o, old, now) -> {
			updateListBPs();
			updateGroupList();
		});

		allowedGroups.setMinWidth(300);
		allowedGroups.setMaxWidth(300);
		allowedGroups.getSelectionModel().selectedItemProperty().addListener((o, old, now) -> updateListBPs());
		updateGroupList();

		filterNames.setPromptText("filter blueprints name");
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		filterNames.textProperty().addListener((observable, oldValue, newValue) -> {
			pause.setOnFinished(event -> updateListBPs());
			pause.playFromStart();
		});

		filterPane.getChildren().addAll(subset, allowedGroups, filterNames);

		bpsPane = new TableView<>();

		TableColumn<BPRow, String> namecol = new TableColumn<>("name");
		namecol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().name));
		bpsPane.getColumns().add(namecol);

		TableColumn<BPRow, String> groupcol = new TableColumn<>("group");
		groupcol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().group));
		bpsPane.getColumns().add(groupcol);

		TableColumn<BPRow, TextField> materialscol = new TableColumn<>("materials");
		materialscol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().materialField));
		materialscol.setMinWidth(80);
		materialscol.setMaxWidth(80);
		bpsPane.getColumns().add(materialscol);

		TableColumn<BPRow, TextField> productcol = new TableColumn<>("product");
		productcol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().productField));
		productcol.setMinWidth(80);
		productcol.setMaxWidth(80);
		bpsPane.getColumns().add(productcol);

		TableColumn<BPRow, TextField> salescol = new TableColumn<>("sell orders");
		salescol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().soField));
		salescol.setMinWidth(80);
		salescol.setMaxWidth(80);
		bpsPane.getColumns().add(salescol);

		TableColumn<BPRow, Button> sendCol = new TableColumn<>("");
		sendCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().updatebtn));
		bpsPane.getColumns().add(sendCol);

		setTop(new TitledPane("filters", filterPane));
		getTop().setStyle("-fx-padding: 5 5 0 10;");
		setCenter(bpsPane);
		loaded = true;
	}

	@Override
	public void onIsShown(boolean shown) {
		if (shown) {
			load();
		}
		this.shown = shown;
		updateListBPs();
	}

	protected void updateGroupList() {
		String selected = allowedGroups.getSelectionModel().getSelectedItem();
		allowedGroups.getItems().clear();
		streambps(subset.getSelectionModel().getSelectedItem()).map(bp -> bp.groupName).distinct()
		.forEachOrdered(allowedGroups.getItems()::add);
		allowedGroups.getItems().sort(String::compareTo);
		if (allowedGroups.getItems().contains(selected)) {
			allowedGroups.getSelectionModel().select(selected);
		}
	}

	public void updateListBPs() {
		bpsPane.getItems().clear();
		HashMap<Integer, Integer> mats = parent().getFTeamProvision(ProvisionType.MATERIAL).blueprints;
		HashMap<Integer, Integer> prods = parent().getFTeamProvision(ProvisionType.PRODUCT).blueprints;
		HashMap<Integer, Integer> sos = parent().getFTeamProvision(ProvisionType.SO).blueprints;
		streambps().forEachOrdered(bp->{
			BPRow row = makeBPNode(bp);
			row.materialField.setText("" + mats.getOrDefault(bp.id, 0));
			row.productField.setText("" + prods.getOrDefault(bp.id, 0));
			row.soField.setText("" + sos.getOrDefault(bp.id, 0));
			bpsPane.getItems().add(row);
		});
	}

	protected BPRow makeBPNode(Blueprint bp) {
		BPRow ret = new BPRow();
		ret.name = bp.name.replaceAll(" Blueprint", "");
		ret.group = bp.groupName.replaceAll(" Blueprint", "");
		ret.updatebtn.setOnAction(e -> update(bp, ret.materialField, ret.productField, ret.soField));
		return ret;
	}

	protected void update(Blueprint bp, TextField materialField, TextField productField, TextField soField) {
		int mat = Integer.parseInt(materialField.getText());
		int product = Integer.parseInt(productField.getText());
		int so = Integer.parseInt(soField.getText());
		parent.provisionBP(bp, mat, product, so);
	}

	protected static class BPRow {
		String name;
		String group;
		TextField materialField = new TextField();
		TextField productField = new TextField();
		TextField soField = new TextField();

		Button updatebtn = new Button("update");
	}

	protected Stream<Blueprint> streambps() {
		Stream<Blueprint> ret = streambps(subset.getSelectionModel().getSelectedItem());
		String group = allowedGroups.getSelectionModel().getSelectedItem();
		if (group != null) {
			ret = ret.filter(bpe -> bpe.groupName.equals(group));
		}
		String filterName = filterNames.getText();
		if (filterName != null && filterName.length() > 0) {
			Pattern pat = Pattern.compile(".*" + filterName.toLowerCase() + ".*");
			ret = ret.filter(bpe -> pat.matcher(bpe.name.toLowerCase()).matches());
		}
		return ret;
	}

	protected Stream<Blueprint> streambps(BpSubset selection) {
		if (selection.forceOwned) {
			Stream<BPEntry> bpeStream = parent.streamFTeamCharacters().parallel().flatMap(c -> c.blueprints().stream());
			if (selection.forceOriginal) {
				bpeStream = bpeStream.filter(bpe -> bpe.runs == -1);
			}
			return bpeStream.map(bpe -> bpe.typeName).distinct().map(blueprints::get).filter(bp -> bp != null);
		} else {
			return blueprints.values().stream();
		}
	}

	@Override
	public void onFocusedTeam(String teamName) {
		if (shown) {
			updateListBPs();
		}
	}

	@Override
	public void onAdd2Team(String team, String character) {
		if (shown && team == parent.settings.focusedTeam) {
			updateListBPs();
		}
	}

}
