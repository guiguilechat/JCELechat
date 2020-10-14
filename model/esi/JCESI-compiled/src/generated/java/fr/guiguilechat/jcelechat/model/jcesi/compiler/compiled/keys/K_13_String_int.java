package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/killmails/{killmail_id}/{killmail_hash}/
 * 
 */
public class K_13_String_int {
    public final String killmail_hash;
    public final int killmail_id;

    public K_13_String_int(String killmail_hash, int killmail_id) {
        this.killmail_hash = killmail_hash;
        this.killmail_id = killmail_id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_13_String_int othersame = ((K_13_String_int) other);
        if ((killmail_hash!= othersame.killmail_hash)&&((killmail_hash == null)||(!killmail_hash.equals(othersame.killmail_hash)))) {
            return false;
        }
        if (killmail_id!= othersame.killmail_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((killmail_hash == null)? 0 :killmail_hash.hashCode())+ killmail_id);
    }
}
