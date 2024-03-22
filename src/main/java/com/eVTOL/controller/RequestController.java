package com.eVTOL.controller;

import com.eVTOL.dto.RequestDTO;
import com.eVTOL.model.Request;
import com.eVTOL.service.implimentation.RequestImplimentation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/request")
@CrossOrigin(origins = "*")
public class RequestController {
    private final RequestImplimentation requestImplimentation;

    public RequestController(RequestImplimentation requestImplimentation) {
        this.requestImplimentation = requestImplimentation;
    }
//    @PostMapping
//    public String makeRequest(@RequestBody RequestDTO requestDTO){
//        requestImplimentation.makeRequest(requestDTO);
//        return "new request has been made now";
//    }
    @GetMapping
    public List<Request> getAllRequest(){
         return  requestImplimentation.getAllRequest();
    }
    @DeleteMapping("/{id}")
    public  String deleteByID(@PathVariable Long id){
        requestImplimentation.deleteRequest(id);
        return "request has been deleted";
    }
    @PatchMapping("/{id}/{state}")
    public String updateState(@PathVariable Long id, @PathVariable int state){
        requestImplimentation.updateState(id, state);
        return "State has been updated";
    }
    @PostMapping("/add/{productId}")
    public Request assignProductToRequest(@PathVariable Long productId, @RequestBody RequestDTO requestDTO){
        return requestImplimentation.assignProductToRequest(productId, requestDTO);
    }
}

