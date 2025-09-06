package com.pusbin.layanan.data_agregat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DataAgregatRepository 
    extends 
        JpaRepository<DataAgregat, Long>,
        JpaSpecificationExecutor<DataAgregat> {


}
