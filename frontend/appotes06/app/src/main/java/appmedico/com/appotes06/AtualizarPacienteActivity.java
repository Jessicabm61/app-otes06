package appmedico.com.appotes06;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import data.PacienteRepository;
import model.DadosAtualizacaoPaciente;
import model.Endereco;

public class AtualizarPacienteActivity extends AppCompatActivity {

    private Long idPaciente;
    private String nomePaciente;

    private PacienteRepository pacienteRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_atualizar_paciente);

        // Inicializar o repositório
        pacienteRepository = new PacienteRepository();

        // Recuperando os dados passados pelo Intent
        idPaciente = getIntent().getLongExtra("ID_PACIENTE", -1);
        nomePaciente = getIntent().getStringExtra("NOME_PACIENTE");

        // Preencher os campos de formulário
        EditText nomeEditText = findViewById(R.id.nameView);
        EditText telefoneEditText = findViewById(R.id.telefoneView);

        // Preenchendo os dados nos campos
        nomeEditText.setText(nomePaciente);

        // Botão de salvar
        AppCompatButton btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(v -> {
            // Coletar os dados dos campos de entrada, que podem ser alterados pelo usuário

            // Dados principais
            String nome = nomeEditText.getText().toString();
            String telefone = telefoneEditText.getText().toString();

            // Dados adicionais (preenchidos pelo usuário)
            String logradouro = ((EditText) findViewById(R.id.logradouroView)).getText().toString();
            String numero = ((EditText) findViewById(R.id.numeroView)).getText().toString();
            String complemento = ((EditText) findViewById(R.id.complementoView)).getText().toString();
            String bairro = ((EditText) findViewById(R.id.bairroView)).getText().toString();
            String cidade = ((EditText) findViewById(R.id.cidadeView)).getText().toString();
            String uf = ((EditText) findViewById(R.id.ufView)).getText().toString();
            String cep = ((EditText) findViewById(R.id.cepView)).getText().toString();
            Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cidade, uf, cep);


            // Criar o objeto DadosAtualizacaoMedico com os dados preenchidos
            DadosAtualizacaoPaciente dados = new DadosAtualizacaoPaciente(idPaciente,
                    nome, telefone, endereco);

            // Chamar o repositório para atualizar os dados do médico
            atualizarPaciente(dados);
        });


        //Botão cancelar
        AppCompatButton btnCancelar = findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para atualizar os dados do paciente
    private void atualizarPaciente(DadosAtualizacaoPaciente dados) {
        // Chamar o repositório para atualizar o médico
        pacienteRepository.atualizarPaciente(idPaciente, dados, new PacienteRepository.PacienteCallback() {
            @Override
            public void onSuccess(String message) {
                // Exibir mensagem de sucesso
                Toast.makeText(AtualizarPacienteActivity.this, message, Toast.LENGTH_SHORT).show();
                // Finalizar a atividade
                finish();
            }

            @Override
            public void onError(String error) {
                // Exibir mensagem de erro
                Toast.makeText(AtualizarPacienteActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}