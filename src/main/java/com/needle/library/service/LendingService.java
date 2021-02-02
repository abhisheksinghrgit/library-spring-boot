package com.needle.library.service;

import com.needle.library.models.Lending;

public interface LendingService {

    Lending reserveBook(Long userId, String isbn) throws Exception;

    Lending unReserveBook(Long userId, Long lendingId) throws Exception;
}
