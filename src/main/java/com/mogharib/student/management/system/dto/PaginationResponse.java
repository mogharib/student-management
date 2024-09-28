package com.mogharib.student.management.system.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationResponse<T> {
    private final int currentPage;
    private final long totalItems;
    private final long totalPages;
    private final List<T> data;
}
