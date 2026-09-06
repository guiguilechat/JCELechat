package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.cosmetics;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.cosmetics.skinr.Skinrid;

public class Skinr {
    public final EsiAnon security;
    public Skinrid skinrid;

    public Skinr(EsiAnon security) {
        this.security = security;
        skinrid = new Skinrid(security);
    }
}
