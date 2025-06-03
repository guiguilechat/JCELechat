package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.disconnected;

import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.SwaggerDCCache;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys.K_13_String_int;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_killmails_killmail_id_killmail_hash;
import fr.lelouet.tools.holders.impl.ObjHolderSimple;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Killmails {
    public final SwaggerDCCache<?> cache;
    private final Map<K_13_String_int, ObjHolderSimple<R_get_killmails_killmail_id_killmail_hash>> get_killmails_killmail_id_killmail_hash_holder = new HashMap<>();

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
    public ObjHolder<R_get_killmails_killmail_id_killmail_hash> get(String killmail_hash, int killmail_id) {
        K_13_String_int param = new K_13_String_int(killmail_hash, killmail_id);
        ObjHolderSimple<R_get_killmails_killmail_id_killmail_hash> ret = get_killmails_killmail_id_killmail_hash_holder.get(param);
        if (ret == null) {
            LockWatchDog.BARKER.tak(get_killmails_killmail_id_killmail_hash_holder);
            try {
                synchronized (get_killmails_killmail_id_killmail_hash_holder)
                {
                    LockWatchDog.BARKER.hld(get_killmails_killmail_id_killmail_hash_holder);
                    {
                        ret = get_killmails_killmail_id_killmail_hash_holder.get(param);
                        if (ret == null) {
                            ret = new ObjHolderSimple<R_get_killmails_killmail_id_killmail_hash>();
                            ObjHolderSimple<R_get_killmails_killmail_id_killmail_hash> finalRet = ret;
                            get_killmails_killmail_id_killmail_hash_holder.put(param, ret);
                            (cache).addFetchCacheObject("get_killmails_killmail_id_killmail_hash", properties -> (cache.swagger).get_killmails(killmail_hash, killmail_id, properties), item -> finalRet.set(item));
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
