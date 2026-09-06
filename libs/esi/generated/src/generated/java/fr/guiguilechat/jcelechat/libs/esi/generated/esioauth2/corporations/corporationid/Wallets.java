package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.wallets.Division;

public class Wallets {
    public final EsiOauth2 security;
    public final Wallets.GET GET = new Wallets.GET();
    public Division division;

    public Wallets(EsiOauth2 security) {
        this.security = security;
        division = new Division(security);
    }

    public class GET {
    }
}
