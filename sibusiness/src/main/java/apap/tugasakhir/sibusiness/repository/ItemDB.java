package apap.tugasakhir.sibusiness.repository;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemDB extends JpaRepository<ItemFactoryModel, String> {
    // ItemFactoryModel findByUsername(String username);
    List<ItemFactoryModel> findAll();
}
