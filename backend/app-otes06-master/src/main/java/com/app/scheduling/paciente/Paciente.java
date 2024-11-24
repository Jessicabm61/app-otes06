package com.app.scheduling.paciente;

import com.app.scheduling.endereco.Endereco;
import com.app.scheduling.medico.DadosAtualizacaoMedico;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pacte_nome;
    private String pacte_email;

    private String pacte_telefone;

    @Embedded
    private Endereco endereco;

    @Column(name = "pacte_ativo")
    private Boolean pacteAtivo;

    public Paciente(DadosCadastroPaciente dados) {
        this.pacteAtivo = true;
        this.pacte_nome = dados.pacte_nome();;
        this.pacte_email = dados.pacte_email();
        this.pacte_telefone = dados.pacte_telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if(dados.nome() != null){
            this.pacte_nome = dados.nome();
        }

        if(dados.telefone() != null){
            this.pacte_telefone = dados.telefone();
        }

        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.pacteAtivo = false;
    }

}
