package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Produto;

public class ProdutoServiceImpl implements ProdutoService {
	
	private static final ProdutoServiceImpl singleton = new ProdutoServiceImpl();
	private List<Produto> produtos = new ArrayList<Produto>();
	private Integer actualId = 1;
	
	public static ProdutoServiceImpl getInstance() {
		return singleton;
	}
	
	public ProdutoServiceImpl() {
		produtos = popularProdutos();
	}

	
	private List<Produto> popularProdutos() {
		List<Produto> retorno = new ArrayList<Produto>();
		
		retorno.add(new Produto(actualId++, "Galaxy S5", "Smartphone Galaxy S5", 500.00));
		retorno.add(new Produto(actualId++,"Galaxy S6", "Smartphone Galaxy S6", 1000.00));
		retorno.add(new Produto(actualId++, "Galaxy S7", "Smartphone Galaxy S7", 1500.00));
		retorno.add(new Produto(actualId++, "Galaxy S8", "Smartphone Galaxy S8", 2500.00));
		retorno.add(new Produto(actualId++, "Iphone 8", "Smartphone Iphone 8", 2600.00));
		retorno.add(new Produto(actualId++, "Iphone X", "Smartphone Iphone X", 5500.00));
		
		return retorno;
	}

	public List<Produto> getAll() {
		return this.produtos;
	}

	public Produto findById(Integer id) {
		Optional<Produto> produtosOptional = produtos.stream().filter(produtos -> produtos.getId().equals(id)).findFirst();

		return produtosOptional.orElse(null);
	}

	public Produto save(Produto item) {
		if (item.getId() != null) {
			this.deleteById(item.getId());
		} else {
			actualId++;
			item.setId(actualId);
		}
		this.produtos.add(item);
		return item;
	}

	public void deleteById(Integer id) {
		this.produtos.removeIf(produtos -> produtos.getId().equals(id));
	}

}
