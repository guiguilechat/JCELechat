package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.stargates.Stargateid;

public class Stargates {
    public final EsiAnon security;
    public Stargateid stargateid;

    public Stargates(EsiAnon security) {
        this.security = security;
        stargateid = new Stargateid(security);
    }
}
