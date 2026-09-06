package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.groups.Groupid;

public class Groups {
    public final EsiAnon security;
    public final Groups.GET GET = new Groups.GET();
    public Groupid groupid;

    public Groups(EsiAnon security) {
        this.security = security;
        groupid = new Groupid(security);
    }

    public class GET {
    }
}
