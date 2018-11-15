package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_corporations_corporation_id {
    /**
     * ID of the alliance that corporation is a member of, if any
     */
    public int alliance_id;
    /**
     * ceo_id integer
     */
    public int ceo_id;
    /**
     * creator_id integer
     */
    public int creator_id;
    /**
     * date_founded string
     */
    public String date_founded;
    /**
     * description string
     */
    public String description;
    /**
     * faction_id integer
     */
    public int faction_id;
    /**
     * home_station_id integer
     */
    public int home_station_id;
    /**
     * member_count integer
     */
    public int member_count;
    /**
     * the full name of the corporation
     */
    public String name;
    /**
     * shares integer
     */
    public long shares;
    /**
     * tax_rate number
     */
    public float tax_rate;
    /**
     * the short name of the corporation
     */
    public String ticker;
    /**
     * url string
     */
    public String url;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id othersame = ((R_get_corporations_corporation_id) other);
        if (alliance_id!= othersame.alliance_id) {
            return false;
        }
        if (ceo_id!= othersame.ceo_id) {
            return false;
        }
        if (creator_id!= othersame.creator_id) {
            return false;
        }
        if ((date_founded!= othersame.date_founded)&&((date_founded == null)||(!date_founded.equals(othersame.date_founded)))) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        if (home_station_id!= othersame.home_station_id) {
            return false;
        }
        if (member_count!= othersame.member_count) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (shares!= othersame.shares) {
            return false;
        }
        if (tax_rate!= othersame.tax_rate) {
            return false;
        }
        if ((ticker!= othersame.ticker)&&((ticker == null)||(!ticker.equals(othersame.ticker)))) {
            return false;
        }
        if ((url!= othersame.url)&&((url == null)||(!url.equals(othersame.url)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((alliance_id + ceo_id)+ creator_id)+((date_founded == null)? 0 :date_founded.hashCode()))+((description == null)? 0 :description.hashCode()))+ faction_id)+ home_station_id)+ member_count)+((name == null)? 0 :name.hashCode()))+ Long.hashCode(shares))+ Double.hashCode(tax_rate))+((ticker == null)? 0 :ticker.hashCode()))+((url == null)? 0 :url.hashCode()));
    }
}
