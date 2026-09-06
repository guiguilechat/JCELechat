package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.projects.Projectid;

public class Projects {
    public final EsiOauth2 security;
    public final Projects.GET GET = new Projects.GET();
    public Projectid projectid;

    public Projects(EsiOauth2 security) {
        this.security = security;
        projectid = new Projectid(security);
    }

    public class GET {
    }
}
