package id.co.inaportempat.postretrofitdemo.model
import com.google.gson.annotations.SerializedName


data class ResponseModel(
    @SerializedName("message")
    val message: String
)