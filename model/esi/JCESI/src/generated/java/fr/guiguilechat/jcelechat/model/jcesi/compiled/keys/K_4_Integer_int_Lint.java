package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/characters/{character_id}/mail/
 * 
 */
public class K_4_Integer_int_Lint {
    public final Integer last_mail_id;
    public final int character_id;
    public final int[] labels;

    public K_4_Integer_int_Lint(Integer last_mail_id, int character_id, int[] labels) {
        this.last_mail_id = last_mail_id;
        this.character_id = character_id;
        this.labels = labels;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_4_Integer_int_Lint othersame = ((K_4_Integer_int_Lint) other);
        if ((last_mail_id!= othersame.last_mail_id)&&((last_mail_id == null)||(!last_mail_id.equals(othersame.last_mail_id)))) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        if ((labels!= othersame.labels)&&((labels == null)||(!labels.equals(othersame.labels)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((last_mail_id == null)? 0 :last_mail_id.hashCode())+ character_id)+((labels == null)? 0 :labels.hashCode()));
    }
}
