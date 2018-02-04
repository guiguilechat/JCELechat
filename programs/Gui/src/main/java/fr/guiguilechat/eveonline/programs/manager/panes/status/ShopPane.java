package fr.guiguilechat.eveonline.programs.manager.panes.status;

import java.util.Map.Entry;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.esi.ESIConnection;
import fr.guiguilechat.eveonline.model.sde.items.MetaInf;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.industry.invention.InventionGainAlgorithm;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;

public class ShopPane extends TableView<Entry<String, Integer>> implements EvePane {

	@SuppressWarnings("unused")
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ShopPane.class);

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ShopPane(Manager parent) {
		this.parent = parent;

		TableColumn<Entry<String, Integer>, String> nameCol = new TableColumn<>("name");
		nameCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().getKey()));
		getColumns().add(nameCol);

		TableColumn<Entry<String, Integer>, Integer> nbCol = new TableColumn<>("quantity");
		nbCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().getValue()));
		getColumns().add(nbCol);

		TableColumn<Entry<String, Integer>, Double> priceCol = new TableColumn<>("price");
		priceCol.setCellValueFactory(
				ed -> new ReadOnlyObjectWrapper<>(
						ESIConnection.DISCONNECTED.markets.getAverage(MetaInf.getItem(ed.getValue().getKey()).id)
						* ed.getValue().getValue()));
		priceCol.setCellFactory(e -> new TableCell<Entry<String, Integer>, Double>() {
			@Override
			public void updateItem(Double value, boolean empty) {
				super.updateItem(value, empty);
				if (empty) {
					setText(null);
				} else {
					setText(InventionGainAlgorithm.formatPrice(value));
				}
			}
		});
		priceCol.setSortType(SortType.DESCENDING);
		getColumns().add(priceCol);
		getSortOrder().add(priceCol);

		TableColumn<Entry<String, Integer>, Button> delCol = new TableColumn<>("");
		delCol.setCellValueFactory(ed -> {
			Button delBtn = new Button("remove");
			delBtn.setOnAction(ev -> delShop(ed.getValue().getKey()));
			return new ReadOnlyObjectWrapper<>(delBtn);
		});
		getColumns().add(delCol);

		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	@Override
	public void onIsShown(boolean shown) {
		if (shown) {
			updShop();
		}
	}

	protected void updShop() {
		getItems().clear();
		getItems().addAll(parent.settings.shopList.entrySet());
		sort();
	}

	protected void delShop(String itemName) {
		parent.delShop(itemName);
		updShop();
	}

}
