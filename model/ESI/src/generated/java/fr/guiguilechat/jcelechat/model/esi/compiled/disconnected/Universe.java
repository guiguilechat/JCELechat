package fr.guiguilechat.jcelechat.model.esi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_ancestries;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_bloodlines;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_constellations_constellation_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_factions;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_graphics_graphic_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_moons_moon_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_planets_planet_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_races;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_regions_region_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_schematics_schematic_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_stargates_stargate_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_stars_star_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_system_jumps;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_system_kills;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_systems_system_id;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_universe_types_type_id;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Universe {
    public final SwaggerDCCache<?> cache;
    private ObservableList<R_get_universe_ancestries> get_universe_ancestries_holder;
    private final Map<Integer, Property<R_get_universe_asteroid_belts_asteroid_belt_id>> get_universe_asteroid_belts_asteroid_belt_id_holder = new HashMap<>();
    private ObservableList<R_get_universe_bloodlines> get_universe_bloodlines_holder;
    private ObservableList<Integer> get_universe_categories_holder;
    private final Map<Integer, Property<R_get_universe_categories_category_id>> get_universe_categories_category_id_holder = new HashMap<>();
    private ObservableList<Integer> get_universe_constellations_holder;
    private final Map<Integer, Property<R_get_universe_constellations_constellation_id>> get_universe_constellations_constellation_id_holder = new HashMap<>();
    private ObservableList<Integer> get_universe_graphics_holder;
    private final Map<Integer, Property<R_get_universe_graphics_graphic_id>> get_universe_graphics_graphic_id_holder = new HashMap<>();
    private ObservableList<Integer> get_universe_groups_holder;
    private final Map<Integer, Property<R_get_universe_groups_group_id>> get_universe_groups_group_id_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_universe_moons_moon_id>> get_universe_moons_moon_id_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_universe_planets_planet_id>> get_universe_planets_planet_id_holder = new HashMap<>();
    private ObservableList<R_get_universe_races> get_universe_races_holder;
    private ObservableList<Integer> get_universe_regions_holder;
    private final Map<Integer, Property<R_get_universe_regions_region_id>> get_universe_regions_region_id_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_universe_schematics_schematic_id>> get_universe_schematics_schematic_id_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_universe_stargates_stargate_id>> get_universe_stargates_stargate_id_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_universe_stars_star_id>> get_universe_stars_star_id_holder = new HashMap<>();
    private ObservableList<Long> get_universe_structures_holder;
    private ObservableList<R_get_universe_system_jumps> get_universe_system_jumps_holder;
    private ObservableList<Integer> get_universe_systems_holder;
    private ObservableList<Integer> get_universe_types_holder;
    private ObservableList<R_get_universe_factions> get_universe_factions_holder;
    private final Map<Integer, Property<R_get_universe_stations_station_id>> get_universe_stations_station_id_holder = new HashMap<>();
    private ObservableList<R_get_universe_system_kills> get_universe_system_kills_holder;
    private final Map<Integer, Property<R_get_universe_types_type_id>> get_universe_types_type_id_holder = new HashMap<>();
    private final Map<Integer, Property<R_get_universe_systems_system_id>> get_universe_systems_system_id_holder = new HashMap<>();

    public Universe(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get all character ancestries
     * 
     * cache over {@link Swagger#get_universe_ancestries}<br />
     */
    public ObservableList<R_get_universe_ancestries> ancestries() {
        if (get_universe_ancestries_holder == null) {
            synchronized (this)
            {
                if (get_universe_ancestries_holder == null) {
                    ObservableList<R_get_universe_ancestries> finalContainer = FXCollections.observableArrayList();
                    get_universe_ancestries_holder = finalContainer;
                    get_universe_ancestries_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_ancestries", (page, headerHandler) -> (cache.swagger).get_universe_ancestries(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_ancestries_holder;
    }

    /**
     * Get information on an asteroid belt
     * 
     * cache over {@link Swagger#get_universe_asteroid_belts}<br />
     * 
     * @param asteroid_belt_id
     *     asteroid_belt_id integer
     */
    public Property<R_get_universe_asteroid_belts_asteroid_belt_id> asteroid_belts(int asteroid_belt_id) {
        Property<R_get_universe_asteroid_belts_asteroid_belt_id> ret = get_universe_asteroid_belts_asteroid_belt_id_holder.get(asteroid_belt_id);
        if (ret == null) {
            synchronized (get_universe_asteroid_belts_asteroid_belt_id_holder)
            {
                ret = get_universe_asteroid_belts_asteroid_belt_id_holder.get(asteroid_belt_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_asteroid_belts_asteroid_belt_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_asteroid_belts_asteroid_belt_id_holder.put(asteroid_belt_id, ret);
                    (cache).addFetchCacheObject("get_universe_asteroid_belts_asteroid_belt_id", headerHandler -> (cache.swagger).get_universe_asteroid_belts(asteroid_belt_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get a list of bloodlines
     * 
     * cache over {@link Swagger#get_universe_bloodlines}<br />
     */
    public ObservableList<R_get_universe_bloodlines> bloodlines() {
        if (get_universe_bloodlines_holder == null) {
            synchronized (this)
            {
                if (get_universe_bloodlines_holder == null) {
                    ObservableList<R_get_universe_bloodlines> finalContainer = FXCollections.observableArrayList();
                    get_universe_bloodlines_holder = finalContainer;
                    get_universe_bloodlines_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_bloodlines", (page, headerHandler) -> (cache.swagger).get_universe_bloodlines(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_bloodlines_holder;
    }

    /**
     * Get a list of item categories
     * 
     * cache over {@link Swagger#get_universe_categories}<br />
     */
    public ObservableList<Integer> categories() {
        if (get_universe_categories_holder == null) {
            synchronized (this)
            {
                if (get_universe_categories_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_universe_categories_holder = finalContainer;
                    get_universe_categories_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_categories", (page, headerHandler) -> IntStream.of((cache.swagger).get_universe_categories(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_categories_holder;
    }

    /**
     * Get information of an item category
     * 
     * cache over {@link Swagger#get_universe_categories}<br />
     * 
     * @param category_id
     *     An Eve item category ID
     */
    public Property<R_get_universe_categories_category_id> categories(int category_id) {
        Property<R_get_universe_categories_category_id> ret = get_universe_categories_category_id_holder.get(category_id);
        if (ret == null) {
            synchronized (get_universe_categories_category_id_holder)
            {
                ret = get_universe_categories_category_id_holder.get(category_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_categories_category_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_categories_category_id_holder.put(category_id, ret);
                    (cache).addFetchCacheObject("get_universe_categories_category_id", headerHandler -> (cache.swagger).get_universe_categories(category_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get a list of constellations
     * 
     * cache over {@link Swagger#get_universe_constellations}<br />
     */
    public ObservableList<Integer> constellations() {
        if (get_universe_constellations_holder == null) {
            synchronized (this)
            {
                if (get_universe_constellations_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_universe_constellations_holder = finalContainer;
                    get_universe_constellations_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_constellations", (page, headerHandler) -> IntStream.of((cache.swagger).get_universe_constellations(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_constellations_holder;
    }

    /**
     * Get information on a constellation
     * 
     * cache over {@link Swagger#get_universe_constellations}<br />
     * 
     * @param constellation_id
     *     constellation_id integer
     */
    public Property<R_get_universe_constellations_constellation_id> constellations(int constellation_id) {
        Property<R_get_universe_constellations_constellation_id> ret = get_universe_constellations_constellation_id_holder.get(constellation_id);
        if (ret == null) {
            synchronized (get_universe_constellations_constellation_id_holder)
            {
                ret = get_universe_constellations_constellation_id_holder.get(constellation_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_constellations_constellation_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_constellations_constellation_id_holder.put(constellation_id, ret);
                    (cache).addFetchCacheObject("get_universe_constellations_constellation_id", headerHandler -> (cache.swagger).get_universe_constellations(constellation_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get a list of graphics
     * 
     * cache over {@link Swagger#get_universe_graphics}<br />
     */
    public ObservableList<Integer> graphics() {
        if (get_universe_graphics_holder == null) {
            synchronized (this)
            {
                if (get_universe_graphics_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_universe_graphics_holder = finalContainer;
                    get_universe_graphics_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_graphics", (page, headerHandler) -> IntStream.of((cache.swagger).get_universe_graphics(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_graphics_holder;
    }

    /**
     * Get information on a graphic
     * 
     * cache over {@link Swagger#get_universe_graphics}<br />
     * 
     * @param graphic_id
     *     graphic_id integer
     */
    public Property<R_get_universe_graphics_graphic_id> graphics(int graphic_id) {
        Property<R_get_universe_graphics_graphic_id> ret = get_universe_graphics_graphic_id_holder.get(graphic_id);
        if (ret == null) {
            synchronized (get_universe_graphics_graphic_id_holder)
            {
                ret = get_universe_graphics_graphic_id_holder.get(graphic_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_graphics_graphic_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_graphics_graphic_id_holder.put(graphic_id, ret);
                    (cache).addFetchCacheObject("get_universe_graphics_graphic_id", headerHandler -> (cache.swagger).get_universe_graphics(graphic_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get a list of item groups
     * 
     * cache over {@link Swagger#get_universe_groups}<br />
     */
    public ObservableList<Integer> groups() {
        if (get_universe_groups_holder == null) {
            synchronized (this)
            {
                if (get_universe_groups_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_universe_groups_holder = finalContainer;
                    get_universe_groups_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_groups", (page, headerHandler) -> IntStream.of((cache.swagger).get_universe_groups(page, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_groups_holder;
    }

    /**
     * Get information on an item group
     * 
     * cache over {@link Swagger#get_universe_groups}<br />
     * 
     * @param group_id
     *     An Eve item group ID
     */
    public Property<R_get_universe_groups_group_id> groups(int group_id) {
        Property<R_get_universe_groups_group_id> ret = get_universe_groups_group_id_holder.get(group_id);
        if (ret == null) {
            synchronized (get_universe_groups_group_id_holder)
            {
                ret = get_universe_groups_group_id_holder.get(group_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_groups_group_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_groups_group_id_holder.put(group_id, ret);
                    (cache).addFetchCacheObject("get_universe_groups_group_id", headerHandler -> (cache.swagger).get_universe_groups(group_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get information on a moon
     * 
     * cache over {@link Swagger#get_universe_moons}<br />
     * 
     * @param moon_id
     *     moon_id integer
     */
    public Property<R_get_universe_moons_moon_id> moons(int moon_id) {
        Property<R_get_universe_moons_moon_id> ret = get_universe_moons_moon_id_holder.get(moon_id);
        if (ret == null) {
            synchronized (get_universe_moons_moon_id_holder)
            {
                ret = get_universe_moons_moon_id_holder.get(moon_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_moons_moon_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_moons_moon_id_holder.put(moon_id, ret);
                    (cache).addFetchCacheObject("get_universe_moons_moon_id", headerHandler -> (cache.swagger).get_universe_moons(moon_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get information on a planet
     * 
     * cache over {@link Swagger#get_universe_planets}<br />
     * 
     * @param planet_id
     *     planet_id integer
     */
    public Property<R_get_universe_planets_planet_id> planets(int planet_id) {
        Property<R_get_universe_planets_planet_id> ret = get_universe_planets_planet_id_holder.get(planet_id);
        if (ret == null) {
            synchronized (get_universe_planets_planet_id_holder)
            {
                ret = get_universe_planets_planet_id_holder.get(planet_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_planets_planet_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_planets_planet_id_holder.put(planet_id, ret);
                    (cache).addFetchCacheObject("get_universe_planets_planet_id", headerHandler -> (cache.swagger).get_universe_planets(planet_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get a list of character races
     * 
     * cache over {@link Swagger#get_universe_races}<br />
     */
    public ObservableList<R_get_universe_races> races() {
        if (get_universe_races_holder == null) {
            synchronized (this)
            {
                if (get_universe_races_holder == null) {
                    ObservableList<R_get_universe_races> finalContainer = FXCollections.observableArrayList();
                    get_universe_races_holder = finalContainer;
                    get_universe_races_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_races", (page, headerHandler) -> (cache.swagger).get_universe_races(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_races_holder;
    }

    /**
     * Get a list of regions
     * 
     * cache over {@link Swagger#get_universe_regions}<br />
     */
    public ObservableList<Integer> regions() {
        if (get_universe_regions_holder == null) {
            synchronized (this)
            {
                if (get_universe_regions_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_universe_regions_holder = finalContainer;
                    get_universe_regions_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_regions", (page, headerHandler) -> IntStream.of((cache.swagger).get_universe_regions(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_regions_holder;
    }

    /**
     * Get information on a region
     * 
     * cache over {@link Swagger#get_universe_regions}<br />
     * 
     * @param region_id
     *     region_id integer
     */
    public Property<R_get_universe_regions_region_id> regions(int region_id) {
        Property<R_get_universe_regions_region_id> ret = get_universe_regions_region_id_holder.get(region_id);
        if (ret == null) {
            synchronized (get_universe_regions_region_id_holder)
            {
                ret = get_universe_regions_region_id_holder.get(region_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_regions_region_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_regions_region_id_holder.put(region_id, ret);
                    (cache).addFetchCacheObject("get_universe_regions_region_id", headerHandler -> (cache.swagger).get_universe_regions(region_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get information on a planetary factory schematic
     * 
     * cache over {@link Swagger#get_universe_schematics}<br />
     * 
     * @param schematic_id
     *     A PI schematic ID
     */
    public Property<R_get_universe_schematics_schematic_id> schematics(int schematic_id) {
        Property<R_get_universe_schematics_schematic_id> ret = get_universe_schematics_schematic_id_holder.get(schematic_id);
        if (ret == null) {
            synchronized (get_universe_schematics_schematic_id_holder)
            {
                ret = get_universe_schematics_schematic_id_holder.get(schematic_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_schematics_schematic_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_schematics_schematic_id_holder.put(schematic_id, ret);
                    (cache).addFetchCacheObject("get_universe_schematics_schematic_id", headerHandler -> (cache.swagger).get_universe_schematics(schematic_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get information on a stargate
     * 
     * cache over {@link Swagger#get_universe_stargates}<br />
     * 
     * @param stargate_id
     *     stargate_id integer
     */
    public Property<R_get_universe_stargates_stargate_id> stargates(int stargate_id) {
        Property<R_get_universe_stargates_stargate_id> ret = get_universe_stargates_stargate_id_holder.get(stargate_id);
        if (ret == null) {
            synchronized (get_universe_stargates_stargate_id_holder)
            {
                ret = get_universe_stargates_stargate_id_holder.get(stargate_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_stargates_stargate_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_stargates_stargate_id_holder.put(stargate_id, ret);
                    (cache).addFetchCacheObject("get_universe_stargates_stargate_id", headerHandler -> (cache.swagger).get_universe_stargates(stargate_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get information on a star
     * 
     * cache over {@link Swagger#get_universe_stars}<br />
     * 
     * @param star_id
     *     star_id integer
     */
    public Property<R_get_universe_stars_star_id> stars(int star_id) {
        Property<R_get_universe_stars_star_id> ret = get_universe_stars_star_id_holder.get(star_id);
        if (ret == null) {
            synchronized (get_universe_stars_star_id_holder)
            {
                ret = get_universe_stars_star_id_holder.get(star_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_stars_star_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_stars_star_id_holder.put(star_id, ret);
                    (cache).addFetchCacheObject("get_universe_stars_star_id", headerHandler -> (cache.swagger).get_universe_stars(star_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * List all public structures
     * 
     * cache over {@link Swagger#get_universe_structures}<br />
     */
    public ObservableList<Long> structures() {
        if (get_universe_structures_holder == null) {
            synchronized (this)
            {
                if (get_universe_structures_holder == null) {
                    ObservableList<Long> finalContainer = FXCollections.observableArrayList();
                    get_universe_structures_holder = finalContainer;
                    get_universe_structures_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_structures", (page, headerHandler) -> LongStream.of((cache.swagger).get_universe_structures(headerHandler)).mapToObj((Long::valueOf)).toArray((Long[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_structures_holder;
    }

    /**
     * Get the number of jumps in solar systems within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with jumps will be listed
     * 
     * cache over {@link Swagger#get_universe_system_jumps}<br />
     */
    public ObservableList<R_get_universe_system_jumps> system_jumps() {
        if (get_universe_system_jumps_holder == null) {
            synchronized (this)
            {
                if (get_universe_system_jumps_holder == null) {
                    ObservableList<R_get_universe_system_jumps> finalContainer = FXCollections.observableArrayList();
                    get_universe_system_jumps_holder = finalContainer;
                    get_universe_system_jumps_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_system_jumps", (page, headerHandler) -> (cache.swagger).get_universe_system_jumps(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_system_jumps_holder;
    }

    /**
     * Get a list of solar systems
     * 
     * cache over {@link Swagger#get_universe_systems}<br />
     */
    public ObservableList<Integer> systems() {
        if (get_universe_systems_holder == null) {
            synchronized (this)
            {
                if (get_universe_systems_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_universe_systems_holder = finalContainer;
                    get_universe_systems_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_systems", (page, headerHandler) -> IntStream.of((cache.swagger).get_universe_systems(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_systems_holder;
    }

    /**
     * Get a list of type ids
     * 
     * cache over {@link Swagger#get_universe_types}<br />
     */
    public ObservableList<Integer> types() {
        if (get_universe_types_holder == null) {
            synchronized (this)
            {
                if (get_universe_types_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_universe_types_holder = finalContainer;
                    get_universe_types_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_types", (page, headerHandler) -> IntStream.of((cache.swagger).get_universe_types(page, headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_types_holder;
    }

    /**
     * Get a list of factions
     * 
     * cache over {@link Swagger#get_universe_factions}<br />
     */
    public ObservableList<R_get_universe_factions> factions() {
        if (get_universe_factions_holder == null) {
            synchronized (this)
            {
                if (get_universe_factions_holder == null) {
                    ObservableList<R_get_universe_factions> finalContainer = FXCollections.observableArrayList();
                    get_universe_factions_holder = finalContainer;
                    get_universe_factions_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_factions", (page, headerHandler) -> (cache.swagger).get_universe_factions(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_factions_holder;
    }

    /**
     * Get information on a station
     * 
     * cache over {@link Swagger#get_universe_stations}<br />
     * 
     * @param station_id
     *     station_id integer
     */
    public Property<R_get_universe_stations_station_id> stations(int station_id) {
        Property<R_get_universe_stations_station_id> ret = get_universe_stations_station_id_holder.get(station_id);
        if (ret == null) {
            synchronized (get_universe_stations_station_id_holder)
            {
                ret = get_universe_stations_station_id_holder.get(station_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_stations_station_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_stations_station_id_holder.put(station_id, ret);
                    (cache).addFetchCacheObject("get_universe_stations_station_id", headerHandler -> (cache.swagger).get_universe_stations(station_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get the number of ship, pod and NPC kills per solar system within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with kills will be listed
     * 
     * cache over {@link Swagger#get_universe_system_kills}<br />
     */
    public ObservableList<R_get_universe_system_kills> system_kills() {
        if (get_universe_system_kills_holder == null) {
            synchronized (this)
            {
                if (get_universe_system_kills_holder == null) {
                    ObservableList<R_get_universe_system_kills> finalContainer = FXCollections.observableArrayList();
                    get_universe_system_kills_holder = finalContainer;
                    get_universe_system_kills_holder.add(null);
                    (cache).addFetchCacheArray("get_universe_system_kills", (page, headerHandler) -> (cache.swagger).get_universe_system_kills(headerHandler), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_universe_system_kills_holder;
    }

    /**
     * Get information on a type
     * 
     * cache over {@link Swagger#get_universe_types}<br />
     * 
     * @param type_id
     *     An Eve item type ID
     */
    public Property<R_get_universe_types_type_id> types(int type_id) {
        Property<R_get_universe_types_type_id> ret = get_universe_types_type_id_holder.get(type_id);
        if (ret == null) {
            synchronized (get_universe_types_type_id_holder)
            {
                ret = get_universe_types_type_id_holder.get(type_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_types_type_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_types_type_id_holder.put(type_id, ret);
                    (cache).addFetchCacheObject("get_universe_types_type_id", headerHandler -> (cache.swagger).get_universe_types(type_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Get information on a solar system.
     * 
     * cache over {@link Swagger#get_universe_systems}<br />
     * 
     * @param system_id
     *     system_id integer
     */
    public Property<R_get_universe_systems_system_id> systems(int system_id) {
        Property<R_get_universe_systems_system_id> ret = get_universe_systems_system_id_holder.get(system_id);
        if (ret == null) {
            synchronized (get_universe_systems_system_id_holder)
            {
                ret = get_universe_systems_system_id_holder.get(system_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_universe_systems_system_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    ret.setValue(null);
                    get_universe_systems_system_id_holder.put(system_id, ret);
                    (cache).addFetchCacheObject("get_universe_systems_system_id", headerHandler -> (cache.swagger).get_universe_systems(system_id, headerHandler), item -> {
                        synchronized (finalret)
                        {
                            finalret.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
