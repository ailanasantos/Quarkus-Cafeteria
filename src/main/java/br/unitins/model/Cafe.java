package br.unitins.model;

import jakarta.persistence.Entity;

@Entity
public class Cafe extends DefaultEntity{

    private String sabor;
    private String quantidade;
    private String temperatura;

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }



}
