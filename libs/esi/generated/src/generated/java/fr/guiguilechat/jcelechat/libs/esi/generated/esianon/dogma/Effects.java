package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.effects.Effectid;

public class Effects {
    public final EsiAnon security;
    public final Effects.GET GET = new Effects.GET();
    public Effectid effectid;

    public Effects(EsiAnon security) {
        this.security = security;
        effectid = new Effectid(security);
    }

    public class GET {
    }
}
