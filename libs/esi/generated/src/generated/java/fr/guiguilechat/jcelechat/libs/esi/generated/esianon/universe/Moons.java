package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.moons.Moonid;

public class Moons {
    public final EsiAnon security;
    public Moonid moonid;

    public Moons(EsiAnon security) {
        this.security = security;
        moonid = new Moonid(security);
    }
}
