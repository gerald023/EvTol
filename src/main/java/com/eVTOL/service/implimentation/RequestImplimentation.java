package com.eVTOL.service.implimentation;

import com.eVTOL.dto.RequestDTO;
import com.eVTOL.enums.RequestState;
import com.eVTOL.model.Product;
import com.eVTOL.model.Request;
import com.eVTOL.repository.ProductRepository;
import com.eVTOL.repository.RequestRepository;
import com.eVTOL.service.RequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Service
public class RequestImplimentation implements RequestService {
    private final RequestRepository requestRepository;

    public RequestImplimentation(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
    @Autowired
    private ProductRepository productRepository;
//    @Override
//    public String makeRequest( RequestDTO requestDTO) {
//        Request request = new Request();
//        request.setName(requestDTO.getName());
//        request.setPrice(requestDTO.getPrice());
//        request.setTime(requestDTO.getTime());
//        request.setPickUp(requestDTO.getPickUp());
//        request.setDropZOne(requestDTO.getDropZOne());
//        request.setRequestState(RequestState.PENDING);
//        requestRepository.save(request);
//        return "request sent";
//    }

    @Override
    public List<Request> getAllRequest() {
        return requestRepository.findAll();
    }

    @Override
    public String deleteRequest(@PathVariable Long id) {
        requestRepository.deleteById(id);
        return "request has been deleted";
    }

    @Override
    public String updateState(@PathVariable Long id, int state) {
        Request update = requestRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("request is not found"));
        switch (state){
            case 116:
                update.setRequestState(RequestState.APPROVED);
                break;
            case 1614:
                update.setRequestState(RequestState.PENDING);
                break;
            case 414:
                update.setRequestState(RequestState.DENIED);
                break;
        }
        requestRepository.save(update);
        return "state has been updated";
    }

    @Override
    public Request assignProductToRequest(Long productId, RequestDTO requestDTO) {
        Set<Product> productSet = null;
        Product product = productRepository.findById(productId).get();
        Request request = new Request();
        request.setName(requestDTO.getName());
        request.setPrice(requestDTO.getPrice());
        request.setTime(requestDTO.getTime());
        request.setPickUp(requestDTO.getPickUp());
        request.setDropZOne(requestDTO.getDropZOne());
        request.setRequestState(RequestState.PENDING);
        productSet = request.getAssignProducts();
        productSet.add(product);
        request.setAssignProducts(productSet);
        requestRepository.save(request);







        return requestRepository.save(request);
    }
}
