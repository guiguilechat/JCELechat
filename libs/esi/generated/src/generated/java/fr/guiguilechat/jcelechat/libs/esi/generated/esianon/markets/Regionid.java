package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets.regionid.History;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets.regionid.Orders;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets.regionid.Types;

public class Regionid {
    public final EsiAnon security;
    public History history;
    public Orders orders;
    public Types types;

    public Regionid(EsiAnon security) {
        this.security = security;
        history = new History(security);
        orders = new Orders(security);
        types = new Types(security);
    }
}
