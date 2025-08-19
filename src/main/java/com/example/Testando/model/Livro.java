package com.example.Testando.model;

import java.util.Objects;

public class Livro {
    private Long Id;
    private String Titulo;
    private String Autor;
    private Double Preco;
    private Categoria Categoria;

    public Livro(Long Id, String Titulo, String Autor, Double Preco, Categoria Categoria) {
        this.Id = Id;
        this.Titulo = Titulo;
        this.Autor = Autor;
        this.Preco = Preco;
        this.Categoria = Categoria;
    }

    public Livro(){
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public Double getPreco() {
        return Preco;
    }

    public void setPreco(Double preco) {
        Preco = preco;
    }

    public Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(Categoria categoria) {
        Categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(Id, livro.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "Id=" + Id +
                ", Titulo='" + Titulo + '\'' +
                ", Autor='" + Autor + '\'' +
                ", Preco=" + Preco +
                ", Categoria=" + Categoria +
                '}';
    }
}
