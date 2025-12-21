package dev.idinaldo.brabank.auth_service.controller;

import dev.idinaldo.brabank.auth_service.service.EncryptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

@RestController
@RequestMapping("/api")
public class Controller {

    private EncryptionService encryptionService;

    public Controller(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello, World!";
    }

}
