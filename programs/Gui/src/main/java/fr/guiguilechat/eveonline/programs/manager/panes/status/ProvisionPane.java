package fr.guiguilechat.eveonline.programs.manager.panes.status;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.MetaInf;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.ProvisionType;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.status.ProvisionPane.ProvisionData;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class ProvisionPane extends TableView<ProvisionData> implements EvePane {

	@SuppressWarnings("unused")
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProvisionPane.class);

	public static class ProvisionData {
		public Item item;
		public long required;
		public long done;
		public ProvisionType type;
		public String team;
		boolean added = false;

		@Override
		public int hashCode() {
			return (int) (item.hashCode() + required + done + team.hashCode());
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
			return item.equals(o.item) && team.equals(o.team)
					&& required == o.required
					&& done == o.done;
		}

		@Override
		public String toString() {
			return item.name;
		}
	}

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ProvisionPane(Manager parent) {
		this.parent = parent;

		TableColumn<ProvisionData, ProvisionType> typeCol = new TableColumn<>("type");
		typeCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().type));
		getColumns().add(typeCol);

		TableColumn<ProvisionData, String> desCol = new TableColumn<>("item");
		desCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().item.name));
		desCol.setMinWidth(400);
		getColumns().add(desCol);

		TableColumn<ProvisionData, String> teamCol = new TableColumn<>("team");
		teamCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().team));
		getColumns().add(teamCol);

		TableColumn<ProvisionData, Long> missingCol = new TableColumn<>("missing");
		missingCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().required - ed.getValue().done));
		getColumns().add(missingCol);

		TableColumn<ProvisionData, Long> ownedcol = new TableColumn<>("required");
		ownedcol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().required));
		getColumns().add(ownedcol);

		TableColumn<ProvisionData, Integer> percentCol = new TableColumn<>("percent");
		percentCol.setCellValueFactory(
				ed -> new ReadOnlyObjectWrapper<>((int) (100 * ed.getValue().done / ed.getValue().required)));
		getColumns().add(percentCol);

		setRowFactory(tv -> new TableRow<ProvisionData>() {
			@Override
			public void updateItem(ProvisionData item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null && item.required <= item.done) {
					setStyle("-fx-background-color: palegreen;");
				} else {
					setStyle("");
				}
			}
		});

		getSortOrder().add(percentCol);
	}

	//
	// provision preparation.
	//

	private class TeamProvisions {
		public HashMap<Integer, ProvisionData> itemsProvisionsMaterial = new HashMap<>();
		public HashMap<Integer, ProvisionData> itemsProvisionsProduct = new HashMap<>();
		public HashMap<Integer, ProvisionData> itemsProvisionsSO = new HashMap<>();

		public final String team;

		public TeamProvisions(String team) {
			this.team = team;
		}

		/** get a provision preparation of given type for given item id */
		public ProvisionData getProvision(int itemID, ProvisionType ptype) {

			HashMap<Integer, ProvisionData> map = null;
			switch (ptype) {
			case MATERIAL:
				map = itemsProvisionsMaterial;
				break;
			case PRODUCT:
				map = itemsProvisionsProduct;
				break;
			case SO:
				map = itemsProvisionsSO;
				break;
			default:
				throw new UnsupportedOperationException("handle " + ptype);
			}
			ProvisionData ret = map.get(itemID);
			if (ret == null) {
				ret = new ProvisionData();
				ret.item = MetaInf.getItem(itemID);
				if (ret.item != null) {
					map.put(itemID, ret);
				} else {
					ret = null;
				}
				ret.team=team;
				ret.type = ptype;
			}
			return ret;
		}

	}

	protected Map<String, TeamProvisions> teamprovisions = new HashMap<>();

	/**
	 * prepare provisions for focused team.
	 */
	protected void prepareProvisions() {
		for (TeamProvisions tp : teamprovisions.values()) {
			tp.itemsProvisionsMaterial.values().stream().forEach(pp -> pp.required = 0);
			tp.itemsProvisionsProduct.values().stream().forEach(pp -> pp.required = 0);
			tp.itemsProvisionsSO.values().stream().forEach(pp -> pp.required = 0);
		}
		getItems().clear();
		for (String team : parent().settings.teams.keySet()) {
			TeamProvisions tp = teamprovisions.get(team);
			if (tp == null) {
				tp = new TeamProvisions(team);
				teamprovisions.put(team, tp);
			}
			for (ProvisionType ptype : ProvisionType.values()) {
				for (Entry<Integer, Integer> e : parent.getTeamProvision(team, ptype).total.entrySet()) {
					ProvisionData pr = tp.getProvision(e.getKey(), ptype);
					pr.required += e.getValue();
					pr.type = ptype;
					getItems().add(pr);
				}
			}
		}
		updateItems();
	}

	public void updateItems() {
		for (String team : parent().settings.teams.keySet()) {
			TeamProvisions tp = teamprovisions.get(team);
			Map<Integer, Long> assets = parent().getTeamAssets(team);
			Map<Integer, Long> bos = parent().getTeamBOs(team);
			Map<Integer, Long> sos = parent().getTeamSOs(team);
			Map<Integer, Long> materials = Stream.concat(assets.entrySet().stream(), bos.entrySet().stream())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
			for (Entry<Integer, Long> e : materials.entrySet()) {
				updateItemQuantity(e.getValue(), tp.getProvision(e.getKey(), ProvisionType.MATERIAL));
			}
			for (Entry<Integer, Long> e : assets.entrySet()) {
				updateItemQuantity(e.getValue(), tp.getProvision(e.getKey(), ProvisionType.PRODUCT));
			}
			for (Entry<Integer, Long> e : sos.entrySet()) {
				updateItemQuantity(e.getValue(), tp.getProvision(e.getKey(), ProvisionType.SO));
			}
		}
		sort();
	}

	/** update graphics on the modification of provisioned item's quantity */
	protected void updateItemQuantity(long qtty, ProvisionData pp) {
		pp.done = qtty;
		getItems().remove(pp);
		if (pp.required > 0) {
			getItems().add(pp);
		}
	}

	boolean shown = false;

	@Override
	public void onIsShown(boolean shown) {
		this.shown = shown;
		if (shown) {
			prepareProvisions();
		}
	}

}
