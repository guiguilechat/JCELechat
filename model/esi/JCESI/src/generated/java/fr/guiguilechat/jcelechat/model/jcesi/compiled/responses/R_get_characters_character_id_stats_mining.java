package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_stats_mining {
    /**
     * drone_mine integer
     */
    public long drone_mine;
    /**
     * ore_arkonor integer
     */
    public long ore_arkonor;
    /**
     * ore_bistot integer
     */
    public long ore_bistot;
    /**
     * ore_crokite integer
     */
    public long ore_crokite;
    /**
     * ore_dark_ochre integer
     */
    public long ore_dark_ochre;
    /**
     * ore_gneiss integer
     */
    public long ore_gneiss;
    /**
     * ore_harvestable_cloud integer
     */
    public long ore_harvestable_cloud;
    /**
     * ore_hedbergite integer
     */
    public long ore_hedbergite;
    /**
     * ore_hemorphite integer
     */
    public long ore_hemorphite;
    /**
     * ore_ice integer
     */
    public long ore_ice;
    /**
     * ore_jaspet integer
     */
    public long ore_jaspet;
    /**
     * ore_kernite integer
     */
    public long ore_kernite;
    /**
     * ore_mercoxit integer
     */
    public long ore_mercoxit;
    /**
     * ore_omber integer
     */
    public long ore_omber;
    /**
     * ore_plagioclase integer
     */
    public long ore_plagioclase;
    /**
     * ore_pyroxeres integer
     */
    public long ore_pyroxeres;
    /**
     * ore_scordite integer
     */
    public long ore_scordite;
    /**
     * ore_spodumain integer
     */
    public long ore_spodumain;
    /**
     * ore_veldspar integer
     */
    public long ore_veldspar;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_mining othersame = ((R_get_characters_character_id_stats_mining) other);
        if (drone_mine!= othersame.drone_mine) {
            return false;
        }
        if (ore_arkonor!= othersame.ore_arkonor) {
            return false;
        }
        if (ore_bistot!= othersame.ore_bistot) {
            return false;
        }
        if (ore_crokite!= othersame.ore_crokite) {
            return false;
        }
        if (ore_dark_ochre!= othersame.ore_dark_ochre) {
            return false;
        }
        if (ore_gneiss!= othersame.ore_gneiss) {
            return false;
        }
        if (ore_harvestable_cloud!= othersame.ore_harvestable_cloud) {
            return false;
        }
        if (ore_hedbergite!= othersame.ore_hedbergite) {
            return false;
        }
        if (ore_hemorphite!= othersame.ore_hemorphite) {
            return false;
        }
        if (ore_ice!= othersame.ore_ice) {
            return false;
        }
        if (ore_jaspet!= othersame.ore_jaspet) {
            return false;
        }
        if (ore_kernite!= othersame.ore_kernite) {
            return false;
        }
        if (ore_mercoxit!= othersame.ore_mercoxit) {
            return false;
        }
        if (ore_omber!= othersame.ore_omber) {
            return false;
        }
        if (ore_plagioclase!= othersame.ore_plagioclase) {
            return false;
        }
        if (ore_pyroxeres!= othersame.ore_pyroxeres) {
            return false;
        }
        if (ore_scordite!= othersame.ore_scordite) {
            return false;
        }
        if (ore_spodumain!= othersame.ore_spodumain) {
            return false;
        }
        if (ore_veldspar!= othersame.ore_veldspar) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((((Long.hashCode(drone_mine)+ Long.hashCode(ore_arkonor))+ Long.hashCode(ore_bistot))+ Long.hashCode(ore_crokite))+ Long.hashCode(ore_dark_ochre))+ Long.hashCode(ore_gneiss))+ Long.hashCode(ore_harvestable_cloud))+ Long.hashCode(ore_hedbergite))+ Long.hashCode(ore_hemorphite))+ Long.hashCode(ore_ice))+ Long.hashCode(ore_jaspet))+ Long.hashCode(ore_kernite))+ Long.hashCode(ore_mercoxit))+ Long.hashCode(ore_omber))+ Long.hashCode(ore_plagioclase))+ Long.hashCode(ore_pyroxeres))+ Long.hashCode(ore_scordite))+ Long.hashCode(ore_spodumain))+ Long.hashCode(ore_veldspar));
    }
}
