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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import fr.guiguilechat.eveonline.programs.gui.panes.options.OptionPane;
import fr.guiguilechat.eveonline.programs.gui.panes.overview.OverViewPane;
import fr.guiguilechat.eveonline.programs.gui.panes.provision.ProvisionPane;
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

	private static final Logger logger = LoggerFactory.getLogger(Manager.class);

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

	@Override
	public void onAdd2Team(String team, String character) {
		cachedTeamsPossibleLocations.remove(team);
	}

	@Override
	public void onDel2Team(String team, String character) {
		cachedTeamsPossibleLocations.remove(team);
	}

	@Override
	public void onDelTeam(String name) {
		cachedTeamsPossibleLocations.remove(name);
	}

	@Override
	public void onTeamAddLoc(String team, long locID) {
		if (team == null) {
			return;
		}
		// for each character of the team, we get the asets of this characxter on
		// the given location and we add them to the team assets.
		HashMap<Integer, Long> itemsGains = new HashMap<>();
		for (String charName : settings.teams.get(team)) {
			Map<Long, Map<Integer, Long>> charItems = itemsByCharName.get(charName);
			if (charItems != null) {
				Map<Integer, Long> locItems = charItems.get(locID);
				for (Entry<Integer, Long> e : locItems.entrySet()) {
					itemsGains.put(e.getKey(), itemsGains.getOrDefault(e.getKey(), 0l) + e.getValue());
				}
			}
		}
		Map<Integer, Long> storedItemsValues = itemsByTeamName.get(team);
		if (storedItemsValues == null) {
			storedItemsValues = itemsGains;
			itemsByTeamName.put(team, storedItemsValues);
		} else {
			for (Entry<Integer, Long> e : itemsGains.entrySet()) {
				long newval = e.getValue() + storedItemsValues.getOrDefault(e.getKey(), 0l);
				if (newval != 0) {
					storedItemsValues.put(e.getKey(), newval);
				} else {
					storedItemsValues.remove(e.getKey());
				}
			}
		}
		if (team.equals(settings.focusedTeam)) {
			propagateFocusedTeamNewItems(itemsGains);
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

	public boolean renameTeam(String old, String now) {
		if (old != null && settings.teams.keySet().contains(old) && now != null && now.length() > 0
				&& !settings.teams.keySet().contains(now)) {
			settings.teams.put(now, settings.teams.get(old));
			settings.teams.remove(old);
			settings.provisions.put(now, settings.provisions.get(old));
			settings.provisions.remove(old);
			if (old.equals(settings.focusedTeam)) {
				settings.focusedTeam = now;
			}
			settings.store();
			propagateRenameTeam(old, now);
			return true;
		} else {
			return false;
		}
	}

	public boolean copyTeam(String from, String newname) {

		if (from != null && settings.teams.keySet().contains(from) && newname != null && newname.length() > 0
				&& !settings.teams.keySet().contains(newname)) {
			settings.teams.put(newname, settings.teams.get(from));
			settings.provisions.put(newname, settings.provisions.get(from));
			settings.store();
			propagateNewTeam(newname);
			return true;
		} else {
			return false;
		}
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

	public Set<String> getFTeamCharacters() {
		if (settings.focusedTeam != null) {
			return settings.teams.get(settings.focusedTeam);
		}
		return Collections.emptySet();
	}

	protected Map<String, Set<Long>> cachedTeamsPossibleLocations = new HashMap<>();

	/**
	 * find all the possible location ID for the given team.
	 * @return
	 */
	public Set<Long> getFTeamPossibleLocations(){
		if (settings.focusedTeam == null) {
			return Collections.emptySet();
		}
		Set<Long> ret = cachedTeamsPossibleLocations.get(settings.focusedTeam);
		if (ret == null) {
			Set<String> allowedChars = getFTeamCharacters();
			Stream<Character> chars = apis.stream().flatMap(a->a.account.characters().stream()).filter(c->allowedChars.contains(c.name));
			ret = chars
					.flatMap(c -> Stream.concat(c.marketOrders().stream().map(oe -> oe.stationID), c.assetList().keySet().stream()))
					.collect(Collectors.toSet());
			cachedTeamsPossibleLocations.put(settings.focusedTeam, ret);
		}
		return ret;
	}

	/**
	 * get the set of stations id the focused team allows
	 *
	 * @return
	 */
	public Set<Long> getFTeamLocations() {
		if (settings.focusedTeam != null) {
			return settings.teamsLocations.get(settings.focusedTeam);
		}
		return Collections.emptySet();
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

	protected Map<String, Map<Long, Map<Integer, Long>>> itemsByCharName = Collections.synchronizedMap(new HashMap<>());

	protected Map<String, Map<Integer, Long>> itemsByTeamName = Collections.synchronizedMap(new HashMap<>());

	// getting the items

	/**
	 * update the focused team's items. this is done on a character basis
	 */
	public void updateFTeamItems() {
		Set<String> allowedNames = getFTeamCharacters();
		Set<Long> allowedLocations = getFTeamLocations();
		Stream<Character> teamCharacters = apis.parallelStream().flatMap(api -> api.account.characters().parallelStream())
				.filter(c -> allowedNames.contains(c.name));
		Map<Integer, Long> totalGain = teamCharacters.map(this::computeItemsDiff).flatMap(m -> m.entrySet().stream())
				.filter(e -> allowedLocations == null || allowedLocations.contains(e.getKey())).map(Map.Entry::getValue)
				.filter(p -> p != null && !p.isEmpty())
				.flatMap(m -> m.entrySet().stream())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
		if (!totalGain.isEmpty()) {
			propagateFocusedTeamNewItems(totalGain);
		}
	}

	public Map<Integer, Long> getFocusedTeamItems() {
		if (settings.focusedTeam == null) {
			return Collections.emptyMap();
		}
		Map<Integer, Long> ret = itemsByTeamName.get(settings.focusedTeam);
		if (ret == null) {
			ret = new HashMap<>();
			itemsByTeamName.put(settings.focusedTeam, ret);
		}
		return ret;
	}

	@Override
	public void onFocusedTeamNewItems(Map<Integer, Long> itemsDiff) {
		Map<Integer, Long> m = getFocusedTeamItems();
		for (Entry<Integer, Long> e : itemsDiff.entrySet()) {
			m.put(e.getKey(), e.getValue() + m.getOrDefault(e.getKey(), 0l));
		}
	}

	protected int assetListCacheDelayMinutes = 30;
	protected Map<Long, Date> assetListCacheByCharID = Collections.synchronizedMap(new HashMap<>());

	/**
	 * compute the difference in items assets and buy orders between last call and
	 * now. if we already called for those assets
	 * {@value #assetListCacheDelayMinutes} or less before, we don't request
	 * again.
	 *
	 * <p>
	 * This should be thread-safe. Only the cache is manipulated, which is in a
	 * synchronize map.
	 * </p>
	 *
	 * @param c
	 *          the character to get the items for
	 * @return a new Hashmap, locationID->itemid->difference in number.
	 */
	protected Map<Long, Map<Integer, Long>> computeItemsDiff(Character c) {
		Date now = new Date();
		Date cacheExpiration = assetListCacheByCharID.get(c.characterID);
		if (cacheExpiration != null && cacheExpiration.after(now)) {
			return Collections.emptyMap();
		}
		logger.debug("invalid cache assets " + c.name);
		assetListCacheByCharID.put(c.characterID, new Date(now.getTime() + 20 * 60000));
		// for each
		HashMap<Long, Map<Integer, Long>> itemsDiff = new HashMap<>();
		for (Entry<Long, ArrayList<Content>> e : c.assetList().entrySet()) {
			Map<Integer, Long> localGains = new HashMap<>();
			itemsDiff.put(e.getKey(), localGains);
			for (Content co : e.getValue()) {
				localGains.put(co.typeID, co.quantity);
			}
		}
		for (OrderEntry a : c.marketOrders()) {
			if (a.isOpen() && a.isBuyOrder()) {
				Map<Integer, Long> localGains = itemsDiff.get(a.stationID);
				if (localGains == null) {
					localGains = new HashMap<>();
					itemsDiff.put(a.stationID, localGains);
				}
				localGains.put(a.typeID, a.volRemaining + localGains.getOrDefault(a.typeID, 0l));
			}
		}

		Map<Long, Map<Integer, Long>> rawItems = itemsByCharName.get(c.name);
		if (rawItems == null) {
			// no items stored yet
			rawItems = itemsDiff;
			itemsByCharName.put(c.name, rawItems);
		} else {
			for (long locID : Stream.concat(rawItems.keySet().stream(), itemsDiff.keySet().stream()).mapToLong(l -> l)
					.toArray()) {
				Map<Integer, Long> locDiff = itemsDiff.get(locID);
				if (locDiff == null) {
					locDiff = new HashMap<>();
					itemsDiff.put(locID, locDiff);
				}
				Map<Integer, Long> locRaw = rawItems.get(locID);
				if (locRaw == null) {
					locRaw = new HashMap<>();
					rawItems.put(locID, locRaw);
				}
				for (int itemID : Stream.concat(locDiff.keySet().stream(), locRaw.keySet().stream()).mapToInt(i -> i)
						.distinct().toArray()) {
					long newVal = locDiff.getOrDefault(itemID, 0l);
					long diff = newVal - locRaw.getOrDefault(itemID, 0l);
					if (newVal != 0) {
						locRaw.put(itemID, newVal);
					} else {
						locRaw.remove(itemID);
					}
					if (diff != 0) {
						locDiff.put(itemID, diff);
					} else {
						locDiff.remove(itemID);
					}
				}
				if (locDiff.isEmpty()) {
					itemsDiff.remove(locID);
				}
				if (locRaw.isEmpty()) {
					rawItems.remove(locID);
				}
			}
		}

		return itemsDiff;
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
