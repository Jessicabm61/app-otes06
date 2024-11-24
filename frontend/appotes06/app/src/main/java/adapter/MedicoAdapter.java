package adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import appmedico.com.appotes06.AtualizarMedicoActivity;
import data.MedicoRepository;
import model.Medico;
import appmedico.com.appotes06.R;

public class MedicoAdapter extends ArrayAdapter<Medico> {

    private Context context;
    private List<Medico> medicos;
    private MedicoRepository medicoRepository; // Repositório para excluir médico

    public MedicoAdapter(Context context, List<Medico> medicos) {
        super(context, 0, medicos);
        this.context = context;
        this.medicos = medicos;
        this.medicoRepository = new MedicoRepository();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_medico, parent, false);
        }

        Medico medico = getItem(position);

        TextView textViewNomeMedico = convertView.findViewById(R.id.textViewNomeMedico);
        TextView textViewEspecialidade = convertView.findViewById(R.id.textViewEspecialidade);
        TextView textViewEmail = convertView.findViewById(R.id.textViewEmail);
        TextView textViewCRM = convertView.findViewById(R.id.textViewCRM);

        if (medico != null) {
            textViewNomeMedico.setText(medico.getNome());
            textViewEspecialidade.setText(medico.getEspecialidade());
            textViewEmail.setText(medico.getEmail());
            textViewCRM.setText(medico.getCrm());
        }

        ImageView imageViewOptions = convertView.findViewById(R.id.imageViewOptions);
        LinearLayout llButtons = convertView.findViewById(R.id.llButtons);
        Button buttonCancelar = convertView.findViewById(R.id.buttonCancelar);
        Button buttonEditar = convertView.findViewById(R.id.buttonEditar);

        //Configura a visibilidade dos botões
        imageViewOptions.setOnClickListener(v -> {
            if (llButtons.getVisibility() == View.GONE) {
                llButtons.setVisibility(View.VISIBLE);
            } else {
                llButtons.setVisibility(View.GONE);
            }
        });

        buttonCancelar.setOnClickListener(v -> {
            if (medico != null) {
                mostrarDialogoDeConfirmacao(medico, position);
            }
        });

        //Botão Editar envia os dados para a Activity de edição
        buttonEditar.setOnClickListener(v -> {
            if (medico != null) {
                // Crie o Intent para navegar para a AtualizarMedicoActivity
                Intent intent = new Intent(context, AtualizarMedicoActivity.class);

                // Passando todos os dados do médico via Intent
                intent.putExtra("ID_MEDICO", medico.getId());
                intent.putExtra("NOME_MEDICO", medico.getNome());

                // Inicie a Activity para editar o médico
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    // Método para mostrar o diálogo de confirmação
    private void mostrarDialogoDeConfirmacao(Medico medico, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_desativar, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);

        Button buttonDesativar = dialogView.findViewById(R.id.buttonDesativar);
        Button buttonCancelar = dialogView.findViewById(R.id.buttonCancelar);

        AlertDialog dialog = builder.create();

        buttonDesativar.setOnClickListener(v -> {
            if (medico != null) {
                medicoRepository.excluirMedico(medico.getId(), new MedicoRepository.MedicoCallback() {
                    @Override
                    public void onSuccess(String message) {
                        medicos.remove(position);
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