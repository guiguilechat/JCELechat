package fr.guiguilechat.eveonline.programs.manager.V2.panes.managed;

import fr.guiguilechat.eveonline.programs.manager.V2.DataHandler;
import fr.guiguilechat.eveonline.programs.manager.V2.panes.ManagedPane;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class ManagedAccordion extends Accordion implements ManagedPane {

	protected final DataHandler handler;

	public ManagedAccordion(DataHandler handler) {
		this.handler = handler;
		expandedPaneProperty().addListener(this::onSelectedItem);
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
		TitledPane tselected = getExpandedPane();
		if (tselected == null) {
			return;
		}
		ManagedPane selected = (ManagedPane) tselected.getContent();
		if (selected != null) {
			selected.setShown(shown);
		}
		this.shown = shown;
	}

	protected void onSelectedItem(ObservableValue<? extends TitledPane> observable, TitledPane oldValue,
			TitledPane newValue) {
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
