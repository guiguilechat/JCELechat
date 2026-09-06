package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.meta;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;

public class Status {
    public final EsiAnon security;
    public final Status.GET GET = new Status.GET();

    public Status(EsiAnon security) {
        this.security = security;
    }

    public class GET {
    }
}
