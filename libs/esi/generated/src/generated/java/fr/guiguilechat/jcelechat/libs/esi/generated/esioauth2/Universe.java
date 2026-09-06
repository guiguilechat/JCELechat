package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.universe.Structures;

public class Universe {
    public final EsiOauth2 security;
    public Structures structures;

    public Universe(EsiOauth2 security) {
        this.security = security;
        structures = new Structures(security);
    }
}
