package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid.squads.Squadid;

public class Squads {
    public final EsiOauth2 security;
    public Squadid squadid;

    public Squads(EsiOauth2 security) {
        this.security = security;
        squadid = new Squadid(security);
    }
}
