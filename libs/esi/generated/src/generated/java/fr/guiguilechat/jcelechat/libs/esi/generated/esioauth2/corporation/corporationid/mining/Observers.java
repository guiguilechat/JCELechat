package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporation.corporationid.mining;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporation.corporationid.mining.observers.Observerid;

public class Observers {
    public final EsiOauth2 security;
    public final Observers.GET GET = new Observers.GET();
    public Observerid observerid;

    public Observers(EsiOauth2 security) {
        this.security = security;
        observerid = new Observerid(security);
    }

    public class GET {
    }
}
