package stepan.gorokhov.viboranet.tests.presentation.ongoingTest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.stringResource
import stepan.gorokhov.viboranet.coreui.mvi.EventHandler
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.OngoingTestEvent
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.OngoingTestState
import stepan.gorokhov.viboranet.tests.presentation.ongoingTest.TestOptionVO
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.VerticalSpacer
import viboranet.features.home.tests.presentation.generated.resources.Res
import viboranet.features.home.tests.presentation.generated.resources.question
import viboranet.features.home.tests.presentation.generated.resources.stage

@Composable
internal fun OngoingTestScreen(
    state: OngoingTestState.Started,
    eventHandler: EventHandler<OngoingTestEvent>
) {
    val onOptionClicked: (String) -> Unit =
        { eventHandler.handleEvent(OngoingTestEvent.OptionPicked(it)) }
    BaseScaffold {
        Column(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            TestHeader(state, modifier = Modifier.padding(bottom = 20.dp))
            TestOption(
                testOption = state.currentChoice.first,
                onClick = onOptionClicked,
                modifier = Modifier.fillMaxSize().weight(1f)
            )
            VerticalSpacer(10.dp)
            TestOption(
                testOption = state.currentChoice.second,
                onClick = onOptionClicked,
                modifier = Modifier.fillMaxSize().weight(1f)
            )
        }
    }
}

@Composable
private fun TestHeader(state: OngoingTestState.Started, modifier: Modifier = Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            state.testTitle,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(stringResource(Res.string.stage, state.currentStage, state.stageCount))
        Text(stringResource(Res.string.question, state.currentChoiceIndex, state.choiceCount))
    }
}

@Composable
private fun TestOption(
    testOption: TestOptionVO,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.height(IntrinsicSize.Max)) {
        AsyncImage(
            testOption.image,
            contentDescription = testOption.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClick(testOption.id) }
                .background(Color.Gray)
                .alpha(0.5f)
        )
        AsyncImage(
            testOption.image,
            contentDescription = testOption.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize(0.9f)
                .clip(RoundedCornerShape(16.dp))
                .align(Alignment.Center)
        )
    }
}