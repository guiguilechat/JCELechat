package fr.guiguilechat.jcelechat.model.sde.types.celestial;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.Untargetable;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleStrength;
import fr.guiguilechat.jcelechat.model.sde.types.Celestial;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class EncounterSurveillanceSystem
    extends Celestial
{
    /**
     * Attribute to disallow targetting.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int untargetable;
    /**
     * Amount to modify ships warp scramble status by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestrength;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Untargetable.INSTANCE, Capacity.INSTANCE, WarpScrambleStrength.INSTANCE })));
    public static final EncounterSurveillanceSystem.MetaGroup METAGROUP = new EncounterSurveillanceSystem.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1158 :
            {
                return untargetable;
            }
            case  105 :
            {
                return warpscramblestrength;
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
    public IMetaGroup<EncounterSurveillanceSystem> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EncounterSurveillanceSystem>
    {
        public static final String RESOURCE_PATH = "SDE/types/celestial/EncounterSurveillanceSystem.yaml";
        private Map<Integer, EncounterSurveillanceSystem> cache = (null);

        @Override
        public IMetaCategory<? super EncounterSurveillanceSystem> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4079;
        }

        @Override
        public String getName() {
            return "EncounterSurveillanceSystem";
        }

        @Override
        public synchronized Map<Integer, EncounterSurveillanceSystem> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EncounterSurveillanceSystem.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, EncounterSurveillanceSystem> types;
        }
    }
}
