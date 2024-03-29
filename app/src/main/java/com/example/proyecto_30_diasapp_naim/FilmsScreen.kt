package com.example.proyecto_30_diasapp_naim

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyecto_30_diasapp_naim.model.Film
import com.example.proyecto_30_diasapp_naim.ui.theme.Proyecto_30_DiasAPP_NaimTheme
import com.example.proyecto_30_diasapp_naim.ui.theme.changeIconColor
import com.example.proyecto_30_diasapp_naim.ui.theme.changeStarsColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmTopAppBar(modifier: Modifier = Modifier) {
    val icon = changeIconColor()
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.icon_size)),
                    painter = icon,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacer_rndm)))
                Box(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        modifier = modifier
    )
}


@Composable
fun FilmsList(filmsList: List<Film>, modifier: Modifier = Modifier)
{
    LazyColumn(modifier = modifier,
        contentPadding = PaddingValues(
            top = dimensionResource(R.dimen.padding_small),
            bottom = dimensionResource(R.dimen.padding_small))) {
        items(filmsList) {
            FilmItem(film = it)
        }
    }
}

@Composable
fun FilmItem(film: Film, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var myRating by rememberSaveable { mutableStateOf(1) }
    var savedRating by rememberSaveable { mutableStateOf(myRating) }
    val starsColor = changeStarsColor()
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.onTertiaryContainer else MaterialTheme.colorScheme.tertiaryContainer,
        label = "inspection label"
    )



    Card(
        modifier = modifier.padding
            (
            vertical = dimensionResource(R.dimen.padding_small),
            horizontal = dimensionResource(R.dimen.padding_medium)
                    ),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.padding_verysmall))
    ) {
        Column(
            modifier = Modifier
                .background(color = color)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.dia,film.dayRes),
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = stringResource(film.titleRes),
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                    Image(
                        modifier = Modifier
                            .width(dimensionResource(R.dimen.image_width))
                            .height(dimensionResource(R.dimen.image_height))
                            .clip(RoundedCornerShape(dimensionResource(R.dimen.padding_medium)))
                            .clickable { expanded = !expanded },
                        painter = painterResource(film.imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                FilmItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) {
                Column(
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small)
                    )
                ) {
                    Text(
                        text = stringResource(film.descRes),
                        style = MaterialTheme.typography.labelSmall
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
                    RatingBar(
                        currentRating = myRating,
                        onRatingChanged = {
                            myRating = it
                            savedRating = it
                        },
                        starsColor = starsColor
                    )
                }

            }
        }
    }

}

@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit,
    starsColor: Color = Color.Yellow
) {
    Row {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) Icons.Filled.Star
                else Icons.Filled.StarOutline,
                contentDescription = null,
                tint = if (i <= currentRating) starsColor
                else Color.Unspecified,
                modifier = Modifier
                    .clickable { onRatingChanged(i) }
                    .padding(dimensionResource(R.dimen.padding_smaller))
            )
        }
    }
}



@Composable
private fun FilmItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ){
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    Proyecto_30_DiasAPP_NaimTheme {
        FilmApp()
    }
}