package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets.regionid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;

public class Orders {
    public final EsiAnon security;
    public final Orders.GET GET = new Orders.GET();

    public Orders(EsiAnon security) {
        this.security = security;
    }

    public class GET {
    }
}
