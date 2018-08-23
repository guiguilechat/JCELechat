package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


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

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_4_Integer_int_Lint other2 = ((K_4_Integer_int_Lint) other);
        return ((((last_mail_id == other2 .last_mail_id)||((last_mail_id!= null)&&last_mail_id.equals(other2 .last_mail_id)))&&(character_id == other2 .character_id))&&((labels == other2 .labels)||((labels!= null)&&labels.equals(other2 .labels))));
    }

    public int hashCode() {
        return ((int)(((0 +((last_mail_id == null)? 0 :last_mail_id.hashCode()))+ character_id)+((labels == null)? 0 :labels.hashCode())));
    }
}
