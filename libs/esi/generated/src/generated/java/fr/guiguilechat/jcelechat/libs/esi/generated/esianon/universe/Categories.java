package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.categories.Categoryid;

public class Categories {
    public final EsiAnon security;
    public final Categories.GET GET = new Categories.GET();
    public Categoryid categoryid;

    public Categories(EsiAnon security) {
        this.security = security;
        categoryid = new Categoryid(security);
    }

    public class GET {
    }
}
