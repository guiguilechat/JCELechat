package fr.guiguilechat.eveonline.programs.manager.panes.provision;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.apiv2.Char.BPEntry;
import fr.guiguilechat.eveonline.model.sde.industry.Blueprint;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.ProvisionType;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import javafx.animation.PauseTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ScrollEvent;
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

		my_bpos(true), all_bps(false);

		public final boolean forceOwned;

		private BpSubset(boolean forceOwned) {
			this.forceOwned = forceOwned;
		}

	}

	protected ChoiceBox<BpSubset> subset = new ChoiceBox<>();
	protected TextField filterNames = new TextField();

	public void load() {
		if (loaded) {
			return;
		}
		if (blueprints == null) {
			blueprints = Blueprint.load();
		}
		filterPane = new HBox(10);

		subset.getItems().addAll(BpSubset.values());
		subset.getSelectionModel().select(BpSubset.all_bps);
		subset.getSelectionModel().selectedItemProperty().addListener((o, old, now) -> {
			updateListBPs();
		});


		filterNames.setPromptText("filter blueprints name");
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		filterNames.textProperty().addListener((observable, oldValue, newValue) -> {
			pause.setOnFinished(event -> updateListBPs());
			pause.playFromStart();
		});

		filterPane.getChildren().addAll(subset, filterNames);

		bpsPane = new TableView<>();

		TableColumn<BPRow, String> namecol = new TableColumn<>("name");
		namecol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().name));
		bpsPane.getColumns().add(namecol);

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

	public void updateListBPs() {
		bpsPane.getItems().clear();
		String team = parent.settings.focusedTeam;
		HashMap<Integer, Integer> mats = parent().getTeamProvision(team, ProvisionType.MATERIAL).blueprints;
		HashMap<Integer, Integer> prods = parent().getTeamProvision(team, ProvisionType.PRODUCT).blueprints;
		HashMap<Integer, Integer> sos = parent().getTeamProvision(team, ProvisionType.SO).blueprints;
		streambps().forEachOrdered(bp -> {
			BPRow row = makeBPNode(bp);
			row.materialField.setValue(mats.getOrDefault(bp.id, 0));
			row.productField.setValue(prods.getOrDefault(bp.id, 0));
			row.soField.setValue(sos.getOrDefault(bp.id, 0));
			bpsPane.getItems().add(row);
		});
	}

	protected BPRow makeBPNode(Blueprint bp) {
		BPRow ret = new BPRow();
		ret.name = bp.name.replaceAll(" Blueprint", "");
		ret.updatebtn.setOnAction(e -> update(bp, ret.materialField, ret.productField, ret.soField));
		return ret;
	}

	protected void update(Blueprint bp, TextField materialField, TextField productField, TextField soField) {
		int mat = getNbValue(materialField);
		int product = getNbValue(productField);
		int so = getNbValue(soField);
		parent.provisionBP(bp, mat, product, so);
	}

	public static int getNbValue(TextField field) {
		try {
			return Integer.parseInt(field.getText());
		} catch (Exception e) {
			return 0;
		}
	}

	protected static class BPRow {
		String name;
		TypedField<Integer> materialField = TypedField.positivIntField(0);
		TypedField<Integer> productField = TypedField.positivIntField(0);
		TypedField<Integer> soField = TypedField.positivIntField(0);

		Button updatebtn = new Button("update");

		protected void handleScrollMat(ScrollEvent se) {
			materialField.setValue(materialField.getValue() + (se.getDeltaY() > 0 ? 1 : -1));
			se.consume();
		}

		protected void handleScrollProd(ScrollEvent se) {
			productField.setValue(productField.getValue() + (se.getDeltaY() > 0 ? 1 : -1));
			se.consume();
		}

		protected void handleScrollSo(ScrollEvent se) {
			soField.setValue(soField.getValue() + (se.getDeltaY() > 0 ? 1 : -1));
			se.consume();
		}

		public BPRow() {
			materialField.setOnScroll(this::handleScrollMat);
			productField.setOnScroll(this::handleScrollProd);
			soField.setOnScroll(this::handleScrollSo);
		}
	}

	protected Stream<Blueprint> streambps() {
		Stream<Blueprint> ret = streambps(subset.getSelectionModel().getSelectedItem());
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
