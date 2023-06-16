package com.backend.app.service;

import com.backend.app.model.DeliveryAgency;

import java.util.List;

public interface DeliveryAgencyService {
    List<DeliveryAgency> getAllAgencies();
    DeliveryAgency getAgencyById(Long Id);
    void deleteAgency(String agencyName);
    DeliveryAgency addAgency(DeliveryAgency deliveryAgency);
}
