package fr.guiguilechat.eveonline.model.esi.compiled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
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
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_leaderboards;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_leaderboards_characters;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_leaderboards_corporations;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_tasks_task_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_status;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_asteroid_belts_asteroid_belt_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_graphics_graphic_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_moons_moon_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_planets_planet_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_schematics_schematic_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stargates_stargate_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stars_star_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_stations_station_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_universe_structures_structure_id;
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
    public final Corporations corporations = new Corporations();
    public final Dogma dogma = new Dogma();
    public final Fleets fleets = new Fleets();
    public final Fw fw = new Fw();
    public final Incursions incursions = new Incursions();
    public final Industry industry = new Industry();
    public final Insurance insurance = new Insurance();
    public final Loyalty loyalty = new Loyalty();
    public final Markets markets = new Markets();
    public final Opportunities opportunities = new Opportunities();
    public final Sovereignty sovereignty = new Sovereignty();
    public final Status status = new Status();
    public final Universe universe = new Universe();
    public final Wars wars = new Wars();

    public SwaggerCache(T swag) {
        swagger = swag;
    }

    public abstract<U> SwaggerCache.Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>> , U[]> fetcher, Consumer<List<U>> cacheHandler, String... requiredRoles);

    public abstract<U> SwaggerCache.Pausable addFetchCacheObject(String name, Function<Map<String, List<String>> , U> fetcher, Consumer<U> cacheHandler, String... requiredRoles);

    public class Alliances {
        private HashMap<Integer, SimpleObjectProperty<R_get_alliances_alliance_id_icons>> get_alliances_alliance_id_icons_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_alliances_alliance_id>> get_alliances_alliance_id_container = new HashMap<>();

        public Property<R_get_alliances_alliance_id_icons> get_alliances_alliance_id_icons(int param) {
            SimpleObjectProperty<R_get_alliances_alliance_id_icons> holder = get_alliances_alliance_id_icons_container.get(param);
            if (holder == null) {
                synchronized (get_alliances_alliance_id_icons_container)
                {
                    holder = get_alliances_alliance_id_icons_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_alliances_alliance_id_icons_container.put(param, holder);
                        addFetchCacheObject("get_alliances_alliance_id_icons", (m->swagger.get_alliances_alliance_id_icons(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_alliances_alliance_id> get_alliances_alliance_id(int param) {
            SimpleObjectProperty<R_get_alliances_alliance_id> holder = get_alliances_alliance_id_container.get(param);
            if (holder == null) {
                synchronized (get_alliances_alliance_id_container)
                {
                    holder = get_alliances_alliance_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_alliances_alliance_id_container.put(param, holder);
                        addFetchCacheObject("get_alliances_alliance_id", (m->swagger.get_alliances_alliance_id(param, m)), (holder::set));
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

        public Property<R_get_characters_character_id_attributes> get_characters_character_id_attributes(int param) {
            SimpleObjectProperty<R_get_characters_character_id_attributes> holder = get_characters_character_id_attributes_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_attributes_container)
                {
                    holder = get_characters_character_id_attributes_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_attributes_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_attributes", (m->swagger.get_characters_character_id_attributes(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_fatigue> get_characters_character_id_fatigue(int param) {
            SimpleObjectProperty<R_get_characters_character_id_fatigue> holder = get_characters_character_id_fatigue_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_fatigue_container)
                {
                    holder = get_characters_character_id_fatigue_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_fatigue_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_fatigue", (m->swagger.get_characters_character_id_fatigue(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_fleet> get_characters_character_id_fleet(int param) {
            SimpleObjectProperty<R_get_characters_character_id_fleet> holder = get_characters_character_id_fleet_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_fleet_container)
                {
                    holder = get_characters_character_id_fleet_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_fleet_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_fleet", (m->swagger.get_characters_character_id_fleet(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_fw_stats> get_characters_character_id_fw_stats(int param) {
            SimpleObjectProperty<R_get_characters_character_id_fw_stats> holder = get_characters_character_id_fw_stats_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_fw_stats_container)
                {
                    holder = get_characters_character_id_fw_stats_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_fw_stats_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_fw_stats", (m->swagger.get_characters_character_id_fw_stats(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_location> get_characters_character_id_location(int param) {
            SimpleObjectProperty<R_get_characters_character_id_location> holder = get_characters_character_id_location_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_location_container)
                {
                    holder = get_characters_character_id_location_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_location_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_location", (m->swagger.get_characters_character_id_location(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_ship> get_characters_character_id_ship(int param) {
            SimpleObjectProperty<R_get_characters_character_id_ship> holder = get_characters_character_id_ship_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_ship_container)
                {
                    holder = get_characters_character_id_ship_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_ship_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_ship", (m->swagger.get_characters_character_id_ship(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<Double> get_characters_character_id_wallet(int param) {
            SimpleObjectProperty<Double> holder = get_characters_character_id_wallet_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_wallet_container)
                {
                    holder = get_characters_character_id_wallet_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_wallet_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_wallet", (m->swagger.get_characters_character_id_wallet(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_online> get_characters_character_id_online(int param) {
            SimpleObjectProperty<R_get_characters_character_id_online> holder = get_characters_character_id_online_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_online_container)
                {
                    holder = get_characters_character_id_online_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_online_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_online", (m->swagger.get_characters_character_id_online(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_portrait> get_characters_character_id_portrait(int param) {
            SimpleObjectProperty<R_get_characters_character_id_portrait> holder = get_characters_character_id_portrait_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_portrait_container)
                {
                    holder = get_characters_character_id_portrait_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_portrait_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_portrait", (m->swagger.get_characters_character_id_portrait(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_roles> get_characters_character_id_roles(int param) {
            SimpleObjectProperty<R_get_characters_character_id_roles> holder = get_characters_character_id_roles_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_roles_container)
                {
                    holder = get_characters_character_id_roles_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_roles_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_roles", (m->swagger.get_characters_character_id_roles(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_clones> get_characters_character_id_clones(int param) {
            SimpleObjectProperty<R_get_characters_character_id_clones> holder = get_characters_character_id_clones_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_clones_container)
                {
                    holder = get_characters_character_id_clones_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_clones_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_clones", (m->swagger.get_characters_character_id_clones(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_mail_labels> get_characters_character_id_mail_labels(int param) {
            SimpleObjectProperty<R_get_characters_character_id_mail_labels> holder = get_characters_character_id_mail_labels_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_mail_labels_container)
                {
                    holder = get_characters_character_id_mail_labels_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_mail_labels_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_mail_labels", (m->swagger.get_characters_character_id_mail_labels(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id> get_characters_character_id(int param) {
            SimpleObjectProperty<R_get_characters_character_id> holder = get_characters_character_id_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_container)
                {
                    holder = get_characters_character_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id", (m->swagger.get_characters_character_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_characters_character_id_skills> get_characters_character_id_skills(int param) {
            SimpleObjectProperty<R_get_characters_character_id_skills> holder = get_characters_character_id_skills_container.get(param);
            if (holder == null) {
                synchronized (get_characters_character_id_skills_container)
                {
                    holder = get_characters_character_id_skills_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_characters_character_id_skills_container.put(param, holder);
                        addFetchCacheObject("get_characters_character_id_skills", (m->swagger.get_characters_character_id_skills(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }
    }

    public class Corporations {
        private HashMap<Integer, SimpleObjectProperty<R_get_corporations_corporation_id_divisions>> get_corporations_corporation_id_divisions_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_corporations_corporation_id_fw_stats>> get_corporations_corporation_id_fw_stats_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_corporations_corporation_id_icons>> get_corporations_corporation_id_icons_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<Integer>> get_corporations_corporation_id_members_limit_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_corporations_corporation_id>> get_corporations_corporation_id_container = new HashMap<>();

        public Property<R_get_corporations_corporation_id_divisions> get_corporations_corporation_id_divisions(int param) {
            SimpleObjectProperty<R_get_corporations_corporation_id_divisions> holder = get_corporations_corporation_id_divisions_container.get(param);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_divisions_container)
                {
                    holder = get_corporations_corporation_id_divisions_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_divisions_container.put(param, holder);
                        addFetchCacheObject("get_corporations_corporation_id_divisions", (m->swagger.get_corporations_corporation_id_divisions(param, m)), (holder::set), new String[] {"Director"});
                    }
                }
            }
            return holder;
        }

        public Property<R_get_corporations_corporation_id_fw_stats> get_corporations_corporation_id_fw_stats(int param) {
            SimpleObjectProperty<R_get_corporations_corporation_id_fw_stats> holder = get_corporations_corporation_id_fw_stats_container.get(param);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_fw_stats_container)
                {
                    holder = get_corporations_corporation_id_fw_stats_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_fw_stats_container.put(param, holder);
                        addFetchCacheObject("get_corporations_corporation_id_fw_stats", (m->swagger.get_corporations_corporation_id_fw_stats(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_corporations_corporation_id_icons> get_corporations_corporation_id_icons(int param) {
            SimpleObjectProperty<R_get_corporations_corporation_id_icons> holder = get_corporations_corporation_id_icons_container.get(param);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_icons_container)
                {
                    holder = get_corporations_corporation_id_icons_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_icons_container.put(param, holder);
                        addFetchCacheObject("get_corporations_corporation_id_icons", (m->swagger.get_corporations_corporation_id_icons(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<Integer> get_corporations_corporation_id_members_limit(int param) {
            SimpleObjectProperty<Integer> holder = get_corporations_corporation_id_members_limit_container.get(param);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_members_limit_container)
                {
                    holder = get_corporations_corporation_id_members_limit_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_members_limit_container.put(param, holder);
                        addFetchCacheObject("get_corporations_corporation_id_members_limit", (m->swagger.get_corporations_corporation_id_members_limit(param, m)), (holder::set), new String[] {"Director"});
                    }
                }
            }
            return holder;
        }

        public Property<R_get_corporations_corporation_id> get_corporations_corporation_id(int param) {
            SimpleObjectProperty<R_get_corporations_corporation_id> holder = get_corporations_corporation_id_container.get(param);
            if (holder == null) {
                synchronized (get_corporations_corporation_id_container)
                {
                    holder = get_corporations_corporation_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_corporations_corporation_id_container.put(param, holder);
                        addFetchCacheObject("get_corporations_corporation_id", (m->swagger.get_corporations_corporation_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }
    }

    public class Dogma {
        private HashMap<Integer, SimpleObjectProperty<R_get_dogma_attributes_attribute_id>> get_dogma_attributes_attribute_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_dogma_effects_effect_id>> get_dogma_effects_effect_id_container = new HashMap<>();

        public Property<R_get_dogma_attributes_attribute_id> get_dogma_attributes_attribute_id(int param) {
            SimpleObjectProperty<R_get_dogma_attributes_attribute_id> holder = get_dogma_attributes_attribute_id_container.get(param);
            if (holder == null) {
                synchronized (get_dogma_attributes_attribute_id_container)
                {
                    holder = get_dogma_attributes_attribute_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_dogma_attributes_attribute_id_container.put(param, holder);
                        addFetchCacheObject("get_dogma_attributes_attribute_id", (m->swagger.get_dogma_attributes_attribute_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_dogma_effects_effect_id> get_dogma_effects_effect_id(int param) {
            SimpleObjectProperty<R_get_dogma_effects_effect_id> holder = get_dogma_effects_effect_id_container.get(param);
            if (holder == null) {
                synchronized (get_dogma_effects_effect_id_container)
                {
                    holder = get_dogma_effects_effect_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_dogma_effects_effect_id_container.put(param, holder);
                        addFetchCacheObject("get_dogma_effects_effect_id", (m->swagger.get_dogma_effects_effect_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }
    }

    public class Fleets {
        private HashMap<Long, SimpleObjectProperty<R_get_fleets_fleet_id>> get_fleets_fleet_id_container = new HashMap<>();

        public Property<R_get_fleets_fleet_id> get_fleets_fleet_id(long param) {
            SimpleObjectProperty<R_get_fleets_fleet_id> holder = get_fleets_fleet_id_container.get(param);
            if (holder == null) {
                synchronized (get_fleets_fleet_id_container)
                {
                    holder = get_fleets_fleet_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_fleets_fleet_id_container.put(param, holder);
                        addFetchCacheObject("get_fleets_fleet_id", (m->swagger.get_fleets_fleet_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }
    }

    public class Fw {
        private SimpleObjectProperty<R_get_fw_leaderboards> get_fw_leaderboards_container = null;
        private SimpleObjectProperty<R_get_fw_leaderboards_characters> get_fw_leaderboards_characters_container = null;
        private SimpleObjectProperty<R_get_fw_leaderboards_corporations> get_fw_leaderboards_corporations_container = null;

        public Property<R_get_fw_leaderboards> get_fw_leaderboards() {
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

        public Property<R_get_fw_leaderboards_characters> get_fw_leaderboards_characters() {
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

        public Property<R_get_fw_leaderboards_corporations> get_fw_leaderboards_corporations() {
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

    public class Loyalty {
    }

    public class Markets {
    }

    public class Opportunities {
        private HashMap<Integer, SimpleObjectProperty<R_get_opportunities_tasks_task_id>> get_opportunities_tasks_task_id_container = new HashMap<>();

        public Property<R_get_opportunities_tasks_task_id> get_opportunities_tasks_task_id(int param) {
            SimpleObjectProperty<R_get_opportunities_tasks_task_id> holder = get_opportunities_tasks_task_id_container.get(param);
            if (holder == null) {
                synchronized (get_opportunities_tasks_task_id_container)
                {
                    holder = get_opportunities_tasks_task_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_opportunities_tasks_task_id_container.put(param, holder);
                        addFetchCacheObject("get_opportunities_tasks_task_id", (m->swagger.get_opportunities_tasks_task_id(param, m)), (holder::set));
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
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_graphics_graphic_id>> get_universe_graphics_graphic_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_moons_moon_id>> get_universe_moons_moon_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_planets_planet_id>> get_universe_planets_planet_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_schematics_schematic_id>> get_universe_schematics_schematic_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_stargates_stargate_id>> get_universe_stargates_stargate_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_stars_star_id>> get_universe_stars_star_id_container = new HashMap<>();
        private HashMap<Long, SimpleObjectProperty<R_get_universe_structures_structure_id>> get_universe_structures_structure_id_container = new HashMap<>();
        private HashMap<Integer, SimpleObjectProperty<R_get_universe_stations_station_id>> get_universe_stations_station_id_container = new HashMap<>();

        public Property<R_get_universe_asteroid_belts_asteroid_belt_id> get_universe_asteroid_belts_asteroid_belt_id(int param) {
            SimpleObjectProperty<R_get_universe_asteroid_belts_asteroid_belt_id> holder = get_universe_asteroid_belts_asteroid_belt_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_asteroid_belts_asteroid_belt_id_container)
                {
                    holder = get_universe_asteroid_belts_asteroid_belt_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_asteroid_belts_asteroid_belt_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_asteroid_belts_asteroid_belt_id", (m->swagger.get_universe_asteroid_belts_asteroid_belt_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_graphics_graphic_id> get_universe_graphics_graphic_id(int param) {
            SimpleObjectProperty<R_get_universe_graphics_graphic_id> holder = get_universe_graphics_graphic_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_graphics_graphic_id_container)
                {
                    holder = get_universe_graphics_graphic_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_graphics_graphic_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_graphics_graphic_id", (m->swagger.get_universe_graphics_graphic_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_moons_moon_id> get_universe_moons_moon_id(int param) {
            SimpleObjectProperty<R_get_universe_moons_moon_id> holder = get_universe_moons_moon_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_moons_moon_id_container)
                {
                    holder = get_universe_moons_moon_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_moons_moon_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_moons_moon_id", (m->swagger.get_universe_moons_moon_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_planets_planet_id> get_universe_planets_planet_id(int param) {
            SimpleObjectProperty<R_get_universe_planets_planet_id> holder = get_universe_planets_planet_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_planets_planet_id_container)
                {
                    holder = get_universe_planets_planet_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_planets_planet_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_planets_planet_id", (m->swagger.get_universe_planets_planet_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_schematics_schematic_id> get_universe_schematics_schematic_id(int param) {
            SimpleObjectProperty<R_get_universe_schematics_schematic_id> holder = get_universe_schematics_schematic_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_schematics_schematic_id_container)
                {
                    holder = get_universe_schematics_schematic_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_schematics_schematic_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_schematics_schematic_id", (m->swagger.get_universe_schematics_schematic_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_stargates_stargate_id> get_universe_stargates_stargate_id(int param) {
            SimpleObjectProperty<R_get_universe_stargates_stargate_id> holder = get_universe_stargates_stargate_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_stargates_stargate_id_container)
                {
                    holder = get_universe_stargates_stargate_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_stargates_stargate_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_stargates_stargate_id", (m->swagger.get_universe_stargates_stargate_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_stars_star_id> get_universe_stars_star_id(int param) {
            SimpleObjectProperty<R_get_universe_stars_star_id> holder = get_universe_stars_star_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_stars_star_id_container)
                {
                    holder = get_universe_stars_star_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_stars_star_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_stars_star_id", (m->swagger.get_universe_stars_star_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_structures_structure_id> get_universe_structures_structure_id(long param) {
            SimpleObjectProperty<R_get_universe_structures_structure_id> holder = get_universe_structures_structure_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_structures_structure_id_container)
                {
                    holder = get_universe_structures_structure_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_structures_structure_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_structures_structure_id", (m->swagger.get_universe_structures_structure_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }

        public Property<R_get_universe_stations_station_id> get_universe_stations_station_id(int param) {
            SimpleObjectProperty<R_get_universe_stations_station_id> holder = get_universe_stations_station_id_container.get(param);
            if (holder == null) {
                synchronized (get_universe_stations_station_id_container)
                {
                    holder = get_universe_stations_station_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_universe_stations_station_id_container.put(param, holder);
                        addFetchCacheObject("get_universe_stations_station_id", (m->swagger.get_universe_stations_station_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }
    }

    public class Wars {
        private HashMap<Integer, SimpleObjectProperty<R_get_wars_war_id>> get_wars_war_id_container = new HashMap<>();

        public Property<R_get_wars_war_id> get_wars_war_id(int param) {
            SimpleObjectProperty<R_get_wars_war_id> holder = get_wars_war_id_container.get(param);
            if (holder == null) {
                synchronized (get_wars_war_id_container)
                {
                    holder = get_wars_war_id_container.get(param);
                    if (holder == null) {
                        holder = new SimpleObjectProperty<>();
                        get_wars_war_id_container.put(param, holder);
                        addFetchCacheObject("get_wars_war_id", (m->swagger.get_wars_war_id(param, m)), (holder::set));
                    }
                }
            }
            return holder;
        }
    }
}
