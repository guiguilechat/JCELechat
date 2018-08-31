package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_dogma_attributes_attribute_id {
    /**
     * attribute_id integer
     */
    public int attribute_id;
    /**
     * default_value number
     */
    public float default_value;
    /**
     * description string
     */
    public String description;
    /**
     * display_name string
     */
    public String display_name;
    /**
     * high_is_good boolean
     */
    public boolean high_is_good;
    /**
     * icon_id integer
     */
    public int icon_id;
    /**
     * name string
     */
    public String name;
    /**
     * published boolean
     */
    public boolean published;
    /**
     * stackable boolean
     */
    public boolean stackable;
    /**
     * unit_id integer
     */
    public int unit_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_dogma_attributes_attribute_id othersame = ((R_get_dogma_attributes_attribute_id) other);
        if (attribute_id!= othersame.attribute_id) {
            return false;
        }
        if (default_value!= othersame.default_value) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if ((display_name!= othersame.display_name)&&((display_name == null)||(!display_name.equals(othersame.display_name)))) {
            return false;
        }
        if (high_is_good!= othersame.high_is_good) {
            return false;
        }
        if (icon_id!= othersame.icon_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (published!= othersame.published) {
            return false;
        }
        if (stackable!= othersame.stackable) {
            return false;
        }
        if (unit_id!= othersame.unit_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((attribute_id + Double.hashCode(default_value))+((description == null)? 0 :description.hashCode()))+((display_name == null)? 0 :display_name.hashCode()))+ Boolean.hashCode(high_is_good))+ icon_id)+((name == null)? 0 :name.hashCode()))+ Boolean.hashCode(published))+ Boolean.hashCode(stackable))+ unit_id);
    }
}
