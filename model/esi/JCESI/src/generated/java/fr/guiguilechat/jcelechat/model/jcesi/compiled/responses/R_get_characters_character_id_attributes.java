package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_attributes {
    /**
     * Neural remapping cooldown after a character uses remap accrued over time
     */
    public String accrued_remap_cooldown_date;
    /**
     * Number of available bonus character neural remaps
     */
    public int bonus_remaps;
    /**
     * charisma integer
     */
    public int charisma;
    /**
     * intelligence integer
     */
    public int intelligence;
    /**
     * Datetime of last neural remap, including usage of bonus remaps
     */
    public String last_remap_date;
    /**
     * memory integer
     */
    public int memory;
    /**
     * perception integer
     */
    public int perception;
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
        R_get_characters_character_id_attributes othersame = ((R_get_characters_character_id_attributes) other);
        if ((accrued_remap_cooldown_date!= othersame.accrued_remap_cooldown_date)&&((accrued_remap_cooldown_date == null)||(!accrued_remap_cooldown_date.equals(othersame.accrued_remap_cooldown_date)))) {
            return false;
        }
        if (bonus_remaps!= othersame.bonus_remaps) {
            return false;
        }
        if (charisma!= othersame.charisma) {
            return false;
        }
        if (intelligence!= othersame.intelligence) {
            return false;
        }
        if ((last_remap_date!= othersame.last_remap_date)&&((last_remap_date == null)||(!last_remap_date.equals(othersame.last_remap_date)))) {
            return false;
        }
        if (memory!= othersame.memory) {
            return false;
        }
        if (perception!= othersame.perception) {
            return false;
        }
        if (willpower!= othersame.willpower) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((accrued_remap_cooldown_date == null)? 0 :accrued_remap_cooldown_date.hashCode())+ bonus_remaps)+ charisma)+ intelligence)+((last_remap_date == null)? 0 :last_remap_date.hashCode()))+ memory)+ perception)+ willpower);
    }
}
