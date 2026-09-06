package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.fw;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.fw.leaderboards.Characters;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.fw.leaderboards.Corporations;

public class Leaderboards {
    public final EsiAnon security;
    public final Leaderboards.GET GET = new Leaderboards.GET();
    public Characters characters;
    public Corporations corporations;

    public Leaderboards(EsiAnon security) {
        this.security = security;
        characters = new Characters(security);
        corporations = new Corporations(security);
    }

    public class GET {
    }
}
