package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_opportunities_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_opportunities_tasks_task_id;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Opportunities {
    public final SwaggerDCCache<?> cache;
    private ObsListHolder<Integer> get_opportunities_groups_holder;
    private final Map<Integer, ObsObjHolder<R_get_opportunities_groups_group_id>> get_opportunities_groups_group_id_holder = new HashMap<>();
    private ObsListHolder<Integer> get_opportunities_tasks_holder;
    private final Map<Integer, ObsObjHolder<R_get_opportunities_tasks_task_id>> get_opportunities_tasks_task_id_holder = new HashMap<>();

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
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_opportunities_groups_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_opportunities_groups_holder;
                            (cache).addFetchCacheArray("get_opportunities_groups", (page, properties) -> (cache.swagger).get_opportunities_groups(properties), arr -> {
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
        ObsObjHolder<R_get_opportunities_groups_group_id> ret = get_opportunities_groups_group_id_holder.get(group_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_opportunities_groups_group_id_holder);
            try {
                synchronized (get_opportunities_groups_group_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_opportunities_groups_group_id_holder);
                    {
                        ret = get_opportunities_groups_group_id_holder.get(group_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_opportunities_groups_group_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_opportunities_groups_group_id_holder.put(group_id, ret);
                            (cache).addFetchCacheObject("get_opportunities_groups_group_id", properties -> (cache.swagger).get_opportunities_groups(group_id, properties), item -> {
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
                            ObservableList<Integer> holder = FXCollections.observableArrayList();
                            get_opportunities_tasks_holder = (cache).toHolder(holder);
                            ObsListHolder<Integer> finalRet = get_opportunities_tasks_holder;
                            (cache).addFetchCacheArray("get_opportunities_tasks", (page, properties) -> (cache.swagger).get_opportunities_tasks(properties), arr -> {
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
        ObsObjHolder<R_get_opportunities_tasks_task_id> ret = get_opportunities_tasks_task_id_holder.get(task_id);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_opportunities_tasks_task_id_holder);
            try {
                synchronized (get_opportunities_tasks_task_id_holder)
                {
                    LockWatchDog.BARKER.hld(get_opportunities_tasks_task_id_holder);
                    {
                        ret = get_opportunities_tasks_task_id_holder.get(task_id);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_opportunities_tasks_task_id> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_opportunities_tasks_task_id_holder.put(task_id, ret);
                            (cache).addFetchCacheObject("get_opportunities_tasks_task_id", properties -> (cache.swagger).get_opportunities_tasks(task_id, properties), item -> {
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
                    LockWatchDog.BARKER.rel(get_opportunities_tasks_task_id_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_opportunities_tasks_task_id_holder);
            }
        }
        return ret;
    }
}
