package com.example.blog_app_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse <T>
{

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private List<T> content;
    private boolean hasNextPage;

}
