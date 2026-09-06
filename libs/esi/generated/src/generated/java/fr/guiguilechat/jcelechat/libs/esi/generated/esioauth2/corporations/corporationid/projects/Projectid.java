package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.projects;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.projects.projectid.Contribution;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.projects.projectid.Contributors;

public class Projectid {
    public final EsiOauth2 security;
    public final Projectid.GET GET = new Projectid.GET();
    public Contribution contribution;
    public Contributors contributors;

    public Projectid(EsiOauth2 security) {
        this.security = security;
        contribution = new Contribution(security);
        contributors = new Contributors(security);
    }

    public class GET {
    }
}
