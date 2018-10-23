package pl.piotrjaniszewski.crudrest.domain;


import java.util.List;

public class ProductList {
    List<Product> list;

    public ProductList(List<Product> list) {
        this.list = list;
    }

    public ProductList() {
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
