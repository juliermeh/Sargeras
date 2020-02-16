package br.edu.ifpb.bdnc.main;

import br.edu.ifpb.bdnc.dao.PedidoDAO;
import br.edu.ifpb.bdnc.modelo.ItemPedido;
import br.edu.ifpb.bdnc.modelo.Pedido;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Iniciar {

    public static void main(String [] args) {

        ItemPedido item = new ItemPedido(3, 5, 1);
        ItemPedido item2 = new ItemPedido(4, 4, 2);
        List<ItemPedido> lista = new ArrayList<>();
        lista.add(item);
        lista.add(item2);
        Pedido p = new Pedido(2, LocalDate.now(), lista);

        try {
            PedidoDAO dao = new PedidoDAO();
            dao.salvar(p);
            System.out.println("Pedido: " + dao.buscar(2).toString());
        } catch (
                SQLException ex) {
            System.out.println("Error 404");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iniciar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
