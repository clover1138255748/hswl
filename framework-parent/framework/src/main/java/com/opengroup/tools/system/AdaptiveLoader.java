package com.opengroup.tools.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.opengroup.exception.BaseFrameworkException;

public class AdaptiveLoader<T> {

    private static Logger                                           logger           = Logger
        .getLogger(AdaptiveLoader.class);

    public static final String                                      HONGSHI_DIR      = "META-INF/hongshi/";

    private static final ConcurrentMap<Class<?>, AdaptiveLoader<?>> ADAPTIVE_LOADERS = new ConcurrentHashMap<Class<?>, AdaptiveLoader<?>>();

    @SuppressWarnings("unchecked")
    public static <T> AdaptiveLoader<T> getAdaptiveLoader(Class<T> type) {
        if (type == null)
            throw new IllegalArgumentException("Extension type == null");
        if (!type.isInterface()) {
            throw new IllegalArgumentException("Extension type(" + type + ") is not interface!");
        }

        AdaptiveLoader<T> loader = (AdaptiveLoader<T>) ADAPTIVE_LOADERS.get(type);
        if (loader == null) {
            ADAPTIVE_LOADERS.putIfAbsent(type, new AdaptiveLoader<T>(type));
            loader = (AdaptiveLoader<T>) ADAPTIVE_LOADERS.get(type);
        }
        return loader;
    }

    private final Map<String, Class<?>>    cachedClasses   = new HashMap<String, Class<?>>();

    private final ConcurrentMap<String, T> cachedInstances = new ConcurrentHashMap<String, T>();

    private final Class<?>                 type;

    /**
     * 构造函数时查找可用的type类型的类
     * @param type
     */
    private AdaptiveLoader(Class<?> type) {
        this.type = type;
        loadFile(cachedClasses, HONGSHI_DIR);
    }

    /**
     * 根据名称获取对应接口的适配器
     * @param name
     * @return
     */
    public T getAdapter(String name) {
        T o = cachedInstances.get(name);
        if (o == null) {
            o = createAdapter(name);
            cachedInstances.putIfAbsent(name, o);
        }
        return o;
    }

    /**
     * 创建适配对象
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    private T createAdapter(String name) {
        Class<?> cls = cachedClasses.get(name);
        if (cls == null) {
            StringBuilder buffer = new StringBuilder();
            buffer.append("接口类型[").append(type.getName()).append("]没有找到[").append(name)
                .append("]名称的类型实现");
            throw new BaseFrameworkException(buffer.toString());
        }
        try {
            return (T) cls.newInstance();
        } catch (Exception e) {
            StringBuilder buffer = new StringBuilder();
            buffer.append("创建接口[").append(type.getName()).append("],类型名称[").append(name)
                .append("]失败");
            throw new BaseFrameworkException(e, buffer.toString());
        }
    }

    private ClassLoader findClassLoader() {
        return type.getClassLoader();
    }

    private void loadFile(Map<String, Class<?>> map, String dir) {
        String fileName = new StringBuilder(dir).append(type.getName()).toString();
        try {
            Enumeration<java.net.URL> urls;
            ClassLoader classLoader = findClassLoader();
            if (classLoader != null) {
                urls = classLoader.getResources(fileName);
            } else {
                urls = ClassLoader.getSystemResources(fileName);
            }
            if (urls != null) {
                while (urls.hasMoreElements()) {
                    java.net.URL url = urls.nextElement();
                    try {
                        BufferedReader reader = new BufferedReader(
                            new InputStreamReader(url.openStream(), "utf-8"));
                        try {
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                final int ci = line.indexOf('#');
                                if (ci >= 0)
                                    line = line.substring(0, ci);
                                line = line.trim();
                                if (line.length() > 0) {
                                    try {
                                        String name = null;
                                        int i = line.indexOf('=');
                                        if (i > 0) {
                                            name = line.substring(0, i).trim();
                                            line = line.substring(i + 1).trim();
                                        }
                                        Class<?> clazz = null;
                                        if (line.length() > 0) {
                                            clazz = Class.forName(line, true, classLoader);
                                        }
                                        Class<?> cls = (Class<?>) map.get(name);
                                        if (cls != null) {
                                            StringBuilder buffer = new StringBuilder();
                                            buffer.append("class:[").append(type)
                                                .append("],the name is [").append(name)
                                                .append("] has one more define class.[")
                                                .append(cls.getName()).append("]-[").append(clazz)
                                                .append("]");
                                            logger.info(buffer.toString());
                                        }
                                        map.put(name, clazz);
                                    } catch (Throwable t) {
                                        String msg = new StringBuilder(
                                            "Failed to load extension class(").append(type)
                                                .append(", class line: ").append(line)
                                                .append(") in ").append(url).append(", cause: ")
                                                .append(t.getMessage()).toString();
                                        logger.error(msg);
                                        throw new BaseFrameworkException(t, msg);
                                    }
                                }
                            }
                        } finally {
                            reader.close();
                        }
                    } catch (Throwable t) {
                        logger.error("Exception when load extension class(interface: " + type
                                     + ", class file: " + url + ") in " + url,
                            t);
                    }
                }
            }
        } catch (Throwable t) {
            String msg = new StringBuilder("Exception when load extension class(interface: ")
                .append(type).append(", description file: ").append(fileName).append(").")
                .toString();
            logger.error(msg, t);
            throw new BaseFrameworkException(t, msg);
        }
    }
}