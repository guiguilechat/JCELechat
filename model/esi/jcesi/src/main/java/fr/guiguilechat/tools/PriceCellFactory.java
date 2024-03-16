package fr.guiguilechat.tools;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class PriceCellFactory<T, W extends Number> extends TableCell<T, W> {

	@Override
	public void updateItem(W value, boolean empty) {
		super.updateItem(value, empty);
		if (empty || value == null) {
			setText(null);
		} else {
			setText(FormatTools.formatPrice(value.doubleValue()));
		}
	}

	public static <U, V extends Number> TableCell<U, V> create(TableColumn<U, V> col) {
		return new PriceCellFactory<>();
	}
}