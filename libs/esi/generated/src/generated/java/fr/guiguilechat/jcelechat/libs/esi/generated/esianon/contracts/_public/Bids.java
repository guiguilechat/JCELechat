package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts._public;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts._public.bids.Contractid;

public class Bids {
    public final EsiAnon security;
    public Contractid contractid;

    public Bids(EsiAnon security) {
        this.security = security;
        contractid = new Contractid(security);
    }
}
