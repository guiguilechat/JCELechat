package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.graphics.Graphicid;

public class Graphics {
    public final EsiAnon security;
    public final Graphics.GET GET = new Graphics.GET();
    public Graphicid graphicid;

    public Graphics(EsiAnon security) {
        this.security = security;
        graphicid = new Graphicid(security);
    }

    public class GET {
    }
}
