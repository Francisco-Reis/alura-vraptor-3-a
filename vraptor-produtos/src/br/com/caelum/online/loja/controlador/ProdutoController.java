package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {

	private final ProdutoDao produtos;
	private Result result;
	
	public ProdutoController(ProdutoDao produtos, Result result) {
		this.result = result;
		this.produtos = produtos;
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
		
		result.include("mensagem","Produto adicionado com sucesso");
		result.forwardTo(ProdutoController.class).lista();
	}
	
	@Path("/produto/{id}/xml")
	public void exibeComoXML(Long id){
		Produto produto = this.produtos.pegaPorId(id);
		
		result.use(Results.xml()).from(produto).serialize();
	}
}