package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.projects.projectid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.projects.projectid.contribution.Characterid;

public class Contribution {
    public final EsiOauth2 security;
    public Characterid characterid;

    public Contribution(EsiOauth2 security) {
        this.security = security;
        characterid = new Characterid(security);
    }
}
