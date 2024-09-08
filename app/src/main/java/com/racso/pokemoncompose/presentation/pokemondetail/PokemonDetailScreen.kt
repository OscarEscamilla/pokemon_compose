package com.racso.pokemoncompose.presentation.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.racso.pokemoncompose.domain.models.Pokemon
import com.racso.pokemoncompose.ui.theme.BlueStat
import com.racso.pokemoncompose.ui.theme.GreenStat
import com.racso.pokemoncompose.ui.theme.Red40
import com.racso.pokemoncompose.ui.theme.RedStat
import com.racso.pokemoncompose.ui.theme.YellowStat
import com.racso.pokemoncompose.ui.theme.RedPink40


@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    pokemonId: Int,
    onBackClick: () -> Unit) {

    val pokemon by viewModel.pokemon.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val likedStatus by viewModel.favoriteStatus.collectAsState()

    LaunchedEffect(pokemonId) {
        viewModel.getPokemon(pokemonId)
    }

    if (isLoading) {
        CircularProgressIndicator(
            color = RedPink40,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    } else if (pokemon != null) {
        PokemonDetailContent(
            pokemon = pokemon!!,
            onBackClick = onBackClick,
            onFavoriteClick = { viewModel.saveFavoritePokemon(pokemon!!) },
            likedStatus = likedStatus)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Pokémon not found.")
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailContent(
    pokemon: Pokemon,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    likedStatus: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { onFavoriteClick() }) {
                        Icon(
                            imageVector = if (likedStatus) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = Red40)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = pokemon.name.capitalize(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                Card(colors = CardDefaults.cardColors(
                    containerColor = RedPink40,
                ),) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Weight: ${pokemon.weight} kg",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.width(10.dp))
                Card(colors = CardDefaults.cardColors(
                    containerColor = RedPink40,
                ),) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Height: ${pokemon.height} m",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

            }
            PokemonStats(pokemon)
        }
    }
}

@Composable
fun PokemonStats(pokemon: Pokemon) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            StatRow(statName = "HP", statValue = pokemon.hp, maxValue = 100)
            StatRow(statName = "Attack", statValue = pokemon.attack, maxValue = 100)
            StatRow(statName = "Defense", statValue = pokemon.defense, maxValue = 100)
            StatRow(statName = "Special Attack", statValue = pokemon.specialAtack, maxValue = 100)
        }
    }

@Composable
fun StatRow(statName: String, statValue: Int, maxValue: Int) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "$statName: $statValue", style = MaterialTheme.typography.bodyLarge)

        val progress = statValue / maxValue.toFloat()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .padding(vertical = 4.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Gray.copy(alpha = 0.2f))
        ) {
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)), // Match the border radius of the Box
                color = when (statName) {
                    "HP" -> RedStat
                    "Attack" -> GreenStat
                    "Defense" -> BlueStat
                    "Special Attack" -> YellowStat
                    else -> Color.Gray
                },
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PokemonDetailScreenPreview() {
    val pokemon = Pokemon(
        id = 25,
        name = "Pikachu",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        weight = 905,
        height = 17,
        hp = 80,
        attack = 85,
        defense = 75,
        specialAtack = 100
    )
    PokemonDetailContent(pokemon = pokemon, onBackClick = {}, onFavoriteClick = {}, likedStatus = true)
}
