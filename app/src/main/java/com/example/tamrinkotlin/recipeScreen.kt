package com.example.tamrinkotlin

import android.media.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.lazy.grid.items


@Composable
fun recipescreen(modifier: Modifier = Modifier)
{
val recipmainviewmodel : MainViewModel = viewModel()
    val viewstate by recipmainviewmodel.Categorieissstate
    Box(modifier = Modifier.fillMaxSize()) {
        when{
            viewstate.Loading ->{
                CircularProgressIndicator(Modifier.align (Alignment.Center ))
            }
            viewstate.error!=null ->{
                Text("error")
            }
            else->{
                categoryscreen(viewstate.List)

            }
        }
    }

}
@Composable
fun categoryscreen(categorys: List<category>)
{
    LazyVerticalGrid(GridCells.Fixed(2), Modifier.fillMaxSize())
    {
        items(categorys) {
            category->
            CategoryItem(category)



        }

    }
}
@Composable
fun CategoryItem(category: category)
{
    Column(modifier = Modifier.padding(8.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)

        )



        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)




        )
    }
}