package edu.quinnipiac.ser210.fuelapp2.Screen

import com.example.FuelApp.model.FuelViewModel
import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.FuelApp.api.FuelPrice
import com.example.FuelApp.model.FuelViewModel
import com.example.FuelApp.navigation.AppScreens
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun DetailsScreen(
    navController: NavController, fuelViewModel: FuelViewModel,
    cityName: String?) {
    val fuelResult = fuelViewModel.fuelResult.observeAsState()
    val fuelList = fuelResult.value?.body()
    val fuelFiltered = fuelList?.filter { fuel ->
        fuel.city == cityName
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue,
                fontSize = 24.sp)
            ) {
                append("Price Details: ")
            }
            withStyle(style = SpanStyle(color = Color.Blue,
                fontSize = 20.sp)) {
                fuelFiltered?.get(0)?.let { append(it.details) }
            }
        }, modifier = Modifier.padding(6.dp))

        HorizontalDivider(modifier = Modifier.padding(3.dp))
        Text(text = "Region: ${fuelFiltered?.get(0)?.region}", style = MaterialTheme.typography.titleLarge)
        Text(text = "Fuel Type: ${fuelFiltered?.get(0)?.fuelType}", style = MaterialTheme.typography.titleLarge)
        Text(text = "Last Updated: ${fuelFiltered?.get(0)?.lastUpdated}", style = MaterialTheme.typography.titleLarge)
    }
}