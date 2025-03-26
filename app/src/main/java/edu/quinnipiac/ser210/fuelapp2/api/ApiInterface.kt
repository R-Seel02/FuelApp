package edu.quinnipiac.ser210.fuelapp2.api



import androidx.tracing.perfetto.handshake.protocol.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory


interface ApiInterface {

    @GET("(\"CityFuelPriceToday")
    suspend fun CityFuelPriceToday(): String


    companion object {

        var BASE_URL = "\"https://daily-petrol-diesel-lpg-cng-fuel-prices-in-india.p.rapidapi.com/v1/fuel-prices/today/india/maharashtra/mumbai\""


        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}}