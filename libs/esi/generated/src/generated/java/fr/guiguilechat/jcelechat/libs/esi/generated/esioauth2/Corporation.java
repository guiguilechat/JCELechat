package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporation.Corporationid;

public class Corporation {
    public final EsiOauth2 security;
    public Corporationid corporationid;

    public Corporation(EsiOauth2 security) {
        this.security = security;
        corporationid = new Corporationid(security);
    }
}
