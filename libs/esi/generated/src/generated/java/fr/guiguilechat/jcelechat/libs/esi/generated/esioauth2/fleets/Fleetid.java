package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid.Members;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid.Squads;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid.Wings;

public class Fleetid {
    public final EsiOauth2 security;
    public final Fleetid.GET GET = new Fleetid.GET();
    public final Fleetid.PUT PUT = new Fleetid.PUT();
    public Members members;
    public Squads squads;
    public Wings wings;

    public Fleetid(EsiOauth2 security) {
        this.security = security;
        members = new Members(security);
        squads = new Squads(security);
        wings = new Wings(security);
    }

    public class GET {
    }

    public class PUT {
    }
}
