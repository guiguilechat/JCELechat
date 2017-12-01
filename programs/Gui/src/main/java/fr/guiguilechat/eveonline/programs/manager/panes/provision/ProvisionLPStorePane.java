package fr.guiguilechat.eveonline.programs.manager.panes.provision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.ProvisionType;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import fr.guiguilechat.eveonline.programs.manager.panes.ScrollAdd.IntScrollAdd;
import javafx.animation.PauseTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class ProvisionLPStorePane extends BorderPane implements EvePane {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProvisionLPStorePane.class);

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ProvisionLPStorePane(Manager parent) {
		this.parent = parent;
	}

	boolean loaded = false;
	protected ArrayList<LPOffer> lpoffers;

	protected ChoiceBox<String> corporationChoice = new ChoiceBox<>();
	protected ChoiceBox<Boolean> blueprintAllowedChoice = new ChoiceBox<>();
	protected TextField filterNames = new TextField();
	protected HBox selectionPane = new HBox();
	protected TableView<OfferRow> listOffersPane = new TableView<>();

	public void load() {
		if (loaded) {
			return;
		}
		if (lpoffers == null) {
			lpoffers = db().getLPOffers();
		}
		selectionPane.getChildren().add(new Label("corporation: "));
		corporationChoice.getItems().add(null);
		lpoffers.stream().map(lo -> lo.corporation).distinct().sorted().forEachOrdered(corporationChoice.getItems()::add);
		corporationChoice.setOnAction(ev -> updateOffers());
		selectionPane.getChildren().add(corporationChoice);

		selectionPane.getChildren().add(new Label("blueprints: "));
		blueprintAllowedChoice.getItems().addAll(true, false, null);
		blueprintAllowedChoice.setOnAction(ev -> updateOffers());
		selectionPane.getChildren().add(blueprintAllowedChoice);

		filterNames.setPromptText("filter offer name");
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		filterNames.textProperty().addListener((observable, oldValue, newValue) -> {
			pause.setOnFinished(event -> updateOffers());
			pause.playFromStart();
		});
		selectionPane.getChildren().add(filterNames);

		setTop(selectionPane);

		TableColumn<OfferRow, String> namecol = new TableColumn<>("name");
		namecol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().offer.offer_name));
		namecol.setMinWidth(50);
		listOffersPane.getColumns().add(namecol);

		TableColumn<OfferRow, Integer> lpcol = new TableColumn<>("lp");
		lpcol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().offer.requirements.lp));
		lpcol.setMinWidth(80);
		lpcol.setMaxWidth(80);
		listOffersPane.getColumns().add(lpcol);
		lpcol.setSortType(SortType.DESCENDING);
		listOffersPane.getSortOrder().add(lpcol);

		TableColumn<OfferRow, TextField> matCol = new TableColumn<>("materials");
		matCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().mat_field));
		matCol.setMinWidth(50);
		listOffersPane.getColumns().add(matCol);

		TableColumn<OfferRow, TextField> prodCol = new TableColumn<>("product");
		prodCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().prod_field));
		prodCol.setMinWidth(50);
		listOffersPane.getColumns().add(prodCol);

		TableColumn<OfferRow, TextField> soCol = new TableColumn<>("sell orders");
		soCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().so_field));
		soCol.setMinWidth(50);
		listOffersPane.getColumns().add(soCol);

		TableColumn<OfferRow, Button> sendCol = new TableColumn<>("");
		sendCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().bt_send));
		sendCol.setMinWidth(100);
		sendCol.setMaxWidth(100);
		listOffersPane.getColumns().add(sendCol);

		listOffersPane.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		setCenter(listOffersPane);
		loaded = true;
	}

	protected void updateOffers() {
		listOffersPane.getItems().clear();
		if (!shown) {
			return;
		}
		load();
		String team = parent().settings.focusedTeam;

		HashMap<Integer, Integer> materials = parent().getTeamProvision(team, ProvisionType.MATERIAL).lpoffers,
				products = parent().getTeamProvision(team, ProvisionType.PRODUCT).lpoffers,
				sorders = parent().getTeamProvision(team, ProvisionType.SO).lpoffers;
		String corp = corporationChoice.getValue();
		Boolean bp = blueprintAllowedChoice.getValue();
		String nameFilter = filterNames.getText();
		Pattern pat = null;
		if (nameFilter != null && nameFilter.length() != 0) {
			pat = Pattern.compile(".*" + nameFilter.toLowerCase() + ".*");
		}
		HashSet<Integer> addedIDs = new HashSet<>();
		for (LPOffer lo : lpoffers) {
			boolean isbp = lo.offer_name.contains("Blueprint");
			if ((corp == null || corp.equals(lo.corporation)) && (bp == null || bp == isbp)
					&& (pat == null || pat.matcher(lo.offer_name.toLowerCase()).matches()) && addedIDs.add(lo.id)) {
				OfferRow row = getRow(lo);
				row.mat_field.setValue(materials.getOrDefault(lo.id, 0));
				row.prod_field.setValue(products.getOrDefault(lo.id, 0));
				row.so_field.setValue(sorders.getOrDefault(lo.id, 0));
				listOffersPane.getItems().add(row);
			}
		}
		listOffersPane.sort();
	}

	/**
	 * show a lp store offer as a row
	 *
	 */
	protected static class OfferRow {
		public LPOffer offer;
		public TypedField<Integer> mat_field, prod_field, so_field;
		public Button bt_send;

		public OfferRow() {
			bt_send = new Button("update");
			mat_field = TypedField.positivIntField(0);
			mat_field.setOnScroll(new IntScrollAdd(1, mat_field));
			prod_field = TypedField.positivIntField(0);
			prod_field.setOnScroll(new IntScrollAdd(1, prod_field));
			so_field = TypedField.positivIntField(0);
			so_field.setOnScroll(new IntScrollAdd(1, so_field));
		}
	}

	protected HashMap<LPOffer, OfferRow> cacherows = new HashMap<>();

	protected OfferRow getRow(LPOffer offer) {
		if (cacherows.containsKey(offer)) {
			return cacherows.get(offer);
		}
		OfferRow ret = new OfferRow();
		ret.offer = offer;
		ret.bt_send.setOnAction(ev -> parent().provisionLPOffer(offer, ret.mat_field.getValue(), ret.prod_field.getValue(),
				ret.so_field.getValue()));
		cacherows.put(offer, ret);
		return ret;
	}

	@Override
	public void onFocusedTeam(String teamName) {
		updateOffers();
	}

	@Override
	public void onNewProvision(ProvisionType ptype, int itemID, int qtty) {
		updateOffers();
	}

	protected boolean shown = false;

	@Override
	public void onIsShown(boolean shown) {
		this.shown = shown;
		if (shown) {
			updateOffers();
		}
	}
}
