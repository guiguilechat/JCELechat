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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * panel to handle the esi dev keys.
 */
public class ESIDevPane extends BorderPane implements EvePane {

	protected final Manager parent;

	TableView<String> table = new TableView<>();
	protected TextField nameField = new TextField();
	protected TextField appIdField = new TextField();
	protected TextField appSecretField = new TextField();
	TextField calllBackField = new TextField(ESITools.LOCAL_CALLBACK);

	@Override
	public Manager parent() {
		return parent;
	}

	public ESIDevPane(Manager parent) {
		this.parent = parent;
		ObservableList<String> items = FXCollections.observableArrayList(parent.ssoDev2Clients.keySet());
		parent.ssoDev2Clients.addListener((MapChangeListener<String, ObservableList<ESIConnection>>) change -> {
			if (change.wasAdded()) {
				items.add(change.getKey());
			}
			if (change.wasRemoved()) {
				items.remove(change.getKey());
			}	});
		table.setItems(items);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<String, String> nameCol = new TableColumn<>("name");
		nameCol.setCellValueFactory(lo -> new ReadOnlyObjectWrapper<>(lo.getValue()));
		table.getColumns().add(nameCol);

		TableColumn<String, Button> delCol = new TableColumn<>("");
		delCol.setCellValueFactory(ed -> {
			Button delBtn = new Button("remove");
			delBtn.setOnAction(ev -> parent.delSSODev(ed.getValue()));
			return new ReadOnlyObjectWrapper<>(delBtn);
		});
		table.getColumns().add(delCol);
		setCenter(table);

		GridPane addPane = new GridPane();
		Button createBtn = new Button("new");
		createBtn.setOnAction(e -> ESITools.openBrowserForDevCreate());
		Button existingBtn = new Button("load existing");
		existingBtn.setOnAction(e -> ESITools.openBrowserForDevRetrieve());
		addPane.addRow(0, createBtn, existingBtn);
		addPane.addRow(1, new Label("name"), nameField);
		nameField.setTooltip(new Tooltip("name we give to the dev key"));
		addPane.addRow(2, new Label("client id"), appIdField);
		appIdField.setTooltip(new Tooltip("client id provided by ccp"));
		addPane.addRow(3, new Label("secret key"), appSecretField);
		appSecretField.setTooltip(new Tooltip("secret key provided by ccp"));
		addPane.addRow(4, new Label("callback"), calllBackField);
		calllBackField.setTooltip(new Tooltip("you MUST set the callback field o the app to this value"));
		Button submit = new Button("submit");
		submit.setOnAction(e -> submitDevApp());
		addPane.addRow(5, new Label(), submit);
		setRight(addPane);
	}

	protected void submitDevApp() {
		String name = nameField.getText();
		String appId = appIdField.getText();
		String appSecret = appSecretField.getText();
		String callback = calllBackField.getText();
		if (ESITools.checkAppId(appId) && ESITools.checkAppSecret(appSecret)) {
			String base64 = ESITools.encode(appId, appSecret);
			SSODevKey added = parent.addSSODev(name, appId, base64, callback);
			if (added != null) {
				debug("added dev key " + name);
				nameField.setText(null);
				appIdField.setText(null);
				appSecretField.setText(null);
			}
		} else {
			debug("invalid app ID/secret");
		}
	}

}
