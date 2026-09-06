package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.markets;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.markets.structures.Structureid;

public class Structures {
    public final EsiOauth2 security;
    public Structureid structureid;

    public Structures(EsiOauth2 security) {
        this.security = security;
        structureid = new Structureid(security);
    }
}
