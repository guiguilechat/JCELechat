package fr.guiguilechat.jcelechat.jcesi.holders;

import java.util.function.Consumer;

public interface Listener<T> extends Consumer<Notification<T>> {

}