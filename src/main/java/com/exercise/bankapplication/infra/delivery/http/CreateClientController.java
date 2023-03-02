package com.exercise.bankapplication.infra.delivery.http;

import com.exercise.bankapplication.application.client.usecase.CreateClientUseCase;
import com.exercise.bankapplication.domain.client.entities.Client;
import com.exercise.bankapplication.domain.client.exeptions.InvalidClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clientes")
@Validated
public class CreateClientController {

    @Autowired
    CreateClientUseCase actions;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client.isValid(client);
            Client createdClient = actions.execute(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } catch (InvalidClientException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se ha producido un error interno en el servidor." + e.getMessage());
        }
    }


}
