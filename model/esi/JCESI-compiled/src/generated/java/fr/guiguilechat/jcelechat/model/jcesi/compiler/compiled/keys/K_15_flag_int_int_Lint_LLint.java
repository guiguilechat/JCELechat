package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/route/{origin}/{destination}/
 * 
 */
public class K_15_flag_int_int_Lint_LLint {
    public final fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag flag;
    public final int origin;
    public final int destination;
    public final int[] avoid;
    public final int[][] connections;

    public K_15_flag_int_int_Lint_LLint(fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.flag flag,
        int origin,
        int destination,
        int[] avoid,
        int[][] connections) {
        this.flag = flag;
        this.origin = origin;
        this.destination = destination;
        this.avoid = avoid;
        this.connections = connections;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_15_flag_int_int_Lint_LLint othersame = ((K_15_flag_int_int_Lint_LLint) other);
        if ((flag!= othersame.flag)&&((flag == null)||(!flag.equals(othersame.flag)))) {
            return false;
        }
        if (origin!= othersame.origin) {
            return false;
        }
        if (destination!= othersame.destination) {
            return false;
        }
        if ((avoid!= othersame.avoid)&&((avoid == null)||(!avoid.equals(othersame.avoid)))) {
            return false;
        }
        if ((connections!= othersame.connections)&&((connections == null)||(!connections.equals(othersame.connections)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((flag == null)? 0 :flag.hashCode())+ origin)+ destination)+((avoid == null)? 0 :avoid.hashCode()))+((connections == null)? 0 :connections.hashCode()));
    }
}
