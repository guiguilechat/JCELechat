package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.characters.Affiliation;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.characters.Characterid;

public class Characters {
    public final EsiAnon security;
    public Affiliation affiliation;
    public Characterid characterid;

    public Characters(EsiAnon security) {
        this.security = security;
        affiliation = new Affiliation(security);
        characterid = new Characterid(security);
    }
}
