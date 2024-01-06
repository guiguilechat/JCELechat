package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_ancestries {
    /**
     * The bloodline associated with this ancestry
     */
    public int bloodline_id;
    /**
     * description string
     */
    public String description;
    /**
     * icon_id integer
     */
    public int icon_id;
    /**
     * id integer
     */
    public int id;
    /**
     * name string
     */
    public String name;
    /**
     * short_description string
     */
    public String short_description;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_ancestries othersame = ((R_get_universe_ancestries) other);
        if (bloodline_id!= othersame.bloodline_id) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (icon_id!= othersame.icon_id) {
            return false;
        }
        if (id!= othersame.id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if ((short_description!= othersame.short_description)&&((short_description == null)||(!short_description.equals(othersame.short_description)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((bloodline_id +((description == null)? 0 :description.hashCode()))+ icon_id)+ id)+((name == null)? 0 :name.hashCode()))+((short_description == null)? 0 :short_description.hashCode()));
    }
}
