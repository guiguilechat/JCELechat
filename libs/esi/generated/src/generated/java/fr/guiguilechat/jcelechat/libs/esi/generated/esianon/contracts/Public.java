package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts._public.Bids;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts._public.Items;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.contracts._public.Regionid;

public class Public {
    public final EsiAnon security;
    public Bids bids;
    public Items items;
    public Regionid regionid;

    public Public(EsiAnon security) {
        this.security = security;
        bids = new Bids(security);
        items = new Items(security);
        regionid = new Regionid(security);
    }
}
