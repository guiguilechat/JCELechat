package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.asteroidbelts.Asteroidbeltid;

public class Asteroidbelts {
    public final EsiAnon security;
    public Asteroidbeltid asteroidbeltid;

    public Asteroidbelts(EsiAnon security) {
        this.security = security;
        asteroidbeltid = new Asteroidbeltid(security);
    }
}
