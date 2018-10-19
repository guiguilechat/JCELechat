package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.get_characters_character_id_gender;

public class R_get_characters_character_id {
    /**
     * The character's alliance ID
     */
    public int alliance_id;
    /**
     * ancestry_id integer
     */
    public int ancestry_id;
    /**
     * Creation date of the character
     */
    public String birthday;
    /**
     * bloodline_id integer
     */
    public int bloodline_id;
    /**
     * The character's corporation ID
     */
    public int corporation_id;
    /**
     * description string
     */
    public String description;
    /**
     * ID of the faction the character is fighting for, if the character is enlisted in Factional Warfare
     */
    public int faction_id;
    /**
     * gender string
     */
    public get_characters_character_id_gender gender;
    /**
     * name string
     */
    public String name;
    /**
     * race_id integer
     */
    public int race_id;
    /**
     * security_status number
     */
    public float security_status;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id othersame = ((R_get_characters_character_id) other);
        if (alliance_id!= othersame.alliance_id) {
            return false;
        }
        if (ancestry_id!= othersame.ancestry_id) {
            return false;
        }
        if ((birthday!= othersame.birthday)&&((birthday == null)||(!birthday.equals(othersame.birthday)))) {
            return false;
        }
        if (bloodline_id!= othersame.bloodline_id) {
            return false;
        }
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        if ((gender!= othersame.gender)&&((gender == null)||(!gender.equals(othersame.gender)))) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (race_id!= othersame.race_id) {
            return false;
        }
        if (security_status!= othersame.security_status) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((alliance_id + ancestry_id)+((birthday == null)? 0 :birthday.hashCode()))+ bloodline_id)+ corporation_id)+((description == null)? 0 :description.hashCode()))+ faction_id)+((gender == null)? 0 :gender.hashCode()))+((name == null)? 0 :name.hashCode()))+ race_id)+ Double.hashCode(security_status));
    }
}
