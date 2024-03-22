package com.eVTOL.service;

import com.eVTOL.dto.RequestDTO;
import com.eVTOL.model.Request;

import java.util.List;

public interface RequestService {
//    public String makeRequest(RequestDTO requestDTO);
    public List<Request> getAllRequest();
    public String deleteRequest(Long id);
    public String updateState(Long id, int state);

    Request assignProductToRequest(Long productId, RequestDTO requestDTO);
}
