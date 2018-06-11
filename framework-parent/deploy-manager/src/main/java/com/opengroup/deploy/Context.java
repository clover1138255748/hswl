/**
 * hongshiwl.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */
package com.opengroup.deploy;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author UU
 * @version $Id: Context.java, v 0.1 2017年1月11日 下午5:43:39 UU Exp $
 */
public class Context {
    private Map<String, Object>     global     = new HashMap<String, Object>();

    private Set<ExecutionObject<?>> executions = new HashSet<ExecutionObject<?>>();

    private Map<String, Object>     current;

    public void setAttributes(Map<String, Object> attributes) {
        this.current = attributes;
    }

    public void clean() {
        this.current = null;
    }

    public void removeAttribute(String key) {
        if (current != null) {
            current.remove(key);
        }
    }

    public Object getAttribute(String key) {
        if (current != null) {
            return current.get(key);
        }
        return null;
    }

    public Set<String> getKeys() {
        if (current != null) {
            return current.keySet();
        }
        return null;
    }

    public Collection<Object> getValues() {
        if (current != null) {
            return current.values();
        }
        return null;
    }

    public void setGlobalAttribute(String key, Object value) {
        global.put(key, value);
    }

    public void removeGlobalAttribute(String key) {
        global.remove(key);
    }

    public Object getGlobalAttribute(String key) {
        return global.get(key);
    }

    public Set<String> getGlobalKeys() {
        return global.keySet();
    }

    public Collection<Object> getGlobalValues() {
        return global.values();
    }

    public void addExecution(ExecutionObject<?> execution) {
        ExecutionObject<?> eo = getExecution(execution.getName());
        if (eo != null) {
            if (eo.isAlive()) {
                eo.stop();
            }
            executions.remove(eo);
        }
        execution.start();
        executions.add(execution);
    }

    public ExecutionObject<?> getExecution(String name) {
        Iterator<ExecutionObject<?>> ips = executions.iterator();
        while (ips.hasNext()) {
            ExecutionObject<?> p = ips.next();
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void removeExecution(String name) {
        ExecutionObject<?> eo = getExecution(name);
        if (eo != null) {
            if (eo.isAlive()) {
                eo.stop();
            }
            executions.remove(eo);
        }
    }

    public void removeExecution(ExecutionObject<?> eo) {
        if (eo != null) {
            executions.remove(eo);
        }
    }

    public Set<ExecutionObject<?>> getExecutions() {
        return executions;
    }
}
