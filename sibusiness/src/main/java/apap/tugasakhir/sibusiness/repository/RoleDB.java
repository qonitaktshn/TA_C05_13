package apap.tugasakhir.sibusiness.repository;

import apap.tugasakhir.sibusiness.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDB extends JpaRepository<RoleModel, Long> {

}
