package br.edu.ifpb.bdnc.dao;

import br.edu.ifpb.bdnc.conexao.PostgreSQL;
import br.edu.ifpb.bdnc.conexao.Redis;
import br.edu.ifpb.bdnc.modelo.CarrinhoDeCompras;
import br.edu.ifpb.bdnc.modelo.Pedido;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private PostgreSQL sql;
    private Connection connection;
    private Jedis jedis;
    private Redis redis;
    private Gson gson;

    public PedidoDAO() throws SQLException, ClassNotFoundException {
        sql = new PostgreSQL();
        connection = sql.getConnection();
        redis = new Redis();
        gson = new Gson();
    }

    public boolean salvar(Pedido p) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Pedido "
                + "(codigo,data) values (?,?)");
        stmt.setInt(1, p.getCodigo());
        stmt.setDate(2, Date.valueOf(p.getData()));
        stmt.execute();
        PreparedStatement stmt3 = connection.prepareStatement("INSERT INTO Carrinho"
                + " (id,quant,produto) values (?,?,?)");

        PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO listapedidos"
                + " (codigopedido,idcarrinho) values (?,?)");
        for (int k = 0; k < p.getItens().size(); k++) {

            stmt3.setInt(1, p.getItens().get(k).getId());
            stmt3.setInt(2, p.getItens().get(k).getQuant());
            stmt3.setInt(3, p.getItens().get(k).getProduto());
            stmt3.execute();

            stmt2.setInt(1, p.getCodigo());
            stmt2.setInt(2, p.getItens().get(k).getId());
            stmt2.execute();
        }

        String json = gson.toJson(p);
        jedis = redis.conectar();
        jedis.setex("" + p.getCodigo(), 200, json);
        return true;
    }

    public Pedido buscar(int codigo) throws SQLException {

        if (jedis.get("" + codigo) == null) {
            System.out.println("O pedido não se encontra no Redis. Procurando no PostgreSQL...");

            PreparedStatement stmt = connection.prepareStatement("SELECT * from pedido where codigo= ? ");
            stmt.setInt(1, codigo);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Instant instant = Instant.ofEpochMilli(result.getDate("data").getTime());
                LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

                PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM listapedidos"
                        + " where codigopedido = ?");
                stmt2.setInt(1, codigo);
                ResultSet listapedidos = stmt2.executeQuery();
                List<CarrinhoDeCompras> lista = new ArrayList<>();
                while (listapedidos.next()) {

                    PreparedStatement stmt3 = connection.prepareStatement("SELECT * "
                            + "FROM Carrinho WHERE id= ?");
                    stmt3.setInt(1, listapedidos.getInt("produto"));
                    ResultSet itempedido = stmt3.executeQuery();
                    if (itempedido.next()) {
                        CarrinhoDeCompras item = new CarrinhoDeCompras(itempedido.getInt("id"),
                                itempedido.getInt("quant"), itempedido.getInt("codigoproduto"));
                        lista.add(item);
                    }
                }
                Pedido p = new Pedido(codigo, localDate, lista);
                return p;
            }
        } else {
            System.out.println("O pedido encontrado!");
            String resultado = jedis.get("" + codigo);
            System.out.println("JSON: " + resultado);
            Pedido p = gson.fromJson(resultado, Pedido.class);
            return p;
        }
        return null;
    }

}
