package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.fittings.Fittingid;

public class Fittings {
    public final EsiOauth2 security;
    public final Fittings.GET GET = new Fittings.GET();
    public final Fittings.POST POST = new Fittings.POST();
    public Fittingid fittingid;

    public Fittings(EsiOauth2 security) {
        this.security = security;
        fittingid = new Fittingid(security);
    }

    public class GET {
    }

    public class POST {
    }
}
