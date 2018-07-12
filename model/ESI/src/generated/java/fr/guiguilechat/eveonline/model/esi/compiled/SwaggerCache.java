package fr.guiguilechat.eveonline.model.esi.compiled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.M_get_fw_leaderboards_2;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_alliances_alliance_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_alliances_alliance_id_icons;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_attributes;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_clones;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fatigue;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fleet;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_fw_stats;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_location;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_mail_labels;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_online;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_portrait;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_ship;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_characters_character_id_skills;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_divisions;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_fw_stats;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_corporations_corporation_id_icons;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_dogma_effects_effect_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fleets_fleet_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_markets_groups_market_group_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_groups_group_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_tasks_task_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_status;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_categories_category_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_constellations_constellation_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_graphics_graphic_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_groups_group_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_moons_moon_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_planets_planet_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_regions_region_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_schematics_schematic_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stargates_stargate_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stars_star_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_structures_structure_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_systems_system_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_wars_war_id;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


/**
 * @param T
 *     the type of Swagger this refers to. this parameter allows to work on specific implementation of Swagger, thus call its methdos instead of having to cast.
 */
public abstract class SwaggerCache<T extends Swagger> {
    public final T swagger;
    public final Alliances alliances = new Alliances();
    public final Characters characters = new Characters();
    public final Corporation corporation = new Corporation();
    public final Corporations corporations = new Corporations();
    public final Dogma dogma = new Dogma();
    public final Fleets fleets = new Fleets();
    public final Fw fw = new Fw();
    public final Incursions incursions = new Incursions();
    public final Industry industry = new Industry();
    public final Insurance insurance = new Insurance();
    public final Killmails killmails = new Killmails();
    public final Loyalty loyalty = new Loyalty();
    public final Markets markets = new Markets();
    public final Opportunities opportunities = new Opportunities();
    public final Route route = new Route();
    public final Sovereignty sovereignty = new Sovereignty();
    public final Status status = new Status();
    public final Universe universe = new Universe();
    public final Wars wars = new Wars();
    public final Search search = new Search();

    public SwaggerCache(T swag) {
        swagger = swag;
    }

