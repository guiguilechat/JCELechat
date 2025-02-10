package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_asteroid_belts_asteroid_belt_id {
    /**
     * name string
     */
    public String name;
    /**
     * position object
     */
    public get_killmails_killmail_id_killmail_hash_position position;
    /**
     * The solar system this asteroid belt is in
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
        R_get_universe_asteroid_belts_asteroid_belt_id othersame = ((R_get_universe_asteroid_belts_asteroid_belt_id) other);
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if ((position!= othersame.position)&&((position == null)||(!position.equals(othersame.position)))) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((name == null)? 0 :name.hashCode())+((position == null)? 0 :position.hashCode()))+ system_id);
    }
}
