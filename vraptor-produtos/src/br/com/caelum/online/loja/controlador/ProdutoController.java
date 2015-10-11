package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {

	private final ProdutoDao produtos;
	private Result result;
	private Validator validator;
	
	public ProdutoController(ProdutoDao produtos, Result result, Validator validator) {
		this.result = result;
		this.produtos = produtos;
		this.validator = validator;
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
	public void adiciona(final Produto produto){
		validator.checking(new Validations(){{
	        that(produto.getPreco() > 0.1,"preco", "produto.preco.invalido");
	        that(produto.getDescricao() != null && produto.getDescricao().length() > 0, "descricao",  "produto.descricao.invalido");
            that(produto.getNome() != null && produto.getNome().length() >= 3 && produto.getNome().length() <= 100, "nome",  "produto.nome.invalido");
	       }});
		validator.onErrorUsePageOf(ProdutoController.class).formulario();
		
		this.produtos.salva(produto);
		
		result.include("mensagem","Produto adicionado com sucesso");
		result.forwardTo(ProdutoController.class).lista();
	}
	
	@Path("/produto/{id}/xml")
	public void exibeComoXML(Long id){
		Produto produto = this.produtos.pegaPorId(id);
		
		result.use(Results.xml()).from(produto).serialize();
	}
	
	
	public void remove(Produto produto) {
	    this.produtos.remove(produto);
	    result.nothing();
	}
	
}