package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub.skinr;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub.skinr.corporations.Corporationid;

public class Corporations {
    public final EsiOauth2 security;
    public Corporationid corporationid;

    public Corporations(EsiOauth2 security) {
        this.security = security;
        corporationid = new Corporationid(security);
    }
}
