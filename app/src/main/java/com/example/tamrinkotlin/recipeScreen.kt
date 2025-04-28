package com.example.tamrinkotlin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.aspectRatio
import coil.compose.rememberAsyncImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val recipeMainViewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "recipe_list"
    ) {
        composable("recipe_list") {
            RecipeListScreen(
                modifier = modifier,
                viewModel = recipeMainViewModel,
                onCategoryClick = { categoryId ->
                    navController.navigate("category_detail/$categoryId")
                }
            )
        }
        composable("category_detail/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            val category = recipeMainViewModel.categoriesState.value.list.find { it.idCategory == categoryId }
            if (category != null) {
                CategoryDetailScreen(category = category)
            } else {
                Text("Error: Category not found", modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onCategoryClick: (String) -> Unit
) {
    val viewState by viewModel.categoriesState

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            viewState.error != null -> {
                Text(
                    text = viewState.error ?: "Unknown error",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                CategoryScreen(
                    categories = viewState.list,
                    onCategoryClick = onCategoryClick
                )
            }
        }
    }
}

@Composable
fun CategoryScreen(
    categories: List<Category>,
    onCategoryClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(categories) { category ->
            CategoryItem(
                category = category,
                onClick = { onCategoryClick(category.idCategory) }
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}