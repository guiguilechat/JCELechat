package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_13_String_int other2 = ((K_13_String_int) other);
        return (((killmail_hash == other2 .killmail_hash)||((killmail_hash!= null)&&killmail_hash.equals(other2 .killmail_hash)))&&(killmail_id == other2 .killmail_id));
    }

    public int hashCode() {
        return ((int)((0 +((killmail_hash == null)? 0 :killmail_hash.hashCode()))+ killmail_id));
    }
}
