package com.backend.app.repository;

import com.backend.app.model.DeliveryAgency;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAgencyRepository extends JpaRepository<DeliveryAgency,Long> {
DeliveryAgency findDeliveryAgencyByAgencyName(String agencyName);
@Transactional
void deleteDeliveryAgencyByAgencyName(String agencyName);
}
