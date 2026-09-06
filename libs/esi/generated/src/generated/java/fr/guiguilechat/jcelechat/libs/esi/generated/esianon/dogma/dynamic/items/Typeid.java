package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.dynamic.items;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.dynamic.items.typeid.Itemid;

public class Typeid {
    public final EsiAnon security;
    public Itemid itemid;

    public Typeid(EsiAnon security) {
        this.security = security;
        itemid = new Itemid(security);
    }
}
