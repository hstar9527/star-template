package com.star.template;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Context {
    private static final ThreadLocal<Context> LOCAL = new ThreadLocal<Context>();

    private final Thread thread;

    private final int level;

    private final Context parent;

    private Map<String, Object> current;

    private Object out;

    private Template template;

    private Engine engine;

    private Context(Context parent, Map<String, Object> current) {
        this.thread = parent == null ? Thread.currentThread() : parent.thread;
        this.level = parent == null ? 0 : parent.getLevel() + 1;
        this.parent = parent;
        setCurrent(current);
    }

    public static Context getContext() {
        Context context = LOCAL.get();
        if (context == null) {
            context = new Context(null, null);
            LOCAL.set(context);
        }
        return context;
    }

    public static Context pushContext() {
        return pushContext(null);
    }

    public static Context pushContext(Map<String, Object> current) {
        Context context = new Context(getContext(), current);
        LOCAL.set(context);
        return context;
    }

    public static void popContext() {
        Context context = LOCAL.get();
        if (context != null) {
            Context parent = context.getParent();
            if (parent != null) {
                LOCAL.set(parent);
            } else {
                LOCAL.remove();
            }
        }
    }

    public static void removeContext() {
        LOCAL.remove();
    }

    private void checkThread() {
        if (Thread.currentThread() != thread) {
            throw new IllegalStateException("Don't cross-thread using the "
                    + Context.class.getName() + " object, it's thread-local only. context thread: "
                    + thread.getName() + ", current thread: " + Thread.currentThread().getName());
        }
    }

    private void setCurrent(Map<String, Object> current) {
        if (current instanceof Context) {
            throw new IllegalArgumentException("Don't using the " + Context.class.getName()
                    + " object as a parameters, it's implicitly delivery by thread-local. parameter context: "
                    + ((Context) current).thread.getName() + ", current context: " + thread.getName());
        }
        this.current = current;
    }

    public int getLevel() {
        checkThread();
        return level;
    }

    public Context getParent() {
        checkThread();
        return parent;
    }

    public Template getTemplate() {
        checkThread();
        return template;
    }

    public Context setTemplate(Template template) {
        checkThread();
        if (template != null) {
            setEngine(template.getEngine());
        }
        this.template = template;
        return this;
    }

    public Engine getEngine() {
        checkThread();
        return engine;
    }

    public Context setEngine(Engine engine) {
        checkThread();
        if (engine != null) {
            if (template != null && template.getEngine() != engine) {
                throw new IllegalStateException("Failed to set the context engine, because is not the same to template engine. template engine: "
                        + template.getEngine().getName() + ", context engine: " + engine.getName()
                        + ", template: " + template.getName() + ", context: " + thread.getName());
            }
            if (parent != null && parent.getEngine() != engine) {
                parent.setEngine(engine);
            }
            if (this.engine == null) {
                setCurrent(engine.createContext(parent, current));
            }
        }
        this.engine = engine;
        return this;
    }

    public Object getOut() {
        checkThread();
        return out;
    }

    public Context setOut(Writer out) {
        checkThread();
        this.out = out;
        return this;
    }

    public Context setOut(OutputStream out) {
        checkThread();
        this.out = out;
        return this;
    }

    public Object get(String key, Object defaultValue) {
        Object value = get(key);
        return value == null ? defaultValue : value;
    }

    public Object get(Object key) {
        checkThread();
        return current == null ? null : current.get(key);
    }

    public int size() {
        checkThread();
        return current == null ? 0 : current.size();
    }

    public boolean isEmpty() {
        checkThread();
        return current == null ? true : current.isEmpty();
    }

    public boolean containsKey(Object key) {
        checkThread();
        return current == null ? false : current.containsKey(key);
    }

    public boolean containsValue(Object value) {
        checkThread();
        return current == null ? false : current.containsValue(value);
    }

    @SuppressWarnings("unchecked")
    public Set<String> keySet() {
        checkThread();
        return current == null ? Collections.EMPTY_SET : current.keySet();
    }

    @SuppressWarnings("unchecked")
    public Collection<Object> values() {
        checkThread();
        return current == null ? Collections.EMPTY_SET : current.values();
    }

    @SuppressWarnings("unchecked")
    public Set<Map.Entry<String, Object>> entrySet() {
        checkThread();
        return current == null ? Collections.EMPTY_SET : current.entrySet();
    }

    public Object put(String key, Object value) {
        checkThread();
        if (current == null) {
            current = new HashMap<String, Object>();
        }
        return current.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends Object> m) {
        checkThread();
        if (current == null) {
            current = new HashMap<String, Object>();
        }
        current.putAll(m);
    }

    public Object remove(Object key) {
        checkThread();
        return current == null ? null : current.remove(key);
    }

    public void clear() {
        checkThread();
        if (current != null) {
            current.clear();
        }
    }
}