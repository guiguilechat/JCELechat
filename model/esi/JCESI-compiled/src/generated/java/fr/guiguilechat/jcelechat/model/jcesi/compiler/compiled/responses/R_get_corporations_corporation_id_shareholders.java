package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_shareholders_shareholder_type;

public class R_get_corporations_corporation_id_shareholders {
    /**
     * share_count integer
     */
    public long share_count;
    /**
     * shareholder_id integer
     */
    public int shareholder_id;
    /**
     * shareholder_type string
     */
    public get_corporations_corporation_id_shareholders_shareholder_type shareholder_type;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_shareholders othersame = ((R_get_corporations_corporation_id_shareholders) other);
        if (share_count!= othersame.share_count) {
            return false;
        }
        if (shareholder_id!= othersame.shareholder_id) {
            return false;
        }
        if ((shareholder_type!= othersame.shareholder_type)&&((shareholder_type == null)||(!shareholder_type.equals(othersame.shareholder_type)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Long.hashCode(share_count)+ shareholder_id)+((shareholder_type == null)? 0 :shareholder_type.hashCode()));
    }
}
