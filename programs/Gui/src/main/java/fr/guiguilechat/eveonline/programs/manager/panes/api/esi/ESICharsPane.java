package fr.guiguilechat.eveonline.programs.manager.panes.api.esi;

import java.util.stream.Stream;

import fr.guiguilechat.eveonline.model.esi.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.ESITools;
import fr.guiguilechat.eveonline.model.esi.ESITools.SCOPES;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.SSODevKey;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

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

		TableColumn<ESIConnection, String> nameCol = new TableColumn<>();
		nameCol.setCellValueFactory(co -> new ReadOnlyObjectWrapper<>(co.getValue().verify.characterName()));
		table.getColumns().add(nameCol);

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
			SSODevKey devKey = parent.settings.ssoKeys.get(selectDev.getValue());
			ESITools.openBrowserForApp(devKey.appID, devKey.callback,
					Stream.of(SCOPES.values()).map(SCOPES::name).toArray(String[]::new));
		});
		addPane.addRow(0, selectDev, btnCreate);
		TextField urlf = new TextField();
		addPane.addRow(1, urlf, new Label("redirected url"));
		Button addAccount = new Button("add account");
		addAccount.setOnAction(ev -> {
			String redirectURL = urlf.getText();
			if (redirectURL != null && redirectURL.length() > 0) {
				SSODevKey dev = parent.settings.ssoKeys.get(selectDev.getValue());
				String authCode = ESITools.callbackURLToAuthCode(redirectURL, dev.callback);
				String refresh = ESITools.getRefreshToken(dev.base64, authCode);
				if (refresh != null) {
					parent.addSSOClient(selectDev.getValue(), refresh);
					urlf.setText(null);
				}
			}
		});
		addPane.addRow(2, addAccount);

		setRight(addPane);
	}

}
