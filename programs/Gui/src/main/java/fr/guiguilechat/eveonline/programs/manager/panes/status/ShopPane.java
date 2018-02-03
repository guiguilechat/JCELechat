package fr.guiguilechat.eveonline.programs.manager.panes.status;

import java.util.Map.Entry;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ShopPane extends GridPane implements EvePane {

	@SuppressWarnings("unused")
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ShopPane.class);

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ShopPane(Manager parent) {
		this.parent = parent;
	}

	@Override
	public void onIsShown(boolean shown) {
		if (shown) {
			updShop();
		}
	}

	protected void updShop() {
		getChildren().clear();
		int row = 0;
		for (Entry<String, Integer> e : parent.settings.shopList.entrySet()) {
			Button delBtn = new Button("del");
			delBtn.setOnAction(ev -> delShop(e.getKey()));
			addRow(row, new Label(e.getKey()), new Label("" + e.getValue()), delBtn);
			row++;
		}
	}

	protected void delShop(String itemName) {
		parent.delShop(itemName);
		updShop();
	}

}
