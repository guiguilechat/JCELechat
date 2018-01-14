
package fr.guiguilechat.eveonline.model.sde.compiled.items;

import fr.guiguilechat.eveonline.model.sde.compiled.EveItem;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;

public abstract class Skill
    extends EveItem
{

    /**
     * Level of skill
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double SkillLevel;

    @Override
    public int getCategoryId() {
        return  16;
    }

    @Override
    public Class<?> getCategory() {
        return Skill.class;
    }

}
