package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_3_xnumber_ynumber_znumber {
    /**
     * x number
     */
    public double x;
    /**
     * y number
     */
    public double y;
    /**
     * z number
     */
    public double z;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_3_xnumber_ynumber_znumber othersame = ((M_3_xnumber_ynumber_znumber) other);
        if (x!= othersame.x) {
            return false;
        }
        if (y!= othersame.y) {
            return false;
        }
        if (z!= othersame.z) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Double.hashCode(x)+ Double.hashCode(y))+ Double.hashCode(z));
    }
}
