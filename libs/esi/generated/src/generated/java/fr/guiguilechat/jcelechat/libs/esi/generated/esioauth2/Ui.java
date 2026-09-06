package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui.Autopilot;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.ui.Openwindow;

public class Ui {
    public final EsiOauth2 security;
    public Autopilot autopilot;
    public Openwindow openwindow;

    public Ui(EsiOauth2 security) {
        this.security = security;
        autopilot = new Autopilot(security);
        openwindow = new Openwindow(security);
    }
}
