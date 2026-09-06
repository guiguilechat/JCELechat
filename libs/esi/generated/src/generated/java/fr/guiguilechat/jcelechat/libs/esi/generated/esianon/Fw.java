package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.fw.Leaderboards;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.fw.Stats;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.fw.Systems;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.fw.Wars;

public class Fw {
    public final EsiAnon security;
    public Leaderboards leaderboards;
    public Stats stats;
    public Systems systems;
    public Wars wars;

    public Fw(EsiAnon security) {
        this.security = security;
        leaderboards = new Leaderboards(security);
        stats = new Stats(security);
        systems = new Systems(security);
        wars = new Wars(security);
    }
}
