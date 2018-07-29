package fr.guiguilechat.jcelechat.model.esi.compiled;

import fr.guiguilechat.jcelechat.model.esi.compiled.connected.Alliances;
import fr.guiguilechat.jcelechat.model.esi.compiled.connected.Characters;
import fr.guiguilechat.jcelechat.model.esi.compiled.connected.Corporation;
import fr.guiguilechat.jcelechat.model.esi.compiled.connected.Corporations;
import fr.guiguilechat.jcelechat.model.esi.compiled.connected.Fleets;
import fr.guiguilechat.jcelechat.model.esi.compiled.connected.Markets;
import fr.guiguilechat.jcelechat.model.esi.compiled.connected.Universe;

public abstract class SwaggerCOCache<T extends G_ICOAccess>
    implements ISwaggerCache
{
    public final T swagger;
    public final Alliances alliances = new Alliances(this);
    public final Characters characters = new Characters(this);
    public final Corporation corporation = new Corporation(this);
    public final Corporations corporations = new Corporations(this);
    public final Fleets fleets = new Fleets(this);
    public final Markets markets = new Markets(this);
    public final Universe universe = new Universe(this);

    public SwaggerCOCache(T swag) {
        swagger = swag;
    }
}
