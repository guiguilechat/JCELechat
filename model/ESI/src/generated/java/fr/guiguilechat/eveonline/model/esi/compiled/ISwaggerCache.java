package fr.guiguilechat.eveonline.model.esi.compiled;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ISwaggerCache {

    public<U> ISwaggerCache.Pausable addFetchCacheArray(String name, BiFunction<Integer, Map<String, List<String>> , U[]> fetcher, Consumer<List<U>> cacheHandler, String... requiredRoles);

    public<U> ISwaggerCache.Pausable addFetchCacheObject(String name, Function<Map<String, List<String>> , U> fetcher, Consumer<U> cacheHandler, String... requiredRoles);

    public interface Pausable {

        public void pause();

        public void resume();
    }
}
