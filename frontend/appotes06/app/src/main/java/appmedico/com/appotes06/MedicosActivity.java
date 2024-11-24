package appmedico.com.appotes06;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import adapter.MedicoAdapter;
import data.MedicoRepository;
import model.Medico;

public class MedicosActivity extends AppCompatActivity {

    private ListView listViewMedicos;
    private MedicoAdapter adapter;
    private List<Medico> medicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicos);

        // Inicializando o ListView
        listViewMedicos = findViewById(R.id.listViewMedicos);

        // Inicializando o EditText de pesquisa
        AppCompatEditText editTextPesquisar = findViewById(R.id.editTextText2);

        // Carregar os médicos ao abrir a Activity
        carregarMedicos();

        // Configura button_cadastrar para navegar para activity de cadastro
        findViewById(R.id.button_cadastrar).setOnClickListener(view ->
                startActivity(new Intent(MedicosActivity.this, MedicosCreateActivity.class))
        );

        // Adiciona o TextWatcher no EditText de pesquisa
        editTextPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Verifique se o adapter foi inicializado
                if (adapter != null) {
                    // Usa o método filtrar do adapter
                    adapter.filtrar(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Ajuste da interface para lidar com barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para carregar médicos
    private void carregarMedicos() {
        MedicoRepository medicoRepository = new MedicoRepository();
        medicoRepository.listarMedicos(new MedicoRepository.MedicosCallback() {
            @Override
            public void onSucess(List<Medico> medicos) {
                // Armazena os médicos e configura o adapter
                MedicosActivity.this.medicos = medicos;
                // Inicializando o adapter com os dados
                adapter = new MedicoAdapter(MedicosActivity.this, medicos);
                listViewMedicos.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                // Exibe mensagem de erro
                Toast.makeText(MedicosActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
