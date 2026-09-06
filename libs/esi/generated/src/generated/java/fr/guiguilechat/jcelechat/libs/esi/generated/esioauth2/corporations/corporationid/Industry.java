package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporations.corporationid.industry.Jobs;

public class Industry {
    public final EsiOauth2 security;
    public Jobs jobs;

    public Industry(EsiOauth2 security) {
        this.security = security;
        jobs = new Jobs(security);
    }
}
