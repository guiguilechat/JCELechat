package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.schematics.Schematicid;

public class Schematics {
    public final EsiAnon security;
    public Schematicid schematicid;

    public Schematics(EsiAnon security) {
        this.security = security;
        schematicid = new Schematicid(security);
    }
}
