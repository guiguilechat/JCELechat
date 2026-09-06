package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.freelancejobs;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.freelancejobs.jobid.Participants;

public class Jobid {
    public final EsiOauth2 security;
    public Participants participants;

    public Jobid(EsiOauth2 security) {
        this.security = security;
        participants = new Participants(security);
    }
}
