package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts._public;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts._public.items.Contractid;

public class Items {
    public final EsiAnon security;
    public Contractid contractid;

    public Items(EsiAnon security) {
        this.security = security;
        contractid = new Contractid(security);
    }
}
