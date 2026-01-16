package fr.guiguilechat.jcelechat.model.sde.types.charge;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff1ID;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff1Multiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff2ID;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff2Multiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff3ID;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff3Multiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff4ID;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarfareBuff4Multiplier;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class ArmorCommandBurstCharges
    extends Charge
{
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff1id;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff1multiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff2id;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff2multiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff3id;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff3multiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff4id;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warfarebuff4multiplier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {WarfareBuff1ID.INSTANCE, WarfareBuff1Multiplier.INSTANCE, WarfareBuff2Multiplier.INSTANCE, WarfareBuff2ID.INSTANCE, WarfareBuff3Multiplier.INSTANCE, WarfareBuff4Multiplier.INSTANCE, WarfareBuff3ID.INSTANCE, WarfareBuff4ID.INSTANCE, LauncherGroup.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill2 .INSTANCE })));
    public static final ArmorCommandBurstCharges.MetaGroup METAGROUP = new ArmorCommandBurstCharges.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  137 :
            {
                return launchergroup;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  2468 :
            {
                return warfarebuff1id;
            }
            case  2596 :
            {
                return warfarebuff1multiplier;
            }
            case  2470 :
            {
                return warfarebuff2id;
            }
            case  2597 :
            {
                return warfarebuff2multiplier;
            }
            case  2472 :
            {
                return warfarebuff3id;
            }
            case  2598 :
            {
                return warfarebuff3multiplier;
            }
            case  2536 :
            {
                return warfarebuff4id;
            }
            case  2599 :
            {
                return warfarebuff4multiplier;
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
    public IMetaGroup<ArmorCommandBurstCharges> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ArmorCommandBurstCharges>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/ArmorCommandBurstCharges.yaml";
        private Map<Integer, ArmorCommandBurstCharges> cache = (null);

        @Override
        public IMetaCategory<? super ArmorCommandBurstCharges> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1774;
        }

        @Override
        public String getName() {
            return "ArmorCommandBurstCharges";
        }

        @Override
        public synchronized Map<Integer, ArmorCommandBurstCharges> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ArmorCommandBurstCharges.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, ArmorCommandBurstCharges> types;
        }
    }
}
