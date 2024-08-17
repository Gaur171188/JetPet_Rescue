package hoods.com.jetpetrescue.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.R
import hoods.com.jetpetrescue.components.GenderTag
import hoods.com.jetpetrescue.data.DummyPetDataSource
import hoods.com.jetpetrescue.data.model.Owner
import hoods.com.jetpetrescue.data.model.Pet


@Composable
fun DetailsScreen(index: Int, onNavigate: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Detail") },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onNavigate()
                        }, tint = MaterialTheme.colors.onSurface
                )
            },
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onSurface
        )
    }) {
        val pat = DummyPetDataSource.dogList[index]
        LazyColumn(contentPadding = it) {
            item {
                Image(
                    painter = painterResource(id = pat.image),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.CenterStart
                )
                Spacer(modifier = Modifier.height(16.dp))
                PetBasicInfo(name =pat.name, gender = pat.gender , location = pat.location)
                MyStoryItem(pet = pat)
            }
            
            item { 
                PetInfo(pet = pat)
            }
            
            item { 
                OwnerInfo()
            }

            item {
                PetButton{

                }
            }
            
        }
    }
}

@Composable
fun PetBasicInfo(name: String, gender: String, location: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = name, color = MaterialTheme.colors.onSurface, fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Icon(
                    painterResource(id = R.drawable.ic_location), contentDescription = "",
                    modifier = Modifier.size(16.dp), tint = Color.Red
                )
                Text(
                    text = location, modifier = Modifier.padding(
                        start = 8.dp,
                        top = 0.dp, bottom = 0.dp
                    ),
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.caption
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Adaptive", color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.overline
                )
        }
        Column(modifier = Modifier.height(80.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
            GenderTag(gender = gender , modifier = Modifier)
            Text(text = "Dog",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption
                )
        }
    }
}

@Composable
fun MyStoryItem(pet: Pet){
Column() {
Spacer( modifier = Modifier.height(24.dp))
Title(title = "My Story")
 Spacer(modifier = Modifier.height(16.dp))
    Text(text = pet.description,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Start
        )
}
}

@Composable
fun Title(title:String){
    Text(text = title, modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp),
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
        )
}


@Composable
fun PetInfo(pet: Pet){
Column {
    Spacer(modifier = Modifier.height(24.dp))
    Title(title = "Pet Info")
    Spacer(modifier =Modifier.height(16.dp) )
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
        ) {
        InfoCard(primaryText = pet.age, secondaryText ="Age", modifier = Modifier.weight(1f) )
        InfoCard(primaryText = pet.color, secondaryText ="Color", modifier = Modifier.weight(1f) )
        InfoCard(primaryText = pet.breed, secondaryText = "Bread", modifier = Modifier.weight(1f))
    }
}
}

@Composable
fun InfoCard(modifier: Modifier = Modifier,
             primaryText:String,
             secondaryText:String
             ){
Surface(shape = MaterialTheme.shapes.medium, modifier = modifier) {
Column(modifier = Modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled){
        Text(text = secondaryText)
    }
    Text(text = primaryText, style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
        )
    Spacer(modifier = Modifier.size(4.dp))

}
}
}


@Composable
fun OwnerInfoCard(owner: Owner){
    Spacer(modifier = Modifier.height(16.dp))
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            
            ) {
            Image(painter = painterResource(id = owner.image), contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
                alignment = Alignment.CenterStart,
                contentScale = ContentScale.Crop
                )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = owner.name, color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Start
                    )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = owner.basicInfo,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.caption
                    )

            }
        }

        Surface(modifier = Modifier
            .size(40.dp)
            .clickable { }
            .clip(CircleShape),
            color = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
            ) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.size(20.dp)
                ){
                Icon(painter = painterResource(id = R.drawable.ic_messenger), contentDescription = "", tint = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OwnerInfo(){
    val owner =DummyPetDataSource.dogList[0].owner
    OwnerInfoCard(owner = owner)
}

@Preview(showSystemUi = true)
@Composable
fun DetailsScreenPreview() {

    DetailsScreen(0) {

    }
}

@Preview(showSystemUi = true)
@Composable
fun BasicInfo() {
    PetBasicInfo("Pat", "Male", "USA")
}

@Composable
fun PetButton(onClick:() -> Unit){
    Spacer(modifier = Modifier.height(36.dp))
    Button(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = "Adopt Me")
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Preview(showSystemUi = true)
@Composable
fun PetInfoPreview(){
    val pet = DummyPetDataSource.dogList[0]
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "Pet Info")
        Spacer(modifier =Modifier.height(16.dp) )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoCard(primaryText = pet.age, secondaryText ="Age", modifier = Modifier
                .weight(1f)
                .padding(4.dp) )
            InfoCard(primaryText = pet.color, secondaryText ="Color", modifier = Modifier
                .weight(1f)
                .padding(4.dp) )
            InfoCard(primaryText = pet.breed, secondaryText = "Bread", modifier = Modifier
                .weight(1f)
                .padding(4.dp))
        }
    }
}



