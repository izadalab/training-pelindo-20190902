package id.co.inaportempat.postretrofitdemo.data

import id.co.inaportempat.postretrofitdemo.model.RequestModel
import id.co.inaportempat.postretrofitdemo.model.ResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiEndpoint {

    @POST("api/storewatereealization")
    fun addStoreWater(
        @Body request: RequestModel,
        @Header("Authorization") auth : String) : Call<ResponseModel>
}