package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_alliances_alliance_id {
    /**
     * ID of the corporation that created the alliance
     */
    public int creator_corporation_id;
    /**
     * ID of the character that created the alliance
     */
    public int creator_id;
    /**
     * date_founded string
     */
    public String date_founded;
    /**
     * the executor corporation ID, if this alliance is not closed
     */
    public int executor_corporation_id;
    /**
     * Faction ID this alliance is fighting for, if this alliance is enlisted in factional warfare
     */
    public int faction_id;
    /**
     * the full name of the alliance
     */
    public String name;
    /**
     * the short name of the alliance
     */
    public String ticker;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_alliances_alliance_id othersame = ((R_get_alliances_alliance_id) other);
        if (creator_corporation_id!= othersame.creator_corporation_id) {
            return false;
        }
        if (creator_id!= othersame.creator_id) {
            return false;
        }
        if ((date_founded!= othersame.date_founded)&&((date_founded == null)||(!date_founded.equals(othersame.date_founded)))) {
            return false;
        }
        if (executor_corporation_id!= othersame.executor_corporation_id) {
            return false;
        }
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if ((ticker!= othersame.ticker)&&((ticker == null)||(!ticker.equals(othersame.ticker)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((creator_corporation_id + creator_id)+((date_founded == null)? 0 :date_founded.hashCode()))+ executor_corporation_id)+ faction_id)+((name == null)? 0 :name.hashCode()))+((ticker == null)? 0 :ticker.hashCode()));
    }
}
