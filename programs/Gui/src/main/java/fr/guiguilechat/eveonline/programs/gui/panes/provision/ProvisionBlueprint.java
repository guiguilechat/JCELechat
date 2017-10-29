package fr.guiguilechat.eveonline.programs.gui.panes.provision;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.apiv2.Char.BPEntry;
import fr.guiguilechat.eveonline.model.database.yaml.Blueprint;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.animation.PauseTransition;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	protected VBox bpsPane;

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
		subset.getSelectionModel().selectedItemProperty().addListener((o, old, now) -> updateListBPs());

		blueprints.values().stream().map(bp -> bp.groupName).distinct().forEach(allowedGroups.getItems()::add);
		allowedGroups.getItems().sort(String::compareTo);
		allowedGroups.getItems().add(0, null);
		allowedGroups.getSelectionModel().selectedItemProperty().addListener((o, old, now) -> updateListBPs());

		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		filterNames.textProperty().addListener((observable, oldValue, newValue) -> {
			pause.setOnFinished(event -> updateListBPs());
			pause.playFromStart();
		});

		filterPane.getChildren().addAll(subset, allowedGroups, filterNames);
		bpsPane = new VBox();
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
		bpsPane.getChildren().clear();
		addBPs(streambps());
	}

	protected void addBPs(Stream<Blueprint> bps) {
		bpsPane.getChildren().addAll(
				bps.map(bp -> new Label(bp.catName + " : " + bp.groupName + " : " + bp.name))
				.collect(Collectors.toList()));
	}

	protected Stream<Blueprint> streambps() {
		BpSubset selection = subset.getSelectionModel().getSelectedItem();
		Stream<Blueprint> ret = null;
		if (selection.forceOwned) {
			Stream<BPEntry> bpeStream = parent.streamFTeamCharacters().parallel().flatMap(c -> c.blueprints().stream());
			if (selection.forceOriginal) {
				bpeStream = bpeStream.filter(bpe -> bpe.runs == -1);
			}
			ret = bpeStream.map(bpe -> bpe.typeName).distinct().map(blueprints::get).filter(bp -> bp != null);
		} else {
			ret = blueprints.values().stream();
		}
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
