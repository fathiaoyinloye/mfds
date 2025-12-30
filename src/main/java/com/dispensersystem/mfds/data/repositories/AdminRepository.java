package com.dispensersystem.mfds.data.repositories;

import com.dispensersystem.mfds.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository <Admin,Long> {
    Admin findAdminByUsername(String username);
}
