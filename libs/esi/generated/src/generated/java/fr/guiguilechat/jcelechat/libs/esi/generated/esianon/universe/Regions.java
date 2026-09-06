package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.regions.Regionid;

public class Regions {
    public final EsiAnon security;
    public final Regions.GET GET = new Regions.GET();
    public Regionid regionid;

    public Regions(EsiAnon security) {
        this.security = security;
        regionid = new Regionid(security);
    }

    public class GET {
    }
}
