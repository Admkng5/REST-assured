package data;

import java.util.List;

public class ResponsePage {

    Integer page;
    Integer per_page;
    Integer total;
    Integer total_pages;
    List<UsersData> data;
    SupportData support;

    public ResponsePage() {
        super();
    }

    public ResponsePage(Integer page, Integer per_page, Integer total, Integer total_pages, List<UsersData> data, SupportData support) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
        this.support=support;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<UsersData> getData() {
        return data;
    }

    public void setData(List<UsersData> data) {
        this.data = data;
    }

    public SupportData getSupport() {
        return support;
    }

    public void setSupport(SupportData support) {
        this.support = support;
    }

}
