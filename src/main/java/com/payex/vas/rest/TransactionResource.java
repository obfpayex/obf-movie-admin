package com.payex.vas.rest;

//import com.payex.vas.case24.dal.db.businessobjects.Foo;
//import com.payex.vas.common.vasutil.utils.Stopwatch;
//import com.payex.vas.config.slalogging.LogApiSla;
//import com.payex.vas.config.slalogging.RestEntrypointLoggingContext;
//import com.payex.vas.config.slalogging.TimedApi;
//import com.payex.vas.config.surveillance.AppSurveillance;
import com.google.common.base.Stopwatch;
import com.payex.vas.domain.Foo;
import com.payex.vas.service.TransactionService;
import com.payex.vas.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.payex.vas.util.Constants.APPLICATION_NAME;


@RestController
@RequestMapping("/api")
public class TransactionResource {
    private static final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    //@AppSurveillance
    //@LogApiSla
    //@TimedApi(applicationName = APPLICATION_NAME)
    //@RestEntrypointLoggingContext()
    @GetMapping(value = "/transaction/{oid}")
    public ResponseEntity<Foo> getTransactionByOid(@PathVariable("oid") Long oid) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("getTransactionByOid() invoked: {} ", oid);

            Foo response = transactionService.getTransaction(oid);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "getTransactionByOid", stopwatch.stop().toString());
        }
    }

    //@AppSurveillance
    //@LogApiSla
    //@TimedApi(applicationName = APPLICATION_NAME)
    //@RestEntrypointLoggingContext(requestBodyClientIdPath = "acquirerId")
    @PostMapping(value = "/transaction")
    public ResponseEntity<Foo> addTransaction(@RequestBody @Valid Foo request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("addTransaction() invoked for transaction with oid: {} ", request.getOid());

            Foo response = transactionService.save(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "addTransaction", stopwatch.stop().toString());
        }
    }

    //@AppSurveillance
    //@LogApiSla
    //@TimedApi(applicationName = APPLICATION_NAME)
    //@RestEntrypointLoggingContext(requestBodyClientIdPath = "acquirerId")
    @PutMapping(value = "/transaction")
    public ResponseEntity<Foo> updateTransaction(@RequestBody @Valid Foo request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("updateTransaction() invoked for transaction with oid: {} ", request.getOid());

            Foo response = transactionService.update(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "updateTransaction", stopwatch.stop().toString());
        }
    }

    //@AppSurveillance
    //@LogApiSla
    //@TimedApi(applicationName = APPLICATION_NAME)
    //@RestEntrypointLoggingContext(requestBodyClientIdPath = "acquirerId")
    @PatchMapping(value = "/transaction")
    public ResponseEntity<Foo> patchTransaction(@RequestBody Foo request) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("patchTransaction() invoked for transaction with oid: {} ", request.getOid());

            Foo response = transactionService.partialUpdate(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "patchTransaction", stopwatch.stop().toString());
        }
    }
}
