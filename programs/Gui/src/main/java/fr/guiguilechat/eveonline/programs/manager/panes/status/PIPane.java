package fr.guiguilechat.eveonline.programs.manager.panes.status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.esi.ESIAccount;
import fr.guiguilechat.eveonline.model.sde.locations.SolarSystem;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import is.ccp.tech.esi.responses.R_get_characters_character_id_planets_planet_id_pins;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PIPane extends BorderPane implements EvePane {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PIPane.class);

	public static class PIData {
		public Date time;
		public String type;
		public String where;
		public String who;

		@Override
		public int hashCode() {
			return +type.hashCode() + +where.hashCode() + who.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != PIData.class) {
				return false;
			}
			PIData o = (PIData) obj;
			return type.equals(o.type) && where.equals(o.where) && who.equals(o.who);
		}

		@Override
		public String toString() {
			return "" + type + ":" + where;
		}
	}

	protected final Manager parent;

	protected TableView<PIData> table = new TableView<>();

	@Override
	public Manager parent() {
		return parent;
	}

	public PIPane(Manager parent) {
		this.parent = parent;
		setCenter(table);

		TableColumn<PIData, Date> dateCol = new TableColumn<>("date");
		dateCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().time));
		DateFormat df = new SimpleDateFormat("dd/MM HH:mm:ss");
		dateCol.setCellFactory(param -> new TableCell<PIData, Date>() {
			@Override
			protected void updateItem(Date item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					setText(df.format(item));
				} else {
					setText("");
				}
			}
		});
		dateCol.setMinWidth(120);
		dateCol.setSortable(true);
		dateCol.setSortType(SortType.ASCENDING);
		table.getColumns().add(dateCol);

		TableColumn<PIData, String> typeCol = new TableColumn<>("type");
		typeCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().type));
		table.getColumns().add(typeCol);

		TableColumn<PIData, String> whereCol = new TableColumn<>("where");
		whereCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().where));
		table.getColumns().add(whereCol);

		TableColumn<PIData, String> whoCol = new TableColumn<>("who");
		whoCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().who));
		table.getColumns().add(whoCol);

		table.getSortOrder().add(dateCol);

		table.setRowFactory(tv -> new TableRow<PIData>() {
			@Override
			public void updateItem(PIData item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null && item.time != null && item.time.getTime() <= System.currentTimeMillis()) {
					setStyle("-fx-background-color: palegreen;");
				} else {
					setStyle("");
				}
			}
		});
	}

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	static {
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	public void update() {
		table.getItems().clear();
		for (Entry<String, ESIAccount> acc : parent.ssoChar2Con.entrySet()) {
			if (parent.settings.planets().contains(acc.getKey())) {

				acc.getValue().pi.getPlanets().values().parallelStream().forEach(pl->{
					Date firstExtractor = null;
					for (R_get_characters_character_id_planets_planet_id_pins pin : pl.pins) {
						if (pin.expiry_time != null) {
							Date extractordate;
							try {
								synchronized (sdf) {
									extractordate = sdf.parse(pin.expiry_time);
								}
							} catch (ParseException e) {
								throw new UnsupportedOperationException("catch this", e);
							}
							if (firstExtractor == null || firstExtractor.after(extractordate)) {
								firstExtractor=extractordate;
							}
						}
					}
					if (firstExtractor != null) {
						PIData pid = new PIData();
						pid.time = firstExtractor;
						pid.type = "extractor";
						pid.where = SolarSystem.loadById().get(pl.solar_system_id);
						pid.who = acc.getKey();
						synchronized (table) {
							table.getItems().add(pid);
						}
					}
				});
			}
		}
		table.sort();
	}

	protected boolean shown = false;

	@Override
	public void onIsShown(boolean shown) {
		this.shown = shown;
		if (shown) {
			update();
		}
	}
}
