package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.route;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.route.originsystemid.Destinationsystemid;

public class Originsystemid {
    public final EsiAnon security;
    public Destinationsystemid destinationsystemid;

    public Originsystemid(EsiAnon security) {
        this.security = security;
        destinationsystemid = new Destinationsystemid(security);
    }
}
