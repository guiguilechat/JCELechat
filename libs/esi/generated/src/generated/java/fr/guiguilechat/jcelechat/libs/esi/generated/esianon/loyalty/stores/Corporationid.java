package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.loyalty.stores;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.loyalty.stores.corporationid.Offers;

public class Corporationid {
    public final EsiAnon security;
    public Offers offers;

    public Corporationid(EsiAnon security) {
        this.security = security;
        offers = new Offers(security);
    }
}
