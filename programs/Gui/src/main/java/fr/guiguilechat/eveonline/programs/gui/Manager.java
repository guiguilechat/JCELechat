package fr.guiguilechat.eveonline.programs.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import fr.guiguilechat.eveonline.database.yaml.Station;
import fr.guiguilechat.eveonline.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.gui.Settings.TeamDescription;
import fr.guiguilechat.eveonline.programs.gui.Settings.TeamDescription.Provision;
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
			for (String charname : settings.teams.get(team).members) {
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
		cachedTeamsPossibleSystems.remove(team);
	}

	@Override
	public void onDel2Team(String team, String character) {
		cachedTeamsPossibleSystems.remove(team);
	}

	@Override
	public void onDelTeam(String name) {
		cachedTeamsPossibleSystems.remove(name);
	}

	@Override
	public void onTeamAddSystem(String team, String locID) {
		if (team == null) {
			return;
		}
		// for each character of the team, we get the asets of this characxter on
		// the given location and we add them to the team assets.
		HashMap<Integer, Long> itemsGains = new HashMap<>();
		for (String charName : settings.teams.get(team).members) {
			Map<String, Map<Integer, Long>> charItems = itemsByCharName.get(charName);
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
		settings.teams.put(name, new TeamDescription());
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
		if (from != null && settings.teams.containsKey(from) && newname != null && newname.length() > 0
				&& !settings.teams.containsKey(newname)) {
			settings.teams.put(newname, settings.teams.get(from).clone());
			settings.store();
			propagateNewTeam(newname);
			return true;
		} else {
			return false;
		}
	}

	public void add2Team(String character, String team) {
		settings.teams.get(team).members.add(character);
		settings.store();
		propagateAdd2Team(team, character);
	}

	public void del2Team(String character, String team) {
		settings.teams.get(team).members.remove(character);
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
			return settings.teams.get(settings.focusedTeam).members;
		}
		return Collections.emptySet();
	}

	protected Map<String, Set<String>> cachedTeamsPossibleSystems = new HashMap<>();

	/**
	 * find all the possible location ID for the given team.
	 * @return
	 */
	public Set<String> getFTeamPossibleSystems() {
		if (settings.focusedTeam == null) {
			return Collections.emptySet();
		}
		Set<String> ret = cachedTeamsPossibleSystems.get(settings.focusedTeam);
		if (ret == null) {
			HashMap<Long, Station> stationsById = db.getStationById();
			Set<String> allowedChars = getFTeamCharacters();
			Stream<Character> chars = apis.stream().flatMap(a->a.account.characters().stream()).filter(c->allowedChars.contains(c.name));
			ret = chars
					.flatMap(
							c -> Stream.concat(c.marketOrders().stream().map(oe -> oe.stationID), c.assetList().keySet().stream()))
					.distinct().map(l -> {
						if (stationsById.containsKey(l)) {
							return stationsById.get(l);
						} else {
							System.err.println("can't find station for id "+l);
							return null;
						}
					})
					.filter(l -> l != null).map(s -> s.system)
					.collect(Collectors.toSet());
			cachedTeamsPossibleSystems.put(settings.focusedTeam, ret);
		}
		return ret;
	}

	/**
	 * get the set of system the focused team is limited to
	 *
	 * @return
	 */
	public Set<String> getFTeamSystemLimit() {
		if (settings.focusedTeam != null) {
			return settings.teams.get(settings.focusedTeam).systems;
		}
		return Collections.emptySet();
	}

	/**
	 * add a system to the limit of a team
	 *
	 * @param teamName
	 * @param sysName
	 * @return
	 */
	public boolean addTeamSystem(String teamName, String sysName) {
		Set<String> teamSystems = settings.teams.get(teamName).systems;
		if (!teamSystems.add(sysName)) {
			return false;
		}
		propagateAddTeamSystem(teamName, sysName);
		settings.store();
		return true;
	}

	/**
	 * remove a system from the limit of a team
	 *
	 * @param teamName
	 * @param sysName
	 * @return
	 */
	public boolean remTeamSystem(String teamName, String sysName) {
		Set<String> teamSystems = settings.teams.get(teamName).systems;
		if (!teamSystems.remove(sysName)) {
			return false;
		}
		propagateRemTeamSystem(teamName, sysName);
		settings.store();
		return true;
	}

	// provision

	/** get the provision of the focused team. */
	public Provision getFTeamProvision() {
		if (settings.focusedTeam == null) {
			return null;
		}
		return settings.teams.get(settings.focusedTeam).provision;
	}

	public void provision(HashMap<Integer, Integer> items) {
		debug("provision " + items);
		for (Entry<Integer, Integer> e : items.entrySet()) {
			propagateNewProvision(e.getKey(), e.getValue());
			getFTeamProvision().total.put(e.getKey(),
					Math.max(0, getFTeamProvision().total.getOrDefault(e.getKey(), 0) + e.getValue()));
		}
		settings.store();
	}

	/** set the requirement in lp offer to given value for the focused team */
	public void provisionLPOffer(LPOffer offer, int requirement) {
		Provision p = getFTeamProvision();
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

	protected Map<String, Map<String, Map<Integer, Long>>> itemsByCharName = Collections.synchronizedMap(new HashMap<>());

	protected Map<String, Map<Integer, Long>> itemsByTeamName = Collections.synchronizedMap(new HashMap<>());

	// getting the items

	/**
	 * update the focused team's items. this is done on a character basis
	 */
	public void updateFTeamItems() {
		Set<String> teamChars = getFTeamCharacters();
		Set<String> teamSystems = getFTeamSystemLimit();
		Stream<Character> charStream = apis.parallelStream().flatMap(api -> api.account.characters().parallelStream())
				.filter(c -> teamChars.contains(c.name));
		Map<Integer, Long> totalGain = charStream.map(this::computeItemsDiff).flatMap(m -> m.entrySet().stream())
				.filter(e -> teamSystems == null || teamSystems.contains(e.getKey())).map(Map.Entry::getValue)
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
	 * @return a new Hashmap, system->itemid->difference in number.
	 */
	protected Map<String, Map<Integer, Long>> computeItemsDiff(Character c) {
		Date now = new Date();
		Date cacheExpiration = assetListCacheByCharID.get(c.characterID);
		if (cacheExpiration != null && cacheExpiration.after(now)) {
			return Collections.emptyMap();
		}
		logger.debug("invalid cache assets " + c.name);
		assetListCacheByCharID.put(c.characterID, new Date(now.getTime() + 20 * 60000));
		HashMap<Long, Station> stationById = db().getStationById();
		// for each
		HashMap<String, Map<Integer, Long>> itemsDiff = new HashMap<>();
		for (Entry<Long, ArrayList<Content>> e : c.assetList().entrySet()) {
			Station station = stationById.get(e.getKey());
			if (station != null) {
				Map<Integer, Long> localGains = itemsDiff.get(station.system);
				if (localGains == null) {
					localGains = new HashMap<>();
					itemsDiff.put(station.system, localGains);
				}
				for (Content co : e.getValue()) {
					localGains.put(co.typeID, co.quantity + localGains.getOrDefault(co.typeID, 0l));
				}
			} else {
				logger.debug("no station for id " + e.getKey());
			}
		}
		for (OrderEntry a : c.marketOrders()) {
			if (a.isOpen() && a.isBuyOrder()) {
				Station station = stationById.get(a.stationID);
				if (station != null) {
					Map<Integer, Long> localGains = itemsDiff.get(station.system);
					if (localGains == null) {
						localGains = new HashMap<>();
						itemsDiff.put(station.system, localGains);
					}
					localGains.put(a.typeID, a.volRemaining + localGains.getOrDefault(a.typeID, 0l));
				} else {
					logger.debug("no station for id " + a.stationID);
				}
			}
		}

		Map<String, Map<Integer, Long>> rawItems = itemsByCharName.get(c.name);
		if (rawItems == null) {
			// no items stored yet
			rawItems = itemsDiff;
			itemsByCharName.put(c.name, rawItems);
		} else {
			for (String system : Stream.concat(rawItems.keySet().stream(), itemsDiff.keySet().stream())
					.collect(Collectors.toList())) {
				Map<Integer, Long> systemDiff = itemsDiff.get(system);
				if (systemDiff == null) {
					systemDiff = new HashMap<>();
					itemsDiff.put(system, systemDiff);
				}
				Map<Integer, Long> systemRaw = rawItems.get(system);
				if (systemRaw == null) {
					systemRaw = new HashMap<>();
					rawItems.put(system, systemRaw);
				}
				for (int itemID : Stream.concat(systemDiff.keySet().stream(), systemRaw.keySet().stream()).mapToInt(i -> i)
						.distinct().toArray()) {
					long newVal = systemDiff.getOrDefault(itemID, 0l);
					long diff = newVal - systemRaw.getOrDefault(itemID, 0l);
					if (newVal != 0) {
						systemRaw.put(itemID, newVal);
					} else {
						systemRaw.remove(itemID);
					}
					if (diff != 0) {
						systemDiff.put(itemID, diff);
					} else {
						systemDiff.remove(itemID);
					}
				}
				if (systemDiff.isEmpty()) {
					itemsDiff.remove(system);
				}
				if (systemRaw.isEmpty()) {
					rawItems.remove(system);
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
