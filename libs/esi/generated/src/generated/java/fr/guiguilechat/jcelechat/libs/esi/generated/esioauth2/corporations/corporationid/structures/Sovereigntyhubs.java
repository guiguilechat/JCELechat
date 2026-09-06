package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.structures;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.structures.sovereigntyhubs.Sovereigntyhubid;

public class Sovereigntyhubs {
    public final EsiOauth2 security;
    public final Sovereigntyhubs.GET GET = new Sovereigntyhubs.GET();
    public Sovereigntyhubid sovereigntyhubid;

    public Sovereigntyhubs(EsiOauth2 security) {
        this.security = security;
        sovereigntyhubid = new Sovereigntyhubid(security);
    }

    public class GET {
    }
}
