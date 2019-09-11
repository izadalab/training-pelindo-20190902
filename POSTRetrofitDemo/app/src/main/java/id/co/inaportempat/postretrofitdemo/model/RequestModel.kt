package id.co.inaportempat.postretrofitdemo.model
import com.google.gson.annotations.SerializedName

data class RequestModel(
    @SerializedName("filling_date")
    val fillingDate: String,
    @SerializedName("final_meter")
    val finalMeter: String,
    @SerializedName("initial_meter")
    val initialMeter: String,
    @SerializedName("operator")
    val `operator`: String,
    @SerializedName("water_resource_id")
    val waterResourceId: String,
    @SerializedName("water_work_order_id")
    val waterWorkOrderId: String
)