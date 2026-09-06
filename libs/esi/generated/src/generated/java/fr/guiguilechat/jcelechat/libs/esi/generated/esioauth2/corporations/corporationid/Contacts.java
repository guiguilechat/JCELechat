package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.contacts.Labels;

public class Contacts {
    public final EsiOauth2 security;
    public final Contacts.GET GET = new Contacts.GET();
    public Labels labels;

    public Contacts(EsiOauth2 security) {
        this.security = security;
        labels = new Labels(security);
    }

    public class GET {
    }
}
