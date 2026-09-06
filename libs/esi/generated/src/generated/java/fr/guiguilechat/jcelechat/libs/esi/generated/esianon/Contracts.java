package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts.Public;

public class Contracts {
    public final EsiAnon security;
    public Public _public;

    public Contracts(EsiAnon security) {
        this.security = security;
        _public = new Public(security);
    }
}
