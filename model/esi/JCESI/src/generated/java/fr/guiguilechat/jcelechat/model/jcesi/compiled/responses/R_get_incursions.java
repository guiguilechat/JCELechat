package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.get_incursions_state;

public class R_get_incursions {
    /**
     * The constellation id in which this incursion takes place
     */
    public int constellation_id;
    /**
     * The attacking faction's id
     */
    public int faction_id;
    /**
     * Whether the final encounter has boss or not
     */
    public boolean has_boss;
    /**
     * A list of infested solar system ids that are a part of this incursion
     */
    public int[] infested_solar_systems;
    /**
     * Influence of this incursion as a float from 0 to 1
     */
    public float influence;
    /**
     * Staging solar system for this incursion
     */
    public int staging_solar_system_id;
    /**
     * The state of this incursion
     */
    public get_incursions_state state;
    /**
     * The type of this incursion
     */
    public String type;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_incursions othersame = ((R_get_incursions) other);
        if (constellation_id!= othersame.constellation_id) {
            return false;
        }
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        if (has_boss!= othersame.has_boss) {
            return false;
        }
        if ((infested_solar_systems!= othersame.infested_solar_systems)&&((infested_solar_systems == null)||(!infested_solar_systems.equals(othersame.infested_solar_systems)))) {
            return false;
        }
        if (influence!= othersame.influence) {
            return false;
        }
        if (staging_solar_system_id!= othersame.staging_solar_system_id) {
            return false;
        }
        if ((state!= othersame.state)&&((state == null)||(!state.equals(othersame.state)))) {
            return false;
        }
        if ((type!= othersame.type)&&((type == null)||(!type.equals(othersame.type)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((constellation_id + faction_id)+ Boolean.hashCode(has_boss))+((infested_solar_systems == null)? 0 :infested_solar_systems.hashCode()))+ Double.hashCode(influence))+ staging_solar_system_id)+((state == null)? 0 :state.hashCode()))+((type == null)? 0 :type.hashCode()));
    }
}
