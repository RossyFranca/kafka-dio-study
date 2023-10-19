package com.franca.kafkadiostudy.controller;

import com.franca.kafkadiostudy.data.OrderData;
import com.franca.kafkadiostudy.service.EventRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderController {
    private final EventRegisterService eventRegisterService;
    @PostMapping
    public ResponseEntity<String> saveOrder(@RequestBody OrderData order){
        eventRegisterService.addEvent("saveorder", order);
        return ResponseEntity.ok("Success");
    }
}
