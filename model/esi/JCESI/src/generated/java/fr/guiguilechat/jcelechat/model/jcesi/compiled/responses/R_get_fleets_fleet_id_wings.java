package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_fleets_fleet_id_wings {
    /**
     * id integer
     */
    public long id;
    /**
     * name string
     */
    public String name;
    /**
     * squads array
     */
    public M_2_namestring_idinteger[] squads;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_fleets_fleet_id_wings othersame = ((R_get_fleets_fleet_id_wings) other);
        if (id!= othersame.id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if ((squads!= othersame.squads)&&((squads == null)||(!squads.equals(othersame.squads)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Long.hashCode(id)+((name == null)? 0 :name.hashCode()))+((squads == null)? 0 :squads.hashCode()));
    }
}
