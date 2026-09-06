package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.loyalty.Stores;

public class Loyalty {
    public final EsiAnon security;
    public Stores stores;

    public Loyalty(EsiAnon security) {
        this.security = security;
        stores = new Stores(security);
    }
}
