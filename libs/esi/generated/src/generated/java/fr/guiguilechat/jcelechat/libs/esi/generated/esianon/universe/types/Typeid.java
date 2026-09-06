package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.types;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;

public class Typeid {
    public final EsiAnon security;
    public final Typeid.GET GET = new Typeid.GET();

    public Typeid(EsiAnon security) {
        this.security = security;
    }

    public class GET {
    }
}
