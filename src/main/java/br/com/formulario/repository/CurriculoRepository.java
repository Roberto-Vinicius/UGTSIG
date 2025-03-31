package br.com.formulario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.formulario.model.Curriculo;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {

}
