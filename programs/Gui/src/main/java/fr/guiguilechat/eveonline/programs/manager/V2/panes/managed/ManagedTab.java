package fr.guiguilechat.eveonline.programs.manager.V2.panes.managed;

import fr.guiguilechat.eveonline.programs.manager.V2.DataHandler;
import fr.guiguilechat.eveonline.programs.manager.V2.panes.ManagedPane;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ManagedTab extends TabPane implements ManagedPane {

	protected final DataHandler handler;

	public ManagedTab(DataHandler handler) {
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
		ManagedPane selected = (ManagedPane) tselected.getContent();
		if (selected != null) {
			selected.setShown(shown);
		}
		this.shown = shown;

	}

	protected void onSelectedItem(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
		ManagedPane oldSelected = oldValue == null ? null : (ManagedPane) oldValue.getContent();
		if (oldSelected != null) {
			oldSelected.setShown(false);
		}
		if (shown) {
			ManagedPane newSelected = newValue == null ? null : (ManagedPane) newValue.getContent();
			if (newSelected != null) {
				newSelected.setShown(true);
			}
		}
	}

}
