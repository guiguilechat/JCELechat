package fr.guiguilechat.eveonline.model.esi.compiled;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_leaderboards;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_leaderboards_characters;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_fw_leaderboards_corporations;
import fr.guiguilechat.eveonline.model.esi.compiled.responses.R_get_status;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;


/**
 * @param T
 *     the type of Swagger this refers to. this parameter allows to work on specific implementation of Swagger, thus call its methdos instead of having to cast.
 */
public abstract class SwaggerCache<T extends Swagger> {
    public final T swagger;
    public final alliances alliances = new alliances();
    public final characters characters = new characters();
    public final corporations corporations = new corporations();
    public final dogma dogma = new dogma();
    public final fleets fleets = new fleets();
    public final fw fw = new fw();
    public final incursions incursions = new incursions();
    public final industry industry = new industry();
    public final insurance insurance = new insurance();
    public final loyalty loyalty = new loyalty();
    public final markets markets = new markets();
    public final opportunities opportunities = new opportunities();
    public final sovereignty sovereignty = new sovereignty();
    public final status status = new status();
    public final universe universe = new universe();
    public final wars wars = new wars();

    public SwaggerCache(T swag) {
        swagger = swag;
    }

    public abstract<U> SwaggerCache.Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>> , U[]> fetcher, Consumer<List<U>> cacheHandler, String... requiredRoles);

    public abstract<U> SwaggerCache.Pausable addFetchCacheObject(String name, Function<Map<String, List<String>> , U> fetcher, Consumer<U> cacheHandler, String... requiredRoles);

    public class alliances {
    }

    public class characters {
    }

    public class corporations {
    }

    public class dogma {
    }

    public class fleets {
    }

    public class fw {
        private SimpleObjectProperty<R_get_fw_leaderboards> get_fw_leaderboards_container = null;
        private SimpleObjectProperty<R_get_fw_leaderboards_characters> get_fw_leaderboards_characters_container = null;
        private SimpleObjectProperty<R_get_fw_leaderboards_corporations> get_fw_leaderboards_corporations_container = null;

        public Property<R_get_fw_leaderboards> get_fw_leaderboards() {
            if (get_fw_leaderboards_container == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_container == null) {
                        get_fw_leaderboards_container = new SimpleObjectProperty<>();
                        addFetchCacheObject("get_fw_leaderboards", (m->swagger.get_fw_leaderboards(m)), (get_fw_leaderboards_container::set));
                    }
                }
            }
            return get_fw_leaderboards_container;
        }

        public Property<R_get_fw_leaderboards_characters> get_fw_leaderboards_characters() {
            if (get_fw_leaderboards_characters_container == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_characters_container == null) {
                        get_fw_leaderboards_characters_container = new SimpleObjectProperty<>();
                        addFetchCacheObject("get_fw_leaderboards_characters", (m->swagger.get_fw_leaderboards_characters(m)), (get_fw_leaderboards_characters_container::set));
                    }
                }
            }
            return get_fw_leaderboards_characters_container;
        }

        public Property<R_get_fw_leaderboards_corporations> get_fw_leaderboards_corporations() {
            if (get_fw_leaderboards_corporations_container == null) {
                synchronized (this)
                {
                    if (get_fw_leaderboards_corporations_container == null) {
                        get_fw_leaderboards_corporations_container = new SimpleObjectProperty<>();
                        addFetchCacheObject("get_fw_leaderboards_corporations", (m->swagger.get_fw_leaderboards_corporations(m)), (get_fw_leaderboards_corporations_container::set));
                    }
                }
            }
            return get_fw_leaderboards_corporations_container;
        }
    }

    public class incursions {
    }

    public class industry {
    }

    public class insurance {
    }

    public class loyalty {
    }

    public class markets {
    }

    public class opportunities {
    }

    public interface Pausable {

        public void pause();

        public void resume();
    }

    public class sovereignty {
    }

    public class status {
        private SimpleObjectProperty<R_get_status> get_status_container = null;

        public Property<R_get_status> get_status() {
            if (get_status_container == null) {
                synchronized (this)
                {
                    if (get_status_container == null) {
                        get_status_container = new SimpleObjectProperty<>();
                        addFetchCacheObject("get_status", (m->swagger.get_status(m)), (get_status_container::set));
                    }
                }
            }
            return get_status_container;
        }
    }

    public class universe {
    }

    public class wars {
    }
}
