package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.fleets.fleetid.members.Memberid;

public class Members {
    public final EsiOauth2 security;
    public final Members.GET GET = new Members.GET();
    public final Members.POST POST = new Members.POST();
    public Memberid memberid;

    public Members(EsiOauth2 security) {
        this.security = security;
        memberid = new Memberid(security);
    }

    public class GET {
    }

    public class POST {
    }
}
