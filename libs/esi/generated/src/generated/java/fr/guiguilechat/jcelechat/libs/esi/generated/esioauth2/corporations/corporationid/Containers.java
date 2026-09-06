package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.containers.Logs;

public class Containers {
    public final EsiOauth2 security;
    public Logs logs;

    public Containers(EsiOauth2 security) {
        this.security = security;
        logs = new Logs(security);
    }
}
