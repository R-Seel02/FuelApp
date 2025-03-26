package edu.quinnipiac.ser210.fuelapp2.navigation

import java.lang.IllegalArgumentException



enum class AppScreens {
    HomeScreen,
    DetailScreen;
    companion object {
        fun fromRoute (route: String?): AppScreens
                = when(route?.substringBefore("/"))
        {
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }

    }

}
