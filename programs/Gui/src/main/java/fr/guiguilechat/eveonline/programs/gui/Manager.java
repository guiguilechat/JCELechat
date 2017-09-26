package fr.guiguilechat.eveonline.programs.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import fr.guiguilechat.eveonline.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.database.apiv2.Account.Character;
import fr.guiguilechat.eveonline.database.apiv2.Char.Content;
import fr.guiguilechat.eveonline.database.apiv2.Char.OrderEntry;
import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.database.yaml.LPOffer.ItemRef;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.gui.Settings.Provision;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.MenuPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OptionPane;
import fr.guiguilechat.eveonline.programs.gui.panes.OverViewPane;
import fr.guiguilechat.eveonline.programs.gui.panes.ProvisionPane;
import fr.guiguilechat.eveonline.programs.settings.ISettings;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Manager extends Application implements EvePane {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public Manager parent() {
		return this;
	}

	public Settings settings = ISettings.load(Settings.class);

	public final ObservableList<APIRoot> apis = FXCollections.observableArrayList();

	public BorderPane mainLayout = new BorderPane();

	public MenuPane menuHBox = new MenuPane(this);

	public OverViewPane overviewPane = new OverViewPane(this);

	public ProvisionPane provisionpane = new ProvisionPane(this);

	public OptionPane optionPane = new OptionPane(this);

	public EvePane[] children = new EvePane[] { menuHBox, overviewPane, provisionpane, optionPane };

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	public void showOptions() {
		mainLayout.setCenter(optionPane);
	}

	public void showProvision() {
		mainLayout.setCenter(provisionpane);
	}

	public void showOverview() {
		mainLayout.setCenter(overviewPane);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("guigui lechat manager");
		mainLayout.setTop(menuHBox);
		if (!settings.hideDebug) {
			mainLayout.setBottom(debugPane);
		}

		Scene scene = new Scene(mainLayout, 800, 900);
		primaryStage.setScene(scene);
		primaryStage.show();
		showOverview();
		for (Entry<Integer, String> a : settings.apiKeys.entrySet()) {
			propagateNewAPI(a.getKey(), a.getValue());
		}
		for (String team : settings.teams.keySet()) {
			propagateNewTeam(team);
			for (String charname : settings.teams.get(team)) {
				propagateAdd2Team(team, charname);
			}
		}
		propagateFocusedTeam(settings.focusedTeam);
	}

	//
	// event handling
	//

	@Override
	public void onNewAPI(int key, String code) {
		for (APIRoot ar : apis) {
			if (ar.key.keyID == key) {
				return;
			}
		}
		apis.add(new APIRoot(key, code));
		debug("new api " + key);
	}

	@Override
	public void onDelAPI(int key) {
		for (Iterator<APIRoot> it = apis.iterator(); it.hasNext();) {
			if (it.next().key.keyID == key) {
				it.remove();
			}
		}
	}

	// external calls
	// modification of the settings
	//

	// API

	public void removeApi(int keyID) {
		settings.apiKeys.remove(keyID);
		settings.store();
		propagateDelAPI(keyID);
	}

	public void addAPI(int key, String code) {
		settings.apiKeys.put(key, code);
		settings.store();
		propagateNewAPI(key, code);
	}

	public APIRoot getAPI(int key) {
		for (APIRoot a : apis) {
			if (a.key.keyID == key) {
				return a;
			}
		}
		return null;
	}

	// team

	public void addTeam(String name) {
		settings.teams.put(name, new LinkedHashSet<>());
		settings.store();
		propagateNewTeam(name);
	}

	public void delTeam(String name) {
		settings.teams.remove(name);
		settings.store();
		propagateDelTeam(name);

	}

	public void add2Team(String character, String team) {
		settings.teams.get(team).add(character);
		settings.store();
		propagateAdd2Team(team, character);
	}

	public void del2Team(String character, String team) {
		settings.teams.get(team).remove(character);
		settings.store();
		propagateDel2Team(team, character);
	}

	public void setFocusedTeam(String name) {
		debug("focusing on team " + name);
		settings.focusedTeam = name;
		settings.store();
		propagateFocusedTeam(name);
	}

	public LinkedHashSet<String> getTeamCharacters() {
		if (settings.focusedTeam != null) {
			return settings.teams.get(settings.focusedTeam);
		}
		LinkedHashSet<String> ret = new LinkedHashSet<>();
		for (APIRoot a : apis) {
			for (Character c : a.account.characters()) {
				ret.add(c.name);
			}
		}
		return ret;
	}

	// provision

	/** get the provision of the focused team. */
	public Provision getProvision() {
		Provision ret = settings.provisions.get(settings.focusedTeam);
		if (ret == null) {
			ret = new Provision();
			settings.provisions.put(settings.focusedTeam, ret);
		}
		return ret;
	}

	public void provision(HashMap<Integer, Integer> items) {
		debug("provision " + items);
		for (Entry<Integer, Integer> e : items.entrySet()) {
			propagateNewProvision(e.getKey(), e.getValue());
			getProvision().total.put(e.getKey(),
					Math.max(0, getProvision().total.getOrDefault(e.getKey(), 0) + e.getValue()));
		}
		settings.store();
	}

	/** set the requirement in lp offer to given value for the focused team */
	public void provisionLPOffer(LPOffer offer, int requirement) {
		Provision p = getProvision();
		int diff = requirement - p.lpoffers.getOrDefault(offer.id, 0);
		if (requirement <= 0) {
			p.lpoffers.remove(offer.id);
		} else {
			p.lpoffers.put(offer.id, requirement);
		}
		for (ItemRef e : offer.requirements.items) {
			int newQtty = p.total.getOrDefault(e.type_id, 0) + e.quantity * diff;
			propagateNewProvision(e.type_id, newQtty);
			if (newQtty > 0) {
				p.total.put(e.type_id, newQtty);
			} else {
				p.total.remove(e.type_id);
			}
		}
		settings.store();
	}

	// items

	protected HashMap<String, HashMap<Integer, Long>> itemsByCharName = new HashMap<>();

	protected HashMap<String, HashMap<Integer, Long>> itemsByTeamName = new HashMap<>();

	// getting the items

	/**
	 * update the focused team's items. this is done on a character basis
	 */
	public void updateTeamItems() {
		HashMap<Integer, Long> totalGain = new HashMap<>();
		for (APIRoot api : apis) {
			for (Character c : api.account.characters()) {
				if (getTeamCharacters().contains(c.name)) {
					HashMap<Integer, Long> gain = computeItemsDiff(api, c);
					for (Entry<Integer, Long> e : gain.entrySet()) {
						totalGain.put(e.getKey(), e.getValue() + totalGain.getOrDefault(e.getKey(), 0l));
					}
					for (OrderEntry a : api.chars.marketOrders(c.characterID)) {
						if (a.isBuyOrder() && a.isOpen()) {
							totalGain.put(a.typeID, a.volRemaining + totalGain.getOrDefault(a.typeID, 0l));
						}
					}
				}
			}
		}
		if (!totalGain.isEmpty()) {
			propagateFocusedTeamNewItems(totalGain);
		}
	}

	public Map<Integer, Long> getFocusedTeamItems() {
		if (settings.focusedTeam == null) {
			return Collections.emptyMap();
		}
		HashMap<Integer, Long> ret = itemsByTeamName.get(settings.focusedTeam);
		if (ret == null) {
			ret = new HashMap<>();
			itemsByTeamName.put(settings.focusedTeam, ret);
		}
		return ret;
	}

	@Override
	public void onFocusedTeamNewItems(HashMap<Integer, Long> itemsDiff) {
		Map<Integer, Long> m = getFocusedTeamItems();
		for (Entry<Integer, Long> e : itemsDiff.entrySet()) {
			m.put(e.getKey(), e.getValue() + m.getOrDefault(e.getKey(), 0l));
		}
	}

	/**
	 * compute the difference in items between last call and now.
	 *
	 * @param api
	 *          the API to use
	 * @param c
	 *          the character to get the items for
	 * @return a new Hashmap, for each item id as key, the difference in number as
	 *         value.
	 */
	protected HashMap<Integer, Long> computeItemsDiff(APIRoot api, Character c) {
		HashMap<Integer, Long> itemsGain = new HashMap<>();
		for (ArrayList<Content> ac : api.chars.assetList(c.characterID).values()) {
			for (Content co : ac) {
				itemsGain.put(co.typeID, co.quantity + itemsGain.getOrDefault(co.typeID, 0l));
			}
		}

		HashMap<Integer, Long> oldItems = itemsByCharName.get(c.name);
		if (oldItems == null) {
			// no items stored yet
			oldItems = itemsGain;
			itemsByCharName.put(c.name, oldItems);
		} else {
			for (int itemID : Stream.concat(oldItems.keySet().stream(), itemsGain.keySet().stream()).mapToInt(i -> i)
					.distinct().toArray()) {
				long newVal = itemsGain.getOrDefault(itemID, 0l);
				long diff = newVal - oldItems.getOrDefault(itemID, 0l);
				if (newVal != 0) {
					oldItems.put(itemID, newVal);
				} else {
					oldItems.remove(itemID);
				}
				if (diff != 0) {
					itemsGain.put(itemID, diff);
				} else {
					itemsGain.remove(itemID);
				}
			}
		}

		return itemsGain;
	}

	// debug

	protected static class DebugEntry {
		String message;
		Class<? extends EvePane> context;
		Date date;
	}

	protected TableView<DebugEntry> debugPane = new TableView<>();
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		TableColumn<DebugEntry, String> dateCol = new TableColumn<>("date");
		dateCol.setCellValueFactory(ct -> new ReadOnlyObjectWrapper<>(dateFormat.format(ct.getValue().date)));
		debugPane.getColumns().add(dateCol);

		TableColumn<DebugEntry, String> ctxtCol = new TableColumn<>("context");
		ctxtCol.setCellValueFactory(ct -> new ReadOnlyObjectWrapper<>(ct.getValue().context.getSimpleName()));
		ctxtCol.setMinWidth(130);
		debugPane.getColumns().add(ctxtCol);

		TableColumn<DebugEntry, String> messCol = new TableColumn<>("message");
		messCol.setCellValueFactory(ct -> new ReadOnlyObjectWrapper<>(ct.getValue().message));
		messCol.setMinWidth(500);
		debugPane.getColumns().add(messCol);

		dateCol.setSortType(TableColumn.SortType.DESCENDING);
		dateCol.setSortable(true);
		debugPane.getSortOrder().add(dateCol);
	}

	public void printDebug(Class<? extends EvePane> clazz, String data) {
		DebugEntry de = new DebugEntry();
		de.message = data;
		de.context = clazz;
		de.date = new Date();
		debugPane.getItems().add(de);
		debugPane.sort();
	}

	public void switchDebug() {
		settings.hideDebug = !settings.hideDebug;
		mainLayout.setBottom(settings.hideDebug ? null : debugPane);
		settings.store();
	}

	// database

	protected YamlDatabase db = new YamlDatabase();

	@Override
	public YamlDatabase db() {
		return db;
	}

}
