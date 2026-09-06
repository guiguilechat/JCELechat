package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.mercenarytacticaloperations.Operationid;

public class Mercenarytacticaloperations {
    public final EsiOauth2 security;
    public final Mercenarytacticaloperations.GET GET = new Mercenarytacticaloperations.GET();
    public Operationid operationid;

    public Mercenarytacticaloperations(EsiOauth2 security) {
        this.security = security;
        operationid = new Operationid(security);
    }

    public class GET {
    }
}
