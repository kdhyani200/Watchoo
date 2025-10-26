package kd.dhyani.project0024.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    id: Long,
    vm: DetailsViewModel = koinViewModel()
) {
    val uiState by vm.uiState

    LaunchedEffect(id) { vm.loadDetails(id) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF10131A)),
        containerColor = Color(0xFF10131A)
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFF10131A))
        ) {
            when {
                uiState.isLoading -> ShimmerDetails()
                uiState.errorMessage != null -> Text(
                    uiState.errorMessage ?: "Error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
                uiState.item != null -> {
                    val item = uiState.item!!
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = item.posterUrl?.let {
                                if (it.startsWith("http")) it else "https://image.tmdb.org/t/p/w500$it"
                            } ?: "https://via.placeholder.com/300x450.png?text=No+Image",
                            contentDescription = item.title,
                            modifier = Modifier
                                .size(230.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF2C2F38)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(Modifier.height(16.dp))
                        Text(
                            item.title ?: "",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(item.releaseDate ?: "", color = Color.Gray)

                        Spacer(Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            item.runtime_minutes?.let {
                                if (it > 0) {
                                    Chip("${item.runtime_minutes} min")
                                } else {
                                    Chip("N/A")
                                }
                            }
                        }

                        Spacer(Modifier.height(16.dp))
                        Text(
                            "Description \n",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            item.overview ?: "",
                            color = Color(0xFFB0B0B0),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFF2C2F38), RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text, color = Color.White, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun ShimmerDetails() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Poster shimmer
        Box(
            modifier = Modifier
                .size(width = 230.dp, height = 330.dp)
                .clip(RoundedCornerShape(16.dp))
                .placeholder(
                    visible = true,
                    color = Color(0xFF2C2F38),
                    highlight = PlaceholderHighlight.shimmer()
                )
        )

        Spacer(Modifier.height(20.dp))

        // Title shimmer
        Box(
            modifier = Modifier
                .height(24.dp)
                .width(180.dp)
                .clip(RoundedCornerShape(8.dp))
                .placeholder(
                    visible = true,
                    color = Color(0xFF2C2F38),
                    highlight = PlaceholderHighlight.shimmer()
                )
        )

        Spacer(Modifier.height(12.dp))

        // Release date shimmer
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .placeholder(
                    visible = true,
                    color = Color(0xFF2C2F38),
                    highlight = PlaceholderHighlight.shimmer()
                )
        )

        Spacer(Modifier.height(20.dp))

        // Runtime chip shimmer
        Box(
            modifier = Modifier
                .height(26.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(50))
                .placeholder(
                    visible = true,
                    color = Color(0xFF2C2F38),
                    highlight = PlaceholderHighlight.shimmer()
                )
        )

        Spacer(Modifier.height(30.dp))

        // Description shimmer - 3 lines
        repeat(3) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(6.dp))
                    .placeholder(
                        visible = true,
                        color = Color(0xFF2C2F38),
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )
            Spacer(Modifier.height(10.dp))
        }
    }
}


@Preview
@Composable
fun ShimmerDetailsPreview() {
    ShimmerDetails()
}