package fr.guiguilechat.eveonline.model.esi.compiled;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Alliances;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Characters;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Corporation;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Corporations;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Dogma;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Fleets;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Fw;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Incursions;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Industry;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Insurance;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Killmails;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Loyalty;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Markets;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Opportunities;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Route;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Search;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Sovereignty;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Status;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Universe;
import fr.guiguilechat.eveonline.model.esi.compiled.cacheGroups.Wars;


/**
 * @param T
 *     the type of Swagger this refers to. this parameter allows to work on specific implementation of Swagger, thus call its methdos instead of having to cast.
 */
public abstract class SwaggerCache<T extends Swagger> {
    public final T swagger;
    public final Alliances alliances = new Alliances(this);
    public final Characters characters = new Characters(this);
    public final Corporation corporation = new Corporation(this);
    public final Corporations corporations = new Corporations(this);
    public final Dogma dogma = new Dogma(this);
    public final Fleets fleets = new Fleets(this);
    public final Fw fw = new Fw(this);
    public final Incursions incursions = new Incursions(this);
    public final Industry industry = new Industry(this);
    public final Insurance insurance = new Insurance(this);
    public final Killmails killmails = new Killmails(this);
    public final Loyalty loyalty = new Loyalty(this);
    public final Markets markets = new Markets(this);
    public final Opportunities opportunities = new Opportunities(this);
    public final Route route = new Route(this);
    public final Sovereignty sovereignty = new Sovereignty(this);
    public final Status status = new Status(this);
    public final Universe universe = new Universe(this);
    public final Wars wars = new Wars(this);
    public final Search search = new Search(this);

    public SwaggerCache(T swag) {
        swagger = swag;
    }

    public abstract<U> SwaggerCache.Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>> , U[]> fetcher, Consumer<List<U>> cacheHandler, String... requiredRoles);

    public abstract<U> SwaggerCache.Pausable addFetchCacheObject(String name, Function<Map<String, List<String>> , U> fetcher, Consumer<U> cacheHandler, String... requiredRoles);

    public interface Pausable {

        public void pause();

        public void resume();
    }
}
