package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.structures.Skyhooks;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.structures.Sovereigntyhubs;

public class Structures {
    public final EsiOauth2 security;
    public final Structures.GET GET = new Structures.GET();
    public Skyhooks skyhooks;
    public Sovereigntyhubs sovereigntyhubs;

    public Structures(EsiOauth2 security) {
        this.security = security;
        skyhooks = new Skyhooks(security);
        sovereigntyhubs = new Sovereigntyhubs(security);
    }

    public class GET {
    }
}
