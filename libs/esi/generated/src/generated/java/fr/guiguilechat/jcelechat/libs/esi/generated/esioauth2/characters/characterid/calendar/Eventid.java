package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.calendar;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.calendar.eventid.Attendees;

public class Eventid {
    public final EsiOauth2 security;
    public final Eventid.GET GET = new Eventid.GET();
    public final Eventid.PUT PUT = new Eventid.PUT();
    public Attendees attendees;

    public Eventid(EsiOauth2 security) {
        this.security = security;
        attendees = new Attendees(security);
    }

    public class GET {
    }

    public class PUT {
    }
}
