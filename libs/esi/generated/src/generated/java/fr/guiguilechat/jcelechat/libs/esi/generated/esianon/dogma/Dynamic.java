package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.dynamic.Items;

public class Dynamic {
    public final EsiAnon security;
    public Items items;

    public Dynamic(EsiAnon security) {
        this.security = security;
        items = new Items(security);
    }
}
