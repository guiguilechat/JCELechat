package fr.guiguilechat.jcelechat.model.jcesi.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_opportunities_groups_group_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_opportunities_tasks_task_id;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsListHolder;
import fr.guiguilechat.jcelechat.model.jcesi.interfaces.ObsObjHolder;
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
            synchronized (this)
            {
                if (get_opportunities_groups_holder == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    get_opportunities_groups_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_opportunities_groups", (page, headerHandler) -> IntStream.of((cache.swagger).get_opportunities_groups(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
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
            synchronized (get_opportunities_groups_group_id_holder)
            {
                ret = get_opportunities_groups_group_id_holder.get(group_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_opportunities_groups_group_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_opportunities_groups_group_id_holder.put(group_id, ret);
                    (cache).addFetchCacheObject("get_opportunities_groups_group_id", headerHandler -> (cache.swagger).get_opportunities_groups(group_id, headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
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
            synchronized (this)
            {
                if (get_opportunities_tasks_holder == null) {
                    ObservableList<Integer> holder = FXCollections.observableArrayList();
                    get_opportunities_tasks_holder = (cache).toHolder(holder);
                    (cache).addFetchCacheArray("get_opportunities_tasks", (page, headerHandler) -> IntStream.of((cache.swagger).get_opportunities_tasks(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
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
            synchronized (get_opportunities_tasks_task_id_holder)
            {
                ret = get_opportunities_tasks_task_id_holder.get(task_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_opportunities_tasks_task_id> holder = new SimpleObjectProperty<>();
                    ret = (cache).toHolder(holder);
                    get_opportunities_tasks_task_id_holder.put(task_id, ret);
                    (cache).addFetchCacheObject("get_opportunities_tasks_task_id", headerHandler -> (cache.swagger).get_opportunities_tasks(task_id, headerHandler), item -> {
                        synchronized (holder)
                        {
                            holder.set(item);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
