package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.stars.Starid;

public class Stars {
    public final EsiAnon security;
    public Starid starid;

    public Stars(EsiAnon security) {
        this.security = security;
        starid = new Starid(security);
    }
}
