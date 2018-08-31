package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_search {
    /**
     * agent array
     */
    public int[] agent;
    /**
     * alliance array
     */
    public int[] alliance;
    /**
     * character array
     */
    public int[] character;
    /**
     * constellation array
     */
    public int[] constellation;
    /**
     * corporation array
     */
    public int[] corporation;
    /**
     * faction array
     */
    public int[] faction;
    /**
     * inventory_type array
     */
    public int[] inventory_type;
    /**
     * region array
     */
    public int[] region;
    /**
     * solar_system array
     */
    public int[] solar_system;
    /**
     * station array
     */
    public int[] station;
    /**
     * structure array
     */
    public long[] structure;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_search othersame = ((R_get_characters_character_id_search) other);
        if ((agent!= othersame.agent)&&((agent == null)||(!agent.equals(othersame.agent)))) {
            return false;
        }
        if ((alliance!= othersame.alliance)&&((alliance == null)||(!alliance.equals(othersame.alliance)))) {
            return false;
        }
        if ((character!= othersame.character)&&((character == null)||(!character.equals(othersame.character)))) {
            return false;
        }
        if ((constellation!= othersame.constellation)&&((constellation == null)||(!constellation.equals(othersame.constellation)))) {
            return false;
        }
        if ((corporation!= othersame.corporation)&&((corporation == null)||(!corporation.equals(othersame.corporation)))) {
            return false;
        }
        if ((faction!= othersame.faction)&&((faction == null)||(!faction.equals(othersame.faction)))) {
            return false;
        }
        if ((inventory_type!= othersame.inventory_type)&&((inventory_type == null)||(!inventory_type.equals(othersame.inventory_type)))) {
            return false;
        }
        if ((region!= othersame.region)&&((region == null)||(!region.equals(othersame.region)))) {
            return false;
        }
        if ((solar_system!= othersame.solar_system)&&((solar_system == null)||(!solar_system.equals(othersame.solar_system)))) {
            return false;
        }
        if ((station!= othersame.station)&&((station == null)||(!station.equals(othersame.station)))) {
            return false;
        }
        if ((structure!= othersame.structure)&&((structure == null)||(!structure.equals(othersame.structure)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((agent == null)? 0 :agent.hashCode())+((alliance == null)? 0 :alliance.hashCode()))+((character == null)? 0 :character.hashCode()))+((constellation == null)? 0 :constellation.hashCode()))+((corporation == null)? 0 :corporation.hashCode()))+((faction == null)? 0 :faction.hashCode()))+((inventory_type == null)? 0 :inventory_type.hashCode()))+((region == null)? 0 :region.hashCode()))+((solar_system == null)? 0 :solar_system.hashCode()))+((station == null)? 0 :station.hashCode()))+((structure == null)? 0 :structure.hashCode()));
    }
}