    public abstract<U> SwaggerCache.Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>> , U[]> fetcher, Consumer<List<U>> cacheHandler, String... requiredRoles);

    public abstract<U> SwaggerCache.Pausable addFetchCacheObject(String name, Function<Map<String, List<String>> , U> fetcher, Consumer<U> cacheHandler, String... requiredRoles);

    public class Alliances {
        private HashMap<Integer, SimpleObjectProperty<R_get_alliances_alliance_id_icons>> get_alliances_alliance_id_icons_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_alliances_alliance_id>> get_alliances_alliance_id_container = new HashMap<>();

        public Property<R_get_alliances_alliance_id_icons> get_alliances_alliance_id_icons(int alliance_id) {
            SimpleObjectProperty<R_get_alliances_alliance_id_icons> holder = get_alliances_alliance_id_icons_container.get(alliance_id);
            if (holder == null) {
                synchronized (get_alliances_alliance_id_icons_container)
                {
                    holder = get_alliances_alliance_id_icons_container.get(alliance_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_alliances_alliance_id_icons_container.put(alliance_id, holder);
                        SimpleObjectProperty<R_get_alliances_alliance_id_icons> holderf = holder;
                        addFetchCacheObject("get_alliances_alliance_id_icons", (header->swagger.get_alliances_alliance_id_icons(alliance_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_alliances_alliance_id> get_alliances_alliance_id(int alliance_id) {
            SimpleObjectProperty<R_get_alliances_alliance_id> holder = get_alliances_alliance_id_container.get(alliance_id);
            if (holder == null) {
                synchronized (get_alliances_alliance_id_container)
                {
                    holder = get_alliances_alliance_id_container.get(alliance_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_alliances_alliance_id_container.put(alliance_id, holder);
                        SimpleObjectProperty<R_get_alliances_alliance_id> holderf = holder;
                        addFetchCacheObject("get_alliances_alliance_id", (header->swagger.get_alliances_alliance_id(alliance_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }

    public class Characters {
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_attributes>> get_characters_character_id_attributes_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_fatigue>> get_characters_character_id_fatigue_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_fleet>> get_characters_character_id_fleet_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_fw_stats>> get_characters_character_id_fw_stats_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_location>> get_characters_character_id_location_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_ship>> get_characters_character_id_ship_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<Double>> get_characters_character_id_wallet_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_online>> get_characters_character_id_online_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_portrait>> get_characters_character_id_portrait_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_roles>> get_characters_character_id_roles_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_clones>> get_characters_character_id_clones_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_mail_labels>> get_characters_character_id_mail_labels_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id>> get_characters_character_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_characters_character_id_skills>> get_characters_character_id_skills_container = new HashMap<>();

        public Property<R_get_characters_character_id_attributes> get_characters_character_id_attributes(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_attributes> holder = get_characters_character_id_attributes_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_attributes_container)
                {
                    holder = get_characters_character_id_attributes_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_attributes_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_attributes> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_attributes", (header->swagger.get_characters_character_id_attributes(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_fatigue> get_characters_character_id_fatigue(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_fatigue> holder = get_characters_character_id_fatigue_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_fatigue_container)
                {
                    holder = get_characters_character_id_fatigue_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_fatigue_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_fatigue> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_fatigue", (header->swagger.get_characters_character_id_fatigue(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_fleet> get_characters_character_id_fleet(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_fleet> holder = get_characters_character_id_fleet_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_fleet_container)
                {
                    holder = get_characters_character_id_fleet_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_fleet_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_fleet> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_fleet", (header->swagger.get_characters_character_id_fleet(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_fw_stats> get_characters_character_id_fw_stats(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_fw_stats> holder = get_characters_character_id_fw_stats_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_fw_stats_container)
                {
                    holder = get_characters_character_id_fw_stats_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_fw_stats_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_fw_stats> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_fw_stats", (header->swagger.get_characters_character_id_fw_stats(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_location> get_characters_character_id_location(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_location> holder = get_characters_character_id_location_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_location_container)
                {
                    holder = get_characters_character_id_location_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_location_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_location> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_location", (header->swagger.get_characters_character_id_location(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_ship> get_characters_character_id_ship(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_ship> holder = get_characters_character_id_ship_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_ship_container)
                {
                    holder = get_characters_character_id_ship_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_ship_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_ship> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_ship", (header->swagger.get_characters_character_id_ship(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<Double> get_characters_character_id_wallet(int character_id) {
            SimpleObjectProperty<Double> holder = get_characters_character_id_wallet_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_wallet_container)
                {
                    holder = get_characters_character_id_wallet_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_wallet_container.put(character_id, holder);
                        SimpleObjectProperty<Double> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_wallet", (header->swagger.get_characters_character_id_wallet(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_online> get_characters_character_id_online(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_online> holder = get_characters_character_id_online_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_online_container)
                {
                    holder = get_characters_character_id_online_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_online_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_online> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_online", (header->swagger.get_characters_character_id_online(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_portrait> get_characters_character_id_portrait(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_portrait> holder = get_characters_character_id_portrait_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_portrait_container)
                {
                    holder = get_characters_character_id_portrait_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_portrait_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_portrait> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_portrait", (header->swagger.get_characters_character_id_portrait(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_roles> get_characters_character_id_roles(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_roles> holder = get_characters_character_id_roles_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_roles_container)
                {
                    holder = get_characters_character_id_roles_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_roles_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_roles> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_roles", (header->swagger.get_characters_character_id_roles(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_clones> get_characters_character_id_clones(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_clones> holder = get_characters_character_id_clones_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_clones_container)
                {
                    holder = get_characters_character_id_clones_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_clones_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_clones> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_clones", (header->swagger.get_characters_character_id_clones(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_mail_labels> get_characters_character_id_mail_labels(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_mail_labels> holder = get_characters_character_id_mail_labels_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_mail_labels_container)
                {
                    holder = get_characters_character_id_mail_labels_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_mail_labels_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_mail_labels> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_mail_labels", (header->swagger.get_characters_character_id_mail_labels(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id> get_characters_character_id(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id> holder = get_characters_character_id_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_container)
                {
                    holder = get_characters_character_id_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id> holderf = holder;
                        addFetchCacheObject("get_characters_character_id", (header->swagger.get_characters_character_id(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_skills> get_characters_character_id_skills(int character_id) {
            SimpleObjectProperty<R_get_characters_character_id_skills> holder = get_characters_character_id_skills_container.get(character_id);
            if (holder == null) {
                synchronized (get_characters_character_id_skills_container)
                {
                    holder = get_characters_character_id_skills_container.get(character_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_skills_container.put(character_id, holder);
                        SimpleObjectProperty<R_get_characters_character_id_skills> holderf = holder;
                        addFetchCacheObject("get_characters_character_id_skills", (header->swagger.get_characters_character_id_skills(character_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }

    public class Corporation {
    }

    public class Corporations {
        private HashMap<Integer, SimpleObjectProperty<R_get_corporations_corporation_id_divisions>> get_corporations_corporation_id_divisions_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_corporations_corporation_id_fw_stats>> get_corporations_corporation_id_fw_stats_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_corporations_corporation_id_icons>> get_corporations_corporation_id_icons_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<Integer>> get_corporations_corporation_id_members_limit_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_corporations_corporation_id>> get_corporations_corporation_id_container = new HashMap<>();

        public Property<R_get_corporations_corporation_id_divisions> get_corporations_corporation_id_divisions(int corporation_id) {
            SimpleObjectProperty<R_get_corporations_corporation_id_divisions> holder = get_corporations_corporation_id_divisions_container.get(corporation_id);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_divisions_container)
                {
                    holder = get_corporations_corporation_id_divisions_container.get(corporation_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_divisions_container.put(corporation_id, holder);
                        SimpleObjectProperty<R_get_corporations_corporation_id_divisions> holderf = holder;
                        addFetchCacheObject("get_corporations_corporation_id_divisions", (header->swagger.get_corporations_corporation_id_divisions(corporation_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}), new String[] {"Director"});
                    }
                }
            }
            return holder;
        }

        public Property<R_get_corporations_corporation_id_fw_stats> get_corporations_corporation_id_fw_stats(int corporation_id) {
            SimpleObjectProperty<R_get_corporations_corporation_id_fw_stats> holder = get_corporations_corporation_id_fw_stats_container.get(corporation_id);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_fw_stats_container)
                {
                    holder = get_corporations_corporation_id_fw_stats_container.get(corporation_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_fw_stats_container.put(corporation_id, holder);
                        SimpleObjectProperty<R_get_corporations_corporation_id_fw_stats> holderf = holder;
                        addFetchCacheObject("get_corporations_corporation_id_fw_stats", (header->swagger.get_corporations_corporation_id_fw_stats(corporation_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_corporations_corporation_id_icons> get_corporations_corporation_id_icons(int corporation_id) {
            SimpleObjectProperty<R_get_corporations_corporation_id_icons> holder = get_corporations_corporation_id_icons_container.get(corporation_id);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_icons_container)
                {
                    holder = get_corporations_corporation_id_icons_container.get(corporation_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_icons_container.put(corporation_id, holder);
                        SimpleObjectProperty<R_get_corporations_corporation_id_icons> holderf = holder;
                        addFetchCacheObject("get_corporations_corporation_id_icons", (header->swagger.get_corporations_corporation_id_icons(corporation_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<Integer> get_corporations_corporation_id_members_limit(int corporation_id) {
            SimpleObjectProperty<Integer> holder = get_corporations_corporation_id_members_limit_container.get(corporation_id);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_members_limit_container)
                {
                    holder = get_corporations_corporation_id_members_limit_container.get(corporation_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_members_limit_container.put(corporation_id, holder);
                        SimpleObjectProperty<Integer> holderf = holder;
                        addFetchCacheObject("get_corporations_corporation_id_members_limit", (header->swagger.get_corporations_corporation_id_members_limit(corporation_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}), new String[] {"Director"});
                    }
                }
            }
            return holder;
        }

        public Property<R_get_corporations_corporation_id> get_corporations_corporation_id(int corporation_id) {
            SimpleObjectProperty<R_get_corporations_corporation_id> holder = get_corporations_corporation_id_container.get(corporation_id);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_container)
                {
                    holder = get_corporations_corporation_id_container.get(corporation_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_container.put(corporation_id, holder);
                        SimpleObjectProperty<R_get_corporations_corporation_id> holderf = holder;
                        addFetchCacheObject("get_corporations_corporation_id", (header->swagger.get_corporations_corporation_id(corporation_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }

    public class Dogma {
        private HashMap<Integer, SimpleObjectProperty<R_get_dogma_attributes_attribute_id>> get_dogma_attributes_attribute_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_dogma_effects_effect_id>> get_dogma_effects_effect_id_container = new HashMap<>();

        public Property<R_get_dogma_attributes_attribute_id> get_dogma_attributes_attribute_id(int attribute_id) {
            SimpleObjectProperty<R_get_dogma_attributes_attribute_id> holder = get_dogma_attributes_attribute_id_container.get(attribute_id);
            if (holder == null) {
                synchronized (get_dogma_attributes_attribute_id_container)
                {
                    holder = get_dogma_attributes_attribute_id_container.get(attribute_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_dogma_attributes_attribute_id_container.put(attribute_id, holder);
                        SimpleObjectProperty<R_get_dogma_attributes_attribute_id> holderf = holder;
                        addFetchCacheObject("get_dogma_attributes_attribute_id", (header->swagger.get_dogma_attributes_attribute_id(attribute_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_dogma_effects_effect_id> get_dogma_effects_effect_id(int effect_id) {
            SimpleObjectProperty<R_get_dogma_effects_effect_id> holder = get_dogma_effects_effect_id_container.get(effect_id);
            if (holder == null) {
                synchronized (get_dogma_effects_effect_id_container)
                {
                    holder = get_dogma_effects_effect_id_container.get(effect_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_dogma_effects_effect_id_container.put(effect_id, holder);
                        SimpleObjectProperty<R_get_dogma_effects_effect_id> holderf = holder;
                        addFetchCacheObject("get_dogma_effects_effect_id", (header->swagger.get_dogma_effects_effect_id(effect_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }

    public class Fleets {
        private HashMap<Long, SimpleObjectProperty<R_get_fleets_fleet_id>> get_fleets_fleet_id_container = new HashMap<>();

        public Property<R_get_fleets_fleet_id> get_fleets_fleet_id(long fleet_id) {
            SimpleObjectProperty<R_get_fleets_fleet_id> holder = get_fleets_fleet_id_container.get(fleet_id);
            if (holder == null) {
                synchronized (get_fleets_fleet_id_container)
                {
                    holder = get_fleets_fleet_id_container.get(fleet_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_fleets_fleet_id_container.put(fleet_id, holder);
                        SimpleObjectProperty<R_get_fleets_fleet_id> holderf = holder;
                        addFetchCacheObject("get_fleets_fleet_id", (header->swagger.get_fleets_fleet_id(fleet_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }

    public class Fw {
        private SimpleObjectProperty<M_get_fw_leaderboards_2> get_fw_leaderboards_container = null;
        private SimpleObjectProperty<M_get_fw_leaderboards_2> get_fw_leaderboards_characters_container = null;
        private SimpleObjectProperty<M_get_fw_leaderboards_2> get_fw_leaderboards_corporations_container = null;

        public Property<M_get_fw_leaderboards_2> get_fw_leaderboards() {
            if (get_fw_leaderboards_container == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_container == null) {
                        get_fw_leaderboards_container = new SimpleObjectProperty<>();
                        addFetchCacheObject("get_fw_leaderboards", (m->swagger.get_fw_leaderboards(m)), (get_fw_leaderboards_container::set));
                    }
                }
            }
            return get_fw_leaderboards_container;
        }

        public Property<M_get_fw_leaderboards_2> get_fw_leaderboards_characters() {
            if (get_fw_leaderboards_characters_container == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_characters_container == null) {
                        get_fw_leaderboards_characters_container = new SimpleObjectProperty<>();
                        addFetchCacheObject("get_fw_leaderboards_characters", (m->swagger.get_fw_leaderboards_characters(m)), (get_fw_leaderboards_characters_container::set));
                    }
                }
            }
            return get_fw_leaderboards_characters_container;
        }

        public Property<M_get_fw_leaderboards_2> get_fw_leaderboards_corporations() {
            if (get_fw_leaderboards_corporations_container == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_corporations_container == null) {
                        get_fw_leaderboards_corporations_container = new SimpleObjectProperty<>();
                        addFetchCacheObject("get_fw_leaderboards_corporations", (m->swagger.get_fw_leaderboards_corporations(m)), (get_fw_leaderboards_corporations_container::set));
                    }
                }
            }
            return get_fw_leaderboards_corporations_container;
        }
    }

    public class Incursions {
    }

    public class Industry {
    }

    public class Insurance {
    }

    public class Killmails {
    }

    public class Loyalty {
    }

    public class Markets {
        private HashMap<Integer, SimpleObjectProperty<R_get_markets_groups_market_group_id>> get_markets_groups_market_group_id_container = new HashMap<>();

        public Property<R_get_markets_groups_market_group_id> get_markets_groups_market_group_id(int market_group_id) {
            SimpleObjectProperty<R_get_markets_groups_market_group_id> holder = get_markets_groups_market_group_id_container.get(market_group_id);
            if (holder == null) {
                synchronized (get_markets_groups_market_group_id_container)
                {
                    holder = get_markets_groups_market_group_id_container.get(market_group_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_markets_groups_market_group_id_container.put(market_group_id, holder);
                        SimpleObjectProperty<R_get_markets_groups_market_group_id> holderf = holder;
                        addFetchCacheObject("get_markets_groups_market_group_id", (header->swagger.get_markets_groups_market_group_id(market_group_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }

    public class Opportunities {
        private HashMap<Integer, SimpleObjectProperty<R_get_opportunities_groups_group_id>> get_opportunities_groups_group_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_opportunities_tasks_task_id>> get_opportunities_tasks_task_id_container = new HashMap<>();

        public Property<R_get_opportunities_groups_group_id> get_opportunities_groups_group_id(int group_id) {
            SimpleObjectProperty<R_get_opportunities_groups_group_id> holder = get_opportunities_groups_group_id_container.get(group_id);
            if (holder == null) {
                synchronized (get_opportunities_groups_group_id_container)
                {
                    holder = get_opportunities_groups_group_id_container.get(group_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_opportunities_groups_group_id_container.put(group_id, holder);
                        SimpleObjectProperty<R_get_opportunities_groups_group_id> holderf = holder;
                        addFetchCacheObject("get_opportunities_groups_group_id", (header->swagger.get_opportunities_groups_group_id(group_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_opportunities_tasks_task_id> get_opportunities_tasks_task_id(int task_id) {
            SimpleObjectProperty<R_get_opportunities_tasks_task_id> holder = get_opportunities_tasks_task_id_container.get(task_id);
            if (holder == null) {
                synchronized (get_opportunities_tasks_task_id_container)
                {
                    holder = get_opportunities_tasks_task_id_container.get(task_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_opportunities_tasks_task_id_container.put(task_id, holder);
                        SimpleObjectProperty<R_get_opportunities_tasks_task_id> holderf = holder;
                        addFetchCacheObject("get_opportunities_tasks_task_id", (header->swagger.get_opportunities_tasks_task_id(task_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }

    public interface Pausable {

        public void pause();

        public void resume();
    }

    public class Route {
    }

    public class Search {
    }

    public class Sovereignty {
    }

    public class Status {
        private SimpleObjectProperty<R_get_status> get_status_container = null;

        public Property<R_get_status> get_status() {
            if (get_status_container == null) {
                synchronized (this)
                {
                    if (get_status_container == null) {
                        get_status_container = new SimpleObjectProperty<>();
                        addFetchCacheObject("get_status", (m->swagger.get_status(m)), (get_status_container::set));
                    }
                }
            }
            return get_status_container;
        }
    }

    public class Universe {
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_asteroid_belts_asteroid_belt_id>> get_universe_asteroid_belts_asteroid_belt_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_categories_category_id>> get_universe_categories_category_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_constellations_constellation_id>> get_universe_constellations_constellation_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_graphics_graphic_id>> get_universe_graphics_graphic_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_groups_group_id>> get_universe_groups_group_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_moons_moon_id>> get_universe_moons_moon_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_planets_planet_id>> get_universe_planets_planet_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_regions_region_id>> get_universe_regions_region_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_schematics_schematic_id>> get_universe_schematics_schematic_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_stargates_stargate_id>> get_universe_stargates_stargate_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_stars_star_id>> get_universe_stars_star_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_stations_station_id>> get_universe_stations_station_id_container = new HashMap<>();
        private HashMap<Long, SimpleObjectProperty<R_get_universe_structures_structure_id>> get_universe_structures_structure_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_types_type_id>> get_universe_types_type_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_systems_system_id>> get_universe_systems_system_id_container = new HashMap<>();

        public Property<R_get_universe_asteroid_belts_asteroid_belt_id> get_universe_asteroid_belts_asteroid_belt_id(int asteroid_belt_id) {
            SimpleObjectProperty<R_get_universe_asteroid_belts_asteroid_belt_id> holder = get_universe_asteroid_belts_asteroid_belt_id_container.get(asteroid_belt_id);
            if (holder == null) {
                synchronized (get_universe_asteroid_belts_asteroid_belt_id_container)
                {
                    holder = get_universe_asteroid_belts_asteroid_belt_id_container.get(asteroid_belt_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_asteroid_belts_asteroid_belt_id_container.put(asteroid_belt_id, holder);
                        SimpleObjectProperty<R_get_universe_asteroid_belts_asteroid_belt_id> holderf = holder;
                        addFetchCacheObject("get_universe_asteroid_belts_asteroid_belt_id", (header->swagger.get_universe_asteroid_belts_asteroid_belt_id(asteroid_belt_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_categories_category_id> get_universe_categories_category_id(int category_id) {
            SimpleObjectProperty<R_get_universe_categories_category_id> holder = get_universe_categories_category_id_container.get(category_id);
            if (holder == null) {
                synchronized (get_universe_categories_category_id_container)
                {
                    holder = get_universe_categories_category_id_container.get(category_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_categories_category_id_container.put(category_id, holder);
                        SimpleObjectProperty<R_get_universe_categories_category_id> holderf = holder;
                        addFetchCacheObject("get_universe_categories_category_id", (header->swagger.get_universe_categories_category_id(category_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_constellations_constellation_id> get_universe_constellations_constellation_id(int constellation_id) {
            SimpleObjectProperty<R_get_universe_constellations_constellation_id> holder = get_universe_constellations_constellation_id_container.get(constellation_id);
            if (holder == null) {
                synchronized (get_universe_constellations_constellation_id_container)
                {
                    holder = get_universe_constellations_constellation_id_container.get(constellation_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_constellations_constellation_id_container.put(constellation_id, holder);
                        SimpleObjectProperty<R_get_universe_constellations_constellation_id> holderf = holder;
                        addFetchCacheObject("get_universe_constellations_constellation_id", (header->swagger.get_universe_constellations_constellation_id(constellation_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_graphics_graphic_id> get_universe_graphics_graphic_id(int graphic_id) {
            SimpleObjectProperty<R_get_universe_graphics_graphic_id> holder = get_universe_graphics_graphic_id_container.get(graphic_id);
            if (holder == null) {
                synchronized (get_universe_graphics_graphic_id_container)
                {
                    holder = get_universe_graphics_graphic_id_container.get(graphic_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_graphics_graphic_id_container.put(graphic_id, holder);
                        SimpleObjectProperty<R_get_universe_graphics_graphic_id> holderf = holder;
                        addFetchCacheObject("get_universe_graphics_graphic_id", (header->swagger.get_universe_graphics_graphic_id(graphic_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_groups_group_id> get_universe_groups_group_id(int group_id) {
            SimpleObjectProperty<R_get_universe_groups_group_id> holder = get_universe_groups_group_id_container.get(group_id);
            if (holder == null) {
                synchronized (get_universe_groups_group_id_container)
                {
                    holder = get_universe_groups_group_id_container.get(group_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_groups_group_id_container.put(group_id, holder);
                        SimpleObjectProperty<R_get_universe_groups_group_id> holderf = holder;
                        addFetchCacheObject("get_universe_groups_group_id", (header->swagger.get_universe_groups_group_id(group_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_moons_moon_id> get_universe_moons_moon_id(int moon_id) {
            SimpleObjectProperty<R_get_universe_moons_moon_id> holder = get_universe_moons_moon_id_container.get(moon_id);
            if (holder == null) {
                synchronized (get_universe_moons_moon_id_container)
                {
                    holder = get_universe_moons_moon_id_container.get(moon_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_moons_moon_id_container.put(moon_id, holder);
                        SimpleObjectProperty<R_get_universe_moons_moon_id> holderf = holder;
                        addFetchCacheObject("get_universe_moons_moon_id", (header->swagger.get_universe_moons_moon_id(moon_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_planets_planet_id> get_universe_planets_planet_id(int planet_id) {
            SimpleObjectProperty<R_get_universe_planets_planet_id> holder = get_universe_planets_planet_id_container.get(planet_id);
            if (holder == null) {
                synchronized (get_universe_planets_planet_id_container)
                {
                    holder = get_universe_planets_planet_id_container.get(planet_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_planets_planet_id_container.put(planet_id, holder);
                        SimpleObjectProperty<R_get_universe_planets_planet_id> holderf = holder;
                        addFetchCacheObject("get_universe_planets_planet_id", (header->swagger.get_universe_planets_planet_id(planet_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_regions_region_id> get_universe_regions_region_id(int region_id) {
            SimpleObjectProperty<R_get_universe_regions_region_id> holder = get_universe_regions_region_id_container.get(region_id);
            if (holder == null) {
                synchronized (get_universe_regions_region_id_container)
                {
                    holder = get_universe_regions_region_id_container.get(region_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_regions_region_id_container.put(region_id, holder);
                        SimpleObjectProperty<R_get_universe_regions_region_id> holderf = holder;
                        addFetchCacheObject("get_universe_regions_region_id", (header->swagger.get_universe_regions_region_id(region_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_schematics_schematic_id> get_universe_schematics_schematic_id(int schematic_id) {
            SimpleObjectProperty<R_get_universe_schematics_schematic_id> holder = get_universe_schematics_schematic_id_container.get(schematic_id);
            if (holder == null) {
                synchronized (get_universe_schematics_schematic_id_container)
                {
                    holder = get_universe_schematics_schematic_id_container.get(schematic_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_schematics_schematic_id_container.put(schematic_id, holder);
                        SimpleObjectProperty<R_get_universe_schematics_schematic_id> holderf = holder;
                        addFetchCacheObject("get_universe_schematics_schematic_id", (header->swagger.get_universe_schematics_schematic_id(schematic_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_stargates_stargate_id> get_universe_stargates_stargate_id(int stargate_id) {
            SimpleObjectProperty<R_get_universe_stargates_stargate_id> holder = get_universe_stargates_stargate_id_container.get(stargate_id);
            if (holder == null) {
                synchronized (get_universe_stargates_stargate_id_container)
                {
                    holder = get_universe_stargates_stargate_id_container.get(stargate_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_stargates_stargate_id_container.put(stargate_id, holder);
                        SimpleObjectProperty<R_get_universe_stargates_stargate_id> holderf = holder;
                        addFetchCacheObject("get_universe_stargates_stargate_id", (header->swagger.get_universe_stargates_stargate_id(stargate_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_stars_star_id> get_universe_stars_star_id(int star_id) {
            SimpleObjectProperty<R_get_universe_stars_star_id> holder = get_universe_stars_star_id_container.get(star_id);
            if (holder == null) {
                synchronized (get_universe_stars_star_id_container)
                {
                    holder = get_universe_stars_star_id_container.get(star_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_stars_star_id_container.put(star_id, holder);
                        SimpleObjectProperty<R_get_universe_stars_star_id> holderf = holder;
                        addFetchCacheObject("get_universe_stars_star_id", (header->swagger.get_universe_stars_star_id(star_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_stations_station_id> get_universe_stations_station_id(int station_id) {
            SimpleObjectProperty<R_get_universe_stations_station_id> holder = get_universe_stations_station_id_container.get(station_id);
            if (holder == null) {
                synchronized (get_universe_stations_station_id_container)
                {
                    holder = get_universe_stations_station_id_container.get(station_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_stations_station_id_container.put(station_id, holder);
                        SimpleObjectProperty<R_get_universe_stations_station_id> holderf = holder;
                        addFetchCacheObject("get_universe_stations_station_id", (header->swagger.get_universe_stations_station_id(station_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_structures_structure_id> get_universe_structures_structure_id(long structure_id) {
            SimpleObjectProperty<R_get_universe_structures_structure_id> holder = get_universe_structures_structure_id_container.get(structure_id);
            if (holder == null) {
                synchronized (get_universe_structures_structure_id_container)
                {
                    holder = get_universe_structures_structure_id_container.get(structure_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_structures_structure_id_container.put(structure_id, holder);
                        SimpleObjectProperty<R_get_universe_structures_structure_id> holderf = holder;
                        addFetchCacheObject("get_universe_structures_structure_id", (header->swagger.get_universe_structures_structure_id(structure_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_types_type_id> get_universe_types_type_id(int type_id) {
            SimpleObjectProperty<R_get_universe_types_type_id> holder = get_universe_types_type_id_container.get(type_id);
            if (holder == null) {
                synchronized (get_universe_types_type_id_container)
                {
                    holder = get_universe_types_type_id_container.get(type_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_types_type_id_container.put(type_id, holder);
                        SimpleObjectProperty<R_get_universe_types_type_id> holderf = holder;
                        addFetchCacheObject("get_universe_types_type_id", (header->swagger.get_universe_types_type_id(type_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_systems_system_id> get_universe_systems_system_id(int system_id) {
            SimpleObjectProperty<R_get_universe_systems_system_id> holder = get_universe_systems_system_id_container.get(system_id);
            if (holder == null) {
                synchronized (get_universe_systems_system_id_container)
                {
                    holder = get_universe_systems_system_id_container.get(system_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_systems_system_id_container.put(system_id, holder);
                        SimpleObjectProperty<R_get_universe_systems_system_id> holderf = holder;
                        addFetchCacheObject("get_universe_systems_system_id", (header->swagger.get_universe_systems_system_id(system_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }

    public class Wars {
        private HashMap<Integer, SimpleObjectProperty<R_get_wars_war_id>> get_wars_war_id_container = new HashMap<>();

        public Property<R_get_wars_war_id> get_wars_war_id(int war_id) {
            SimpleObjectProperty<R_get_wars_war_id> holder = get_wars_war_id_container.get(war_id);
            if (holder == null) {
                synchronized (get_wars_war_id_container)
                {
                    holder = get_wars_war_id_container.get(war_id);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_wars_war_id_container.put(war_id, holder);
                        SimpleObjectProperty<R_get_wars_war_id> holderf = holder;
                        addFetchCacheObject("get_wars_war_id", (header->swagger.get_wars_war_id(war_id, header)), (o-> {synchronized(holderf){holderf.set(o);}}));
                    }
                }
            }
            return holder;
        }
    }
}
