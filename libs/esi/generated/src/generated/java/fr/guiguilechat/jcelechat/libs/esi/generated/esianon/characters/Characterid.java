package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.characters;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.characters.characterid.Corporationhistory;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.characters.characterid.Portrait;

public class Characterid {
    public final EsiAnon security;
    public final Characterid.GET GET = new Characterid.GET();
    public Corporationhistory corporationhistory;
    public Portrait portrait;

    public Characterid(EsiAnon security) {
        this.security = security;
        corporationhistory = new Corporationhistory(security);
        portrait = new Portrait(security);
    }

    public class GET {
    }
}
