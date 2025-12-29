package fr.guiguilechat.jcelechat.model.sde.types.apparel;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Gender;
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;

public class Tattoos
    extends Apparel
{
    /**
     * Used to describe what sex a given item is meant for.
     * 
     *  1 = Male,
     *  2 = Unisex,
     *  3 = Female
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2)
    public int gender;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Gender.INSTANCE })));
    public static final Tattoos.MetaGroup METAGROUP = new Tattoos.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1773 :
            {
                return gender;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Tattoos> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Tattoos>
    {
        public static final String RESOURCE_PATH = "SDE/types/apparel/Tattoos.yaml";
        private Map<Integer, Tattoos> cache = (null);

        @Override
        public IMetaCategory<? super Tattoos> category() {
            return Apparel.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1084;
        }

        @Override
        public String getName() {
            return "Tattoos";
        }

        @Override
        public synchronized Map<Integer, Tattoos> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Tattoos.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, Tattoos> types;
        }
    }
}
