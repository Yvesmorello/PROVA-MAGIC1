package com.example.apiMagic.apiMagic.controller;

import com.example.apiMagic.apiMagic.dto.*;
import com.example.apiMagic.apiMagic.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/deck")
public class DeckController {

    @Autowired
    private DeckService deckService;

    @GetMapping("/commanders")
    public ResponseEntity<ApiResponse> commander() {
        ApiResponse response = deckService.getAllCommanders();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/commander/{name}")
    public ResponseEntity<ApiResponse> commanderName(@PathVariable String name) {
        ApiResponse response = deckService.fetchAndSaveCommanderByName(name);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else if (response.getData() != null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

        @GetMapping("/cards")
        public ResponseEntity<ApiResponse> cards() throws IOException {
            ApiResponse response = deckService.getDeckByCommanderColor();

            if(response.isSuccess()) {
                return ResponseEntity.ok((response));
            } else if (response.getData() != null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }

}


