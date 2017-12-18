
package is.ccp.tech.esi.responses;


public class get_characters_character_id_chat_channels_ok {

    public int channel_id;
    public String name;
    public int owner_id;
    public String comparison_key;
    public boolean has_password;
    public String motd;
    public get_characters_character_id_chat_channels_ok_allowed[] allowed;
    public get_characters_character_id_chat_channels_ok_operators[] operators;
    public get_characters_character_id_chat_channels_ok_blocked[] blocked;
    public get_characters_character_id_chat_channels_ok_muted[] muted;

}
