package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.universe.structures.Structureid;

public class Structures {
    public final EsiOauth2 security;
    public Structureid structureid;

    public Structures(EsiOauth2 security) {
        this.security = security;
        structureid = new Structureid(security);
    }
}
