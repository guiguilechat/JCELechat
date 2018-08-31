package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_post_assets_locations_2 {
    /**
     * item_id integer
     */
    public long item_id;
    /**
     * position object
     */
    public M_3_xnumber_ynumber_znumber position;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_post_assets_locations_2 othersame = ((M_post_assets_locations_2) other);
        if (item_id!= othersame.item_id) {
            return false;
        }
        if ((position!= othersame.position)&&((position == null)||(!position.equals(othersame.position)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(item_id)+((position == null)? 0 :position.hashCode()));
    }
}
