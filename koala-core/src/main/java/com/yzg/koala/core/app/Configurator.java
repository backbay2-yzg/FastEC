package com.yzg.koala.core.app;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

public class Configurator {

    private static final WeakHashMap<Object,Object> KOALA_CONFIGS = new WeakHashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    public Configurator() {
        KOALA_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static  Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public WeakHashMap<Object,Object>  getKoalaConfigs(){
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

    public final  Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        KOALA_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    public final  Configurator withInterceptors(ArrayList<Interceptor>  interceptors){
        INTERCEPTORS.addAll(interceptors);
        KOALA_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    private void checkConfiguration(){
        final boolean isReady = (boolean) KOALA_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        final Object value = KOALA_CONFIGS.get(key);
        if (value==null){
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) KOALA_CONFIGS.get(key);
    }
}

