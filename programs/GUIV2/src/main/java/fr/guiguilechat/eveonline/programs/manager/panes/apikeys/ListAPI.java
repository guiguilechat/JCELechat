package fr.guiguilechat.eveonline.programs.manager.panes.apikeys;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.programs.manager.DataHandler;
import fr.guiguilechat.eveonline.programs.manager.MPane;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ListAPI extends TableView<ESIAccount> implements MPane {

	protected final DataHandler handler;

	@Override
	public DataHandler getDataHandler() {
		return handler;
	}

	public ListAPI(DataHandler handler) {
		this.handler = handler;
		setStyle("-fx-border-color: black");
		setItems(handler.apis);

		TableColumn<ESIAccount, String> keyCol = new TableColumn<>("character");
		keyCol.setCellValueFactory(apiroot -> new ReadOnlyObjectWrapper<>(apiroot.getValue().verify.characterName()));
		getColumns().add(keyCol);
		TableColumn<ESIAccount, ESIAccount> delCol = new TableColumn<>("");
		delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		delCol.setCellFactory(param -> new TableCell<ESIAccount, ESIAccount>() {
			private final Button deleteButton = new Button("X");

			@Override
			protected void updateItem(ESIAccount person, boolean empty) {
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
				deleteButton.setOnAction(event -> getItems().remove(getItem()));
			}
		});
		getColumns().add(delCol);
	}

}
