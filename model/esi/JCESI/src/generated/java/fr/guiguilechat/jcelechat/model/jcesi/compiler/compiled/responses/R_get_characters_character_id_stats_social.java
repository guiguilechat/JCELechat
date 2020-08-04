package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_stats_social {
    /**
     * add_contact_bad integer
     */
    public long add_contact_bad;
    /**
     * add_contact_good integer
     */
    public long add_contact_good;
    /**
     * add_contact_high integer
     */
    public long add_contact_high;
    /**
     * add_contact_horrible integer
     */
    public long add_contact_horrible;
    /**
     * add_contact_neutral integer
     */
    public long add_contact_neutral;
    /**
     * add_note integer
     */
    public long add_note;
    /**
     * added_as_contact_bad integer
     */
    public long added_as_contact_bad;
    /**
     * added_as_contact_good integer
     */
    public long added_as_contact_good;
    /**
     * added_as_contact_high integer
     */
    public long added_as_contact_high;
    /**
     * added_as_contact_horrible integer
     */
    public long added_as_contact_horrible;
    /**
     * added_as_contact_neutral integer
     */
    public long added_as_contact_neutral;
    /**
     * calendar_event_created integer
     */
    public long calendar_event_created;
    /**
     * chat_messages_alliance integer
     */
    public long chat_messages_alliance;
    /**
     * chat_messages_constellation integer
     */
    public long chat_messages_constellation;
    /**
     * chat_messages_corporation integer
     */
    public long chat_messages_corporation;
    /**
     * chat_messages_fleet integer
     */
    public long chat_messages_fleet;
    /**
     * chat_messages_region integer
     */
    public long chat_messages_region;
    /**
     * chat_messages_solarsystem integer
     */
    public long chat_messages_solarsystem;
    /**
     * chat_messages_warfaction integer
     */
    public long chat_messages_warfaction;
    /**
     * chat_total_message_length integer
     */
    public long chat_total_message_length;
    /**
     * direct_trades integer
     */
    public long direct_trades;
    /**
     * fleet_broadcasts integer
     */
    public long fleet_broadcasts;
    /**
     * fleet_joins integer
     */
    public long fleet_joins;
    /**
     * mails_received integer
     */
    public long mails_received;
    /**
     * mails_sent integer
     */
    public long mails_sent;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_social othersame = ((R_get_characters_character_id_stats_social) other);
        if (add_contact_bad!= othersame.add_contact_bad) {
            return false;
        }
        if (add_contact_good!= othersame.add_contact_good) {
            return false;
        }
        if (add_contact_high!= othersame.add_contact_high) {
            return false;
        }
        if (add_contact_horrible!= othersame.add_contact_horrible) {
            return false;
        }
        if (add_contact_neutral!= othersame.add_contact_neutral) {
            return false;
        }
        if (add_note!= othersame.add_note) {
            return false;
        }
        if (added_as_contact_bad!= othersame.added_as_contact_bad) {
            return false;
        }
        if (added_as_contact_good!= othersame.added_as_contact_good) {
            return false;
        }
        if (added_as_contact_high!= othersame.added_as_contact_high) {
            return false;
        }
        if (added_as_contact_horrible!= othersame.added_as_contact_horrible) {
            return false;
        }
        if (added_as_contact_neutral!= othersame.added_as_contact_neutral) {
            return false;
        }
        if (calendar_event_created!= othersame.calendar_event_created) {
            return false;
        }
        if (chat_messages_alliance!= othersame.chat_messages_alliance) {
            return false;
        }
        if (chat_messages_constellation!= othersame.chat_messages_constellation) {
            return false;
        }
        if (chat_messages_corporation!= othersame.chat_messages_corporation) {
            return false;
        }
        if (chat_messages_fleet!= othersame.chat_messages_fleet) {
            return false;
        }
        if (chat_messages_region!= othersame.chat_messages_region) {
            return false;
        }
        if (chat_messages_solarsystem!= othersame.chat_messages_solarsystem) {
            return false;
        }
        if (chat_messages_warfaction!= othersame.chat_messages_warfaction) {
            return false;
        }
        if (chat_total_message_length!= othersame.chat_total_message_length) {
            return false;
        }
        if (direct_trades!= othersame.direct_trades) {
            return false;
        }
        if (fleet_broadcasts!= othersame.fleet_broadcasts) {
            return false;
        }
        if (fleet_joins!= othersame.fleet_joins) {
            return false;
        }
        if (mails_received!= othersame.mails_received) {
            return false;
        }
        if (mails_sent!= othersame.mails_sent) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((((((((((Long.hashCode(add_contact_bad)+ Long.hashCode(add_contact_good))+ Long.hashCode(add_contact_high))+ Long.hashCode(add_contact_horrible))+ Long.hashCode(add_contact_neutral))+ Long.hashCode(add_note))+ Long.hashCode(added_as_contact_bad))+ Long.hashCode(added_as_contact_good))+ Long.hashCode(added_as_contact_high))+ Long.hashCode(added_as_contact_horrible))+ Long.hashCode(added_as_contact_neutral))+ Long.hashCode(calendar_event_created))+ Long.hashCode(chat_messages_alliance))+ Long.hashCode(chat_messages_constellation))+ Long.hashCode(chat_messages_corporation))+ Long.hashCode(chat_messages_fleet))+ Long.hashCode(chat_messages_region))+ Long.hashCode(chat_messages_solarsystem))+ Long.hashCode(chat_messages_warfaction))+ Long.hashCode(chat_total_message_length))+ Long.hashCode(direct_trades))+ Long.hashCode(fleet_broadcasts))+ Long.hashCode(fleet_joins))+ Long.hashCode(mails_received))+ Long.hashCode(mails_sent));
    }
}
