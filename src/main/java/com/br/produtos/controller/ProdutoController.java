package com.br.produtos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.produtos.models.Produto;
import com.br.produtos.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@PostMapping
	public Produto cadastrar(@RequestBody Produto produto) {
		Produto novo = new Produto();
		novo.setMarca(produto.getMarca());
		novo.setNome(produto.getNome());
		novo.setPreco(produto.getPreco());
		novo.setQtdEstoque(produto.getQtdEstoque());
		repository.save(novo);
		
		return novo;
	}
	
	@GetMapping
	public List<Produto> listar() {
		return repository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Produto não encontrado"));
		repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Produto atualizar(@RequestBody Produto produto, @PathVariable Long id) {
		Produto novo = repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Produto não encontrado"));
	
		novo.setMarca(produto.getMarca());
		novo.setNome(produto.getNome());
		novo.setPreco(produto.getPreco());
		novo.setQtdEstoque(produto.getQtdEstoque());
		repository.save(novo);
		
		return novo;
	}
	
	
}
