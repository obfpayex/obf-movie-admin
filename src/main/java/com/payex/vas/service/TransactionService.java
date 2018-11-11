package com.payex.vas.service;

import com.payex.vas.domain.Foo;
import com.payex.vas.repository.TransactionRepository;
import com.payex.vas.util.PartialUpdateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Transactional(readOnly = true)
    public Foo getTransaction(Long oid) {

        Foo tx = transactionRepository.findOneByOid(oid);
        if (tx == null)
            log.info("Could not find Foo by oid: {}", oid);

        return tx;
    }

    @Transactional
    public Foo update(Foo transaction) {

        Foo tx = transactionRepository.findOneByOid(transaction.getOid());
        if (tx == null) {
            log.info("Could not find Foo by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Updating Foo with oid: {}", tx.getOid());
        transaction.setModified(LocalDateTime.now());
        transaction = transactionRepository.save(transaction);

        return transaction;
    }

    @Transactional
    public Foo partialUpdate(Foo transaction) throws Exception {
        if (transaction.getOid() == null)
            return null;

        Foo tx = transactionRepository.findOneByOid(transaction.getOid());
        if (tx == null) {
            log.info("Could not find Foo by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Partially updating Foo with oid: {}", tx.getOid());

        PartialUpdateUtil.copyNonNullProperties(transaction, tx);
        tx.setModified(LocalDateTime.now());
        tx = transactionRepository.save(tx);

        return tx;
    }

    @Transactional
    public Foo save(Foo transaction) {
        log.info("Saving tx");
        transaction.setModified(LocalDateTime.now());
        transactionRepository.save(transaction);

        return transaction;
    }
}
