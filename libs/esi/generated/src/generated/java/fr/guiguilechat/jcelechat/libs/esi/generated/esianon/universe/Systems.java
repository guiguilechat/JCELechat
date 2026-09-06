package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.systems.Systemid;

public class Systems {
    public final EsiAnon security;
    public final Systems.GET GET = new Systems.GET();
    public Systemid systemid;

    public Systems(EsiAnon security) {
        this.security = security;
        systemid = new Systemid(security);
    }

    public class GET {
    }
}
