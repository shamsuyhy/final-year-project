package com.backend.app.service.impl;

import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.DeliveryAgency;
import com.backend.app.repository.DeliveryAgencyRepository;
import com.backend.app.service.DeliveryAgencyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DeliveryAgencyServiceImpl implements DeliveryAgencyService {


    DeliveryAgencyRepository deliveryAgencyRepository;

    public DeliveryAgencyServiceImpl(DeliveryAgencyRepository deliveryAgencyRepository) {
        this.deliveryAgencyRepository = deliveryAgencyRepository;
    }

    @Override
    public List<DeliveryAgency> getAllAgencies() {
        return deliveryAgencyRepository.findAll();
    }

    @Override
    public DeliveryAgency getAgencyById(Long Id) {
        Optional<DeliveryAgency> deliveryAgency= deliveryAgencyRepository.findById(Id);
        if(deliveryAgency.isPresent())return deliveryAgency.get();
        else throw  new ResourceNotFoundException("Delivery Agency","id",Id);
    }

    @Override
    public void deleteAgency(Long Id) {
        deliveryAgencyRepository.deleteById(Id);
    }

    @Override
    public DeliveryAgency addAgency(DeliveryAgency deliveryAgency) {
        return deliveryAgencyRepository.save(deliveryAgency);
    }
}
