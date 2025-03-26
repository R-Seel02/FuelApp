package edu.quinnipiac.ser210.fuelapp2.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp

import edu.quinnipiac.ser210.fuelapp2.model.FuelUiState
import edu.quinnipiac.ser210.fuelapp2.navigation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <FuelViewModel> HomeScreen(
    navController: NavController,
    fuelViewModel: FuelViewModel = viewModel()
) {
    val uiState by FuelViewModel.fuelUiState.collectAsState()

    when (uiState) {
        is FuelUiState.Loading -> CircularProgressIndicator()
        is FuelUiState.Error -> Text("Error loading fuel prices")
        is FuelUiState.Success -> {
            val fuelList = (uiState as FuelUiState.Success).fuelPrices.filterNotNull()
            Column(modifier = Modifier.padding(12.dp)) {
                LazyColumn {
                    items(fuelList) { fuel ->
                        FuelRow(fuel = fuel) { selectedFuel ->
                            navController.navigate("${AppScreens.DetailScreen.name}/$selectedFuel")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FuelRow(fuel: Int, itemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { itemClick(fuel.city) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Image( painterResource(image = "")
                    contentDescription = "Fuel Station Image"
                )
            }
            Text(
                text = fuel.city,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "Price: ${fuel.price} per liter",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "Updated: ${fuel.lastUpdated}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}
