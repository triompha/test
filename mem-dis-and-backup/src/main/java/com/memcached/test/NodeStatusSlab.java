package com.memcached.test;

public class NodeStatusSlab {
	private final int slabId;
	private long cas_hits;
	private long used_chunks;
	private long get_hits;
	private long free_chunks;
	private long total_chunks;
	private long decr_hits;
	private long cmd_set;
	private long chunks_per_page;
	private long chunk_size;
	private long cas_badval;
	private long total_pages;
	private long delete_hits;
	private long free_chunks_end;
	private long mem_requested;
	private long incr_hits;

	public NodeStatusSlab(int slabId) {
		super();
		this.slabId = slabId;
	}

	public int getSlabId() {
		return slabId;
	}

	public long getCas_hits() {
		return cas_hits;
	}
	public void setCas_hits(long casHits) {
		cas_hits = casHits;
	}
	public long getUsed_chunks() {
		return used_chunks;
	}
	public void setUsed_chunks(long usedChunks) {
		used_chunks = usedChunks;
	}
	public long getGet_hits() {
		return get_hits;
	}
	public void setGet_hits(long getHits) {
		get_hits = getHits;
	}
	public long getFree_chunks() {
		return free_chunks;
	}
	public void setFree_chunks(long freeChunks) {
		free_chunks = freeChunks;
	}
	public long getTotal_chunks() {
		return total_chunks;
	}
	public void setTotal_chunks(long totalChunks) {
		total_chunks = totalChunks;
	}
	public long getDecr_hits() {
		return decr_hits;
	}
	public void setDecr_hits(long decrHits) {
		decr_hits = decrHits;
	}
	public long getCmd_set() {
		return cmd_set;
	}
	public void setCmd_set(long cmdSet) {
		cmd_set = cmdSet;
	}
	public long getChunks_per_page() {
		return chunks_per_page;
	}
	public void setChunks_per_page(long chunksPerPage) {
		chunks_per_page = chunksPerPage;
	}
	public long getChunk_size() {
		return chunk_size;
	}
	public void setChunk_size(long chunkSize) {
		chunk_size = chunkSize;
	}
	public long getCas_badval() {
		return cas_badval;
	}
	public void setCas_badval(long casBadval) {
		cas_badval = casBadval;
	}
	public long getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(long totalPages) {
		total_pages = totalPages;
	}
	public long getDelete_hits() {
		return delete_hits;
	}
	public void setDelete_hits(long deleteHits) {
		delete_hits = deleteHits;
	}
	public long getFree_chunks_end() {
		return free_chunks_end;
	}
	public void setFree_chunks_end(long freeChunksEnd) {
		free_chunks_end = freeChunksEnd;
	}
	public long getMem_requested() {
		return mem_requested;
	}
	public void setMem_requested(long memRequested) {
		mem_requested = memRequested;
	}
	public long getIncr_hits() {
		return incr_hits;
	}
	public void setIncr_hits(long incrHits) {
		incr_hits = incrHits;
	}

}
