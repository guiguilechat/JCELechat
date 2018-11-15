package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_systems_system_id {
    /**
     * The constellation this solar system is in
     */
    public int constellation_id;
    /**
     * name string
     */
    public String name;
    /**
     * planets array
     */
    public get_universe_systems_system_id_planets[] planets;
    /**
     * position object
     */
    public M_3_xnumber_ynumber_znumber position;
    /**
     * security_class string
     */
    public String security_class;
    /**
     * security_status number
     */
    public float security_status;
    /**
     * star_id integer
     */
    public int star_id;
    /**
     * stargates array
     */
    public int[] stargates;
    /**
     * stations array
     */
    public int[] stations;
    /**
     * system_id integer
     */
    public int system_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_systems_system_id othersame = ((R_get_universe_systems_system_id) other);
        if (constellation_id!= othersame.constellation_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if ((planets!= othersame.planets)&&((planets == null)||(!planets.equals(othersame.planets)))) {
            return false;
        }
        if ((position!= othersame.position)&&((position == null)||(!position.equals(othersame.position)))) {
            return false;
        }
        if ((security_class!= othersame.security_class)&&((security_class == null)||(!security_class.equals(othersame.security_class)))) {
            return false;
        }
        if (security_status!= othersame.security_status) {
            return false;
        }
        if (star_id!= othersame.star_id) {
            return false;
        }
        if ((stargates!= othersame.stargates)&&((stargates == null)||(!stargates.equals(othersame.stargates)))) {
            return false;
        }
        if ((stations!= othersame.stations)&&((stations == null)||(!stations.equals(othersame.stations)))) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((constellation_id +((name == null)? 0 :name.hashCode()))+((planets == null)? 0 :planets.hashCode()))+((position == null)? 0 :position.hashCode()))+((security_class == null)? 0 :security_class.hashCode()))+ Double.hashCode(security_status))+ star_id)+((stargates == null)? 0 :stargates.hashCode()))+((stations == null)? 0 :stations.hashCode()))+ system_id);
    }
}
