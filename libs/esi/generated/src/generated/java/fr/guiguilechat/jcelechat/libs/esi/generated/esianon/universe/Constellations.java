package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.constellations.Constellationid;

public class Constellations {
    public final EsiAnon security;
    public final Constellations.GET GET = new Constellations.GET();
    public Constellationid constellationid;

    public Constellations(EsiAnon security) {
        this.security = security;
        constellationid = new Constellationid(security);
    }

    public class GET {
    }
}
