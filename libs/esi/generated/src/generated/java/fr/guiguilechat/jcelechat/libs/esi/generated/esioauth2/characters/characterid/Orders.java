package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.orders.History;

public class Orders {
    public final EsiOauth2 security;
    public final Orders.GET GET = new Orders.GET();
    public History history;

    public Orders(EsiOauth2 security) {
        this.security = security;
        history = new History(security);
    }

    public class GET {
    }
}
