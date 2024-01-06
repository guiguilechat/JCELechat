package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_corporations_corporation_id_wallets {
    /**
     * balance number
     */
    public double balance;
    /**
     * division integer
     */
    public int division;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_wallets othersame = ((R_get_corporations_corporation_id_wallets) other);
        if (balance!= othersame.balance) {
            return false;
        }
        if (division!= othersame.division) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Double.hashCode(balance)+ division);
    }
}
