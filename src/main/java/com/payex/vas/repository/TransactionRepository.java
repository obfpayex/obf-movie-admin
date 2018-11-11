package com.payex.vas.repository;



import com.payex.vas.domain.Foo;
import com.payex.vas.repository.sla.SlaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends SlaRepository<Foo, Long> {
    //@LogDbSla(type = SELECT, system = VASCASE24DBA, tableName = "Transaction")
    Foo findOneByOid(long oid);
}
