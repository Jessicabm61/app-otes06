package model;

public class DadosAtualizacaoPaciente {
    Long id;
    String nome;
    String telefone;
    Endereco endereco;

    // Construtor para inicializar todos os campos
    public DadosAtualizacaoPaciente(Long id, String nome, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}
