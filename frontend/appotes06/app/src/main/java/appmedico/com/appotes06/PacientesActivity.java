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

import adapter.PacienteAdapter;
import data.PacienteRepository;
import model.Paciente;

public class PacientesActivity extends AppCompatActivity {

    private ListView listViewPacientes;
    private PacienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pacientes);

        listViewPacientes = findViewById(R.id.listViewPacientes);
        AppCompatEditText editTextPesquisar = findViewById(R.id.editTextText2);  // EditText de pesquisa

        // Carregar os pacientes ao abrir a Activity
        carregarPacientes();

        // Configura o botão de cadastro para navegar para a activity de cadastro
        findViewById(R.id.button_cadastrar).setOnClickListener(view ->
                startActivity(new Intent(PacientesActivity.this, PacientesCreateActivity.class))
        );

        // Adiciona o TextWatcher para o campo de pesquisa
        editTextPesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Chama o método de filtro sempre que o texto for alterado
                if (adapter != null) {
                    adapter.filtrar(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Ajuste da interface para lidar com barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para carregar pacientes
    private void carregarPacientes() {
        PacienteRepository pacienteRepository = new PacienteRepository();
        pacienteRepository.listarPacientes(new PacienteRepository.PacientesCallback() {
            @Override
            public void onSuccess(List<Paciente> pacientes) {
                adapter = new PacienteAdapter(PacientesActivity.this, pacientes);
                listViewPacientes.setAdapter(adapter);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(PacientesActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
