package com.swaggerDocumentation.SwaggerDocumentation.repositories;

import com.swaggerDocumentation.SwaggerDocumentation.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
