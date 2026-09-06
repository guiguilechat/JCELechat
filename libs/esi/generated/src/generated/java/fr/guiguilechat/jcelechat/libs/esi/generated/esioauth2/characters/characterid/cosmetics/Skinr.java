package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.cosmetics;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.cosmetics.skinr.Components;

public class Skinr {
    public final EsiOauth2 security;
    public final Skinr.GET GET = new Skinr.GET();
    public Components components;

    public Skinr(EsiOauth2 security) {
        this.security = security;
        components = new Components(security);
    }

    public class GET {
    }
}
