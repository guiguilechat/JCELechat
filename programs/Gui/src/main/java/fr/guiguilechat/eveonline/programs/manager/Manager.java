package fr.guiguilechat.eveonline.programs.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import fr.guiguilechat.eveonline.model.apiv2.APIRoot;
import fr.guiguilechat.eveonline.model.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.model.apiv2.Char.Content;
import fr.guiguilechat.eveonline.model.apiv2.Char.OrderEntry;
import fr.guiguilechat.eveonline.model.sde.industry.Blueprint;
import fr.guiguilechat.eveonline.model.sde.industry.Blueprint.Material;
import fr.guiguilechat.eveonline.model.sde.items.MetaInf;
import fr.guiguilechat.eveonline.model.sde.locations.Station;
import fr.guiguilechat.eveonline.model.sde.npcs.LPOffer;
import fr.guiguilechat.eveonline.model.sde.npcs.LPOffer.ItemRef;
import fr.guiguilechat.eveonline.programs.manager.Settings.ProvisionType;
import fr.guiguilechat.eveonline.programs.manager.Settings.TeamDescription;
import fr.guiguilechat.eveonline.programs.manager.Settings.TeamDescription.Provision;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.api.APIPane;
import fr.guiguilechat.eveonline.programs.manager.panes.provision.ProvisionPane;
import fr.guiguilechat.eveonline.programs.manager.panes.status.StatusPane;
import fr.guiguilechat.eveonline.programs.manager.panes.team.TeamsPane;
import fr.guiguilechat.eveonline.programs.manager.panes.tools.ToolsTab;
import fr.guiguilechat.eveonline.programs.manager.settings.ISettings;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Manager extends Application implements EvePane {

	private static final Logger logger = LoggerFactory.getLogger(Manager.class);

	public static void main(String[] args) {
		int parrallelism = Runtime.getRuntime().availableProcessors() * 50;
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "" + parrallelism);

		launch(args);
	}

	@Override
	public Manager parent() {
		return this;
	}

	public Settings settings = ISettings.load(Settings.class);

	public final ObservableList<APIRoot> apis = FXCollections.observableArrayList();

	protected BorderPane mainLayout = new BorderPane();

	protected StatusPane statuspane = new StatusPane(this);
	protected ProvisionPane provisionpane = new ProvisionPane(this);
	protected TeamsPane teamPane = new TeamsPane(this);
	protected APIPane apiPane = new APIPane(this);
	protected ToolsTab toolsPane = new ToolsTab(this);
	protected TabPane tabs;
	protected Tab statustab, provisiontab, teamtab, apitab, tooltab;

	private EvePane[] children = new EvePane[] { statuspane, provisionpane, teamPane, apiPane, toolsPane };

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	@Override
	public boolean isShownSubPane(EvePane child) {
		return tabs.getSelectionModel().getSelectedItem().getContent() == child;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.debug("start manager");
		primaryStage.setTitle("guigui lechat manager");

		// set the tabs
		statustab = new Tab("status", statuspane);
		provisiontab = new Tab("provision", provisionpane);
		teamtab = new Tab("teams", teamPane);
		apitab = new Tab("apis", apiPane);
		tooltab = new Tab("tools", toolsPane);
		tabs = new TabPane(statustab, provisiontab, tooltab, teamtab, apitab);
		tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabs.setSide(Side.LEFT);
		tabs.getSelectionModel().selectedItemProperty().addListener((ov, old, now) -> {
			if (old != null) {
				((EvePane) old.getContent()).propagateIsShown(false);
			}
			if (now != null) {
				((EvePane) now.getContent()).propagateIsShown(true);
			}
		});
		// prevent moving out of options until we have at least one correct API.
		tabs.getSelectionModel().selectedItemProperty().addListener((obj, old, now) -> checkAPIOrSetOptionsTab());
		checkAPIOrSetOptionsTab();

		TitledPane tpDebug = new TitledPane("debug", debugPane);
		tpDebug.setExpanded(false);
		mainLayout.setCenter(tabs);
		mainLayout.setBottom(tpDebug);

		logger.debug("making scene");
		Scene scene = new Scene(mainLayout, 800, 900);
		primaryStage.setScene(scene);
		primaryStage.show();

		logger.debug("propagate apis");
		for (Entry<Integer, String> a : settings.apiKeys.entrySet()) {
			apis.add(new APIRoot(a.getKey(), a.getValue()));
		}
		propagateNewAPI(apis.toArray(new APIRoot[0]));

		logger.debug("propagate teams");
		for (String team : settings.teams.keySet()) {
			propagateNewTeam(team);
			for (String charname : settings.teams.get(team).members) {
				propagateAdd2Team(team, charname);
			}
		}
		propagateFocusedTeam(settings.focusedTeam);
		logger.debug("manager started");
		propagateIsShown(true);
		new Thread(this::precache).start();
	}

	protected void precache() {
	}

	protected void checkAPIOrSetOptionsTab() {
		if (settings.apiKeys.isEmpty()) {
			tabs.getSelectionModel().select(apitab);
		}
	}

	//
	// event handling
	//


	@Override
	public void onDelAPI(int key) {
		for (Iterator<APIRoot> it = apis.iterator(); it.hasNext();) {
			if (it.next().key.keyID == key) {
				it.remove();
			}
		}
	}

	@Override
	public void onDelTeam(String name) {
		cachedTeamAssets.remove(name);
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

	public APIRoot addAPI(int key, String code) {
		// first check the api
		APIRoot newapi = new APIRoot(key, code);
		if (newapi.getInfos() == null) {
			return null;
		}
		// then remove former api with same key
		String oldCode = settings.apiKeys.put(key, code);
		settings.store();

		// we can't modify the apiroot, as they have final args. so remove and add.
		if (oldCode != null) {
			apis.removeIf(ar -> ar.key.keyID == key);
		}
		apis.add(newapi);
		if (oldCode == null) {
			propagateNewAPI(newapi);
		}
		return newapi;
	}

	/**
	 * add an sso api
	 *
	 * @param key
	 *          the base64 value of {id}:{secret}
	 * @param code
	 *          the refreshtoken
	 */
	public void addAPI(String key, String code) {
		settings.ssoKeys.put(key, code);
		settings.store();
	}

	public APIRoot getAPI(int key) {
		for (APIRoot a : apis) {
			if (a.key.keyID == key) {
				return a;
			}
		}
		return null;
	}

	public Stream<EveChar> streamChars() {
		return apis.parallelStream().flatMap(a -> a.account.characters().stream());
	}

	public Stream<EveChar> streamTeamCharacters(String team) {
		if (team != null && settings.teams.containsKey(team)) {
			Set<String> members = settings.teams.get(team).members;
			return streamChars().filter(c -> members.contains(c.name));
		}
		return Stream.empty();
	}

	public Stream<EveChar> streamFTeamCharacters() {
		return streamTeamCharacters(settings.focusedTeam);
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
			settings.teams.put(now, settings.teams.remove(old));
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
		if (name == settings.focusedTeam || name != null && name.equals(settings.focusedTeam)) {
			return;
		}
		debug("focusing on team " + name);
		settings.focusedTeam = name;
		settings.store();
		propagateFocusedTeam(name);
	}

	/**
	 * find all the possible systems names for the given team.
	 *
	 * @return distinct parallel stream of system names
	 */
	public Stream<String> streamTeamPossibleSystems(String team) {
		Set<String> allowedChars = settings.teams.get(team).members;
		Stream<EveChar> chars = apis.parallelStream().flatMap(a -> a.account.characters().parallelStream())
				.filter(c -> allowedChars.contains(c.name));
		return chars.parallel().flatMap(this::streamCharPossibleSystems).distinct();
	}

	public Stream<String> streamCharPossibleSystems(EveChar c) {
		return Stream.concat(getCharAssets(c).keySet().stream(),
				Stream.concat(getCharBOs(c).keySet().stream(), getCharSOs(c).keySet().stream())).filter(s -> s != null)
				.distinct();
	}

	/**
	 * get the set of system the focused team is limited to
	 *
	 * @return
	 */
	public Set<String> getFTeamSystemLimit() {
		return getTeamSystemLimit(settings.focusedTeam);
	}

	public Set<String> getTeamSystemLimit(String team) {
		if (team != null) {
			return settings.teams.get(team).systems;
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

	/** get the provision of materials for the focused team. */
	public Provision getTeamProvision(String team, ProvisionType provitype) {
		if (team == null) {
			return null;
		}
		switch (provitype) {
		case MATERIAL:
			return settings.teams.get(team).provisionMaterials;
		case PRODUCT:
			return settings.teams.get(team).provisionProduct;
		case SO:
			return settings.teams.get(team).provisionSO;
		default:
			throw new UnsupportedOperationException("can't handle " + provitype);
		}
	}

	/** set the requirement in lp offer to given value for the focused team */
	public void provisionLPOffer(ProvisionType ptype, LPOffer offer, int requirement) {
		HashMap<Integer, Integer> proviMatLP = getTeamProvision(settings.focusedTeam, ptype).lpoffers;
		int diff = requirement - proviMatLP.getOrDefault(offer.id, 0);
		if (requirement <= 0) {
			proviMatLP.remove(offer.id);
		} else {
			proviMatLP.put(offer.id, requirement);
		}
		HashMap<Integer, Integer> proviTotal = getTeamProvision(settings.focusedTeam, ptype).total;

		for (ItemRef e : ptype == ProvisionType.MATERIAL ? offer.requirements.items : Arrays.asList(offer.product)) {
			int id = fr.guiguilechat.eveonline.model.sde.items.MetaInf.getItem(e.item).id;
			int newQtty = proviTotal.getOrDefault(id, 0)
					+ e.quantity * diff;
			if (newQtty > 0) {
				proviTotal.put(id, newQtty);
			} else {
				proviTotal.remove(id);
			}
			propagateNewProvision(ptype, id, newQtty);
		}

		settings.store();
	}

	public void provisionLPOffer(LPOffer offer, int mat, int product, int so) {
		provisionLPOffer(ProvisionType.MATERIAL, offer, mat);
		provisionLPOffer(ProvisionType.PRODUCT, offer, product);
		provisionLPOffer(ProvisionType.SO, offer, so);
	}

	/** set the requirement in BP to given value for the focused team */
	public void provisionBP(ProvisionType ptype, Blueprint bp, int requirement) {
		HashMap<Integer, Integer> proviBP = getTeamProvision(settings.focusedTeam, ptype).blueprints;
		int diff = requirement - proviBP.getOrDefault(bp.id, 0);
		if (requirement <= 0) {
			proviBP.remove(bp.id);
		} else {
			proviBP.put(bp.id, requirement);
		}
		HashMap<Integer, Integer> proviTotal = getTeamProvision(settings.focusedTeam, ptype).total;
		for (Material m : ptype == ProvisionType.MATERIAL ? bp.manufacturing.materials : bp.manufacturing.products) {
			int mid = MetaInf.getItem(m.name).id;
			int newQtty = proviTotal.getOrDefault(mid, 0) + m.quantity * diff;
			if (newQtty > 0) {
				proviTotal.put(mid, newQtty);
			} else {
				proviTotal.remove(mid);
			}
			propagateNewProvision(ptype, mid, newQtty);
		}
		settings.store();
	}

	public void provisionBP(Blueprint bp, int mat, int product, int so) {
		provisionBP(ProvisionType.MATERIAL, bp, mat);
		provisionBP(ProvisionType.PRODUCT, bp, product);
		provisionBP(ProvisionType.SO, bp, so);
	}

	// items, bo, so

	/** only fetch characer data once every X minutes */
	protected int assetCacheDelayMinutes = 10;

	/** char->system->typeID->qtty */
	protected Map<String, Map<String, Map<Integer, Long>>> cachedAssetsByCharName = Collections
			.synchronizedMap(new HashMap<>());
	protected Map<String, Date> expireAssetsByCharName = Collections.synchronizedMap(new HashMap<>());

	/**
	 * cache and get the (possible) assets of a char.
	 *
	 * @param c
	 *          a char.
	 * @return system > itemId > qtty for this char
	 */
	public Map<String, Map<Integer, Long>> getCharAssets(EveChar c) {
		Date cacheExpire = expireAssetsByCharName.get(c.name);
		Date now = new Date();
		if (cacheExpire != null && cacheExpire.after(now)) {
			logger.trace("returning old cache for assets of character " + c.name);
			return cachedAssetsByCharName.get(c.name);
		} else {
			Map<String, Map<Integer, Long>> itemsqtty = fetchCharAssets(c);
			cachedAssetsByCharName.put(c.name, itemsqtty);
			expireAssetsByCharName.put(c.name, new Date(now.getTime() + assetCacheDelayMinutes * 60000));
			logger.trace("new items for " + c.name + " : " + itemsqtty);
			return itemsqtty;
		}
	}

	/** char->system->typeID->qtty */
	protected Map<String, Map<String, Map<Integer, Long>>> cachedBOsByCharName = Collections
			.synchronizedMap(new HashMap<>());
	protected Map<String, Date> expireBOsByCharName = Collections.synchronizedMap(new HashMap<>());

	/**
	 * cache and get the (possible) buy orders of a char.
	 *
	 * @param c
	 *          a char.
	 * @return system > itemId > qtty for this char
	 */
	public Map<String, Map<Integer, Long>> getCharBOs(EveChar c) {
		Date cacheExpire = expireBOsByCharName.get(c.name);
		Date now = new Date();
		if (cacheExpire != null && cacheExpire.after(now)) {
			logger.trace("returning old cache for bo of character " + c.name);
			return cachedBOsByCharName.get(c.name);
		} else {
			Map<String, Map<Integer, Long>> itemsqtty = fetchCharBOs(c);
			cachedBOsByCharName.put(c.name, itemsqtty);
			expireBOsByCharName.put(c.name, new Date(now.getTime() + assetCacheDelayMinutes * 60000));
			logger.trace("new bo for " + c.name + " : " + itemsqtty);
			return itemsqtty;
		}
	}

	/** char->system->typeID->qtty */
	protected Map<String, Map<String, Map<Integer, Long>>> cachedSOsByCharName = Collections
			.synchronizedMap(new HashMap<>());
	protected Map<String, Date> expireSOsByCharName = Collections.synchronizedMap(new HashMap<>());

	/**
	 * cache and get the (possible) sell orders of a char.
	 *
	 * @param c
	 *          a char.
	 * @return system > itemId > qtty for this char
	 */
	public Map<String, Map<Integer, Long>> getCharSOs(EveChar c) {
		Date cacheExpire = expireSOsByCharName.get(c.name);
		Date now = new Date();
		if (cacheExpire != null && cacheExpire.after(now)) {
			logger.trace("returning old cache for so of character " + c.name);
			return cachedSOsByCharName.get(c.name);
		} else {
			Map<String, Map<Integer, Long>> itemsqtty = fetchCharSOs(c);
			cachedSOsByCharName.put(c.name, itemsqtty);
			expireSOsByCharName.put(c.name, new Date(now.getTime() + assetCacheDelayMinutes * 60000));
			logger.trace("new items for " + c.name + " : " + itemsqtty);
			return itemsqtty;
		}
	}

	/** get the name of a station's system */
	protected String getStationSystem(long stationId) {
		Station station = Station.load().get(Station.loadById().get((int) stationId));
		String system = null;
		if (station != null) {
			system = station.solarSystem;
		}
		return system;

	}

	/** fetch the assets of given character */
	protected Map<String, Map<Integer, Long>> fetchCharAssets(EveChar c) {
		HashMap<String, Map<Integer, Long>> itemsqtty = new HashMap<>();
		for (Entry<Long, ArrayList<Content>> e : c.assetList().entrySet()) {
			String system = getStationSystem(e.getKey());
			if (system != null) {
				Map<Integer, Long> sysItems = itemsqtty.get(system);
				if (sysItems == null) {
					sysItems = new HashMap<>();
					itemsqtty.put(system, sysItems);
				}
				for (Content co : e.getValue()) {
					sysItems.put(co.typeID, co.quantity + sysItems.getOrDefault(co.typeID, 0l));
					// System.err.println("inv " + c.name + " " + station.name + " " +
					// station.system + " "
					// + db.getElementById(co.typeID) + " : " + co.quantity);
				}
			} else {
				logger.debug("no station for char " + c.name + " assets with id " + e.getKey());
			}
		}
		return itemsqtty;
	}

	/** fetch BO of given character */
	protected Map<String, Map<Integer, Long>> fetchCharBOs(EveChar c) {
		HashMap<String, Map<Integer, Long>> itemsqtty = new HashMap<>();
		for (OrderEntry a : c.marketOrders()) {
			if (a.isOpen() && a.isBuyOrder()) {
				String system = getStationSystem(a.stationID);
				if (system == null) {
					logger.debug("no station for BO with id " + a.stationID);
				}
				Map<Integer, Long> sysGain = itemsqtty.get(system);
				if (sysGain == null) {
					sysGain = new HashMap<>();
					itemsqtty.put(system, sysGain);
				}
				sysGain.put(a.typeID, a.volRemaining + sysGain.getOrDefault(a.typeID, 0l));
			}
		}
		return itemsqtty;
	}

	/** fetch BO of given character */
	protected Map<String, Map<Integer, Long>> fetchCharSOs(EveChar c) {
		HashMap<String, Map<Integer, Long>> itemsqtty = new HashMap<>();
		for (OrderEntry a : c.marketOrders()) {
			if (a.isOpen() && !a.isBuyOrder()) {
				String system = getStationSystem(a.stationID);
				if (system == null) {
					logger.debug("no station for SO with id " + a.stationID);
				}
				Map<Integer, Long> sysGain = itemsqtty.get(system);
				if (sysGain == null) {
					sysGain = new HashMap<>();
					itemsqtty.put(system, sysGain);
				}
				sysGain.put(a.typeID, a.volRemaining + sysGain.getOrDefault(a.typeID, 0l));

			}
		}
		return itemsqtty;
	}

	// team management for bo, so, and assets

	// assets

	/**
	 * contains for each team the last list of items retrieved
	 */
	protected Map<String, Map<Integer, Long>> cachedTeamAssets = Collections.synchronizedMap(new HashMap<>());

	/**
	 * get the list of items for given team. fires a
	 * {@link #propagateFocusedTeamNewItems(Map)}
	 *
	 * @param team
	 *          the name of the team
	 * @return the new list of items.
	 */
	public Map<Integer, Long> getTeamAssets(String team) {
		if (team == null) {
			return Collections.emptyMap();
		}
		Map<Integer, Long> newItems = fetchTeamAssets(team, getTeamSystemLimit(team));

		Map<Integer, Long> oldItems = cachedTeamAssets.get(team);
		Map<Integer, Long> diff = new HashMap<>(newItems);
		if (oldItems != null) {
			for (Entry<Integer, Long> e : oldItems.entrySet()) {
				long value = newItems.getOrDefault(e.getKey(), 0l) - e.getValue();
				if (value != 0) {
					diff.put(e.getKey(), value);
				} else {
					diff.remove(e.getKey());
				}
			}
		}
		cachedTeamAssets.put(team, newItems);
		if (!diff.isEmpty()) {
			logger.trace("items diff for team " + team + " : " + diff);
			propagateTeamNewAssets(team, diff);
		}
		return newItems;
	}

	protected Map<Integer, Long> fetchTeamAssets(String name, Set<String> teamSystems) {
		Stream<Map.Entry<Integer, Long>> teamAssetsStream = streamTeamCharacters(name)
				.flatMap(c -> getCharAssets(c).entrySet().stream())
				.filter(e -> teamSystems.isEmpty() || teamSystems.contains(e.getKey()))
				.flatMap(e -> e.getValue().entrySet().stream());
		return teamAssetsStream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
	}

	public Map<Integer, Long> getFTeamAssets() {
		if (settings.focusedTeam == null) {
			logger.debug("null focused team");
			return Collections.emptyMap();
		}
		return getTeamAssets(settings.focusedTeam);
	}

	// bos

	public Map<Integer, Long> getTeamBOs(String team) {
		if (team == null) {
			logger.debug("null team");
			return Collections.emptyMap();
		}
		Set<String> teamSystems = getTeamSystemLimit(team);
		Stream<Map.Entry<Integer, Long>> teamBOsStream = streamTeamCharacters(team)
				.flatMap(c -> getCharBOs(c).entrySet().stream())
				.filter(e -> e.getKey() == null || teamSystems.isEmpty() || teamSystems.contains(e.getKey()))
				.flatMap(e -> e.getValue().entrySet().stream());
		return teamBOsStream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
	}

	// sos

	public Map<Integer, Long> getTeamSOs(String team) {
		if (team == null) {
			logger.debug("null team");
			return Collections.emptyMap();
		}
		Set<String> teamSystems = getTeamSystemLimit(team);
		Stream<Map.Entry<Integer, Long>> teamBOsStream = streamTeamCharacters(team)
				.flatMap(c -> getCharSOs(c).entrySet().stream())
				.filter(e -> e.getKey() == null || teamSystems.isEmpty() || teamSystems.contains(e.getKey()))
				.flatMap(e -> e.getValue().entrySet().stream());
		return teamBOsStream.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
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

	/**
	 * add item to the debug pane. synchronized.
	 *
	 * @param clazz
	 *          the class of the item which wants to add an entry
	 * @param data
	 *          the information
	 */
	public void printDebug(Class<? extends EvePane> clazz, String data) {
		synchronized (debugPane) {
			DebugEntry de = new DebugEntry();
			de.message = data;
			de.context = clazz;
			de.date = new Date();
			debugPane.getItems().add(de);
			debugPane.sort();
		}
	}

}
