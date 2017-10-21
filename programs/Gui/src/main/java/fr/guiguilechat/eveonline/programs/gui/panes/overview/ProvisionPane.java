package fr.guiguilechat.eveonline.programs.gui.panes.overview;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.overview.ProvisionPane.ProvisionData;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProvisionPane extends TableView<ProvisionData> implements EvePane {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProvisionPane.class);

	public static class ProvisionData {
		public String description;
		public String who;

		@Override
		public int hashCode() {
			return description.hashCode() + who.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != ProvisionData.class) {
				return false;
			}
			ProvisionData o = (ProvisionData) obj;
			return description.equals(o.description) && who.equals(o.who);
		}

		@Override
		public String toString() {
			return description;
		}
	}

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ProvisionPane(Manager parent) {
		this.parent = parent;

		TableColumn<ProvisionData, String> desCol = new TableColumn<>("description");
		desCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().description));
		desCol.setMinWidth(400);
		getColumns().add(desCol);

		TableColumn<ProvisionData, String> whoCol = new TableColumn<>("who");
		whoCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().who));
		getColumns().add(whoCol);
	}

	//
	// provision preparation.
	//

	protected static class ProvisionPreparation {
		public String name;
		public int required;
		public ProvisionData ed;
		public boolean added = false;
		public int itemID;
	}

	HashMap<Integer, ProvisionPreparation> itemsProvisions = new HashMap<>();

	/** get a provision preparation for given item id */
	public ProvisionPreparation getProvision(int itemID) {
		ProvisionPreparation ret = itemsProvisions.get(itemID);
		if (ret == null) {
			logger.trace("creating provision for item " + itemID);
			ret = new ProvisionPreparation();
			ret.name = db().getElementById(itemID);
			ret.ed = new ProvisionData();
			ret.itemID = itemID;
			itemsProvisions.put(itemID, ret);
		}
		return ret;
	}

	/**
	 * prepare provisions for focused team.
	 */
	protected void prepareProvisions() {
		itemsProvisions.values().stream().forEach(pp -> pp.required = 0);
		for (Entry<Integer, Integer> e : parent.getFTeamProvision().total.entrySet()) {
			ProvisionPreparation pr = getProvision(e.getKey());
			pr.required = e.getValue();
			pr.ed.who = parent().settings.focusedTeam;
		}
		onTeamNewItems(parent().settings.focusedTeam, parent().getFTeamItems());
	}

	@Override
	public void onNewProvision(int itemID, int qtty) {
		if (shown) {
			ProvisionPreparation pr = getProvision(itemID);
			pr.required = qtty;
			pr.ed.who = parent().settings.focusedTeam;
			updateItemQuantity(parent().getFTeamItems().getOrDefault(itemID, 0l), pr);
		}
	}

	@Override
	public void onTeamNewItems(String team, Map<Integer, Long> itemsDiff) {
		if (!shown || team == null || !team.equals(parent.settings.focusedTeam)) {
			logger.trace("skipping items diff for team " + team);
			return;
		}
		logger.trace("new items for focused team " + team + " : " + itemsDiff);
		Map<Integer, Long> items = parent().getFTeamItems();
		for (Integer itemID : itemsDiff.keySet()) {
			updateItemQuantity(items.getOrDefault(itemID, 0l), getProvision(itemID));
		}
		sort();
	}

	/** update graphics on the modification of provisioned item's quantity */
	protected void updateItemQuantity(long qtty, ProvisionPreparation pp) {
		if (pp.required > 0) {
			logger.trace("updating items " + pp.name + " required" + pp.required + " qtty" + qtty);
		}
		if (qtty < pp.required) {
			logger.trace("adding item " + pp.name + " required " + pp.required + ", we have " + qtty);
			pp.ed.description = "" + (pp.required - qtty) + " " + pp.name;
			if (!pp.added) {
				getItems().add(pp.ed);
			}
			pp.added = true;
		} else {
			if (pp.added) {
				getItems().remove(pp.ed);
				logger.trace("removing item " + pp.name + " required " + pp.required + ", we have " + qtty);
			}
			pp.added = false;
		}

	}

	@Override
	public void onFocusedTeam(String teamName) {
		getItems().clear();
		itemsProvisions.values().forEach(pp -> pp.added = false);
		if (shown) {
			prepareProvisions();
		}
	}

	boolean shown = false;

	public void setShown(boolean shown) {
		this.shown = shown;
		if (shown) {
			prepareProvisions();
		}

	}

}
