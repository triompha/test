package net.csdn.common.thread;

import net.csdn.common.logging.CSLogger;
import net.csdn.common.logging.Loggers;

import java.lang.ref.Reference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BlogInfo: william
 * Date: 11-9-5
 * Time: 下午10:20
 */
public class ThreadLocals {

    private static final CSLogger logger = Loggers.getLogger(ThreadLocals.class);

    public static class CleanableValue<T> {

        private T value;

        public CleanableValue(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public void set(T value) {
            this.value = value;
        }
    }

    public static void clearReferencesThreadLocals() {
        try {
            Thread[] threads = getThreads();
            // Make the fields in the Thread class that store ThreadLocals
            // accessible
            Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
            threadLocalsField.setAccessible(true);
            Field inheritableThreadLocalsField = Thread.class.getDeclaredField("inheritableThreadLocals");
            inheritableThreadLocalsField.setAccessible(true);
            // Make the underlying array of ThreadLoad.ThreadLocalMap.Entry objects
            // accessible
            Class<?> tlmClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);

            for (int i = 0; i < threads.length; i++) {
                Object threadLocalMap;
                if (threads[i] != null) {
                    // Clear the first map
                    threadLocalMap = threadLocalsField.get(threads[i]);
                    clearThreadLocalMap(threadLocalMap, tableField);
                    // Clear the second map
                    threadLocalMap =
                            inheritableThreadLocalsField.get(threads[i]);
                    clearThreadLocalMap(threadLocalMap, tableField);
                }
            }
        } catch (Exception e) {
            logger.warn("Failed to clean thread locals", e);
        }
    }


    /*
     * Clears the given thread local map object. Also pass in the field that
     * points to the internal table to save re-calculating it on every
     * call to this method.
     */

    private static void clearThreadLocalMap(Object map, Field internalTableField) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        if (map != null) {
            Method mapRemove = map.getClass().getDeclaredMethod("remove", ThreadLocal.class);
            mapRemove.setAccessible(true);
            Object[] table = (Object[]) internalTableField.get(map);
            int staleEntriesCount = 0;
            if (table != null) {
                for (int j = 0; j < table.length; j++) {
                    if (table[j] != null) {
                        boolean remove = false;
                        // Check the key
                        Object key = ((Reference<?>) table[j]).get();
                        // Check the value
                        Field valueField = table[j].getClass().getDeclaredField("value");
                        valueField.setAccessible(true);
                        Object value = valueField.get(table[j]);
                        if ((value != null && CleanableValue.class.isAssignableFrom(value.getClass()))) {
                            remove = true;
                        }
                        if (remove) {
                            Object[] args = new Object[4];
                            if (key != null) {
                                args[0] = key.getClass().getCanonicalName();
                                args[1] = key.toString();
                            }
                            args[2] = value.getClass().getCanonicalName();
                            args[3] = value.toString();
                            if (logger.isTraceEnabled()) {
                                logger.trace("ThreadLocal with key of type [{}] (value [{}]) and a value of type [{}] (value [{}]):  The ThreadLocal has been forcibly removed.", args);
                            }
                            if (key == null) {
                                staleEntriesCount++;
                            } else {
                                mapRemove.invoke(map, key);
                            }
                        }
                    }
                }
            }
            if (staleEntriesCount > 0) {
                Method mapRemoveStale = map.getClass().getDeclaredMethod("expungeStaleEntries");
                mapRemoveStale.setAccessible(true);
                mapRemoveStale.invoke(map);
            }
        }
    }

    /*
     * Get the set of current threads as an array.
     */

    private static Thread[] getThreads() {
        // Get the current thread group
        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        // Find the root thread group
        while (tg.getParent() != null) {
            tg = tg.getParent();
        }

        int threadCountGuess = tg.activeCount() + 50;
        Thread[] threads = new Thread[threadCountGuess];
        int threadCountActual = tg.enumerate(threads);
        // Make sure we don't miss any threads
        while (threadCountActual == threadCountGuess) {
            threadCountGuess *= 2;
            threads = new Thread[threadCountGuess];
            // Note tg.enumerate(Thread[]) silently ignores any threads that
            // can't fit into the array
            threadCountActual = tg.enumerate(threads);
        }

        return threads;
    }
}
