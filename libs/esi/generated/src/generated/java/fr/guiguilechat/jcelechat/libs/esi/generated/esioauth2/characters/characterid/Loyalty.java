package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.loyalty.Points;

public class Loyalty {
    public final EsiOauth2 security;
    public Points points;

    public Loyalty(EsiOauth2 security) {
        this.security = security;
        points = new Points(security);
    }
}
