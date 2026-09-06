package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.skyhooks.Raidable;

public class Skyhooks {
    public final EsiAnon security;
    public Raidable raidable;

    public Skyhooks(EsiAnon security) {
        this.security = security;
        raidable = new Raidable(security);
    }
}
