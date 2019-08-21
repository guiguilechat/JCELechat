package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_13_String_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_killmails_killmail_id_killmail_hash;
import fr.lelouet.collectionholders.impl.AObsObjHolder;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import javafx.beans.property.SimpleObjectProperty;

public class Killmails {
    public final SwaggerDCCache<?> cache;
    private final Map<K_13_String_int, AObsObjHolder<R_get_killmails_killmail_id_killmail_hash>> get_killmails_killmail_id_killmail_hash_holder = new HashMap<>();

    public Killmails(SwaggerDCCache<?> parent) {
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
    public ObsObjHolder<R_get_killmails_killmail_id_killmail_hash> get(String killmail_hash, int killmail_id) {
        K_13_String_int param = new K_13_String_int(killmail_hash, killmail_id);
        AObsObjHolder<R_get_killmails_killmail_id_killmail_hash> ret = get_killmails_killmail_id_killmail_hash_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_killmails_killmail_id_killmail_hash_holder);
            try {
                synchronized (get_killmails_killmail_id_killmail_hash_holder)
                {
                    LockWatchDog.BARKER.hld(get_killmails_killmail_id_killmail_hash_holder);
                    {
                        ret = get_killmails_killmail_id_killmail_hash_holder.get(param);
                        if (ret == null) {
                            SimpleObjectProperty<R_get_killmails_killmail_id_killmail_hash> holder = new SimpleObjectProperty<>();
                            ret = (cache).toHolder(holder);
                            get_killmails_killmail_id_killmail_hash_holder.put(param, ret);
                            (cache).addFetchCacheObject("get_killmails_killmail_id_killmail_hash", properties -> (cache.swagger).get_killmails(killmail_hash, killmail_id, properties), item -> {
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
                    LockWatchDog.BARKER.rel(get_killmails_killmail_id_killmail_hash_holder);
                }
            } finally {
                LockWatchDog.BARKER.rel(get_killmails_killmail_id_killmail_hash_holder);
            }
        }
        return ret;
    }
}
