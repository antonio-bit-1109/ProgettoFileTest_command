package database.DTOs;

public class ProdottoDTO {

    private int id;
    private String nome;
    private float quantita;


    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantita(float quantita) {
        this.quantita = quantita;
    }

    public ProdottoDTO(int id, String nome, Float qta) {
        setId(id);
        setNome(nome);
        setQuantita(qta);
    }
}
