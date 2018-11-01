package controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Produto;
import services.ProdutoServiceImpl;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	private ProdutoServiceImpl produtoService;

	public ProdutoController() {
		produtoService = ProdutoServiceImpl.getInstance();
	}

	@GET
	public List<Produto> getAll() {
		logger.info("getAllProdutos: {}");
		List<Produto> produtos = produtoService.getAll();
		return produtos;
	}

	@GET
	@Path("{id : \\d+}")
	public Response getById(@PathParam("id") Integer id) {
		logger.info("getById : {}", id);
		Produto produto = produtoService.findById(id);
		if (produto == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(produto).build();
	}

	@POST
	public Response create(Produto item, @Context UriInfo uriInfo) {
		logger.info("create: {}", item);
		Produto produtoSaved = produtoService.save(item);
		logger.debug("Produto criado com o id = ", produtoSaved.getId());
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(produtoSaved.getId().toString());
		return Response.created(builder.build()).entity(produtoSaved).build();
	}

	@PUT
	@Path("/{id : \\d+}")
	public Response update(@PathParam("id") Integer id, Produto produto) {
		logger.info("Produto ID: {} ", id, produto);
		Produto produtoSaved = produtoService.findById(id);
		if (produtoSaved == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		produtoSaved = produtoService.save(produto);
		return Response.ok().entity(produtoSaved).build();
	}

	@DELETE
	@Path("/{id : \\d+}")
	public Response delete(@PathParam("id") Integer id) {
		logger.info("Produto ID: {} ", id);
		produtoService.deleteById(id);
		return Response.noContent().build();
	}
}
