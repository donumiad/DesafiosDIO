package com.example.eletriccars.UI

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.eletriccars.Data.CarApi
import com.example.eletriccars.Data.CarFactory
import com.example.eletriccars.R
import com.example.eletriccars.UI.adapters.CarAdapter
import com.example.eletriccars.domain.Carro
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CarFragment: Fragment() {

    private lateinit var btnCalcularmain: FloatingActionButton
    lateinit var listaCarros: RecyclerView
    lateinit var barraProgresso: ProgressBar
    lateinit var noItenetImage: ImageView
    lateinit var noInternetText: TextView
    lateinit var carsApi: CarApi
    var carrosArray : ArrayList<Carro> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_car, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupCarFragment(view)
        setupListenersmain()

    }

    override fun onResume() {
        super.onResume()
        if (checForInternet(context)){
            //callService() -> esse eh outra forma de chamar serviço
            getAllCars()
        } else{
            emptyState()
        }
    }

    fun setupRetrofit(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://igorbag.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carsApi = retrofit.create(CarApi::class.java)
    }

    fun getAllCars(){
        carsApi.getAllCars().enqueue(object: Callback<List<Carro>>{
            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if (response.isSuccessful){
                    noItenetImage.visibility = View.GONE
                    noInternetText.visibility = View.GONE
                    barraProgresso.visibility = View.GONE
                    response.body()?.let {
                        setupList(it)
                    }
                } else{
                    Toast.makeText(context, R.string.response_erro, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context, R.string.response_erro, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun emptyState(){
        barraProgresso.visibility = View.GONE
        listaCarros.visibility = View.GONE
        noItenetImage.visibility = View.VISIBLE
        noInternetText.visibility = View.VISIBLE
    }

    fun setupCarFragment(view: View){
        view.apply {
            btnCalcularmain = findViewById(R.id.btn_calcularmain)
            listaCarros = findViewById(R.id.rv_carros)
            barraProgresso = findViewById(R.id.pb_progresso)
            noItenetImage = findViewById(R.id.iv_noconection)
            noInternetText = findViewById(R.id.tv_sem_internet)
        }
    }

    fun setupList(lista: List<Carro>){
        val carroAdapter = CarAdapter(lista)
        listaCarros.apply {
            visibility = View.VISIBLE
            adapter = carroAdapter
        }
        carroAdapter.carItemLister = { carro ->
            val bateria = carro.bateria
        }


    }

    fun callService(){
        val urlBase = "http://igorbag.github.io/cars-api/cars.json"
        Mytask().execute(urlBase)

    }

    fun setupListenersmain(){
        btnCalcularmain.setOnClickListener{
            startActivity(Intent(context, CalcularAltonomiaActivity:: class.java))
        }
    }

    fun checForInternet(contexto: Context?): Boolean{
        val conectividadeManager =
            contexto?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            val network = conectividadeManager.activeNetwork ?: return false
            val activeNetwork = conectividadeManager.getNetworkCapabilities(network) ?: return false

            return when{
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else{
            val networkInfo = conectividadeManager.activeNetworkInfo?: return false
            return networkInfo.isConnected
        }
    }

    inner class Mytask: AsyncTask<String, String, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("Maytask", "Iniciando...")
            barraProgresso.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null

            try {
                val urlString = url[0]?.replace("http://", "https://") ?: throw IllegalArgumentException("URL cannot be null")
                val urlBase = URL(urlString)

                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty("Accept", "application/json")

                val responseCode = urlConnection.responseCode

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    publishProgress(response)
                    return response
                } else {
                    Log.e("Erro", "Serviço indisponível, código de resposta: $responseCode")
                    return "Erro: Serviço indisponível, código de resposta: $responseCode"
                }
            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao realizar processamento", ex)
                return "Erro: ${ex.message}"
            } finally {
                urlConnection?.disconnect()
            }
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                var jsonArray = JSONTokener(values[0]).nextValue() as JSONArray

                for (i in 0 until jsonArray.length()){
                    val id = jsonArray.getJSONObject(i).getString("id")
                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")

                    val model = Carro(
                        id = id.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto,
                        isFavorite = false
                    )

                    carrosArray.add(model)
                }
                noItenetImage.visibility = View.GONE
                noInternetText.visibility = View.GONE
                barraProgresso.visibility = View.GONE
                //setupList()

            } catch (ex: Exception){
                Log.e("Erro", ex.message.toString())

            }
        }

        /*fun streamToString(inputStream: InputStream): String{
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))

            var line: String
            var result = ""

            try {
                do{
                    line = bufferedReader.readLine()
                    line?.let {
                        result += line
                    }
                } while (line != null)
            } catch (ex: Exception){
                Log.e("Erro", "Erro ao parcelar Stream")
            }

            return result
        }*/

    }
}