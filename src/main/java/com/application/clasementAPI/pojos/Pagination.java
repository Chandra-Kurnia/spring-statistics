package com.application.clasementAPI.pojos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pagination {
    private Long totalData;
    private int totalPages;
    private int currentPage;
}
