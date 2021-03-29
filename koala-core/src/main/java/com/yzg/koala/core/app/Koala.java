package com.yzg.koala.core.app;

import android.content.Context;

import java.util.WeakHashMap;

public final class Koala {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();

    }

    public static WeakHashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getKoalaConfigs();
    }
    public static  Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }
    @SuppressWarnings("unchecked")
    public static  <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }
}
