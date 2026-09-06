package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.Attributes;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.Dynamic;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.dogma.Effects;

public class Dogma {
    public final EsiAnon security;
    public Attributes attributes;
    public Dynamic dynamic;
    public Effects effects;

    public Dogma(EsiAnon security) {
        this.security = security;
        attributes = new Attributes(security);
        dynamic = new Dynamic(security);
        effects = new Effects(security);
    }
}
