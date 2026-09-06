package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.meta.Changelog;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.meta.Compatibilitydates;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.meta.Name;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.meta.Status;

public class Meta {
    public final EsiAnon security;
    public Changelog changelog;
    public Compatibilitydates compatibilitydates;
    public Name name;
    public Status status;

    public Meta(EsiAnon security) {
        this.security = security;
        changelog = new Changelog(security);
        compatibilitydates = new Compatibilitydates(security);
        name = new Name(security);
        status = new Status(security);
    }
}
