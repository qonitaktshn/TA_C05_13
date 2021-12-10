package apap.tugasakhir.sibusiness.repository;

import apap.tugasakhir.sibusiness.model.CabangModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CabangDB extends JpaRepository<CabangModel, Long> {
    List<CabangModel> findAll();
}