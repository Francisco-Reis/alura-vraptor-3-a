package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;

@Resource
public class ProdutoController {

	private final ProdutoDao produtos;
	
	public ProdutoController() {
		this.produtos = new ProdutoDao();
	}

	public List<Produto> lista() {
		return this.produtos.pegaTodos();
	}
	
	@Path("/produto/{id}")
	public Produto exibe(Long id){
		return this.produtos.pegaPorId(id);
	}
	
	public void formulario(){	
	}
	
	@Post
	public void adiciona(Produto produto){
		this.produtos.salva(produto);
	}
}