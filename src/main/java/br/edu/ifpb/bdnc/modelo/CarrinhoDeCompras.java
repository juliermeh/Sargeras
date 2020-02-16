package br.edu.ifpb.bdnc.modelo;

public class CarrinhoDeCompras {

    private int id;
    private int quant;
    private int produto;

    public CarrinhoDeCompras(int id, int quant, int produto) {
        this.id = id;
        this.quant = quant;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 77 * hash + this.id;
        hash = 77 * hash + this.quant;
        hash = 77 * hash + this.produto;
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
        final CarrinhoDeCompras other = (CarrinhoDeCompras) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quant != other.quant) {
            return false;
        }
        if (this.produto != other.produto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemPedido{" + "id=" + id + ", quant=" + quant + ", produto=" + produto + '}';
    }

}
