package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_stats {
    /**
     * character object
     */
    public R_get_characters_character_id_stats_character character;
    /**
     * combat object
     */
    public R_get_characters_character_id_stats_combat combat;
    /**
     * industry object
     */
    public R_get_characters_character_id_stats_industry industry;
    /**
     * inventory object
     */
    public R_get_characters_character_id_stats_inventory inventory;
    /**
     * isk object
     */
    public R_get_characters_character_id_stats_isk isk;
    /**
     * market object
     */
    public R_get_characters_character_id_stats_market market;
    /**
     * mining object
     */
    public R_get_characters_character_id_stats_mining mining;
    /**
     * module object
     */
    public R_get_characters_character_id_stats_module module;
    /**
     * orbital object
     */
    public R_get_characters_character_id_stats_orbital orbital;
    /**
     * pve object
     */
    public R_get_characters_character_id_stats_pve pve;
    /**
     * social object
     */
    public R_get_characters_character_id_stats_social social;
    /**
     * travel object
     */
    public R_get_characters_character_id_stats_travel travel;
    /**
     * Gregorian year for this set of aggregates
     */
    public int year;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats othersame = ((R_get_characters_character_id_stats) other);
        if ((character!= othersame.character)&&((character == null)||(!character.equals(othersame.character)))) {
            return false;
        }
        if ((combat!= othersame.combat)&&((combat == null)||(!combat.equals(othersame.combat)))) {
            return false;
        }
        if ((industry!= othersame.industry)&&((industry == null)||(!industry.equals(othersame.industry)))) {
            return false;
        }
        if ((inventory!= othersame.inventory)&&((inventory == null)||(!inventory.equals(othersame.inventory)))) {
            return false;
        }
        if ((isk!= othersame.isk)&&((isk == null)||(!isk.equals(othersame.isk)))) {
            return false;
        }
        if ((market!= othersame.market)&&((market == null)||(!market.equals(othersame.market)))) {
            return false;
        }
        if ((mining!= othersame.mining)&&((mining == null)||(!mining.equals(othersame.mining)))) {
            return false;
        }
        if ((module!= othersame.module)&&((module == null)||(!module.equals(othersame.module)))) {
            return false;
        }
        if ((orbital!= othersame.orbital)&&((orbital == null)||(!orbital.equals(othersame.orbital)))) {
            return false;
        }
        if ((pve!= othersame.pve)&&((pve == null)||(!pve.equals(othersame.pve)))) {
            return false;
        }
        if ((social!= othersame.social)&&((social == null)||(!social.equals(othersame.social)))) {
            return false;
        }
        if ((travel!= othersame.travel)&&((travel == null)||(!travel.equals(othersame.travel)))) {
            return false;
        }
        if (year!= othersame.year) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((character == null)? 0 :character.hashCode())+((combat == null)? 0 :combat.hashCode()))+((industry == null)? 0 :industry.hashCode()))+((inventory == null)? 0 :inventory.hashCode()))+((isk == null)? 0 :isk.hashCode()))+((market == null)? 0 :market.hashCode()))+((mining == null)? 0 :mining.hashCode()))+((module == null)? 0 :module.hashCode()))+((orbital == null)? 0 :orbital.hashCode()))+((pve == null)? 0 :pve.hashCode()))+((social == null)? 0 :social.hashCode()))+((travel == null)? 0 :travel.hashCode()))+ year);
    }
}
