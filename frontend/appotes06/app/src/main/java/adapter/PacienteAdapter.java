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

import java.util.List;

import appmedico.com.appotes06.AtualizarPacienteActivity;
import appmedico.com.appotes06.R;
import data.PacienteRepository;
import model.Paciente;  // Modelo Paciente

public class PacienteAdapter extends ArrayAdapter<Paciente> {

    private Context context;
    private List<Paciente> pacientes;
    private PacienteRepository pacienteRepository; // Repositório para excluir paciente

    // Construtor do adapter
    public PacienteAdapter(Context context, List<Paciente> pacientes) {
        super(context, 0, pacientes);
        this.context = context;
        this.pacientes = pacientes;
        this.pacienteRepository = new PacienteRepository();
    }

    // Método que infla o layout e popula os dados
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Verifica se a view já foi inflada, se não, infla a view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_paciente, parent, false);
        }

        // Obtém o paciente na posição desejada
        Paciente paciente = getItem(position);

        // Verifica se o paciente não é nulo
        if (paciente != null) {
            // Referência dos TextViews
            TextView textViewNomePaciente = convertView.findViewById(R.id.textViewNomePaciente);
            TextView textViewEmailPaciente = convertView.findViewById(R.id.textViewEmailPaciente);

            // Preenche os campos com os dados do paciente
            textViewNomePaciente.setText(paciente.getPacte_nome());
            textViewEmailPaciente.setText(paciente.getPacte_email());
        }

        // Ícone de opções (que pode ser editado ou cancelado)
        ImageView imageViewOptions = convertView.findViewById(R.id.imageViewOptions);

        // Botões Editar e Cancelar
        LinearLayout llButtons = convertView.findViewById(R.id.llButtons);
        Button buttonCancelar = convertView.findViewById(R.id.buttonCancelar);
        Button buttonEditar = convertView.findViewById(R.id.buttonEditar);

        // Exibe ou oculta os botões Editar e Cancelar
        imageViewOptions.setOnClickListener(v -> {
            if (llButtons.getVisibility() == View.GONE) {
                llButtons.setVisibility(View.VISIBLE);
            } else {
                llButtons.setVisibility(View.GONE);
            }
        });

        buttonCancelar.setOnClickListener(v -> {
            if (paciente != null) {
                mostrarDialogoDeConfirmacao(paciente, position);
            }
        });

        //Botão Editar envia os dados para a Activity de edição
        buttonEditar.setOnClickListener(v -> {
            if (paciente != null) {
                // Crie o Intent para navegar para a AtualizarPacienteActivity
                Intent intent = new Intent(context, AtualizarPacienteActivity.class);

                // Passando todos os dados do médico via Intent
                intent.putExtra("ID_PACIENTE", paciente.getId());
                intent.putExtra("NOME_PACIENTE", paciente.getPacte_nome());

                // Inicie a Activity para editar o médico
                context.startActivity(intent);
            }
        });

        // Retorna a view preenchida com os dados
        return convertView;
    }

    // Método para mostrar o diálogo de confirmação
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
                        notifyDataSetChanged(); // Atualiza a lista
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            dialog.dismiss();  // Fecha o diálogo após a exclusão
        });

        buttonCancelar.setOnClickListener(v -> {
            dialog.dismiss();  // Fecha o diálogo
        });

        dialog.show();
    }
}
