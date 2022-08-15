package com.example.concurrency.facade;

import com.example.concurrency.repository.LockRepository;
import com.example.concurrency.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class NamedLockStockFacade {

    private LockRepository lockRepository;

    private StockService stockService;

    public NamedLockStockFacade(LockRepository lockRepository, StockService stockService) {
        this.lockRepository = lockRepository;
        this.stockService = stockService;
    }

    public void decrease(long id, long quantity) {
        try {
            lockRepository.getLock(String.valueOf(id));
            stockService.decrease(id, quantity);
        } finally {
            lockRepository.releaseLock(String.valueOf(id));
        }
    }
}
