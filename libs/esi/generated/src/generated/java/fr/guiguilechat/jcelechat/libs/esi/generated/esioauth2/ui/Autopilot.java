package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui.autopilot.Waypoint;

public class Autopilot {
    public final EsiOauth2 security;
    public Waypoint waypoint;

    public Autopilot(EsiOauth2 security) {
        this.security = security;
        waypoint = new Waypoint(security);
    }
}
