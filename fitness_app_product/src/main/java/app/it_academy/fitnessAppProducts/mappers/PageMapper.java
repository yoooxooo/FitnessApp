package app.it_academy.fitnessAppProducts.mappers;

import app.it_academy.fitnessAppProducts.core.dto.pageDto.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper<T> {



    public PageDto<T> toDto(Page<T> page) {
        PageDto<T> pageDto = new PageDto<T>();

        pageDto.setContent(page.getContent());
        pageDto.setFirst(page.isFirst());
        pageDto.setLast(page.isLast());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setNumber(page.getNumber());
        pageDto.setSize(page.getSize());
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setNumberOfElements(page.getNumberOfElements());

        return pageDto;
    }
}
