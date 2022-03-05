package org.generation.Farmacia.repository;

import java.util.List;

import org.generation.Farmacia.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public List<Produto> findAllByPlataformaContainingIgnoreCase(String plataforma);

}