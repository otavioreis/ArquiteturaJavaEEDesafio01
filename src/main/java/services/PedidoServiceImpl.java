package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import domain.Pedido;

public class PedidoServiceImpl implements PedidoService {
	private static final PedidoServiceImpl singleton = new PedidoServiceImpl();
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private Integer actualId = 1;
	
	public static PedidoServiceImpl getInstance() {
		return singleton;
	}

	public List<Pedido> getAll() {
		return pedidos;
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedidosOptional = this.pedidos.stream().filter(pedidos -> pedidos.getId().equals(id)).findFirst();

		return pedidosOptional.orElse(null);
	}

	public Pedido save(Pedido item) {
		if (item.getId() != null) {
			this.deleteById(item.getId());
		} else {
			actualId++;
			item.setId(actualId);
		}
		this.pedidos.add(item);
		return item;
	}

	public void deleteById(Integer id) {
		this.pedidos.removeIf(pedido -> pedido.getId().equals(id));

	}

}
