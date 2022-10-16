package fr.guiguilechat.jcelechat.model.sde.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Stackable {

    public boolean value();
}
