package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.roles.History;

public class Roles {
    public final EsiOauth2 security;
    public final Roles.GET GET = new Roles.GET();
    public History history;

    public Roles(EsiOauth2 security) {
        this.security = security;
        history = new History(security);
    }

    public class GET {
    }
}
