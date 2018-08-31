package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_universe_bloodlines {
    /**
     * bloodline_id integer
     */
    public int bloodline_id;
    /**
     * charisma integer
     */
    public int charisma;
    /**
     * corporation_id integer
     */
    public int corporation_id;
    /**
     * description string
     */
    public String description;
    /**
     * intelligence integer
     */
    public int intelligence;
    /**
     * memory integer
     */
    public int memory;
    /**
     * name string
     */
    public String name;
    /**
     * perception integer
     */
    public int perception;
    /**
     * race_id integer
     */
    public int race_id;
    /**
     * ship_type_id integer
     */
    public int ship_type_id;
    /**
     * willpower integer
     */
    public int willpower;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_bloodlines othersame = ((R_get_universe_bloodlines) other);
        if (bloodline_id!= othersame.bloodline_id) {
            return false;
        }
        if (charisma!= othersame.charisma) {
            return false;
        }
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (intelligence!= othersame.intelligence) {
            return false;
        }
        if (memory!= othersame.memory) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (perception!= othersame.perception) {
            return false;
        }
        if (race_id!= othersame.race_id) {
            return false;
        }
        if (ship_type_id!= othersame.ship_type_id) {
            return false;
        }
        if (willpower!= othersame.willpower) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((bloodline_id + charisma)+ corporation_id)+((description == null)? 0 :description.hashCode()))+ intelligence)+ memory)+((name == null)? 0 :name.hashCode()))+ perception)+ race_id)+ ship_type_id)+ willpower);
    }
}
