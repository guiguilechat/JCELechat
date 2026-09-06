package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.wars.Warid;

public class Wars {
    public final EsiAnon security;
    public final Wars.GET GET = new Wars.GET();
    public Warid warid;

    public Wars(EsiAnon security) {
        this.security = security;
        warid = new Warid(security);
    }

    public class GET {
    }
}
