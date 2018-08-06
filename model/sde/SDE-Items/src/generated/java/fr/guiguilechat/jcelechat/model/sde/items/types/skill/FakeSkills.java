package fr.guiguilechat.jcelechat.model.sde.items.types.skill;

import fr.guiguilechat.jcelechat.model.sde.items.types.Skill;

public class FakeSkills
    extends Skill
{

    @Override
    public int getGroupId() {
        return  505;
    }

    @Override
    public Class<?> getGroup() {
        return FakeSkills.class;
    }
}
