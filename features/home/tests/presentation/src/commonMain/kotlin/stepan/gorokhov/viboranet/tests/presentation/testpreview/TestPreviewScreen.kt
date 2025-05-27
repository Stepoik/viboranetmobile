package stepan.gorokhov.viboranet.tests.presentation.testpreview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.navigateOngoingTest

@Composable
fun TestPreviewScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<TestPreviewViewModel>()
    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is TestPreviewEffect.NavigateTest -> navController.navigateOngoingTest(it.id)
            }
        }
    }
    val state by viewModel.state.collectAsState()

    when (val screenState = state) {
        is TestPreviewState.Idle -> {
            LaunchedEffect(Unit) {
                viewModel.handleEvent(TestPreviewEvent.LoadPreview)
            }
        }

        is TestPreviewState.Loading -> {
            TestPreviewSkeleton()
        }

        is TestPreviewState.Error -> {

        }

        is TestPreviewState.TestPreviewLoaded -> {
            TestPreviewContent(
                preview = screenState.preview,
                onStartClick = { viewModel.handleEvent(TestPreviewEvent.StartButtonClicked) },
                onLikeClick = { viewModel.handleEvent(TestPreviewEvent.LikeClicked) },
                onDislikeClick = { viewModel.handleEvent(TestPreviewEvent.DislikeClicked) }
            )
        }
    }
}

@Composable
private fun TestPreviewContent(
    preview: TestPreview,
    onStartClick: () -> Unit,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Изображение теста
        AsyncImage(
            model = preview.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        // Заголовок
        Text(
            text = preview.title,
            style = MaterialTheme.typography.headlineMedium
        )

        // Описание
        Text(
            text = preview.description,
            style = MaterialTheme.typography.bodyLarge
        )

        // Информация об авторе
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = preview.author.image,
                contentDescription = null,
                modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.Gray)
            )
            Text(
                text = preview.author.name,
                style = MaterialTheme.typography.titleMedium
            )
        }

        // Рейтинги и кнопки лайк/дизлайк
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Локальный рейтинг
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = onLikeClick,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (preview.localRating > 0) Icons.Default.ThumbUp else Icons.Outlined.ThumbUp,
                        contentDescription = "Лайк",
                        tint = if (preview.localRating > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                IconButton(
                    onClick = onDislikeClick,
                    modifier = Modifier
                        .size(32.dp)
                        .rotate(180f)
                ) {
                    Icon(
                        imageVector = if (preview.localRating < 0) Icons.Default.ThumbUp else Icons.Outlined.ThumbUp,
                        contentDescription = "Дизлайк",
                        tint = if (preview.localRating < 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Общий рейтинг
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = preview.globalRating.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Кнопка начала теста
        Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Начать тест")
        }
    }
}
