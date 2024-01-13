package com.example.superheroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository

@Composable
fun HeroesList(heroList: List<Hero>,
               contentPadding: PaddingValues = PaddingValues(0.dp),
               modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier,
        contentPadding = contentPadding) {
        items(heroList) {
            HeroCard(
                hero = it,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun HeroCard(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                //??
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(end=16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = LocalContext.current.getString(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = LocalContext.current.getString(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Image(
                painter = painterResource(hero.imageRes),
                contentDescription = stringResource(hero.nameRes),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SuperheroesListPreview() {
    HeroesList(HeroesRepository.heroes)
}

@Preview(showBackground = true)
@Composable
fun SuperheroesCardPreview() {
    HeroCard(
        Hero(
            nameRes = R.string.hero1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.android_superhero1
        )
    )
}