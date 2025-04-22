package stepan.gorokhov.viboranet.auth.presentation.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import stepan.gorokhov.viboranet.auth.presentation.signin.navigateSignIn
import stepan.gorokhov.viboranet.common.presentation.ApplicationRoute
import stepan.gorokhov.viboranet.coreui.mvi.rememberUIEventHandler
import stepan.gorokhov.viboranet.coreui.routing.popUpGraph
import stepan.gorokhov.viboranet.uikit.components.BaseButton
import stepan.gorokhov.viboranet.uikit.components.BaseScaffold
import stepan.gorokhov.viboranet.uikit.components.TextFieldWithIcon
import stepan.gorokhov.viboranet.uikit.images.Lock
import stepan.gorokhov.viboranet.uikit.images.Mail
import viboranet.features.auth.presentation.generated.resources.Res
import viboranet.features.auth.presentation.generated.resources.already_registered
import viboranet.features.auth.presentation.generated.resources.create_account
import viboranet.features.auth.presentation.generated.resources.email
import viboranet.features.auth.presentation.generated.resources.password
import viboranet.features.auth.presentation.generated.resources.password_confirmation
import viboranet.features.auth.presentation.generated.resources.sign_in
import viboranet.features.auth.presentation.generated.resources.sign_up

@Composable
fun SignUpScreen(navController: NavController) {
    val viewModel = koinViewModel<SignUpViewModel>()
    val eventHandler = rememberUIEventHandler(viewModel)
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SignUpEffect.NavigateSignIn -> navController.navigateSignIn()
                is SignUpEffect.NavigateHome -> navController.navigate(ApplicationRoute.Home.route) {
                    popUpGraph()
                }
            }
        }
    }
    SignUpScreen(state = viewModel.state.collectAsState().value, onEvent = eventHandler::sendEvent)
}

@Composable
fun SignUpScreen(state: SignUpState, onEvent: (SignUpEvent) -> Unit) {
    BaseScaffold {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(Res.string.create_account),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            SignUpForm(state = state, onEvent = onEvent, modifier = Modifier.padding(top = 40.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                modifier = Modifier.padding(top = 40.dp)
            ) {
                Text(
                    stringResource(Res.string.already_registered),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    stringResource(Res.string.sign_in),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.clickable { onEvent(SignUpEvent.NavigateSignInClicked) }
                )
            }
        }
    }
}

@Composable
private fun SignUpForm(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        TextFieldWithIcon(
            icon = Icons.Mail,
            value = state.email,
            placeholder = stringResource(Res.string.email),
            onValueChanged = { onEvent(SignUpEvent.SetEmail(it)) },
            modifier = Modifier.fillMaxWidth()
        )

        TextFieldWithIcon(
            icon = Icons.Lock,
            value = state.password,
            placeholder = stringResource(Res.string.password),
            onValueChanged = { onEvent(SignUpEvent.SetPassword(it)) },
            modifier = Modifier.fillMaxWidth()
        )

        TextFieldWithIcon(
            icon = Icons.Lock,
            value = state.passwordConfirmation,
            placeholder = stringResource(Res.string.password_confirmation),
            onValueChanged = { onEvent(SignUpEvent.SetPasswordConfirmation(it)) },
            modifier = Modifier.fillMaxWidth()
        )

        BaseButton(
            text = stringResource(Res.string.sign_up),
            onClick = { onEvent(SignUpEvent.SignUpClicked) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}