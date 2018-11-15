package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_fleets_fleet_id_wings_squads {
    /**
     * id integer
     */
    public long id;
    /**
     * name string
     */
    public String name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_fleets_fleet_id_wings_squads othersame = ((R_get_fleets_fleet_id_wings_squads) other);
        if (id!= othersame.id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(id)+((name == null)? 0 :name.hashCode()));
    }
}
