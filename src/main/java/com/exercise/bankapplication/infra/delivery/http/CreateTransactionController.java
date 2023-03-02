package com.exercise.bankapplication.infra.delivery.http;

import com.exercise.bankapplication.application.bankaccount.dto.TransactionDTO;
import com.exercise.bankapplication.application.bankaccount.usecase.CreateTransactionsUseCase;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.domain.bankaccount.exeptions.InvalidBankAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movimientos")
@Validated
public class CreateTransactionController {

    @Autowired
    CreateTransactionsUseCase actions;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createClient(@RequestBody Transaction transactionIn) {
        try {
            Transaction.isValid(transactionIn);
            TransactionDTO transaction = actions.execute(transactionIn);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (InvalidBankAccountException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se ha producido un error interno en el servidor." + e.getMessage());
        }
    }


}
