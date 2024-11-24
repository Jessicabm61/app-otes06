package adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import appmedico.com.appotes06.AtualizarPacienteActivity;
import appmedico.com.appotes06.R;
import data.PacienteRepository;
import model.Paciente;

public class PacienteAdapter extends ArrayAdapter<Paciente> {

    private Context context;
    private List<Paciente> pacientes;
    private List<Paciente> pacientesFiltrados;  // Lista filtrada
    private PacienteRepository pacienteRepository;

    // Construtor do adapter
    public PacienteAdapter(Context context, List<Paciente> pacientes) {
        super(context, 0, pacientes);
        this.context = context;
        this.pacientes = pacientes;
        this.pacientesFiltrados = new ArrayList<>(pacientes);  // Inicializa a lista filtrada com todos os pacientes
        this.pacienteRepository = new PacienteRepository();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_paciente, parent, false);
        }

        Paciente paciente = getItem(position);

        if (paciente != null) {
            TextView textViewNomePaciente = convertView.findViewById(R.id.textViewNomePaciente);
            TextView textViewEmailPaciente = convertView.findViewById(R.id.textViewEmailPaciente);
            textViewNomePaciente.setText(paciente.getPacte_nome());
            textViewEmailPaciente.setText(paciente.getPacte_email());
        }

        ImageView imageViewOptions = convertView.findViewById(R.id.imageViewOptions);
        LinearLayout llButtons = convertView.findViewById(R.id.llButtons);
        Button buttonCancelar = convertView.findViewById(R.id.buttonCancelar);
        Button buttonEditar = convertView.findViewById(R.id.buttonEditar);

        imageViewOptions.setOnClickListener(v -> {
            llButtons.setVisibility(llButtons.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        });

        buttonCancelar.setOnClickListener(v -> {
            if (paciente != null) {
                mostrarDialogoDeConfirmacao(paciente, position);
            }
        });

        buttonEditar.setOnClickListener(v -> {
            if (paciente != null) {
                Intent intent = new Intent(context, AtualizarPacienteActivity.class);
                intent.putExtra("ID_PACIENTE", paciente.getId());
                intent.putExtra("NOME_PACIENTE", paciente.getPacte_nome());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    // Método para filtrar a lista de pacientes
    public void filtrar(String texto) {
        if (texto.isEmpty()) {
            pacientesFiltrados = new ArrayList<>(pacientes);  // Se não há texto, exibe todos os pacientes
        } else {
            List<Paciente> listaFiltrada = new ArrayList<>();
            for (Paciente paciente : pacientes) {
                if (paciente.getPacte_nome().toLowerCase().contains(texto.toLowerCase())) {
                    listaFiltrada.add(paciente);
                }
            }
            pacientesFiltrados = listaFiltrada;
        }
        notifyDataSetChanged();  // Notifica a mudança para atualizar a listView
    }

    @Override
    public int getCount() {
        return pacientesFiltrados.size();  // Retorna o número de pacientes filtrados
    }

    @Override
    public Paciente getItem(int position) {
        return pacientesFiltrados.get(position);  // Retorna o paciente filtrado
    }

    private void mostrarDialogoDeConfirmacao(Paciente paciente, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_desativar, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        Button buttonDesativar = dialogView.findViewById(R.id.buttonDesativar);
        Button buttonCancelar = dialogView.findViewById(R.id.buttonCancelar);

        AlertDialog dialog = builder.create();

        buttonDesativar.setOnClickListener(v -> {
            if (paciente != null) {
                pacienteRepository.excluirPaciente(paciente.getId(), new PacienteRepository.PacienteCallback() {
                    @Override
                    public void onSuccess(String message) {
                        pacientes.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            dialog.dismiss();
        });

        buttonCancelar.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }
}
