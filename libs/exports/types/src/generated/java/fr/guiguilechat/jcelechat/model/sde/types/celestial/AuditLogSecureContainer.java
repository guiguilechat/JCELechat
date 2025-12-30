package fr.guiguilechat.jcelechat.model.sde.types.celestial;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanBeJettisoned;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.types.Celestial;

public class AuditLogSecureContainer
    extends Celestial
{
    /**
     * This attribute is not needed by default. You only need to add it if you want to stop something from being jettisoned.
     * 
     * The primary case for this was Station Containers.
     * 
     *  0 = Cannot be jettisoned.
     *  1 = Can be jettisoned.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int canbejettisoned;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, Hp.INSTANCE, CanBeJettisoned.INSTANCE, StructureUniformity.INSTANCE })));
    public static final AuditLogSecureContainer.MetaGroup METAGROUP = new AuditLogSecureContainer.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1852 :
            {
                return canbejettisoned;
            }
            case  9 :
            {
                return hp;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  525 :
            {
                return structureuniformity;
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
    public IMetaGroup<AuditLogSecureContainer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AuditLogSecureContainer>
    {
        public static final String RESOURCE_PATH = "SDE/types/celestial/AuditLogSecureContainer.yaml";
        private Map<Integer, AuditLogSecureContainer> cache = (null);

        @Override
        public IMetaCategory<? super AuditLogSecureContainer> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  448;
        }

        @Override
        public String getName() {
            return "AuditLogSecureContainer";
        }

        @Override
        public synchronized Map<Integer, AuditLogSecureContainer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AuditLogSecureContainer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, AuditLogSecureContainer> types;
        }
    }
}
