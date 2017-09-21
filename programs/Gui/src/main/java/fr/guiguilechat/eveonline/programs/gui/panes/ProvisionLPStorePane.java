package fr.guiguilechat.eveonline.programs.gui.panes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.LPOffer.ItemRef;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ProvisionLPStorePane extends BorderPane implements EvePane {

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

	ChoiceBox<String> corporationChoice = null;
	ChoiceBox<Boolean> blueprintAllowedChoice = null;
	HBox selectionPane = new HBox();
	TableView<OfferRow> listOffersPane = new TableView<>();

	public void load() {
		if (loaded) {
			return;
		}
		lpoffers = db().getLPOffers();
		selectionPane.getChildren().add(new Label("corporation: "));
		corporationChoice = new ChoiceBox<>();
		corporationChoice.getItems()
		.addAll(lpoffers.stream().map(lo -> lo.corporation).distinct().sorted().collect(Collectors.toList()));
		// allow no value in the choicebox
		corporationChoice.getItems().add(null);
		corporationChoice.setOnAction(ev -> updateOffers());
		selectionPane.getChildren().add(corporationChoice);

		selectionPane.getChildren().add(new Label("blueprints: "));
		blueprintAllowedChoice = new ChoiceBox<>();
		blueprintAllowedChoice.getItems().addAll(true, false, null);
		blueprintAllowedChoice.setOnAction(ev -> updateOffers());
		selectionPane.getChildren().add(blueprintAllowedChoice);

		setTop(selectionPane);

		TableColumn<OfferRow, String> namecol = new TableColumn<>("name");
		namecol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().offer.offer_name));
		listOffersPane.getColumns().add(namecol);

		TableColumn<OfferRow, Integer> lpcol = new TableColumn<>("lp");
		lpcol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().offer.requirements.lp));
		lpcol.setMinWidth(80);
		lpcol.setMaxWidth(80);
		listOffersPane.getColumns().add(lpcol);

		TableColumn<OfferRow, TextField> nbCol = new TableColumn<>();
		nbCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().nb_field));
		nbCol.setMinWidth(50);
		nbCol.setMaxWidth(50);
		listOffersPane.getColumns().add(nbCol);

		TableColumn<OfferRow, Button> sendCol = new TableColumn<>("");
		sendCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().bt_send));
		sendCol.setMinWidth(100);
		sendCol.setMaxWidth(100);
		listOffersPane.getColumns().add(sendCol);

		listOffersPane.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		setCenter(listOffersPane);
		updateOffers();
		loaded = true;
	}

	protected void updateOffers() {
		listOffersPane.getItems().clear();
		String corp = corporationChoice.getValue();
		if (corp == null) {
			return;
		}
		Boolean bp = blueprintAllowedChoice.getValue();
		for (LPOffer lo : lpoffers) {
			boolean isbp = lo.offer_name.contains("Blueprint");
			if (corp != null && corp.equals(lo.corporation) && !(bp == Boolean.TRUE && !isbp)
					&& !(bp == Boolean.FALSE && isbp)) {
				listOffersPane.getItems().add(getRow(lo));
			}
		}
	}

	/**
	 * show a lp store offer as a row
	 *
	 */
	protected static class OfferRow {
		public LPOffer offer;
		public TextField nb_field;
		public Button bt_send;
	}

	protected HashMap<LPOffer, OfferRow> cacherows = new HashMap<>();

	protected OfferRow getRow(LPOffer offer) {
		if (cacherows.containsKey(offer)) {
			return cacherows.get(offer);
		}
		OfferRow ret = new OfferRow();
		ret.offer = offer;
		ret.nb_field = new TextField("1");
		ret.bt_send = new Button("provision");
		ret.bt_send.setOnAction(ev -> provision(ret, ret.nb_field.getText()));
		cacherows.put(offer, ret);
		return ret;
	}

	public void provision(OfferRow row, String nb_text) {
		int nb_provision = Integer.parseInt(nb_text);
		HashMap<Integer, Integer> totalreq = new HashMap<>();
		for (ItemRef e : row.offer.requirements.items) {
			int req = totalreq.getOrDefault(e.type_id, 0) + e.quantity * nb_provision;
			totalreq.put(e.type_id, req);
		}
		parent().provision(totalreq);
		row.nb_field.setText("1");
	}

}
