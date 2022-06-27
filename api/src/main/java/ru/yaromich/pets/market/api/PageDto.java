package ru.yaromich.pets.market.api;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Модель страницы")
public class PageDto {
    @Schema (description = "Список продуктов на странице", required = true)
    private List<ProductDto> content;

    @Schema (description = "Количество страниц", required = true, example = "1")
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
