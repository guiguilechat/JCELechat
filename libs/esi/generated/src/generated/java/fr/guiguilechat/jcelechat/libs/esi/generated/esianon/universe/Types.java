package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.types.Typeid;

public class Types {
    public final EsiAnon security;
    public final Types.GET GET = new Types.GET();
    public Typeid typeid;

    public Types(EsiAnon security) {
        this.security = security;
        typeid = new Typeid(security);
    }

    public class GET {
    }
}
