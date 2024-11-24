package api;

import java.util.List;

import model.Agenda;
import model.AgendaResponse;
import model.DadosAtualizacaoMedico;
import model.DadosAtualizacaoPaciente;
import model.Medico;
import model.MedicoResponse;
import model.Paciente;
import model.PacienteResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("medicos")
    Call<Void> criarMedico(@Body Medico medico);

    @GET("medicos")
    Call<MedicoResponse> listarMedicos();

    @DELETE("medicos/{id}")
    Call<Void> deleteMedico(@Path("id") Long id);

    @PUT("medicos/{id}")
    Call<Void> atualizarMedico(@Path("id") Long id, @Body DadosAtualizacaoMedico dados);

    @POST("pacientes")
    Call<Void> criarPaciente(@Body Paciente paciente);

    @GET("pacientes")
    Call<PacienteResponse> listarPacientes();

    @DELETE("pacientes/{id}")
    Call<Void> deletePaciente(@Path("id") Long id);

    @PUT("pacientes/{id}")
    Call<Void> atualizarPaciente(@Path("id") Long id, @Body DadosAtualizacaoPaciente dados);


    @POST("agendar")
    Call<Void> agendarConsulta(@Body Agenda agenda);

    @GET("agendar")
    Call<AgendaResponse> listarAgenda();

    @DELETE("agendar/{id}")
    Call<Void> deleteAgenda(@Path("id") Long id);


}
