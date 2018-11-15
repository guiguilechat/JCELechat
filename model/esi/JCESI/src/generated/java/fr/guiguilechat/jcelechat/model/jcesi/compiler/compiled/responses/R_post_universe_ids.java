package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_post_universe_ids {
    /**
     * agents array
     */
    public R_get_fleets_fleet_id_wings_squads[] agents;
    /**
     * alliances array
     */
    public R_get_fleets_fleet_id_wings_squads[] alliances;
    /**
     * characters array
     */
    public R_get_fleets_fleet_id_wings_squads[] characters;
    /**
     * constellations array
     */
    public R_get_fleets_fleet_id_wings_squads[] constellations;
    /**
     * corporations array
     */
    public R_get_fleets_fleet_id_wings_squads[] corporations;
    /**
     * factions array
     */
    public R_get_fleets_fleet_id_wings_squads[] factions;
    /**
     * inventory_types array
     */
    public R_get_fleets_fleet_id_wings_squads[] inventory_types;
    /**
     * regions array
     */
    public R_get_fleets_fleet_id_wings_squads[] regions;
    /**
     * stations array
     */
    public R_get_fleets_fleet_id_wings_squads[] stations;
    /**
     * systems array
     */
    public R_get_fleets_fleet_id_wings_squads[] systems;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_post_universe_ids othersame = ((R_post_universe_ids) other);
        if ((agents!= othersame.agents)&&((agents == null)||(!agents.equals(othersame.agents)))) {
            return false;
        }
        if ((alliances!= othersame.alliances)&&((alliances == null)||(!alliances.equals(othersame.alliances)))) {
            return false;
        }
        if ((characters!= othersame.characters)&&((characters == null)||(!characters.equals(othersame.characters)))) {
            return false;
        }
        if ((constellations!= othersame.constellations)&&((constellations == null)||(!constellations.equals(othersame.constellations)))) {
            return false;
        }
        if ((corporations!= othersame.corporations)&&((corporations == null)||(!corporations.equals(othersame.corporations)))) {
            return false;
        }
        if ((factions!= othersame.factions)&&((factions == null)||(!factions.equals(othersame.factions)))) {
            return false;
        }
        if ((inventory_types!= othersame.inventory_types)&&((inventory_types == null)||(!inventory_types.equals(othersame.inventory_types)))) {
            return false;
        }
        if ((regions!= othersame.regions)&&((regions == null)||(!regions.equals(othersame.regions)))) {
            return false;
        }
        if ((stations!= othersame.stations)&&((stations == null)||(!stations.equals(othersame.stations)))) {
            return false;
        }
        if ((systems!= othersame.systems)&&((systems == null)||(!systems.equals(othersame.systems)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((agents == null)? 0 :agents.hashCode())+((alliances == null)? 0 :alliances.hashCode()))+((characters == null)? 0 :characters.hashCode()))+((constellations == null)? 0 :constellations.hashCode()))+((corporations == null)? 0 :corporations.hashCode()))+((factions == null)? 0 :factions.hashCode()))+((inventory_types == null)? 0 :inventory_types.hashCode()))+((regions == null)? 0 :regions.hashCode()))+((stations == null)? 0 :stations.hashCode()))+((systems == null)? 0 :systems.hashCode()));
    }
}
