package fr.guiguilechat.eveonline.programs.manager.panes.api.esi;

import fr.guiguilechat.eveonline.model.esi.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.ESITools;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.SSODevKey;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ESICharsPane extends BorderPane implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected final TableView<ESIConnection> table = new TableView<>();

	protected final GridPane addPane = new GridPane();

	public ESICharsPane(Manager parent) {
		this.parent = parent;
		ObservableList<ESIConnection> lCons = FXCollections.observableArrayList(parent.ssoChar2Con.values());
		parent.ssoChar2Con.addListener((MapChangeListener<String, ESIConnection>) change -> {
			if (change.wasAdded()) {
				lCons.add(change.getValueAdded());
			}
			if (change.wasRemoved()) {
				lCons.remove(change.getValueRemoved());
			}
		});
		table.setItems(lCons);

		TableColumn<ESIConnection, String> nameCol = new TableColumn<>("character");
		nameCol.setCellValueFactory(co -> new ReadOnlyObjectWrapper<>(co.getValue().verify.characterName()));
		table.getColumns().add(nameCol);

		TableColumn<ESIConnection, VBox> shopCol = new TableColumn<>("options");
		shopCol.setCellValueFactory(ed -> {
			String name = ed.getValue().verify.characterName();
			CheckBox shopper = new CheckBox("shopper");
			shopper.setTooltip(new Tooltip("if this character is used for shopping"));
			shopper.setSelected(parent.settings.shopper().contains(name));
			shopper.selectedProperty().addListener((obj, old, now) -> {
				if (now) {
					parent.settings.shopper().add(name);
				} else {
					parent.settings.shopper().remove(name);
				}
			});
			CheckBox pi = new CheckBox("PI");
			pi.setTooltip(new Tooltip("if the character has PI to monitor"));
			pi.setSelected(parent.settings.planets().contains(name));
			pi.selectedProperty().addListener((obj, old, now) -> {
				if (now) {
					parent.settings.planets().add(name);
				} else {
					parent.settings.planets().remove(name);
				}
			});
			CheckBox corp = new CheckBox("use corp");
			corp.setTooltip(new Tooltip("use the character's corp access"));
			corp.setSelected(parent.settings.corps().contains(name));
			corp.selectedProperty().addListener((obj, old, now) -> {
				if (now) {
					parent.settings.corps().add(name);
				} else {
					parent.settings.corps().remove(name);
				}
			});
			VBox ret = new VBox(shopper, pi, corp);
			return new ReadOnlyObjectWrapper<>(ret);
		});

		table.getColumns().add(shopCol);

		TableColumn<ESIConnection, Button> delCol = new TableColumn<>("");
		delCol.setCellValueFactory(ed -> {
			Button delBtn = new Button("remove");
			delBtn.setOnAction(ev -> parent.delSSOClient(ed.getValue()));
			return new ReadOnlyObjectWrapper<>(delBtn);
		});
		table.getColumns().add(delCol);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		setCenter(table);

		ObservableList<String> devNames = FXCollections.observableArrayList(parent.ssoDev2Clients.keySet());
		parent.ssoDev2Clients.addListener((MapChangeListener<String, ObservableList<ESIConnection>>) change -> {
			if (change.wasAdded()) {
				devNames.add(change.getKey());
			}
			if (change.wasRemoved()) {
				devNames.remove(change.getKey());
			}
		});
		ChoiceBox<String> selectDev = new ChoiceBox<>(devNames);
		// devNames.addListener((ListChangeListener<String>)change -> {
		// if (!devNames.contains(selectDev.getValue())) {
		// if (devNames.isEmpty()) {
		// selectDev.setValue(null);
		// } else {
		// selectDev.setValue(devNames.get(0));
		// }
		// }
		// });

		Button btnCreate = new Button("link your account");
		btnCreate.setOnAction(ev -> {
			SSODevKey devKey = parent.settings.ssoKeys().get(selectDev.getValue());
			ESITools.openBrowserForApp(devKey.appID, devKey.callback, ESITools.SCOPES);
		});
		addPane.addRow(0, selectDev, btnCreate);
		TextField urlf = new TextField();
		urlf.setPrefWidth(100);
		addPane.addRow(1, urlf, new Label("redirected url"));
		Button addAccount = new Button("add account");
		addAccount.setOnAction(ev -> {
			String redirectURL = urlf.getText();
			if (redirectURL != null && redirectURL.length() > 0) {
				SSODevKey dev = parent.settings.ssoKeys().get(selectDev.getValue());
				String authCode = ESITools.callbackURLToAuthCode(redirectURL, dev.callback);
				String refresh = ESITools.getRefreshToken(dev.base64, authCode);
				if (refresh != null) {
					parent.addSSOClient(selectDev.getValue(), refresh);
					urlf.setText(null);
				}
			}
		});
		addPane.addRow(2, addAccount);

		TitledPane tp = new TitledPane("add an account", addPane);
		tp.setCollapsible(false);
		setRight(tp);
	}

}
