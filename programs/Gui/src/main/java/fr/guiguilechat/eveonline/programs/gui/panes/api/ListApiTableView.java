package fr.guiguilechat.eveonline.programs.gui.panes.api;

import fr.guiguilechat.eveonline.model.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ListApiTableView extends TableView<APIRoot> implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ListApiTableView(Manager parent) {
		this.parent = parent;
		setStyle("-fx-border-color: black");

		setItems(parent.apis);

		TableColumn<APIRoot, Integer> keyCol = new TableColumn<>("key");
		keyCol.setCellValueFactory(apiroot -> new ReadOnlyObjectWrapper<>(apiroot.getValue().key.keyID));
		getColumns().add(keyCol);
		TableColumn<APIRoot, String> codeCol = new TableColumn<>("code");
		codeCol.setCellValueFactory(apiroot -> new ReadOnlyObjectWrapper<>(apiroot.getValue().key.code));
		getColumns().add(codeCol);
		TableColumn<APIRoot, APIRoot> delCol = new TableColumn<>("");
		delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		delCol.setCellFactory(param -> new TableCell<APIRoot, APIRoot>() {
			private final Button deleteButton = new Button("X");

			@Override
			protected void updateItem(APIRoot person, boolean empty) {
				super.updateItem(person, empty);

				if (person == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnMouseEntered((MouseEvent e) -> {
					deleteButton.setScaleX(1.5);
					deleteButton.setScaleY(1.5);
				});
				deleteButton.setOnMouseExited((MouseEvent e) -> {
					deleteButton.setScaleX(1);
					deleteButton.setScaleY(1);
				});
				deleteButton.setOnAction(event -> parent.removeApi(person.key.keyID));
			}
		});
		getColumns().add(delCol);
	}

}
