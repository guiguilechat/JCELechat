package fr.guiguilechat.jcelechat.model.sde.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup4;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAbsorbtionRateModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.OverloadRofBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReloadTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredThermoDynamicsSkill;
import fr.guiguilechat.jcelechat.model.sde.attributes.Slots;
import fr.guiguilechat.jcelechat.model.sde.attributes.Speed;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class MissileLauncherLight
    extends Module
{
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup1;
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup2;
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup4;
    /**
     * Number of charges consumed per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int chargerate;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double heatabsorbtionratemodifier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double heatdamage;
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
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int overloadrofbonus;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * reload time (ms)
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(10000.0)
    public double reloadtime;
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
    public int requiredthermodynamicsskill;
    /**
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int slots;
    /**
     * Time in milliseconds between possible activations
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double speed;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeGroup4 .INSTANCE, ReloadTime.INSTANCE, TechLevel.INSTANCE, TypeColorScheme.INSTANCE, Hp.INSTANCE, Slots.INSTANCE, Cpu.INSTANCE, Speed.INSTANCE, RequiredSkill1Level.INSTANCE, OverloadRofBonus.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill2 .INSTANCE, ChargeRate.INSTANCE, MetaLevelOld.INSTANCE, HeatDamage.INSTANCE, ChargeGroup1 .INSTANCE, HeatAbsorbtionRateModifier.INSTANCE, RequiredThermoDynamicsSkill.INSTANCE, MetaGroupID.INSTANCE, ChargeGroup2 .INSTANCE, Power.INSTANCE })));
    public static final MissileLauncherLight.MetaGroup METAGROUP = new MissileLauncherLight.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  604 :
            {
                return chargegroup1;
            }
            case  605 :
            {
                return chargegroup2;
            }
            case  609 :
            {
                return chargegroup4;
            }
            case  56 :
            {
                return chargerate;
            }
            case  50 :
            {
                return cpu;
            }
            case  1180 :
            {
                return heatabsorbtionratemodifier;
            }
            case  1211 :
            {
                return heatdamage;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  1205 :
            {
                return overloadrofbonus;
            }
            case  30 :
            {
                return power;
            }
            case  1795 :
            {
                return reloadtime;
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
            case  1212 :
            {
                return requiredthermodynamicsskill;
            }
            case  47 :
            {
                return slots;
            }
            case  51 :
            {
                return speed;
            }
            case  1768 :
            {
                return typecolorscheme;
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
    public IMetaGroup<MissileLauncherLight> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissileLauncherLight>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/MissileLauncherLight.yaml";
        private Map<Integer, MissileLauncherLight> cache = (null);

        @Override
        public IMetaCategory<? super MissileLauncherLight> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  509;
        }

        @Override
        public String getName() {
            return "MissileLauncherLight";
        }

        @Override
        public synchronized Map<Integer, MissileLauncherLight> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissileLauncherLight.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, MissileLauncherLight> types;
        }
    }
}
