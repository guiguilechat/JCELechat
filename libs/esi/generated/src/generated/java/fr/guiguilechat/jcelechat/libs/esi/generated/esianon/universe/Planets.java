package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.planets.Planetid;

public class Planets {
    public final EsiAnon security;
    public Planetid planetid;

    public Planets(EsiAnon security) {
        this.security = security;
        planetid = new Planetid(security);
    }
}
