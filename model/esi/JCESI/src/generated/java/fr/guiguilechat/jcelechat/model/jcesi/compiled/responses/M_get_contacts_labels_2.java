package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_contacts_labels_2 {
    /**
     * label_id integer
     */
    public long label_id;
    /**
     * label_name string
     */
    public String label_name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_get_contacts_labels_2 othersame = ((M_get_contacts_labels_2) other);
        if (label_id!= othersame.label_id) {
            return false;
        }
        if ((label_name!= othersame.label_name)&&((label_name == null)||(!label_name.equals(othersame.label_name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(label_id)+((label_name == null)? 0 :label_name.hashCode()));
    }
}
