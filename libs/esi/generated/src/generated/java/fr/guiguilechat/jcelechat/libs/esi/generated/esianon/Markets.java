package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets.Groups;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets.Prices;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets.Regionid;

public class Markets {
    public final EsiAnon security;
    public Groups groups;
    public Prices prices;
    public Regionid regionid;

    public Markets(EsiAnon security) {
        this.security = security;
        groups = new Groups(security);
        prices = new Prices(security);
        regionid = new Regionid(security);
    }
}
