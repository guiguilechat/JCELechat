package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub.Skinr;

public class Paragonhub {
    public final EsiOauth2 security;
    public Skinr skinr;

    public Paragonhub(EsiOauth2 security) {
        this.security = security;
        skinr = new Skinr(security);
    }
}
