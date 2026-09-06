package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;

public class Prices {
    public final EsiAnon security;
    public final Prices.GET GET = new Prices.GET();

    public Prices(EsiAnon security) {
        this.security = security;
    }

    public class GET {
    }
}
