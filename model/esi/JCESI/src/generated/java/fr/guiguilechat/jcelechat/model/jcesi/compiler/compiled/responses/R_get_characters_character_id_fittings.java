package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_fittings {
    /**
     * description string
     */
    public String description;
    /**
     * fitting_id integer
     */
    public int fitting_id;
    /**
     * items array
     */
    public R_get_characters_character_id_fittings_items[] items;
    /**
     * name string
     */
    public String name;
    /**
     * ship_type_id integer
     */
    public int ship_type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_fittings othersame = ((R_get_characters_character_id_fittings) other);
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (fitting_id!= othersame.fitting_id) {
            return false;
        }
        if ((items!= othersame.items)&&((items == null)||(!items.equals(othersame.items)))) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (ship_type_id!= othersame.ship_type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((description == null)? 0 :description.hashCode())+ fitting_id)+((items == null)? 0 :items.hashCode()))+((name == null)? 0 :name.hashCode()))+ ship_type_id);
    }
}
