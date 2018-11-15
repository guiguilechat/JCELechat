package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_markets_region_id_history {
    /**
     * average number
     */
    public double average;
    /**
     * The date of this historical statistic entry
     */
    public String date;
    /**
     * highest number
     */
    public double highest;
    /**
     * lowest number
     */
    public double lowest;
    /**
     * Total number of orders happened that day
     */
    public long order_count;
    /**
     * Total
     */
    public long volume;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_markets_region_id_history othersame = ((R_get_markets_region_id_history) other);
        if (average!= othersame.average) {
            return false;
        }
        if ((date!= othersame.date)&&((date == null)||(!date.equals(othersame.date)))) {
            return false;
        }
        if (highest!= othersame.highest) {
            return false;
        }
        if (lowest!= othersame.lowest) {
            return false;
        }
        if (order_count!= othersame.order_count) {
            return false;
        }
        if (volume!= othersame.volume) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((Double.hashCode(average)+((date == null)? 0 :date.hashCode()))+ Double.hashCode(highest))+ Double.hashCode(lowest))+ Long.hashCode(order_count))+ Long.hashCode(volume));
    }
}
