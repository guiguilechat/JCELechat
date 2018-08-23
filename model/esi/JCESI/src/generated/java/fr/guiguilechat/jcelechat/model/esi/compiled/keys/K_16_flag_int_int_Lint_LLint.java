package fr.guiguilechat.jcelechat.model.esi.compiled.keys;


/**
 * @see https://esi.evetech.net/v1/route/{origin}/{destination}/
 * 
 */
public class K_16_flag_int_int_Lint_LLint {
    public final fr.guiguilechat.jcelechat.model.esi.compiled.structures.flag flag;
    public final int origin;
    public final int destination;
    public final int[] avoid;
    public final int[][] connections;

    public K_16_flag_int_int_Lint_LLint(fr.guiguilechat.jcelechat.model.esi.compiled.structures.flag flag, int origin, int destination, int[] avoid, int[][] connections) {
        this.flag = flag;
        this.origin = origin;
        this.destination = destination;
        this.avoid = avoid;
        this.connections = connections;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_16_flag_int_int_Lint_LLint other2 = ((K_16_flag_int_int_Lint_LLint) other);
        return ((((((flag == other2 .flag)||((flag!= null)&&flag.equals(other2 .flag)))&&(origin == other2 .origin))&&(destination == other2 .destination))&&((avoid == other2 .avoid)||((avoid!= null)&&avoid.equals(other2 .avoid))))&&((connections == other2 .connections)||((connections!= null)&&connections.equals(other2 .connections))));
    }

    public int hashCode() {
        return ((int)(((((0 +((flag == null)? 0 :flag.hashCode()))+ origin)+ destination)+((avoid == null)? 0 :avoid.hashCode()))+((connections == null)? 0 :connections.hashCode())));
    }
}
