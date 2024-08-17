package hoods.com.jetpetrescue.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import hoods.com.jetpetrescue.components.PetInfoItem
import hoods.com.jetpetrescue.components.TopBar
import hoods.com.jetpetrescue.data.DummyPetDataSource

@Composable
fun Home(onSwitchToggle:()->Unit,onPetClick:(Int) -> Unit) {
    val petList = DummyPetDataSource.dogList
    Scaffold(topBar = {
        TopBar(onSwitchToggle = onSwitchToggle)
    }) {paddingValue->
LazyColumn(contentPadding = paddingValue) {
    itemsIndexed(petList){index,pet->
        PetInfoItem(pet = pet){
            onPetClick(index)
        }
    }
}
    }
}