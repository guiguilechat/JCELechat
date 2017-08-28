package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListApi extends TableView<APIRoot> {

	protected Manager parent;

	public ListApi(Manager parent) {
		this.parent = parent;

		setItems(parent.apis);

		TableColumn<APIRoot, Integer> keyCol = new TableColumn<>("key");
		keyCol.setCellValueFactory(apiroot -> new ReadOnlyObjectWrapper<>(apiroot.getValue().key.keyID));
		getColumns().add(keyCol);
		TableColumn<APIRoot, String> codeCol = new TableColumn<>("code");
		codeCol.setCellValueFactory(apiroot -> new ReadOnlyObjectWrapper<>(apiroot.getValue().key.code));
		getColumns().add(codeCol);
		TableColumn<APIRoot, APIRoot> delCol = new TableColumn<>("delete");
		delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		delCol.setCellFactory(param -> new TableCell<APIRoot, APIRoot>() {
			private final Button deleteButton = new Button("delete");

			@Override
			protected void updateItem(APIRoot person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(event -> removeApi(person.key.keyID));
			}
		});
		getColumns().add(delCol);
	}

	public void removeApi(int keyID) {
		parent.settings.apiKeys.remove(keyID);
		parent.settings.store();
		parent.settingsChanged();
	}

	public void settingsChanged() {
		refresh();
		// getChildren().clear();
		/**
		 * for (Entry<String, String> e : parent.settings.apiKeys.entrySet()) {
		 * BorderPane apiBox = new BorderPane();
		 *
		 * Button removeButt = new Button("delete"); removeButt.setOnAction(ev ->
		 * removeApi(e.getKey())); apiBox.setRight(removeButt);
		 * apiBox.getChildren().addAll(new Label(e.getKey()), new
		 * Label(e.getValue()), removeButt); getChildren().add(apiBox); }
		 */
	}

}
