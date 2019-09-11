package id.co.inaportempat.postretrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.co.inaportempat.postretrofitdemo.data.RetrofitService.makeService
import id.co.inaportempat.postretrofitdemo.model.RequestModel
import id.co.inaportempat.postretrofitdemo.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addStoreWater()
    }

    private fun addStoreWater() {
        val request = RequestModel(
            waterWorkOrderId = "81",
            fillingDate = "10/09/2019 14.54",
            initialMeter = "2",
            finalMeter = "4",
            operator = "643",
            waterResourceId = "1"
        )

        makeService()
            .addStoreWater(request, "Bearer IJ8nKgTjOhTOz7pA5u3N7pfycTR6iOwPxn9Awqf8nKnBTG5rG6dMODCL9S4H").enqueue(
                object : Callback<ResponseModel> {
                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        Log.e("error", t.localizedMessage!!)
                    }

                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {

                        val responseModel = response.body()

                        Log.i("message", responseModel?.message.toString())
                    }


                }
            )

    }
}
