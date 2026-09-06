package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub.skinr.Alliances;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub.skinr.Characters;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub.skinr.Corporations;

public class Skinr {
    public final EsiOauth2 security;
    public Alliances alliances;
    public Characters characters;
    public Corporations corporations;

    public Skinr(EsiOauth2 security) {
        this.security = security;
        alliances = new Alliances(security);
        characters = new Characters(security);
        corporations = new Corporations(security);
    }
}
