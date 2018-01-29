package fr.guiguilechat.eveonline.programs.manager.panes.managed;

import fr.guiguilechat.eveonline.programs.manager.DataHandler;
import fr.guiguilechat.eveonline.programs.manager.MPane;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/** managed tab that contains ManagedPane in the tabs */
public class MTabPane extends TabPane implements MPane {

	protected final DataHandler handler;

	public MTabPane(DataHandler handler) {
		this.handler = handler;
		getSelectionModel().selectedItemProperty().addListener(this::onSelectedItem);
	}

	@Override
	public DataHandler getDataHandler() {
		return handler;
	}

	boolean shown = false;

	@Override
	public void setShown(boolean shown) {
		if (shown == this.shown) {
			return;
		}
		Tab tselected = getSelectionModel().getSelectedItem();
		if (tselected == null) {
			return;
		}
		MPane selected = (MPane) tselected.getContent();
		if (selected != null) {
			selected.setShown(shown);
		}
		this.shown = shown;
	}

	protected void onSelectedItem(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
		MPane oldSelected = oldValue == null ? null : (MPane) oldValue.getContent();
		if (oldSelected != null) {
			oldSelected.setShown(false);
		}
		if (shown) {
			MPane newSelected = newValue == null ? null : (MPane) newValue.getContent();
			if (newSelected != null) {
				newSelected.setShown(true);
			}
		}
	}

}
