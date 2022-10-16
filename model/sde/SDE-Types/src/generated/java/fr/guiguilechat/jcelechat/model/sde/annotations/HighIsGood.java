package fr.guiguilechat.jcelechat.model.sde.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface HighIsGood {

    public boolean value();
}
