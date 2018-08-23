package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_fw_leaderboards_3 {
    /**
     * Top 4 ranking of factions active in faction warfare by total kills. A faction is considered "active" if they have participated in faction warfare in the past 14 days.
     */
    public get_fw_leaderboards_active_total[] active_total;
    /**
     * Top 4 ranking of factions by kills in the past week
     */
    public get_fw_leaderboards_active_total[] last_week;
    /**
     * Top 4 ranking of factions by kills in the past day
     */
    public get_fw_leaderboards_active_total[] yesterday;
}
