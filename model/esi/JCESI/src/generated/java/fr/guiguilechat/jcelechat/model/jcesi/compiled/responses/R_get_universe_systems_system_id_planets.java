package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_universe_systems_system_id_planets {
    /**
     * asteroid_belts array
     */
    public int[] asteroid_belts;
    /**
     * moons array
     */
    public int[] moons;
    /**
     * planet_id integer
     */
    public int planet_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_systems_system_id_planets othersame = ((R_get_universe_systems_system_id_planets) other);
        if ((asteroid_belts!= othersame.asteroid_belts)&&((asteroid_belts == null)||(!asteroid_belts.equals(othersame.asteroid_belts)))) {
            return false;
        }
        if ((moons!= othersame.moons)&&((moons == null)||(!moons.equals(othersame.moons)))) {
            return false;
        }
        if (planet_id!= othersame.planet_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((asteroid_belts == null)? 0 :asteroid_belts.hashCode())+((moons == null)? 0 :moons.hashCode()))+ planet_id);
    }
}
