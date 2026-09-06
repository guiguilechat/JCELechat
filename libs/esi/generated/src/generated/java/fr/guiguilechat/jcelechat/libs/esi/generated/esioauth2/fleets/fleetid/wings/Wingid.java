package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid.wings;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid.wings.wingid.Squads;

public class Wingid {
    public final EsiOauth2 security;
    public final Wingid.PUT PUT = new Wingid.PUT();
    public final Wingid.DELETE DELETE = new Wingid.DELETE();
    public Squads squads;

    public Wingid(EsiOauth2 security) {
        this.security = security;
        squads = new Squads(security);
    }

    public class DELETE {
    }

    public class PUT {
    }
}
