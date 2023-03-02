package com.exercise.bankapplication.infra.delivery.http;

import com.exercise.bankapplication.application.bankaccount.usecase.GetTransactionsByDateAndUserUseCase;
import com.exercise.bankapplication.domain.bankaccount.entities.Transaction;
import com.exercise.bankapplication.domain.bankaccount.entities.TransactionListDTO;
import com.exercise.bankapplication.domain.bankaccount.exeptions.InvalidBankAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/movimientos")
@Validated
public class GetTransactionController {

    @Autowired
    GetTransactionsByDateAndUserUseCase actions;

    @RequestMapping(path = "/{userId}/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> createClient(@PathVariable Long userId, @PathVariable LocalDate date) {
        try {
            List<TransactionListDTO> transaction = actions.execute(date, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (InvalidBankAccountException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se ha producido un error interno en el servidor." + e.getMessage());
        }
    }


}
