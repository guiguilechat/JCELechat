package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.attributes.Attributeid;

public class Attributes {
    public final EsiAnon security;
    public final Attributes.GET GET = new Attributes.GET();
    public Attributeid attributeid;

    public Attributes(EsiAnon security) {
        this.security = security;
        attributeid = new Attributeid(security);
    }

    public class GET {
    }
}
