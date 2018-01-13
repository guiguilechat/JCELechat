package is.ccp.tech.esi.responses;

public class R_get_characters_character_id_chat_channels {
    public long channel_id;
    public String name;
    public long owner_id;
    public String comparison_key;
    public boolean has_password;
    public String motd;
    public R_get_characters_character_id_chat_channels_allowed[] allowed;
    public R_get_characters_character_id_chat_channels_operators[] operators;
    public R_get_characters_character_id_chat_channels_blocked[] blocked;
    public R_get_characters_character_id_chat_channels_muted[] muted;
}
