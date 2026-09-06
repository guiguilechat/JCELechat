package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts._public.bids;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;

public class Contractid {
    public final EsiAnon security;
    public final Contractid.GET GET = new Contractid.GET();

    public Contractid(EsiAnon security) {
        this.security = security;
    }

    public class GET {
    }
}
