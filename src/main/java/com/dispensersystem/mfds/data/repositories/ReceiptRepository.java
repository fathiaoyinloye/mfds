package com.dispensersystem.mfds.data.repositories;

import com.dispensersystem.mfds.data.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
