package fr.guiguilechat.eveonline.programs.gui.panes.provision;

import java.util.LinkedHashMap;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.apiv2.Char.BPEntry;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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

	boolean loaded = false;
	protected LinkedHashMap<String, Blueprint> blueprints;

	protected HBox filterPane;
	protected GridPane bpsPane;

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
		subset.getSelectionModel().selectedItemProperty().addListener((o, old, now) -> updateListBPs());
		filterPane.getChildren().addAll(subset);
		bpsPane = new GridPane();
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
		updateListBPs();
	}

	public void updateListBPs() {
		bpsPane.getChildren().clear();
	}

	protected Stream<Blueprint> streambps() {
		BpSubset selection = subset.getSelectionModel().getSelectedItem();
		if (selection.forceOwned) {
			Stream<BPEntry> ret = parent.streamFTeamCharacters().parallel().flatMap(c -> c.blueprints().stream());
			if (selection.forceOriginal) {
				ret = ret.filter(bpe -> bpe.runs == -1);
			}
			return ret.map(bpe -> bpe.typeName).distinct().map(blueprints::get).filter(bp -> bp != null);
		} else {
			return blueprints.values().stream();
		}
	}

}
