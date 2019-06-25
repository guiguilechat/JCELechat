package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_ancestries;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_bloodlines;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_constellations_constellation_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_factions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_graphics_graphic_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_moons_moon_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_planets_planet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_races;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_regions_region_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_schematics_schematic_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stargates_stargate_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stars_star_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_jumps;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_system_kills;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_systems_system_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Universe {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<R_get_universe_ancestries> get_universe_ancestries_holder;
    private final Map<Integer, ObsObjHolder<R_get_universe_asteroid_belts_asteroid_belt_id>> get_universe_asteroid_belts_asteroid_belt_id_holder = new HashMap<>();
    private ObsListHolder<R_get_universe_bloodlines> get_universe_bloodlines_holder;
    private ObsListHolder<Integer> get_universe_categories_holder;
    private final Map<Integer, ObsObjHolder<R_get_universe_categories_category_id>> get_universe_categories_category_id_holder = new HashMap<>();
    private ObsListHolder<Integer> get_universe_constellations_holder;
    private final Map<Integer, ObsObjHolder<R_get_universe_constellations_constellation_id>> get_universe_constellations_constellation_id_holder = new HashMap<>();
    private ObsListHolder<Integer> get_universe_graphics_holder;
    private final Map<Integer, ObsObjHolder<R_get_universe_graphics_graphic_id>> get_universe_graphics_graphic_id_holder = new HashMap<>();
    private ObsListHolder<Integer> get_universe_groups_holder;
    private final Map<Integer, ObsObjHolder<R_get_universe_groups_group_id>> get_universe_groups_group_id_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_universe_moons_moon_id>> get_universe_moons_moon_id_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_universe_planets_planet_id>> get_universe_planets_planet_id_holder = new HashMap<>();
    private ObsListHolder<R_get_universe_races> get_universe_races_holder;
    private ObsListHolder<Integer> get_universe_regions_holder;
    private final Map<Integer, ObsObjHolder<R_get_universe_regions_region_id>> get_universe_regions_region_id_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_universe_schematics_schematic_id>> get_universe_schematics_schematic_id_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_universe_stargates_stargate_id>> get_universe_stargates_stargate_id_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_universe_stars_star_id>> get_universe_stars_star_id_holder = new HashMap<>();
    private final Map<fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.filter, ObsListHolder<Long>> get_universe_structures_holder = new HashMap<>();
    private ObsListHolder<R_get_universe_system_jumps> get_universe_system_jumps_holder;
    private ObsListHolder<Integer> get_universe_systems_holder;
    private ObsListHolder<Integer> get_universe_types_holder;
    private ObsListHolder<R_get_universe_factions> get_universe_factions_holder;
    private final Map<Integer, ObsObjHolder<R_get_universe_stations_station_id>> get_universe_stations_station_id_holder = new HashMap<>();
    private ObsListHolder<R_get_universe_system_kills> get_universe_system_kills_holder;
    private final Map<Integer, ObsObjHolder<R_get_universe_types_type_id>> get_universe_types_type_id_holder = new HashMap<>();
    private final Map<Integer, ObsObjHolder<R_get_universe_systems_system_id>> get_universe_systems_system_id_holder = new HashMap<>();

    public Universe(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Get all character ancestries
     * 
     * cache over {@link Swagger#get_universe_ancestries}<br />
     */
    public ObsListHolder<R_get_universe_ancestries> ancestries() {
        if (get_universe_ancestries_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_ancestries_holder == null) {
                            ObservableList<R_get_universe_ancestries> holder = FXCollections.observableArrayList();
                            get_universe_ancestries_holder = (cache).toHolder(holder);
                            ObsListHolder<R_get_universe_ancestries> finalRet = get_universe_ancestries_holder;
                            (cache).addFetchCacheArray("get_universe_ancestries", (page, properties) -> (cache.swagger).get_universe_ancestries(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObsObjHolder<R_get_universe_asteroid_belts_asteroid_belt_id> asteroid_belts(int asteroid_belt_id) {
        ObsObjHolder<R_get_universe_asteroid_belts_asteroid_belt_id> ret = get_universe_asteroid_belts_asteroid_belt_id_holder.get(asteroid_belt_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_asteroid_belts_asteroid_belt_id_holder);
            try {
                synchronized (get_universe_asteroid_belts_asteroid_belt_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_asteroid_belts_asteroid_belt_id_holder);
                    {
                        ret = get_universe_asteroid_belts_asteroid_belt_id_holder.get(asteroid_belt_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_asteroid_belts_asteroid_belt_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_asteroid_belts_asteroid_belt_id_holder.put(asteroid_belt_id, ret);
                            (cache).addFetchCacheObject("get_universe_asteroid_belts_asteroid_belt_id", properties -> (cache.swagger).get_universe_asteroid_belts(asteroid_belt_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_asteroid_belts_asteroid_belt_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_asteroid_belts_asteroid_belt_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get a list of bloodlines
     * 
     * cache over {@link Swagger#get_universe_bloodlines}<br />
     */
    public ObsListHolder<R_get_universe_bloodlines> bloodlines() {
        if (get_universe_bloodlines_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_bloodlines_holder == null) {
                            ObservableList<R_get_universe_bloodlines> holder = FXCollections.observableArrayList();
                            get_universe_bloodlines_holder = (cache).toHolder(holder);
                            ObsListHolder<R_get_universe_bloodlines> finalRet = get_universe_bloodlines_holder;
                            (cache).addFetchCacheArray("get_universe_bloodlines", (page, properties) -> (cache.swagger).get_universe_bloodlines(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_universe_bloodlines_holder;
    }

    /**
     * Get a list of item categories
     * 
     * cache over {@link Swagger#get_universe_categories}<br />
     */
    public ObsListHolder<Integer> categories() {
        if (get_universe_categories_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_categories_holder == null) {
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_universe_categories_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_universe_categories_holder;
                            (cache).addFetchCacheArray("get_universe_categories", (page, properties) -> (cache.swagger).get_universe_categories(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObsObjHolder<R_get_universe_categories_category_id> categories(int category_id) {
        ObsObjHolder<R_get_universe_categories_category_id> ret = get_universe_categories_category_id_holder.get(category_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_categories_category_id_holder);
            try {
                synchronized (get_universe_categories_category_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_categories_category_id_holder);
                    {
                        ret = get_universe_categories_category_id_holder.get(category_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_categories_category_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_categories_category_id_holder.put(category_id, ret);
                            (cache).addFetchCacheObject("get_universe_categories_category_id", properties -> (cache.swagger).get_universe_categories(category_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_categories_category_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_categories_category_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get a list of constellations
     * 
     * cache over {@link Swagger#get_universe_constellations}<br />
     */
    public ObsListHolder<Integer> constellations() {
        if (get_universe_constellations_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_constellations_holder == null) {
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_universe_constellations_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_universe_constellations_holder;
                            (cache).addFetchCacheArray("get_universe_constellations", (page, properties) -> (cache.swagger).get_universe_constellations(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObsObjHolder<R_get_universe_constellations_constellation_id> constellations(int constellation_id) {
        ObsObjHolder<R_get_universe_constellations_constellation_id> ret = get_universe_constellations_constellation_id_holder.get(constellation_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_constellations_constellation_id_holder);
            try {
                synchronized (get_universe_constellations_constellation_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_constellations_constellation_id_holder);
                    {
                        ret = get_universe_constellations_constellation_id_holder.get(constellation_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_constellations_constellation_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_constellations_constellation_id_holder.put(constellation_id, ret);
                            (cache).addFetchCacheObject("get_universe_constellations_constellation_id", properties -> (cache.swagger).get_universe_constellations(constellation_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_constellations_constellation_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_constellations_constellation_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get a list of graphics
     * 
     * cache over {@link Swagger#get_universe_graphics}<br />
     */
    public ObsListHolder<Integer> graphics() {
        if (get_universe_graphics_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_graphics_holder == null) {
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_universe_graphics_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_universe_graphics_holder;
                            (cache).addFetchCacheArray("get_universe_graphics", (page, properties) -> (cache.swagger).get_universe_graphics(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObsObjHolder<R_get_universe_graphics_graphic_id> graphics(int graphic_id) {
        ObsObjHolder<R_get_universe_graphics_graphic_id> ret = get_universe_graphics_graphic_id_holder.get(graphic_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_graphics_graphic_id_holder);
            try {
                synchronized (get_universe_graphics_graphic_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_graphics_graphic_id_holder);
                    {
                        ret = get_universe_graphics_graphic_id_holder.get(graphic_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_graphics_graphic_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_graphics_graphic_id_holder.put(graphic_id, ret);
                            (cache).addFetchCacheObject("get_universe_graphics_graphic_id", properties -> (cache.swagger).get_universe_graphics(graphic_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_graphics_graphic_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_graphics_graphic_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get a list of item groups
     * 
     * cache over {@link Swagger#get_universe_groups}<br />
     */
    public ObsListHolder<Integer> groups() {
        if (get_universe_groups_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_groups_holder == null) {
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_universe_groups_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_universe_groups_holder;
                            (cache).addFetchCacheArray("get_universe_groups", (page, properties) -> (cache.swagger).get_universe_groups(page, properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObsObjHolder<R_get_universe_groups_group_id> groups(int group_id) {
        ObsObjHolder<R_get_universe_groups_group_id> ret = get_universe_groups_group_id_holder.get(group_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_groups_group_id_holder);
            try {
                synchronized (get_universe_groups_group_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_groups_group_id_holder);
                    {
                        ret = get_universe_groups_group_id_holder.get(group_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_groups_group_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_groups_group_id_holder.put(group_id, ret);
                            (cache).addFetchCacheObject("get_universe_groups_group_id", properties -> (cache.swagger).get_universe_groups(group_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_groups_group_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_groups_group_id_holder);
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
    public ObsObjHolder<R_get_universe_moons_moon_id> moons(int moon_id) {
        ObsObjHolder<R_get_universe_moons_moon_id> ret = get_universe_moons_moon_id_holder.get(moon_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_moons_moon_id_holder);
            try {
                synchronized (get_universe_moons_moon_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_moons_moon_id_holder);
                    {
                        ret = get_universe_moons_moon_id_holder.get(moon_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_moons_moon_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_moons_moon_id_holder.put(moon_id, ret);
                            (cache).addFetchCacheObject("get_universe_moons_moon_id", properties -> (cache.swagger).get_universe_moons(moon_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_moons_moon_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_moons_moon_id_holder);
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
    public ObsObjHolder<R_get_universe_planets_planet_id> planets(int planet_id) {
        ObsObjHolder<R_get_universe_planets_planet_id> ret = get_universe_planets_planet_id_holder.get(planet_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_planets_planet_id_holder);
            try {
                synchronized (get_universe_planets_planet_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_planets_planet_id_holder);
                    {
                        ret = get_universe_planets_planet_id_holder.get(planet_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_planets_planet_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_planets_planet_id_holder.put(planet_id, ret);
                            (cache).addFetchCacheObject("get_universe_planets_planet_id", properties -> (cache.swagger).get_universe_planets(planet_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_planets_planet_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_planets_planet_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get a list of character races
     * 
     * cache over {@link Swagger#get_universe_races}<br />
     */
    public ObsListHolder<R_get_universe_races> races() {
        if (get_universe_races_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_races_holder == null) {
                            ObservableList<R_get_universe_races> holder = FXCollections.observableArrayList();
                            get_universe_races_holder = (cache).toHolder(holder);
                            ObsListHolder<R_get_universe_races> finalRet = get_universe_races_holder;
                            (cache).addFetchCacheArray("get_universe_races", (page, properties) -> (cache.swagger).get_universe_races(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_universe_races_holder;
    }

    /**
     * Get a list of regions
     * 
     * cache over {@link Swagger#get_universe_regions}<br />
     */
    public ObsListHolder<Integer> regions() {
        if (get_universe_regions_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_regions_holder == null) {
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_universe_regions_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_universe_regions_holder;
                            (cache).addFetchCacheArray("get_universe_regions", (page, properties) -> (cache.swagger).get_universe_regions(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObsObjHolder<R_get_universe_regions_region_id> regions(int region_id) {
        ObsObjHolder<R_get_universe_regions_region_id> ret = get_universe_regions_region_id_holder.get(region_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_regions_region_id_holder);
            try {
                synchronized (get_universe_regions_region_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_regions_region_id_holder);
                    {
                        ret = get_universe_regions_region_id_holder.get(region_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_regions_region_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_regions_region_id_holder.put(region_id, ret);
                            (cache).addFetchCacheObject("get_universe_regions_region_id", properties -> (cache.swagger).get_universe_regions(region_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_regions_region_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_regions_region_id_holder);
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
    public ObsObjHolder<R_get_universe_schematics_schematic_id> schematics(int schematic_id) {
        ObsObjHolder<R_get_universe_schematics_schematic_id> ret = get_universe_schematics_schematic_id_holder.get(schematic_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_schematics_schematic_id_holder);
            try {
                synchronized (get_universe_schematics_schematic_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_schematics_schematic_id_holder);
                    {
                        ret = get_universe_schematics_schematic_id_holder.get(schematic_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_schematics_schematic_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_schematics_schematic_id_holder.put(schematic_id, ret);
                            (cache).addFetchCacheObject("get_universe_schematics_schematic_id", properties -> (cache.swagger).get_universe_schematics(schematic_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_schematics_schematic_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_schematics_schematic_id_holder);
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
    public ObsObjHolder<R_get_universe_stargates_stargate_id> stargates(int stargate_id) {
        ObsObjHolder<R_get_universe_stargates_stargate_id> ret = get_universe_stargates_stargate_id_holder.get(stargate_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_stargates_stargate_id_holder);
            try {
                synchronized (get_universe_stargates_stargate_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_stargates_stargate_id_holder);
                    {
                        ret = get_universe_stargates_stargate_id_holder.get(stargate_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_stargates_stargate_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_stargates_stargate_id_holder.put(stargate_id, ret);
                            (cache).addFetchCacheObject("get_universe_stargates_stargate_id", properties -> (cache.swagger).get_universe_stargates(stargate_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_stargates_stargate_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_stargates_stargate_id_holder);
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
    public ObsObjHolder<R_get_universe_stars_star_id> stars(int star_id) {
        ObsObjHolder<R_get_universe_stars_star_id> ret = get_universe_stars_star_id_holder.get(star_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_stars_star_id_holder);
            try {
                synchronized (get_universe_stars_star_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_stars_star_id_holder);
                    {
                        ret = get_universe_stars_star_id_holder.get(star_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_stars_star_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_stars_star_id_holder.put(star_id, ret);
                            (cache).addFetchCacheObject("get_universe_stars_star_id", properties -> (cache.swagger).get_universe_stars(star_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_stars_star_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_stars_star_id_holder);
            }
        }
        return ret;
    }

    /**
     * List all public structures
     * 
     * cache over {@link Swagger#get_universe_structures}<br />
     * 
     * @param filter
     *     Only list public structures that have this service online
     */
    public ObsListHolder<Long> structures(fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.filter filter) {
        ObsListHolder<Long> ret = get_universe_structures_holder.get(filter);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_structures_holder);
            try {
                synchronized (get_universe_structures_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_structures_holder);
                    {
                        ret = get_universe_structures_holder.get(filter);
                        if (ret == null) {
                            ObservableList<Long> holder = FXCollections.observableArrayList();
                            ret = (cache).toHolder(holder);
                            get_universe_structures_holder.put(filter, ret);
                            ObsListHolder<Long> finalRet = ret;
                            (cache).addFetchCacheArray("get_universe_structures", (page, properties) -> (cache.swagger).get_universe_structures(filter, properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_structures_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_structures_holder);
            }
        }
        return ret;
    }

    /**
     * Get the number of jumps in solar systems within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with jumps will be listed
     * 
     * cache over {@link Swagger#get_universe_system_jumps}<br />
     */
    public ObsListHolder<R_get_universe_system_jumps> system_jumps() {
        if (get_universe_system_jumps_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_system_jumps_holder == null) {
                            ObservableList<R_get_universe_system_jumps> holder = FXCollections.observableArrayList();
                            get_universe_system_jumps_holder = (cache).toHolder(holder);
                            ObsListHolder<R_get_universe_system_jumps> finalRet = get_universe_system_jumps_holder;
                            (cache).addFetchCacheArray("get_universe_system_jumps", (page, properties) -> (cache.swagger).get_universe_system_jumps(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_universe_system_jumps_holder;
    }

    /**
     * Get a list of solar systems
     * 
     * cache over {@link Swagger#get_universe_systems}<br />
     */
    public ObsListHolder<Integer> systems() {
        if (get_universe_systems_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_systems_holder == null) {
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_universe_systems_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_universe_systems_holder;
                            (cache).addFetchCacheArray("get_universe_systems", (page, properties) -> (cache.swagger).get_universe_systems(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_universe_systems_holder;
    }

    /**
     * Get a list of type ids
     * 
     * cache over {@link Swagger#get_universe_types}<br />
     */
    public ObsListHolder<Integer> types() {
        if (get_universe_types_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_types_holder == null) {
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_universe_types_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_universe_types_holder;
                            (cache).addFetchCacheArray("get_universe_types", (page, properties) -> (cache.swagger).get_universe_types(page, properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_universe_types_holder;
    }

    /**
     * Get a list of factions
     * 
     * cache over {@link Swagger#get_universe_factions}<br />
     */
    public ObsListHolder<R_get_universe_factions> factions() {
        if (get_universe_factions_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_factions_holder == null) {
                            ObservableList<R_get_universe_factions> holder = FXCollections.observableArrayList();
                            get_universe_factions_holder = (cache).toHolder(holder);
                            ObsListHolder<R_get_universe_factions> finalRet = get_universe_factions_holder;
                            (cache).addFetchCacheArray("get_universe_factions", (page, properties) -> (cache.swagger).get_universe_factions(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObsObjHolder<R_get_universe_stations_station_id> stations(int station_id) {
        ObsObjHolder<R_get_universe_stations_station_id> ret = get_universe_stations_station_id_holder.get(station_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_stations_station_id_holder);
            try {
                synchronized (get_universe_stations_station_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_stations_station_id_holder);
                    {
                        ret = get_universe_stations_station_id_holder.get(station_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_stations_station_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_stations_station_id_holder.put(station_id, ret);
                            (cache).addFetchCacheObject("get_universe_stations_station_id", properties -> (cache.swagger).get_universe_stations(station_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_stations_station_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_stations_station_id_holder);
            }
        }
        return ret;
    }

    /**
     * Get the number of ship, pod and NPC kills per solar system within the last hour ending at the timestamp of the Last-Modified header, excluding wormhole space. Only systems with kills will be listed
     * 
     * cache over {@link Swagger#get_universe_system_kills}<br />
     */
    public ObsListHolder<R_get_universe_system_kills> system_kills() {
        if (get_universe_system_kills_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_universe_system_kills_holder == null) {
                            ObservableList<R_get_universe_system_kills> holder = FXCollections.observableArrayList();
                            get_universe_system_kills_holder = (cache).toHolder(holder);
                            ObsListHolder<R_get_universe_system_kills> finalRet = get_universe_system_kills_holder;
                            (cache).addFetchCacheArray("get_universe_system_kills", (page, properties) -> (cache.swagger).get_universe_system_kills(properties), arr -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.clear();
                                            if (arr!= null) {
                                                holder.addAll(arr);
                                            }
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                                finalRet.dataReceived();
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
    public ObsObjHolder<R_get_universe_types_type_id> types(int type_id) {
        ObsObjHolder<R_get_universe_types_type_id> ret = get_universe_types_type_id_holder.get(type_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_types_type_id_holder);
            try {
                synchronized (get_universe_types_type_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_types_type_id_holder);
                    {
                        ret = get_universe_types_type_id_holder.get(type_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_types_type_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_types_type_id_holder.put(type_id, ret);
                            (cache).addFetchCacheObject("get_universe_types_type_id", properties -> (cache.swagger).get_universe_types(type_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_types_type_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_types_type_id_holder);
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
    public ObsObjHolder<R_get_universe_systems_system_id> systems(int system_id) {
        ObsObjHolder<R_get_universe_systems_system_id> ret = get_universe_systems_system_id_holder.get(system_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_universe_systems_system_id_holder);
            try {
                synchronized (get_universe_systems_system_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_universe_systems_system_id_holder);
                    {
                        ret = get_universe_systems_system_id_holder.get(system_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_universe_systems_system_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_universe_systems_system_id_holder.put(system_id, ret);
                            (cache).addFetchCacheObject("get_universe_systems_system_id", properties -> (cache.swagger).get_universe_systems(system_id, properties), item -> {
                                LockWatchDog.BARKER.tak(holder);
                                try {
                                    synchronized (holder)
                                    {
                                        LockWatchDog.BARKER.hld(holder);
                                        {
                                            holder.set(item);
                                        }
                                        LockWatchDog.BARKER.rel(holder);
                                    }
                                } finally {
                                    LockWatchDog.BARKER.rel(holder);
                                }
                            }
                            );
                        }
                    }
                    LockWatchDog.BARKER.rel(get_universe_systems_system_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_universe_systems_system_id_holder);
            }
        }
        return ret;
    }
}
