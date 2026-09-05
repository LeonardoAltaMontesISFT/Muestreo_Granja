package com.blacmircrosystems.Peso_granja.repository;

import com.blacmircrosystems.Peso_granja.entity.WeigthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WeigthRecordRepository extends JpaRepository<WeigthRecord, Long> {
    //Metodo para obtener los pesos ordeneados
    List<WeigthRecord> findBySamplingIdOrderByBirdNumberAsc(Long samplingId);
    int countBySamplingId(Long samplingId);
    List<WeigthRecord> findBySamplingId(Long id);

}
