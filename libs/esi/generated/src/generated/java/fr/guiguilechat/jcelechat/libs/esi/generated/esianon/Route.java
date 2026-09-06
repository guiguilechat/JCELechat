package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.route.Originsystemid;

public class Route {
    public final EsiAnon security;
    public Originsystemid originsystemid;

    public Route(EsiAnon security) {
        this.security = security;
        originsystemid = new Originsystemid(security);
    }
}
