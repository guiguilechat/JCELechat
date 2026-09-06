package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid.wings.Wingid;

public class Wings {
    public final EsiOauth2 security;
    public final Wings.GET GET = new Wings.GET();
    public final Wings.POST POST = new Wings.POST();
    public Wingid wingid;

    public Wings(EsiOauth2 security) {
        this.security = security;
        wingid = new Wingid(security);
    }

    public class GET {
    }

    public class POST {
    }
}
