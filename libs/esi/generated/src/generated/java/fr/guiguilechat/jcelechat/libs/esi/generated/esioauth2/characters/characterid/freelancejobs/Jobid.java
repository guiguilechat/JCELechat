package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.freelancejobs;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.freelancejobs.jobid.Participation;

public class Jobid {
    public final EsiOauth2 security;
    public Participation participation;

    public Jobid(EsiOauth2 security) {
        this.security = security;
        participation = new Participation(security);
    }
}
