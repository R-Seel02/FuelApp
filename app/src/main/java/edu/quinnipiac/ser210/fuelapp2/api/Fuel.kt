package edu.quinnipiac.ser210.fuelapp2.api

//import com.google.firebase.firestore.auth.User
//import kotlinx.serialization.json.Json
//
//val jsonString = """{"name":"John", "age":30}"""
//val user = Json.decodeFromString<User>(jsonString)
//
//@Serializable


data class Fuel(var price: Double, var city: String, var lastUpdated: String, var name: String)