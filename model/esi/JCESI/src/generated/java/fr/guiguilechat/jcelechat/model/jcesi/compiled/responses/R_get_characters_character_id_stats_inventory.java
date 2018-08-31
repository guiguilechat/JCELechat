package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_stats_inventory {
    /**
     * abandon_loot_quantity integer
     */
    public long abandon_loot_quantity;
    /**
     * trash_item_quantity integer
     */
    public long trash_item_quantity;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_inventory othersame = ((R_get_characters_character_id_stats_inventory) other);
        if (abandon_loot_quantity!= othersame.abandon_loot_quantity) {
            return false;
        }
        if (trash_item_quantity!= othersame.trash_item_quantity) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(abandon_loot_quantity)+ Long.hashCode(trash_item_quantity));
    }
}
