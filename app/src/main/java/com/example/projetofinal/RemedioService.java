package com.example.projetofinal;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RemedioService {

    // GET - Listar todos os remédios
    @GET("remedios")
    Call<List<Remedio>> listarRemedios();

    // GET - Buscar remédio por ID
    @GET("remedios/{id}")
    Call<Remedio> buscarPorId(@Path("id") int id);

    // POST - Cadastrar novo remédio
    @POST("remedios")
    Call<RemedioResponse> cadastrarRemedio(@Body Remedio remedio);

    // PUT - Atualizar remédio existente
    @PUT("remedios/{id}")
    Call<RemedioResponse> atualizarRemedio(@Path("id") int id, @Body Remedio remedio);

    // DELETE - Deletar remédio
    @DELETE("remedios/{id}")
    Call<Void> deletarRemedio(@Path("id") int id);
}