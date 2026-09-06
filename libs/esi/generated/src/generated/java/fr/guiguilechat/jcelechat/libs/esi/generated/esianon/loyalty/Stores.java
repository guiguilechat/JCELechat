package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.loyalty;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.loyalty.stores.Corporationid;

public class Stores {
    public final EsiAnon security;
    public Corporationid corporationid;

    public Stores(EsiAnon security) {
        this.security = security;
        corporationid = new Corporationid(security);
    }
}
