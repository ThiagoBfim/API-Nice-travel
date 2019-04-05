package com.nicetravel.nicetravel.repository.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PagableUtil {

    public static Pageable createPagable(int page, int size) {
        return PageRequest.of(page, size);
    }
}
