package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.wars;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.wars.warid.Killmails;

public class Warid {
    public final EsiAnon security;
    public final Warid.GET GET = new Warid.GET();
    public Killmails killmails;

    public Warid(EsiAnon security) {
        this.security = security;
        killmails = new Killmails(security);
    }

    public class GET {
    }
}
