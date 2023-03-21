package pl.kamilszopa.weatherdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kamilszopa.weatherdemo.domain.RequestDetails;

@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetails, Long> {
}
