package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_groups_group_id;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_opportunities_tasks_task_id;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Opportunities {
    public final SwaggerCache<?> cache;
    private ObservableList<Integer> get_opportunities_groups_holder;
    private final Map<Integer, Property<R_get_opportunities_groups_group_id>> get_opportunities_groups_group_id_holder = new HashMap<>();
    private ObservableList<Integer> get_opportunities_tasks_holder;
    private final Map<Integer, Property<R_get_opportunities_tasks_task_id>> get_opportunities_tasks_task_id_holder = new HashMap<>();

    public Opportunities(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a list of opportunities groups
     * 
     * cache over {@link Swagger#get_opportunities_groups}<br />
     */
    public ObservableList<Integer> groups() {
        if (get_opportunities_groups_holder == null) {
            synchronized (this)
            {
                if (get_opportunities_groups_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_opportunities_groups_holder = finalContainer;
                    (cache).addFetchCacheArray("get_opportunities_groups", (page, headerHandler) -> IntStream.of((cache.swagger).get_opportunities_groups(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
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
    public Property<R_get_opportunities_groups_group_id> groups(int group_id) {
        Property<R_get_opportunities_groups_group_id> ret = get_opportunities_groups_group_id_holder.get(group_id);
        if (ret == null) {
            synchronized (get_opportunities_groups_group_id_holder)
            {
                ret = get_opportunities_groups_group_id_holder.get(group_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_opportunities_groups_group_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    get_opportunities_groups_group_id_holder.put(group_id, ret);
                    (cache).addFetchCacheObject("get_opportunities_groups_group_id", headerHandler -> (cache.swagger).get_opportunities_groups(group_id, headerHandler), item -> {
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
     * Return a list of opportunities tasks
     * 
     * cache over {@link Swagger#get_opportunities_tasks}<br />
     */
    public ObservableList<Integer> tasks() {
        if (get_opportunities_tasks_holder == null) {
            synchronized (this)
            {
                if (get_opportunities_tasks_holder == null) {
                    ObservableList<Integer> finalContainer = FXCollections.observableArrayList();
                    get_opportunities_tasks_holder = finalContainer;
                    (cache).addFetchCacheArray("get_opportunities_tasks", (page, headerHandler) -> IntStream.of((cache.swagger).get_opportunities_tasks(headerHandler)).mapToObj((Integer::valueOf)).toArray((Integer[]::new)), arr -> {
                        synchronized (finalContainer)
                        {
                            finalContainer.setAll(arr);
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
    public Property<R_get_opportunities_tasks_task_id> tasks(int task_id) {
        Property<R_get_opportunities_tasks_task_id> ret = get_opportunities_tasks_task_id_holder.get(task_id);
        if (ret == null) {
            synchronized (get_opportunities_tasks_task_id_holder)
            {
                ret = get_opportunities_tasks_task_id_holder.get(task_id);
                if (ret == null) {
                    SimpleObjectProperty<R_get_opportunities_tasks_task_id> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    get_opportunities_tasks_task_id_holder.put(task_id, ret);
                    (cache).addFetchCacheObject("get_opportunities_tasks_task_id", headerHandler -> (cache.swagger).get_opportunities_tasks(task_id, headerHandler), item -> {
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
