package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.dynamic;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.dynamic.items.Typeid;

public class Items {
    public final EsiAnon security;
    public Typeid typeid;

    public Items(EsiAnon security) {
        this.security = security;
        typeid = new Typeid(security);
    }
}
