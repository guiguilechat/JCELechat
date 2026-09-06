package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.starbases.Starbaseid;

public class Starbases {
    public final EsiOauth2 security;
    public final Starbases.GET GET = new Starbases.GET();
    public Starbaseid starbaseid;

    public Starbases(EsiOauth2 security) {
        this.security = security;
        starbaseid = new Starbaseid(security);
    }

    public class GET {
    }
}
