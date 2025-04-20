package stepan.gorokhov.viboranet.auth.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.uikit.components.BaseButton
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.TextFieldWithIcon
import stepan.gorokhov.viboranet.uikit.images.Lock
import stepan.gorokhov.viboranet.uikit.images.Mail
import viboranet.features.auth.presentation.generated.resources.Res
import viboranet.features.auth.presentation.generated.resources.email
import viboranet.features.auth.presentation.generated.resources.password
import viboranet.features.auth.presentation.generated.resources.sign_in
import viboranet.features.auth.presentation.generated.resources.sign_up

@Composable
fun SignInScreen(navController: NavController) {
    val viewModel = koinViewModel<SignInViewModel>()
    LaunchedEffect(Unit) {
        Channel
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignInEffect.NavigateBack -> navController.navigateUp()
                is SignInEffect.NavigateHome -> navController.navigate(ApplicationRoute.Home.route)
            }
        }
    }
    val eventHandler = rememberUIEventHandler(viewModel)
    SignInScreen(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onEvent = eventHandler::sendEvent
    )
}

@Composable
fun SignInScreen(state: SignInState, onEvent: (SignInEvent) -> Unit) {
    BaseScaffold {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(Res.string.sign_in),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            SignInForm(state = state, onEvent = onEvent, modifier = Modifier.padding(top = 40.dp))
        }
    }
}

@Composable
private fun SignInForm(
    state: SignInState,
    onEvent: (SignInEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextFieldWithIcon(
            icon = Icons.Mail,
            value = state.email,
            placeholder = stringResource(Res.string.email),
            onValueChanged = { onEvent(SignInEvent.SetEmail(it)) },
            modifier = Modifier.fillMaxWidth()
        )

        TextFieldWithIcon(
            icon = Icons.Lock,
            value = state.password,
            placeholder = stringResource(Res.string.password),
            onValueChanged = { onEvent(SignInEvent.SetPassword(it)) },
            modifier = Modifier.fillMaxWidth()
        )

        BaseButton(
            text = stringResource(Res.string.sign_in),
            onClick = { onEvent(SignInEvent.SignInClicked) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}