package com.yzg.koala.core.app;

import java.util.WeakHashMap;

public class Configurator {

    private static final WeakHashMap<String,Object> KOALA_CONFIGS = new WeakHashMap<>();


    public Configurator() {
        KOALA_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static  Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public WeakHashMap<String,Object>  getKoalaConfigs(){
        return KOALA_CONFIGS;
    }

    private static class Holder{
        private static final Configurator INSTANCE =new Configurator();
    }

    public final void configure(){
        KOALA_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host){
        KOALA_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) KOALA_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) KOALA_CONFIGS.get(key.name());
    }
}

