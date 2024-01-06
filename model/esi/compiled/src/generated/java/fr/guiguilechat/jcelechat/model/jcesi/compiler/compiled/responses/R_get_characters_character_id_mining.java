package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_mining {
    /**
     * date string
     */
    public String date;
    /**
     * quantity integer
     */
    public long quantity;
    /**
     * solar_system_id integer
     */
    public int solar_system_id;
    /**
     * type_id integer
     */
    public int type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_mining othersame = ((R_get_characters_character_id_mining) other);
        if ((date!= othersame.date)&&((date == null)||(!date.equals(othersame.date)))) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (solar_system_id!= othersame.solar_system_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((date == null)? 0 :date.hashCode())+ Long.hashCode(quantity))+ solar_system_id)+ type_id);
    }
}
