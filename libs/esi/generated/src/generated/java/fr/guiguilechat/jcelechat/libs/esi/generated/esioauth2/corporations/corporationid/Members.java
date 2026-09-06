package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.members.Limit;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.members.Titles;

public class Members {
    public final EsiOauth2 security;
    public final Members.GET GET = new Members.GET();
    public Limit limit;
    public Titles titles;

    public Members(EsiOauth2 security) {
        this.security = security;
        limit = new Limit(security);
        titles = new Titles(security);
    }

    public class GET {
    }
}
