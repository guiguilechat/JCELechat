package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_corporations_corporation_id_divisions {
    /**
     * hangar array
     */
    public M_get_corporations_corporation_divisions_2 [] hangar;
    /**
     * wallet array
     */
    public M_get_corporations_corporation_divisions_2 [] wallet;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_divisions othersame = ((R_get_corporations_corporation_id_divisions) other);
        if ((hangar!= othersame.hangar)&&((hangar == null)||(!hangar.equals(othersame.hangar)))) {
            return false;
        }
        if ((wallet!= othersame.wallet)&&((wallet == null)||(!wallet.equals(othersame.wallet)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((hangar == null)? 0 :hangar.hashCode())+((wallet == null)? 0 :wallet.hashCode()));
    }
}
