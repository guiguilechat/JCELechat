package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_calendar_event_id_attendees {
    /**
     * character_id integer
     */
    public int character_id;
    /**
     * event_response string
     */
    public String event_response;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_calendar_event_id_attendees othersame = ((R_get_characters_character_id_calendar_event_id_attendees) other);
        if (character_id!= othersame.character_id) {
            return false;
        }
        if ((event_response!= othersame.event_response)&&((event_response == null)||(!event_response.equals(othersame.event_response)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (character_id +((event_response == null)? 0 :event_response.hashCode()));
    }
}
