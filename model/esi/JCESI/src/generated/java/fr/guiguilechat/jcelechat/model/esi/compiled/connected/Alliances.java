package fr.guiguilechat.jcelechat.model.esi.compiled.connected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.esi.compiled.SwaggerCOCache;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.M_get_contacts_labels_2;
import fr.guiguilechat.jcelechat.model.esi.compiled.responses.R_get_alliances_alliance_id_contacts;
import fr.guiguilechat.jcelechat.model.esi.interfaces.ObsListHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Alliances {
    public final SwaggerCOCache<?> cache;
    private final Map<Integer, ObsListHolder<M_get_contacts_labels_2>> get_alliances_alliance_id_contacts_labels_holder = new HashMap<>();
    private final Map<Integer, ObsListHolder<R_get_alliances_alliance_id_contacts>> get_alliances_alliance_id_contacts_holder = new HashMap<>();

    public Alliances(SwaggerCOCache<?> parent) {
        cache = parent;
    }

    /**
     * Return custom labels for an alliance's contacts
     * 
     * cache over {@link Swagger#get_alliances_contacts_labels}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public ObsListHolder<M_get_contacts_labels_2> contacts_labels(int alliance_id) {
        ObsListHolder<M_get_contacts_labels_2> ret = get_alliances_alliance_id_contacts_labels_holder.get(alliance_id);
        if (ret == null) {
            synchronized (get_alliances_alliance_id_contacts_labels_holder)
            {
                ret = get_alliances_alliance_id_contacts_labels_holder.get(alliance_id);
                if (ret == null) {
                    ObservableList<M_get_contacts_labels_2> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_alliances_alliance_id_contacts_labels_holder.put(alliance_id, ret);
                    (cache).addFetchCacheArray("get_alliances_alliance_id_contacts_labels", (page, headerHandler) -> (cache.swagger).get_alliances_contacts_labels(alliance_id, headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }

    /**
     * Return contacts of an alliance
     * 
     * cache over {@link Swagger#get_alliances_contacts}<br />
     * 
     * @param alliance_id
     *     An EVE alliance ID
     */
    public ObsListHolder<R_get_alliances_alliance_id_contacts> contacts(int alliance_id) {
        ObsListHolder<R_get_alliances_alliance_id_contacts> ret = get_alliances_alliance_id_contacts_holder.get(alliance_id);
        if (ret == null) {
            synchronized (get_alliances_alliance_id_contacts_holder)
            {
                ret = get_alliances_alliance_id_contacts_holder.get(alliance_id);
                if (ret == null) {
                    ObservableList<R_get_alliances_alliance_id_contacts> holder = FXCollections.observableArrayList();
                    ret = (cache).toHolder(holder);
                    get_alliances_alliance_id_contacts_holder.put(alliance_id, ret);
                    (cache).addFetchCacheArray("get_alliances_alliance_id_contacts", (page, headerHandler) -> (cache.swagger).get_alliances_contacts(alliance_id, page, headerHandler), arr -> {
                        synchronized (holder)
                        {
                            holder.setAll(arr);
                        }
                    }
                    );
                }
            }
        }
        return ret;
    }
}
