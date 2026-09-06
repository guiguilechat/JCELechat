package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.assets.Locations;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.assets.Names;

public class Assets {
    public final EsiOauth2 security;
    public final Assets.GET GET = new Assets.GET();
    public Locations locations;
    public Names names;

    public Assets(EsiOauth2 security) {
        this.security = security;
        locations = new Locations(security);
        names = new Names(security);
    }

    public class GET {
    }
}
