package fr.guiguilechat.jcelechat.model.jcesi.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.M_get_fw_leaderboards_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_fw_stats;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_fw_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_fw_wars;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Fw {
    public final SwaggerDCCache<?> cache;
    private ObsObjHolder<M_get_fw_leaderboards_2> get_fw_leaderboards_holder;
    private ObsObjHolder<M_get_fw_leaderboards_2> get_fw_leaderboards_characters_holder;
    private ObsObjHolder<M_get_fw_leaderboards_2> get_fw_leaderboards_corporations_holder;
    private ObsListHolder<R_get_fw_stats> get_fw_stats_holder;
    private ObsListHolder<R_get_fw_wars> get_fw_wars_holder;
    private ObsListHolder<R_get_fw_systems> get_fw_systems_holder;

    public Fw(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Top 4 leaderboard of factions for kills and victory points separated by total, last week and yesterday.
     * 
     * cache over {@link Swagger#get_fw_leaderboards}<br />
     */
    public ObsObjHolder<M_get_fw_leaderboards_2> leaderboards() {
        if (get_fw_leaderboards_holder == null) {
            synchronized (this)
            {
                if (get_fw_leaderboards_holder == null) {
                    SimpleObjectProperty<M_get_fw_leaderboards_2> holder = new SimpleObjectProperty<>();
                    get_fw_leaderboards_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheObject("get_fw_leaderboards", headerHandler -> (cache.swagger).get_fw_leaderboards(headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
            }
        }
        return get_fw_leaderboards_holder;
    }

    /**
     * Top 100 leaderboard of pilots for kills and victory points separated by total, last week and yesterday.
     * 
     * cache over {@link Swagger#get_fw_leaderboards_characters}<br />
     */
    public ObsObjHolder<M_get_fw_leaderboards_2> leaderboards_characters() {
        if (get_fw_leaderboards_characters_holder == null) {
            synchronized (this)
            {
                if (get_fw_leaderboards_characters_holder == null) {
                    SimpleObjectProperty<M_get_fw_leaderboards_2> holder = new SimpleObjectProperty<>();
                    get_fw_leaderboards_characters_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheObject("get_fw_leaderboards_characters", headerHandler -> (cache.swagger).get_fw_leaderboards_characters(headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
            }
        }
        return get_fw_leaderboards_characters_holder;
    }

    /**
     * Top 10 leaderboard of corporations for kills and victory points separated by total, last week and yesterday.
     * 
     * cache over {@link Swagger#get_fw_leaderboards_corporations}<br />
     */
    public ObsObjHolder<M_get_fw_leaderboards_2> leaderboards_corporations() {
        if (get_fw_leaderboards_corporations_holder == null) {
            synchronized (this)
            {
                if (get_fw_leaderboards_corporations_holder == null) {
                    SimpleObjectProperty<M_get_fw_leaderboards_2> holder = new SimpleObjectProperty<>();
                    get_fw_leaderboards_corporations_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheObject("get_fw_leaderboards_corporations", headerHandler -> (cache.swagger).get_fw_leaderboards_corporations(headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
            }
        }
        return get_fw_leaderboards_corporations_holder;
    }

    /**
     * Statistical overviews of factions involved in faction warfare
     * 
     * cache over {@link Swagger#get_fw_stats}<br />
     */
    public ObsListHolder<R_get_fw_stats> stats() {
        if (get_fw_stats_holder == null) {
            synchronized (this)
            {
                if (get_fw_stats_holder == null) {
                    ObservableList<R_get_fw_stats> holder = FXCollections.observableArrayList();
                    get_fw_stats_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_fw_stats", (page, headerHandler) -> (cache.swagger).get_fw_stats(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_fw_stats_holder;
    }

    /**
     * Data about which NPC factions are at war
     * 
     * cache over {@link Swagger#get_fw_wars}<br />
     */
    public ObsListHolder<R_get_fw_wars> wars() {
        if (get_fw_wars_holder == null) {
            synchronized (this)
            {
                if (get_fw_wars_holder == null) {
                    ObservableList<R_get_fw_wars> holder = FXCollections.observableArrayList();
                    get_fw_wars_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_fw_wars", (page, headerHandler) -> (cache.swagger).get_fw_wars(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_fw_wars_holder;
    }

    /**
     * An overview of the current ownership of faction warfare solar systems
     * 
     * cache over {@link Swagger#get_fw_systems}<br />
     */
    public ObsListHolder<R_get_fw_systems> systems() {
        if (get_fw_systems_holder == null) {
            synchronized (this)
            {
                if (get_fw_systems_holder == null) {
                    ObservableList<R_get_fw_systems> holder = FXCollections.observableArrayList();
                    get_fw_systems_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_fw_systems", (page, headerHandler) -> (cache.swagger).get_fw_systems(headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return get_fw_systems_holder;
    }
}
