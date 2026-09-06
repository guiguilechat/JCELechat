package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.contracts.Contractid;

public class Contracts {
    public final EsiOauth2 security;
    public final Contracts.GET GET = new Contracts.GET();
    public Contractid contractid;

    public Contracts(EsiOauth2 security) {
        this.security = security;
        contractid = new Contractid(security);
    }

    public class GET {
    }
}
