package fr.guiguilechat.eveonline.programs.manager.panes.managed;

import fr.guiguilechat.eveonline.programs.manager.DataHandler;
import fr.guiguilechat.eveonline.programs.manager.MPane;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class MAccordion extends Accordion implements MPane {

	protected final DataHandler handler;

	public MAccordion(DataHandler handler) {
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
		MPane selected = (MPane) tselected.getContent();
		if (selected != null) {
			selected.setShown(shown);
		}
		this.shown = shown;
	}

	protected void onSelectedItem(ObservableValue<? extends TitledPane> observable, TitledPane oldValue,
			TitledPane newValue) {
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
