package fr.guiguilechat.eveonline.programs.gui.panes;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fr.guiguilechat.eveonline.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.database.apiv2.Account.Character;
import fr.guiguilechat.eveonline.database.apiv2.Char;
import fr.guiguilechat.eveonline.database.apiv2.Char.JobEntry;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * an overview pane contains a table of eventdata.
 */
public class OverViewPane extends VBox implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected SelectTeamPane selectTeamPane;
	protected CheckBox showJobBox;
	protected CheckBox showProvisionsBox;

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	public static class EventData {
		public Date time;
		public String type;
		public String description;
		public String where;
		public String who;

		@Override
		public int hashCode() {
			return +type.hashCode() + description.hashCode() + where.hashCode()
			+ who.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != EventData.class) {
				return false;
			}
			EventData o = (EventData) obj;
			return type.equals(o.type) && description.equals(o.description) && where.equals(o.where)
					&& who.equals(o.who);
		}

		public boolean isIndustryJob() {
			return Char.activityNamesSet.contains(type);
		}

		@Override
		public String toString() {
			return "" + type + ":" + description;
		}
	}

	TableView<EventData> tvEvents = new TableView<>();

	protected boolean showJobs = false;

	protected boolean showProvisions = false;

	public OverViewPane(Manager parent) {
		this.parent = parent;
		HBox menubox = new HBox(10.0);
		selectTeamPane = new SelectTeamPane(parent);
		showJobBox = new CheckBox("show jobs");
		showJobBox.setAllowIndeterminate(false);
		showJobBox.setOnAction(e -> changeShowBox());
		showProvisionsBox = new CheckBox("show provisions");
		showProvisionsBox.setAllowIndeterminate(false);
		showProvisionsBox.setOnAction(e -> changeShowProvision());
		menubox.getChildren().addAll(selectTeamPane, new Label(" "), showJobBox, new Label(" "), showProvisionsBox);
		children = new EvePane[] { selectTeamPane };
		getChildren().addAll(menubox, tvEvents);

		TableColumn<EventData, Date> dateCol = new TableColumn<>("time");
		dateCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().time));
		dateCol.setSortable(true);
		dateCol.setSortType(SortType.ASCENDING);
		tvEvents.getColumns().add(dateCol);

		TableColumn<EventData, String> typeCol = new TableColumn<>("type");
		typeCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().type));
		tvEvents.getColumns().add(typeCol);
		TableColumn<EventData, String> desCol = new TableColumn<>("description");
		desCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().description));
		desCol.setMinWidth(400);
		tvEvents.getColumns().add(desCol);

		TableColumn<EventData, String> whereCol = new TableColumn<>("where");
		whereCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().where));
		tvEvents.getColumns().add(whereCol);

		TableColumn<EventData, String> whoCol = new TableColumn<>("who");
		whoCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().who));
		tvEvents.getColumns().add(whoCol);

		tvEvents.getSortOrder().add(dateCol);

		tvEvents.setRowFactory(tv -> new TableRow<EventData>() {
			@Override
			public void updateItem(EventData item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null && item.time != null && item.time.getTime() <= System.currentTimeMillis()) {
					setStyle("-fx-background-color: palegreen;");
				} else {
					setStyle("");
				}
			}
		});
	}

	@Override
	public void onNewAPI(int key, String code) {
		APIRoot api = parent.getAPI(key);
		if (api == null) {
			debug("can't use apiroot for id " + key);
			return;
		}
		for (Character c : api.account.characters()) {
			delCharInfo(c);
			if (parent.getTeamCharacters().contains(c.name)) {
				addCharInfo(c, api);
			}
		}
		tvEvents.sort();
	}

	@Override
	public void onDelAPI(int key) {
		APIRoot api = parent.getAPI(key);
		if (api == null) {
			debug("can't del apiroot for id " + key);
			return;
		}
		for (Character c : api.account.characters()) {
			delCharInfo(c);
		}
	}

	protected void delCharInfo(Character c) {
		tvEvents.getItems().removeIf(ev -> ev.who.equals(c.name));
	}

	/**
	 * called for new Character
	 *
	 * @param c
	 * @param api
	 */
	protected void addCharInfo(Character c, APIRoot api) {
		if (showJobs) {
			addCharJobs(c, api);
		}
	}

	protected void addCharJobs(Character c, APIRoot api) {
		for (JobEntry e : api.chars.industryJobs(c.characterID)) {
			EventData ed = new EventData();
			ed.time = e.endDate;
			ed.type = Char.activityName(e.activityID);
			ed.description = e.blueprintTypeName;
			ed.where = e.solarSystemName;
			ed.who = c.name;
			tvEvents.getItems().add(ed);
		}
	}

	protected static class ProvisionPreparation {
		public String name;
		public int required;
		public EventData ed;
		public boolean added = false;
	}

	HashMap<Integer, ProvisionPreparation> itemsProvisions = new HashMap<>();

	/** get a provision preparation for given item id */
	public ProvisionPreparation getProvision(int itemID) {
		ProvisionPreparation ret = itemsProvisions.get(itemID);
		if (ret == null) {
			ret = new ProvisionPreparation();
			ret.name = db().getElementById(itemID);
			ret.ed = new EventData();
			ret.ed.type = "provision";
			itemsProvisions.put(itemID, ret);
		}
		return ret;
	}

	protected void loadProvisionsFromItems() {
		Map<Integer, Long> items = parent.getFocusedTeamItems();
		for (Entry<Integer, Integer> e : parent.getProvision().total.entrySet()) {
			ProvisionPreparation pr = getProvision(e.getKey());
			pr.required = e.getValue();
			pr.ed.who = parent().settings.focusedTeam;
			updateItemQuantity(e.getKey(), items.getOrDefault(e.getKey(), 0l), pr);
		}
		parent.updateTeamItems();
	}

	@Override
	public void onNewProvision(int itemID, int qtty) {
		ProvisionPreparation pr = getProvision(itemID);
		pr.required = qtty;
		pr.ed.who = parent().settings.focusedTeam;
		updateItemQuantity(itemID, parent().getFocusedTeamItems().getOrDefault(itemID, 0l), pr);
	}

	@Override
	public void onFocusedTeamNewItems(HashMap<Integer, Long> itemsDiff) {
		Map<Integer, Long> items = parent().getFocusedTeamItems();
		Set<Integer> provisionned = parent.getProvision().total.keySet();
		for (Integer itemID : itemsDiff.keySet()) {
			if (provisionned.contains(itemID)) {
				updateItemQuantity(itemID, items.getOrDefault(itemID, 0l), getProvision(itemID));
			}
		}
		// System.err.println("remaining " + tvEvents.getItems());
	}

	protected void updateItemQuantity(int itemID, long qtty, ProvisionPreparation pp) {
		// System.err.println("" + pp.name + " required:" + pp.required + " qtty:" +
		// qtty);
		if (qtty < pp.required) {
			pp.ed.description = "" + (pp.required - qtty) + " " + pp.name;
			if (!pp.added) {
				tvEvents.getItems().add(pp.ed);
				// System.err.println(" ading " + pp.name);
			}
			pp.added = true;
		} else {
			if (pp.added) {
				// System.err.println(" removing " + pp.name);
				tvEvents.getItems().remove(pp.ed);
			}
			pp.added = false;
		}

	}

	@Override
	public void onFocusedTeam(String teamName) {
		tvEvents.getItems().clear();
		itemsProvisions.values().forEach(pp -> pp.added = false);
		LinkedHashSet<String> allowedCharacters = parent.getTeamCharacters();
		for (APIRoot api : parent.apis) {
			for (Character c : api.account.characters()) {
				if (allowedCharacters.contains(c.name)) {
					addCharInfo(c, api);
				}
			}
		}
		if (showProvisions) {
			loadProvisionsFromItems();
		}
		tvEvents.sort();
	}

	protected void changeShowBox() {
		if (showJobBox.isSelected()) {
			showJobs = true;
			for (APIRoot api : parent().apis) {
				for (Character c : api.account.characters()) {
					if (parent.getTeamCharacters().contains(c.name)) {
						addCharJobs(c, api);
					}
				}
			}
			tvEvents.sort();
		} else {
			showJobs = false;
			tvEvents.getItems().removeIf(EventData::isIndustryJob);
		}
	}

	protected void changeShowProvision() {
		if (showProvisionsBox.isSelected()) {
			showProvisions = true;
			loadProvisionsFromItems();
		} else {
			showProvisions = false;
			tvEvents.getItems().removeIf(ed -> ed.type.equals("provision"));
		}
	}

}
