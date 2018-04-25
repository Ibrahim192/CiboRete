package io.ciborete.dto;

import io.ciborete.enums.SortOrder;

public class Request {

    private static int DEFAULT_PAGE_LIMIT = 20;

    private static int DEFAULT_PAGE_OFFSET=0;

    int pageLimit=DEFAULT_PAGE_LIMIT;

    int pageOffset=DEFAULT_PAGE_OFFSET;

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    String sortKey="";

    SortOrder sortOrder=SortOrder.ASC;

    String search;

    String searchType;

}
