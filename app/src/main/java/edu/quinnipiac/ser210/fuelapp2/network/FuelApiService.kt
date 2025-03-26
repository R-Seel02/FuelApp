package edu.quinnipiac.ser210.fuelapp2.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val BASE_URL ="https://daily-petrol-diesel-lpg-cng-fuel-prices-in-india.p.rapidapi.com/v1/fuel-prices/today/india/maharashtra/mumbai"


/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    // .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface MarsApiService {
    @GET("daily-petrol-diesel-lpg-cng-fuel-prices-in-india/{stateId}/{cityId}")
    @Headers("x-rapidapi-key", "7f584a90a1mshf02cb77037613e9p17a9e2jsnf0b196a8e07e")
    suspend fun getCityFuelPriceToday(@Path("stateId")stateId: String,@Path("cityId") cityId: String):String


}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
