package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.medals.Issued;

public class Medals {
    public final EsiOauth2 security;
    public final Medals.GET GET = new Medals.GET();
    public Issued issued;

    public Medals(EsiOauth2 security) {
        this.security = security;
        issued = new Issued(security);
    }

    public class GET {
    }
}
