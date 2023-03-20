package app.it_academy.fitnessAppUsers.core.dto.pageDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class PageDto<T>{

    @JsonProperty(value = "number")
    private int number;

    @JsonProperty(value = "size")
    private int size;
    @JsonProperty(value = "total_pages")
    private int totalPages;

    @JsonProperty(value = "total_elements")
    private long totalElements;

    @JsonProperty(value = "first")
    private boolean first;

    @JsonProperty(value = "number_of_elements")
    private int numberOfElements;

    @JsonProperty(value = "last")
    private boolean last;

    @JsonProperty(value = "content")
    private List<T> content;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
