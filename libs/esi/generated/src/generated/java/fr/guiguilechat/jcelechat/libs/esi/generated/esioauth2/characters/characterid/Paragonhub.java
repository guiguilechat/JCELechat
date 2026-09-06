package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.paragonhub.Skinr;

public class Paragonhub {
    public final EsiOauth2 security;
    public Skinr skinr;

    public Paragonhub(EsiOauth2 security) {
        this.security = security;
        skinr = new Skinr(security);
    }
}
