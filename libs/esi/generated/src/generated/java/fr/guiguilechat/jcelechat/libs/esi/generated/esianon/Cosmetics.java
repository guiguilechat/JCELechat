package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.cosmetics.Skinr;

public class Cosmetics {
    public final EsiAnon security;
    public Skinr skinr;

    public Cosmetics(EsiAnon security) {
        this.security = security;
        skinr = new Skinr(security);
    }
}
