package fr.guiguilechat.eveonline.programs.gui.panes;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListApi extends TableView<Object> {

	protected Manager parent;

	@SuppressWarnings("unchecked")
	public ListApi(Manager parent) {
		this.parent = parent;

		TableColumn<Object, String> firstNameCol = new TableColumn<>("key");
		TableColumn<Object, String> lastNameCol = new TableColumn<>("code");
		TableColumn<Object, Button> emailCol = new TableColumn<>("delete");

		getColumns().addAll(firstNameCol, lastNameCol, emailCol);
	}

	public void removeApi(String key) {
		parent.settings.apiKeys.remove(key);
		parent.settings.store();
		parent.settingsChanged();
	}

	public void settingsChanged() {
		getChildren().clear();
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
