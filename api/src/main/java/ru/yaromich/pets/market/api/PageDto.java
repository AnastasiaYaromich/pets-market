package ru.yaromich.pets.market.api;
import java.util.ArrayList;
import java.util.List;

public class PageDto {
    private List<ProductDto> content;
    private int totalPages;

    public PageDto(List<ProductDto> content, int totalPages) {
        this.content = content;
        this.totalPages = totalPages;
    }

    public PageDto() {}

    public void setContent(List<ProductDto> content) {
        this.content = content;
    }

    public List<ProductDto> getContent() {
        return content;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
