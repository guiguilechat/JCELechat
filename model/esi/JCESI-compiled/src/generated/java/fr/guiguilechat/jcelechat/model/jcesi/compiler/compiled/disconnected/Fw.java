package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_fw_leaderboards_2;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_stats;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_systems;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_fw_wars;
import fr.lelouet.collectionholders.impl.ObsObjHolderSimple;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Fw {
    public final SwaggerDCCache<?> cache;
    private ObsObjHolderSimple<M_get_fw_leaderboards_2> get_fw_leaderboards_holder;
    private ObsObjHolderSimple<M_get_fw_leaderboards_2> get_fw_leaderboards_characters_holder;
    private ObsObjHolderSimple<M_get_fw_leaderboards_2> get_fw_leaderboards_corporations_holder;
    private ObsListHolderImpl<R_get_fw_stats> get_fw_stats_holder;
    private ObsListHolderImpl<R_get_fw_wars> get_fw_wars_holder;
    private ObsListHolderImpl<R_get_fw_systems> get_fw_systems_holder;

    public Fw(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Top 4 leaderboard of factions for kills and victory points separated by total, last week and yesterday
     * 
     * cache over {@link Swagger#get_fw_leaderboards}<br />
     */
    public ObsObjHolder<M_get_fw_leaderboards_2> leaderboards() {
        if (get_fw_leaderboards_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_fw_leaderboards_holder == null) {
                            get_fw_leaderboards_holder = new ObsObjHolderSimple<M_get_fw_leaderboards_2>();
                            (cache).addFetchCacheObject("get_fw_leaderboards", properties -> (cache.swagger).get_fw_leaderboards(properties), item -> get_fw_leaderboards_holder.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_fw_leaderboards_holder;
    }

    /**
     * Top 100 leaderboard of pilots for kills and victory points separated by total, last week and yesterday
     * 
     * cache over {@link Swagger#get_fw_leaderboards_characters}<br />
     */
    public ObsObjHolder<M_get_fw_leaderboards_2> leaderboards_characters() {
        if (get_fw_leaderboards_characters_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_fw_leaderboards_characters_holder == null) {
                            get_fw_leaderboards_characters_holder = new ObsObjHolderSimple<M_get_fw_leaderboards_2>();
                            (cache).addFetchCacheObject("get_fw_leaderboards_characters", properties -> (cache.swagger).get_fw_leaderboards_characters(properties), item -> get_fw_leaderboards_characters_holder.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_fw_leaderboards_characters_holder;
    }

    /**
     * Top 10 leaderboard of corporations for kills and victory points separated by total, last week and yesterday
     * 
     * cache over {@link Swagger#get_fw_leaderboards_corporations}<br />
     */
    public ObsObjHolder<M_get_fw_leaderboards_2> leaderboards_corporations() {
        if (get_fw_leaderboards_corporations_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_fw_leaderboards_corporations_holder == null) {
                            get_fw_leaderboards_corporations_holder = new ObsObjHolderSimple<M_get_fw_leaderboards_2>();
                            (cache).addFetchCacheObject("get_fw_leaderboards_corporations", properties -> (cache.swagger).get_fw_leaderboards_corporations(properties), item -> get_fw_leaderboards_corporations_holder.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_fw_stats_holder == null) {
                            get_fw_stats_holder = new ObsListHolderImpl<R_get_fw_stats>();
                            (cache).addFetchCacheArray("get_fw_stats", (page, properties) -> (cache.swagger).get_fw_stats(properties), arr -> get_fw_stats_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_fw_wars_holder == null) {
                            get_fw_wars_holder = new ObsListHolderImpl<R_get_fw_wars>();
                            (cache).addFetchCacheArray("get_fw_wars", (page, properties) -> (cache.swagger).get_fw_wars(properties), arr -> get_fw_wars_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
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
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_fw_systems_holder == null) {
                            get_fw_systems_holder = new ObsListHolderImpl<R_get_fw_systems>();
                            (cache).addFetchCacheArray("get_fw_systems", (page, properties) -> (cache.swagger).get_fw_systems(properties), arr -> get_fw_systems_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_fw_systems_holder;
    }
}
