package br.edu.ifpb.bdnc.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Pedido {

    private int codigo;
    private LocalDate data;
    private List<ItemPedido> itens;

    public Pedido(int codigo, LocalDate data, List<ItemPedido> itens) {
        this.codigo = codigo;
        this.data = data;
        this.itens = itens;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 35 * hash + this.codigo;
        hash = 35 * hash + Objects.hashCode(this.data);
        hash = 35 * hash + Objects.hashCode(this.itens);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.itens, other.itens)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pedido{" + "codigo=" + codigo + ", data=" + data + ", itens=" + itens + '}';
    }

}
