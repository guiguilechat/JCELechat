package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;

public class StructureEngineeringRigXLStructureAndComponentEfficiency
    extends StructureModule
{
    /**
     * Bonus on Cost decrease for engineering rigs
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int attributeengrigcostbonus;
    /**
     * Bonus on Material decrease for engineering rigs
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double attributeengrigmatbonus;
    /**
     * Bonus on Time decrease for engineering rigs
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int attributeengrigtimebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double attributethukkerengrigmatbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup02;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hisecmodifier;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double lowsecmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double nullsecmodifier;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int securitymodifier;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * How much of the upgrade capacity is used when this is fitted to a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecost;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {UpgradeCost.INSTANCE, AttributeEngRigTimeBonus.INSTANCE, AttributeEngRigMatBonus.INSTANCE, AttributeEngRigCostBonus.INSTANCE, TechLevel.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, RigSize.INSTANCE, CanFitShipGroup01 .INSTANCE, CanFitShipGroup02 .INSTANCE, HiSecModifier.INSTANCE, LowSecModifier.INSTANCE, RequiredSkill1Level.INSTANCE, NullSecModifier.INSTANCE, RequiredSkill1 .INSTANCE, SecurityModifier.INSTANCE, MetaGroupID.INSTANCE, AttributeThukkerEngRigMatBonus.INSTANCE, StructureItemVisualFlag.INSTANCE })));
    public static final StructureEngineeringRigXLStructureAndComponentEfficiency.MetaGroup METAGROUP = new StructureEngineeringRigXLStructureAndComponentEfficiency.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2595 :
            {
                return attributeengrigcostbonus;
            }
            case  2594 :
            {
                return attributeengrigmatbonus;
            }
            case  2593 :
            {
                return attributeengrigtimebonus;
            }
            case  2653 :
            {
                return attributethukkerengrigmatbonus;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  2355 :
            {
                return hisecmodifier;
            }
            case  9 :
            {
                return hp;
            }
            case  2356 :
            {
                return lowsecmodifier;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  2357 :
            {
                return nullsecmodifier;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  2358 :
            {
                return securitymodifier;
            }
            case  2334 :
            {
                return structureitemvisualflag;
            }
            case  422 :
            {
                return techlevel;
            }
            case  1153 :
            {
                return upgradecost;
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
    public IMetaGroup<StructureEngineeringRigXLStructureAndComponentEfficiency> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureEngineeringRigXLStructureAndComponentEfficiency>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureEngineeringRigXLStructureAndComponentEfficiency.yaml";
        private Map<Integer, StructureEngineeringRigXLStructureAndComponentEfficiency> cache = (null);

        @Override
        public IMetaCategory<? super StructureEngineeringRigXLStructureAndComponentEfficiency> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1869;
        }

        @Override
        public String getName() {
            return "StructureEngineeringRigXLStructureAndComponentEfficiency";
        }

        @Override
        public synchronized Map<Integer, StructureEngineeringRigXLStructureAndComponentEfficiency> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureEngineeringRigXLStructureAndComponentEfficiency.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, StructureEngineeringRigXLStructureAndComponentEfficiency> types;
        }
    }
}
