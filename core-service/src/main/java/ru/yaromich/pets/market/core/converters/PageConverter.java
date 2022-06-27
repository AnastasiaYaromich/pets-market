package ru.yaromich.pets.market.core.converters;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.yaromich.pets.market.api.PageDto;
import ru.yaromich.pets.market.api.ProductDto;

@Component
public class PageConverter {
    public PageDto entityToDto(Page<ProductDto> p) {
        PageDto pageDto = new PageDto();
        pageDto.setContent(p.getContent());
        pageDto.setTotalPages(p.getTotalPages());
        return pageDto;
    }
}
