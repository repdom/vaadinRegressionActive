package com.dany.dany.repositorio;

import com.dany.dany.entidades.Entradas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EntradaPaginaciónRepository extends PagingAndSortingRepository<Entradas, Long> {

    Page<Entradas> findAll(Pageable pageable);

}
