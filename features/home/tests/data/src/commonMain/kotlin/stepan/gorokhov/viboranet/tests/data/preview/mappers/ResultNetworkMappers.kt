package stepan.gorokhov.viboranet.tests.data.preview.mappers

import stepan.gorokhov.viboranet.coredata.network.NetworkConstants
import stepan.gorokhov.viboranet.tests.api.models.NewTournamentResult
import stepan.gorokhov.viboranet.tests.api.models.ResultOption
import stepan.gorokhov.viboranet.tests.api.models.TournamentResult
import stepan.gorokhov.viboranet.tests.api.models.TournamentResultOption
import stepan.gorokhov.viboranet.tests.api.models.TournamentTestOption
import stepan.gorokhov.viboranet.tests.data.preview.network.models.ResultDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.ResultOptionDto
import stepan.gorokhov.viboranet.tests.data.preview.network.models.SaveResultOption
import stepan.gorokhov.viboranet.tests.data.preview.network.models.SaveResultRequest

fun NewTournamentResult.toRequest(): SaveResultRequest {
    return SaveResultRequest(
        testId = testId,
        options = options.map { it.toDto() }
    )
}

fun ResultOption.toDto(): SaveResultOption {
    return SaveResultOption(
        index = index,
        localScore = score
    )
}

fun ResultDto.toDomain(): TournamentResult {
    return TournamentResult(
        id = id,
        userId = userId,
        options = options.map { it.toDomain() }
    )
}

fun ResultOptionDto.toDomain(): TournamentResultOption {
    return TournamentResultOption(
        index = index,
        title = title,
        image = "${NetworkConstants.BASE_URL}/images/$imageId",
        localScore = localScore,
        globalScore = globalScore
    )
}