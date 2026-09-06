package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.Fleetid;

public class Fleets {
    public final EsiOauth2 security;
    public Fleetid fleetid;

    public Fleets(EsiOauth2 security) {
        this.security = security;
        fleetid = new Fleetid(security);
    }
}
