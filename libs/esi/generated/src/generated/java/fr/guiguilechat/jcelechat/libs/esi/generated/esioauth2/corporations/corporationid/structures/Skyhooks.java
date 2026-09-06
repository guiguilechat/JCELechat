package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.structures;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.structures.skyhooks.Skyhookid;

public class Skyhooks {
    public final EsiOauth2 security;
    public final Skyhooks.GET GET = new Skyhooks.GET();
    public Skyhookid skyhookid;

    public Skyhooks(EsiOauth2 security) {
        this.security = security;
        skyhookid = new Skyhookid(security);
    }

    public class GET {
    }
}
