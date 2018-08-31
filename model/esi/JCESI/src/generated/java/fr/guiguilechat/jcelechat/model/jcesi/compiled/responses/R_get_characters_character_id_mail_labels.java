package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_mail_labels {
    /**
     * labels array
     */
    public R_get_characters_character_id_mail_labels_labels[] labels;
    /**
     * total_unread_count integer
     */
    public int total_unread_count;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_mail_labels othersame = ((R_get_characters_character_id_mail_labels) other);
        if ((labels!= othersame.labels)&&((labels == null)||(!labels.equals(othersame.labels)))) {
            return false;
        }
        if (total_unread_count!= othersame.total_unread_count) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((labels == null)? 0 :labels.hashCode())+ total_unread_count);
    }
}
