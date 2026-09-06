package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.freelancejobs.Jobid;

public class Freelancejobs {
    public final EsiAnon security;
    public final Freelancejobs.GET GET = new Freelancejobs.GET();
    public Jobid jobid;

    public Freelancejobs(EsiAnon security) {
        this.security = security;
        jobid = new Jobid(security);
    }

    public class GET {
    }
}
