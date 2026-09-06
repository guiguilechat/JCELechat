package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.calendar.Eventid;

public class Calendar {
    public final EsiOauth2 security;
    public final Calendar.GET GET = new Calendar.GET();
    public Eventid eventid;

    public Calendar(EsiOauth2 security) {
        this.security = security;
        eventid = new Eventid(security);
    }

    public class GET {
    }
}
