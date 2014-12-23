package org.apache.directmemory.jmx;

import org.apache.directmemory.cache.CacheService;
import org.apache.directmemory.memory.MemoryManagerService;

public interface CacheServiceMonitorMBean<K, V>{


    /**
     * Tells the {@link CacheService} to collect and remove all expired keys. In most cases this is automatically
     * handled by scheduling a disposal interval using {@link CacheService#scheduleDisposalEvery} and there are very
     * rare cases where this needs to be called manually.
     */
    void collectExpired();

    /**
     * Tells the {@link CacheService} to collect and remove all least frequently used keys. This operation could
     * possibly clear the whole cache if there were no recent actions. In most cases this is automatically handled by
     * scheduling a disposal interval using {@link CacheService#scheduleDisposalEvery} and there are very rare cases
     * where this needs to be called manually.
     */
    void collectLFU();

    /**
     * Tells the {@link CacheService} to collect and remove all expired AND least frequently used keys. In most cases
     * this is automatically handled by scheduling a disposal interval using {@link CacheService#scheduleDisposalEvery}
     * and there are very rare cases where this needs to be called manually.
     */
    void collectAll();

    /**
     * Clears the whole cache by removing all stored keys. It is up to the underlying {@link MemoryManagerService}
     * implementation to free allocated memory or not.
     */
    void clear();

    /**
     * Retrieves the count of the current entries.
     * 
     * @return Number of entries
     */
    long count();
    
    long capacity();
    
    long used();

    /**
     * Dumps information about the actual internal {@link MemoryManagerService} to the configured {@link Logger} with
     * info loglevel.
     */
//    void dump();

    /**
     * Removes the key and frees the underlying memory area.
     * 
     * @param key The key to remove
     */
    void free( K key );
    

    /**
     * 启动时间
     * @return
     */
    long getStartTime();
    
    /**
     * get的总次数
     * @return
     */
    long gets();
    
    /**
     * 命中次数
     * @return
     */
    long hits();
   
    /**
     * set的总次数
     * @return
     */
    long sets();
    
    
    /**
     * 堆设置的最大内存
     * @return
     */
    long maxMemorys();
    
    /**
     * 堆总共内存
     * @return
     */
    long totalMemorys();
    
    /**
     * 堆空闲内存
     * @return
     */
    long freeMemorys();
    
}
