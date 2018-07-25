package fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.eveonline.model.esi.compiled.SwaggerCache;
import fr.guiguilechat.eveonline.model.esi.compiled.keys.K_13_String_int;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_killmails_killmail_id_killmail_hash;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

public class Killmails {
    public final SwaggerCache<?> cache;
    private final Map<K_13_String_int, Property<R_get_killmails_killmail_id_killmail_hash>> get_killmails_killmail_id_killmail_hash_holder = new HashMap<>();

    public Killmails(SwaggerCache<?> parent) {
        cache = parent;
    }

    /**
     * Return a single killmail from its ID and hash
     * 
     * cache over {@link Swagger#get_killmails}<br />
     * 
     * @param killmail_hash
     *     The killmail hash for verification
     * @param killmail_id
     *     The killmail ID to be queried
     */
    public Property<R_get_killmails_killmail_id_killmail_hash> get(String killmail_hash, int killmail_id) {
        K_13_String_int param = new K_13_String_int(killmail_hash, killmail_id);
        Property<R_get_killmails_killmail_id_killmail_hash> ret = get_killmails_killmail_id_killmail_hash_holder.get(param);
        if (ret == null) {
            synchronized (get_killmails_killmail_id_killmail_hash_holder)
            {
                ret = get_killmails_killmail_id_killmail_hash_holder.get(param);
                if (ret == null) {
                    SimpleObjectProperty<R_get_killmails_killmail_id_killmail_hash> finalret = new SimpleObjectProperty<>();
                    ret = finalret;
                    get_killmails_killmail_id_killmail_hash_holder.put(param, ret);
                    (cache).addFetchCacheObject("get_killmails_killmail_id_killmail_hash", headerHandler -> (cache.swagger).get_killmails(killmail_hash, killmail_id, headerHandler), item -> {
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