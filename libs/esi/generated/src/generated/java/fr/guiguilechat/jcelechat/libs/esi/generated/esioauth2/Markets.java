package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.markets.Structures;

public class Markets {
    public final EsiOauth2 security;
    public Structures structures;

    public Markets(EsiOauth2 security) {
        this.security = security;
        structures = new Structures(security);
    }
}
