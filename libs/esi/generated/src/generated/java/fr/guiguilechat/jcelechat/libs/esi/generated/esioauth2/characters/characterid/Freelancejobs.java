package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.freelancejobs.Jobid;

public class Freelancejobs {
    public final EsiOauth2 security;
    public final Freelancejobs.GET GET = new Freelancejobs.GET();
    public Jobid jobid;

    public Freelancejobs(EsiOauth2 security) {
        this.security = security;
        jobid = new Jobid(security);
    }

    public class GET {
    }
}
