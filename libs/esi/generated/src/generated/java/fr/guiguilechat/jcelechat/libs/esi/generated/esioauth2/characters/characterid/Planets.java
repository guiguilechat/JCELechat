package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.planets.Planetid;

public class Planets {
    public final EsiOauth2 security;
    public final Planets.GET GET = new Planets.GET();
    public Planetid planetid;

    public Planets(EsiOauth2 security) {
        this.security = security;
        planetid = new Planetid(security);
    }

    public class GET {
    }
}
