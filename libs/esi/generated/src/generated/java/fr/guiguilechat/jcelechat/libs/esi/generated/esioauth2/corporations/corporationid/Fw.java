package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.fw.Stats;

public class Fw {
    public final EsiOauth2 security;
    public Stats stats;

    public Fw(EsiOauth2 security) {
        this.security = security;
        stats = new Stats(security);
    }
}
