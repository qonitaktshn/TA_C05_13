package apap.tugasakhir.sibusiness.repository;

import apap.tugasakhir.sibusiness.model.ItemFactoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemFactoryDB extends JpaRepository<ItemFactoryModel, Long> {
    @Query("select i from ItemFactoryModel i where i.nama LIKE %:nama%")
    List<ItemFactoryModel> findByNama(@Param("nama") String nama);
}
