package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.paragonhub.Skinr;

public class Paragonhub {
    public final EsiAnon security;
    public Skinr skinr;

    public Paragonhub(EsiAnon security) {
        this.security = security;
        skinr = new Skinr(security);
    }
}
