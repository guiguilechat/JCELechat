package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_status {
    /**
     * Current online player count
     */
    public long players;
    /**
     * Running version as string
     */
    public String server_version;
    /**
     * Server start timestamp
     */
    public String start_time;
    /**
     * If the server is in VIP mode
     */
    public boolean vip;
}
