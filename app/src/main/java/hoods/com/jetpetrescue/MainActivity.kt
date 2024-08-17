package hoods.com.jetpetrescue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import hoods.com.jetpetrescue.detail.DetailsScreen
import hoods.com.jetpetrescue.home.Home
import hoods.com.jetpetrescue.ui.theme.JetPetTheme


enum class Screens{
    HOME,
    DETAIL
}
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedIndex by remember{ mutableStateOf(0) }
            val navController = rememberNavController()
            var changeTheme by remember {
                mutableStateOf(false)
            }
            JetPetTheme(darkTheme = changeTheme) {
             NavHost(navController = navController, startDestination = "Home") {
                composable("Home"){
                    Home(onSwitchToggle = { changeTheme = !changeTheme  }) {
                        selectedIndex = it
                        navController.navigate("Detail")
                    }
                }
                 composable("Detail"){
                     DetailsScreen(index = selectedIndex) {
                         navController.navigateUp()
                     }
                 }
             }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetPetTheme {
        Greeting("Android")
    }
}