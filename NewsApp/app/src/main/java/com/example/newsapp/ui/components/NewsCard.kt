package com.example.newsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.data.model.Article

@Composable
fun NewsCard(
    article: Article,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Side: Square Rounded Image
        AsyncImage(
            model = article.urlToImage ?: "https://images.unsplash.com/photo-1504711434969-e33886168f5c?w=800",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(76.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Right Side: Article Metadata & Title
        Column(
            modifier = Modifier
                .weight(1f)
                .height(76.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Title
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    lineHeight = 19.sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Metadata Row: Category/Author + Published At
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val category = article.author?.uppercase() ?: "TECHNOLOGY"
                Text(
                    text = category,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary, // Pink/purple color
                        fontSize = 11.sp
                    )
                )

                Text(
                    text = "  •  ${article.publishedAt}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        fontSize = 11.sp
                    )
                )
            }
        }
    }
}
