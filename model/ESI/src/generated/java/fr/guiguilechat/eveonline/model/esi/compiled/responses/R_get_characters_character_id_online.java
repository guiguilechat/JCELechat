package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_characters_character_id_online {
    /**
     * Timestamp of the last login
     */
    public String last_login;
    /**
     * Timestamp of the last logout
     */
    public String last_logout;
    /**
     * Total number of times the character has logged in
     */
    public int logins;
    /**
     * If the character is online
     */
    public boolean online;
}
