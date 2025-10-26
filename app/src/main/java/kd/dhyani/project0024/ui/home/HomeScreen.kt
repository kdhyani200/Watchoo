package kd.dhyani.project0024.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kd.dhyani.project0023.ui.home.HomeViewModel
import kd.dhyani.project0024.R
import kd.dhyani.project0024.data.model.MediaItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    vm: HomeViewModel = koinViewModel()
) {
    val uiState by vm.uiState
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF10131A)),
        containerColor = Color(0xFF10131A),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Watchoo",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF10131A)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(Color(0xFF10131A))
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            // Tabs (Movies / TV Shows)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color(0xFF181B23), shape = RoundedCornerShape(50)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TabButton("Movies", selectedTab == 0) {
                    selectedTab = 0
                    vm.showMovies()
                }
                TabButton("TV Shows", selectedTab == 1) {
                    selectedTab = 1
                    vm.showTvShows()
                }
            }

            Spacer(Modifier.height(12.dp))

            when {
                uiState.isLoading -> ShimmerGrid()

                uiState.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(24.dp)
                        ) {
                            Text(
                                text = "⚠️",
                                style = MaterialTheme.typography.displayMedium
                            )

                            Text(
                                text = "Network Error",
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Failed to fetch data. Please check your internet connection and try again.",
                                color = Color(0xFFB0B0B0),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )

                            Spacer(Modifier.height(8.dp))

                            Button(
                                onClick = { vm.retry() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2C2F38)
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth(0.6f)
                                    .height(48.dp)
                            ) {
                                Text(
                                    text = "Retry",
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(uiState.items) { item ->
                            MovieItem(item = item) {
                                navController.navigate("details/${item.id}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.TabButton(text: String, selected: Boolean, onClick: () -> Unit) {
    val bg = if (selected) Color(0xFF2C2F38) else Color.Transparent
    val textColor = if (selected) Color.White else Color.Gray

    Box(
        modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(50))
            .background(bg)
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = textColor, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun MovieItem(item: MediaItem, onClick: () -> Unit) {
    // Construct poster URL from Watchmode ID (zero-padded to 8 digits)
    val imageUrl = remember(item.id) {
        val paddedId = item.id.toString().padStart(8, '0')
        "https://cdn.watchmode.com/posters/${paddedId}_poster_w185.jpg"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp)
            .clickable { onClick() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF2C2F38)
            )
        ) {

            AsyncImage(
                model = imageUrl,
                contentDescription = item.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_launcher_foreground)
            )
        }

        Spacer(Modifier.height(6.dp))

        Text(
            text = item.title ?: "Unknown",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1
        )

        Text(
            text = item.releaseDate?.take(4) ?: "",
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ShimmerGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(15) {
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = Color(0xFF2C2F38)
                    )
            )
        }
    }
}