package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_opportunities_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_opportunities_tasks_task_id;
import fr.lelouet.collectionholders.impl.ObsObjHolderSimple;
import fr.lelouet.collectionholders.impl.collections.ObsListHolderImpl;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Opportunities {
    public final SwaggerDCCache<?> cache;
    private ObsListHolderImpl<Integer> get_opportunities_groups_holder;
    private final Map<Integer, ObsObjHolderSimple<R_get_opportunities_groups_group_id>> get_opportunities_groups_group_id_holder = new HashMap<>();
    private ObsListHolderImpl<Integer> get_opportunities_tasks_holder;
    private final Map<Integer, ObsObjHolderSimple<R_get_opportunities_tasks_task_id>> get_opportunities_tasks_task_id_holder = new HashMap<>();

    public Opportunities(SwaggerDCCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of opportunities groups
     * 
     * cache over {@link Swagger#get_opportunities_groups}<br />
     */
    public ObsListHolder<Integer> groups() {
        if (get_opportunities_groups_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_opportunities_groups_holder == null) {
                            get_opportunities_groups_holder = new ObsListHolderImpl<Integer>();
                            (cache).addFetchCacheArray("get_opportunities_groups", (page, properties) -> (cache.swagger).get_opportunities_groups(properties), arr -> get_opportunities_groups_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_opportunities_groups_holder;
    }

    /**
     * Return information of an opportunities group
     * 
     * cache over {@link Swagger#get_opportunities_groups}<br />
     * 
     * @param group_id
     *     ID of an opportunities group
     */
    public ObsObjHolder<R_get_opportunities_groups_group_id> groups(int group_id) {
        ObsObjHolderSimple<R_get_opportunities_groups_group_id> ret = get_opportunities_groups_group_id_holder.get(group_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_opportunities_groups_group_id_holder);
            try {
                synchronized (get_opportunities_groups_group_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_opportunities_groups_group_id_holder);
                    {
                        ret = get_opportunities_groups_group_id_holder.get(group_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_opportunities_groups_group_id>();
                            ObsObjHolderSimple<R_get_opportunities_groups_group_id> finalRet = ret;
                            get_opportunities_groups_group_id_holder.put(group_id, ret);
                            (cache).addFetchCacheObject("get_opportunities_groups_group_id", properties -> (cache.swagger).get_opportunities_groups(group_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_opportunities_groups_group_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_opportunities_groups_group_id_holder);
            }
        }
        return ret;
    }

    /**
     * Return a list of opportunities tasks
     * 
     * cache over {@link Swagger#get_opportunities_tasks}<br />
     */
    public ObsListHolder<Integer> tasks() {
        if (get_opportunities_tasks_holder == null) {
            LockWatchDog.BARKER.tak(this);
            try {
                synchronized (this)
                {
                    LockWatchDog.BARKER.hld(this);
                    {
                        if (get_opportunities_tasks_holder == null) {
                            get_opportunities_tasks_holder = new ObsListHolderImpl<Integer>();
                            (cache).addFetchCacheArray("get_opportunities_tasks", (page, properties) -> (cache.swagger).get_opportunities_tasks(properties), arr -> get_opportunities_tasks_holder.set(arr));
                        }
                    }
                    LockWatchDog.BARKER.rel(this);
                }
            } finally {
                LockWatchDog.BARKER.rel(this);
            }
        }
        return get_opportunities_tasks_holder;
    }

    /**
     * Return information of an opportunities task
     * 
     * cache over {@link Swagger#get_opportunities_tasks}<br />
     * 
     * @param task_id
     *     ID of an opportunities task
     */
    public ObsObjHolder<R_get_opportunities_tasks_task_id> tasks(int task_id) {
        ObsObjHolderSimple<R_get_opportunities_tasks_task_id> ret = get_opportunities_tasks_task_id_holder.get(task_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_opportunities_tasks_task_id_holder);
            try {
                synchronized (get_opportunities_tasks_task_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_opportunities_tasks_task_id_holder);
                    {
                        ret = get_opportunities_tasks_task_id_holder.get(task_id);
                        if (ret == null) {
                            ret = new ObsObjHolderSimple<R_get_opportunities_tasks_task_id>();
                            ObsObjHolderSimple<R_get_opportunities_tasks_task_id> finalRet = ret;
                            get_opportunities_tasks_task_id_holder.put(task_id, ret);
                            (cache).addFetchCacheObject("get_opportunities_tasks_task_id", properties -> (cache.swagger).get_opportunities_tasks(task_id, properties), item -> finalRet.set(item));
                        }
                    }
                    LockWatchDog.BARKER.rel(get_opportunities_tasks_task_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_opportunities_tasks_task_id_holder);
            }
        }
        return ret;
    }
}
