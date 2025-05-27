package stepan.gorokhov.viboranet.tests.presentation.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import stepan.gorokhov.viboranet.coreui.coil.AsyncImage
import kotlinx.collections.immutable.ImmutableList
import stepan.gorokhov.viboranet.tests.presentation.main.TestPreviewVO
import stepan.gorokhov.viboranet.uikit.components.BaseButton

internal fun LazyListScope.tests(
    tests: ImmutableList<TestPreviewVO>,
    onTestClicked: (String) -> Unit,
    onStartClicked: (String) -> Unit
) {
    items(tests) { test ->
        TestPreviewItem(
            test = test,
            onTestClicked = { onTestClicked(test.id) },
            onStartClicked = { onStartClicked(test.id) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp).padding(horizontal = 16.dp)
        )
    }
}

@Composable
internal fun TestPreviewItem(
    test: TestPreviewVO,
    onTestClicked: () -> Unit,
    onStartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Изображение теста
            AsyncImage(
                model = test.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Заголовок
            Text(
                text = test.title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Описание
            Text(
                text = test.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            // Информация об авторе и рейтинги
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AsyncImage(
                        model = test.author.image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = test.author.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Локальный рейтинг
                    when {
                        test.localRating > 0 -> {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = "Позитивная оценка",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        test.localRating < 0 -> {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = "Негативная оценка",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(20.dp).rotate(180f)
                            )
                        }
                        else -> {
                            Icon(
                                imageVector = Icons.Outlined.ThumbUp,
                                contentDescription = "Нейтральная оценка",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    
                    // Общий рейтинг
                    Text(
                        text = "★ ${test.rating}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Кнопки
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BaseButton(
                    onClick = onTestClicked,
                    modifier = Modifier.weight(1f),
                    text = "Подробнее"
                )
                BaseButton(
                    onClick = onStartClicked,
                    modifier = Modifier.weight(1f),
                    text = "Начать"
                )
            }
        }
    }
}