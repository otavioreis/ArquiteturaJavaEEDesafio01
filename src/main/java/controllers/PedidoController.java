package controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import domain.Pedido;
import domain.Produto;
import mail.EmailConfig;
import services.PedidoServiceImpl;
import services.ProdutoServiceImpl;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoController {
	private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);
	private PedidoServiceImpl pedidoService;
	private ProdutoServiceImpl produtoService;
	private EmailConfig emailConfig;

	public PedidoController() {
		pedidoService = PedidoServiceImpl.getInstance();
		produtoService = ProdutoServiceImpl.getInstance();
		emailConfig = new EmailConfig();
	}

	@GET
	public List<Pedido> getAll() {
		logger.info("getAllPedidos: {}");
		List<Pedido> pedidos = pedidoService.getAll();
		return pedidos;
	}

	@GET
	@Path("{id : \\d+}")
	public Response getById(@PathParam("id") Integer id) {
		logger.info("getById : {}", id);
		Pedido pedido = pedidoService.findById(id);
		if (pedido == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(pedido).build();
	}

	@POST
	public Response create(Pedido item, @Context UriInfo uriInfo) {
		Produto produto = produtoService.findById(item.getIdProduto());

		if (produto == null)
			return Response.status(Status.NOT_FOUND).build();

		logger.info("create: {}", item);
		Pedido pedidoSaved = pedidoService.save(item);
		logger.debug("Pedido criado com o id = ", pedidoSaved.getId());
		
		this.sendMail(pedidoSaved, produto);
		
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(pedidoSaved.getId().toString());
		return Response.created(builder.build()).entity(pedidoSaved).build();
	}

	private void sendMail(Pedido pedido, Produto produto) {

		final String fromEmail = "email@gmail.com";
		final String password = "*****";
		final String toEmail = "emailenviar@gmail.com";

		StringBuilder sb = new StringBuilder();
		sb.append("Olá " + pedido.getNomeCliente() + "Recebemos o seu pedido!");
		sb.append("");
		sb.append("---------------------------------------------");
		sb.append("Número pedido:"+pedido.getId());
		sb.append("Produto: " + produto.getNome());
		sb.append("Descrição: " + produto.getDescricao());
		sb.append("Valor: " + produto.getValor());
		sb.append("---------------------------------------------");
		sb.append("");
		sb.append("O seu pedido será entregue no endereço informado a seguir:");
		sb.append(pedido.getEnderecoCliente());
		
		emailConfig.sendEmail(fromEmail, password, toEmail, "Recebemos o seu pedido", sb.toString());
	}
}
