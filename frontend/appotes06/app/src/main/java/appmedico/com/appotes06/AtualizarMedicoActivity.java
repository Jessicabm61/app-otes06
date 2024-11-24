package appmedico.com.appotes06;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import data.MedicoRepository;
import model.DadosAtualizacaoMedico;
import model.Endereco;

public class AtualizarMedicoActivity extends AppCompatActivity {

    private Long idMedico;
    private String nomeMedico;

    private MedicoRepository medicoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_atualizar_medico);

        // Inicializar o repositório
        medicoRepository = new MedicoRepository();

        // Recuperando os dados passados pelo Intent
        idMedico = getIntent().getLongExtra("ID_MEDICO", -1);
        nomeMedico = getIntent().getStringExtra("NOME_MEDICO");

        // Preencher os campos de formulário
        EditText nomeEditText = findViewById(R.id.nameView);
        EditText telefoneEditText = findViewById(R.id.telefoneView);

        // Preenchendo os dados nos campos
        nomeEditText.setText(nomeMedico);

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
            DadosAtualizacaoMedico dados = new DadosAtualizacaoMedico(idMedico,
                    nome, telefone, endereco);

            // Chamar o repositório para atualizar os dados do médico
            atualizarMedico(dados);
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

    // Método para atualizar os dados do médico
    private void atualizarMedico(DadosAtualizacaoMedico dados) {
        // Chamar o repositório para atualizar o médico
        medicoRepository.atualizarMedico(idMedico, dados, new MedicoRepository.MedicoCallback() {
            @Override
            public void onSuccess(String message) {
                // Exibir mensagem de sucesso
                Toast.makeText(AtualizarMedicoActivity.this, message, Toast.LENGTH_SHORT).show();
                // Finalizar a atividade
                finish();
            }

            @Override
            public void onError(String error) {
                // Exibir mensagem de erro
                Toast.makeText(AtualizarMedicoActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}