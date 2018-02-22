package fr.guiguilechat.eveonline.programs.manager.panes.status;

import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.sde.items.MetaInf;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.ScrollAdd;
import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import fr.guiguilechat.eveonline.programs.manager.panes.industry.invention.InventionGainAlgorithm;
import javafx.animation.PauseTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.MapChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

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

		parent.settings.shopList().addListener((MapChangeListener<String, Integer>) change -> {
			String key = change.getKey();
			if (change.wasRemoved()) {
				for (Iterator<Entry<String, Integer>> it = getItems().iterator(); it.hasNext();) {
					Entry<String, Integer> e = it.next();
					if (e.getKey().equals(key)) {
						it.remove();
					}
				}
			}
			if (change.wasAdded()) {
				for (Entry<String, Integer> e : parent.settings.shopList().entrySet()) {
					if (e.getKey().equals(key)) {
						getItems().add(e);
					}
				}
			}
		});
		getItems().addAll(parent.settings.shopList().entrySet());

		TableColumn<Entry<String, Integer>, String> nameCol = new TableColumn<>("name");
		nameCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().getKey()));
		getColumns().add(nameCol);

		TableColumn<Entry<String, Integer>, TextField> nbCol = new TableColumn<>("quantity");
		nbCol.setCellValueFactory(ed -> {

			TypedField<Integer> nbcycles = TypedField.positivIntField(ed.getValue().getValue());
			nbcycles.setOnScroll(new ScrollAdd.IntScrollAdd(1, nbcycles));
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			nbcycles.textProperty().addListener((observable, oldValue, newValue) -> {
				pause.setOnFinished(event -> parent.setShop(ed.getValue().getKey(), nbcycles.getValue()));
				pause.playFromStart();
			});
			return new ReadOnlyObjectWrapper<>(nbcycles);
		});
		getColumns().add(nbCol);

		TableColumn<Entry<String, Integer>, Button> openMarketCol = new TableColumn<>("");
		openMarketCol.setCellValueFactory(ed -> {
			Button button = new Button("open market");
			button.setOnAction(action -> {
				if (!parent.settings.shopper().isEmpty()) {
					ESIAccount shopper = parent.ssoChar2Con.get(parent.settings.shopper().iterator().next());
					shopper.raw.post_ui_openwindow_marketdetails(MetaInf.getItem(ed.getValue().getKey()).id, null);
				}
			});
			return new ReadOnlyObjectWrapper<>(button);
		});
		getColumns().add(openMarketCol);

		TableColumn<Entry<String, Integer>, Double> priceCol = new TableColumn<>("price");
		priceCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(
				ESIAccount.DISCONNECTED.markets.getAverage(MetaInf.getItem(ed.getValue().getKey()).id)
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

	protected void delShop(String itemName) {
		parent.delShop(itemName);
	}

}
