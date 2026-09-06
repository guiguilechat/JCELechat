package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.notifications.Contacts;

public class Notifications {
    public final EsiOauth2 security;
    public final Notifications.GET GET = new Notifications.GET();
    public Contacts contacts;

    public Notifications(EsiOauth2 security) {
        this.security = security;
        contacts = new Contacts(security);
    }

    public class GET {
    }
}
