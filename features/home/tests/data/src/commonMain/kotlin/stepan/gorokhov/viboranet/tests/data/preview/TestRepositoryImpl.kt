package stepan.gorokhov.viboranet.tests.data.preview

import kotlinx.coroutines.delay
import stepan.gorokhov.viboranet.tests.api.models.TestAuthor
import stepan.gorokhov.viboranet.tests.api.models.TestPreview
import stepan.gorokhov.viboranet.tests.api.models.TournamentResult
import stepan.gorokhov.viboranet.tests.api.models.TournamentTest
import stepan.gorokhov.viboranet.tests.api.models.TournamentTestOption
import stepan.gorokhov.viboranet.tests.api.repositories.TestRepository

private val testPreview = TestPreview(
    "1",
    title = "Хайповый",
    description = "231312",
    image = "https://cs6.pikabu.ru/post_img/big/2015/06/08/3/1433735650_472905306.jpg",
    author = TestAuthor(
        "","Степан", image = ""
    )
)

class TestRepositoryImpl : TestRepository {
    override suspend fun getTestPreviews(): Result<List<TestPreview>> {
        return Result.success(listOf(testPreview))
    }

    override suspend fun getTestPreview(id: String): Result<TestPreview> {
        return Result.success(testPreview)
    }

    override suspend fun getTournamentTest(id: String): Result<TournamentTest> {
        return Result.success(generateRealImageTournamentTest())
    }

    override suspend fun saveTournamentResult(result: TournamentResult): Result<String> {
        delay(2000)
        return Result.success("")
    }

    private fun generateRealImageTournamentTest(): TournamentTest {
        return TournamentTest(
            id = "real_images_tournament",
            title = "Картинки",
            author = TestAuthor(
                id = "unsplash",
                name = "Unsplash Community",
                image = "https://images.unsplash.com/profile-1581856915492-83c55e9cb0d2image?auto=format&fit=crop&w=64&h=64"
            ),
            options = listOf(
                TournamentTestOption("img1", "Горы", "Живописные горные пейзажи", "https://images.unsplash.com/photo-1454496522480-7a8358a9784f?w=400"),
                TournamentTestOption("img2", "Океан", "Волны и морские просторы", "https://images.unsplash.com/photo-1505228395891-9a51e7e86bf6?w=400"),
                TournamentTestOption("img3", "Лес", "Зеленые лесные массивы", "https://images.unsplash.com/photo-1448375240586-882707db888b?w=400"),
                TournamentTestOption("img4", "Архитектура", "Современные здания", "https://images.unsplash.com/photo-1487958449943-2429e8be8625?w=400"),
                TournamentTestOption("img5", "Еда", "Аппетитные блюда", "https://images.unsplash.com/photo-1504674900247-0877df9cc836?w=400"),
                TournamentTestOption("img6", "Животные", "Мир дикой природы", "https://images.unsplash.com/photo-1474511320723-9a56873867b5?w=400"),
                TournamentTestOption("img7", "Спорт", "Динамичные моменты", "https://images.unsplash.com/photo-1543351611-58f69d7c1781?w=400"),
                TournamentTestOption("img8", "Искусство", "Творческие работы", "https://images.unsplash.com/photo-1499781350541-7783f6c6a0c8?w=400"),
                TournamentTestOption("img9", "Технологии", "Инновационные устройства", "https://images.unsplash.com/photo-1518770660439-4636190af475?w=400"),
                TournamentTestOption("img10", "Путешествия", "Знаковые места", "https://images.unsplash.com/photo-1506929562872-bb421503ef21?w=400"),
                TournamentTestOption("img11", "Наука", "Лабораторные исследования", "https://images.unsplash.com/photo-1532094349884-543bc11b234d?w=400"),
                TournamentTestOption("img12", "Мода", "Стильные образы", "https://images.unsplash.com/photo-1483985988355-763728e1935b?w=400"),
                TournamentTestOption("img13", "Транспорт", "Автомобили и техника", "https://images.unsplash.com/photo-1502877338535-766e1452684a?w=400"),
                TournamentTestOption("img14", "Кофе", "Ароматные напитки", "https://images.unsplash.com/photo-1517701550927-30cf4ba1dba5?w=400"),
                TournamentTestOption("img15", "Книги", "Литературный мир", "https://images.unsplash.com/photo-1544947950-fa07a98d237f?w=400"),
                TournamentTestOption("img16", "Музыка", "Музыкальные инструменты", "https://images.unsplash.com/photo-1511671782779-c97d3d27a1d4?w=400"),
                TournamentTestOption("img17", "Сад", "Садоводство и растения", "https://images.unsplash.com/photo-1585320806297-9794b3e4eeae?w=400"),
                TournamentTestOption("img18", "Ночной город", "Огни мегаполиса", "https://images.unsplash.com/photo-1514565131-fce0801e5785?w=400"),
                TournamentTestOption("img19", "Зима", "Снежные пейзажи", "https://images.unsplash.com/photo-1483728642387-6c3bdd6c93e5?w=400"),
                TournamentTestOption("img20", "Лето", "Солнечные дни", "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=400"),
                TournamentTestOption("img21", "Водопады", "Мощь природы", "https://images.unsplash.com/photo-1519692933481-e162a57d6721?w=400"),
                TournamentTestOption("img22", "Пустыня", "Бескрайние пески", "https://images.unsplash.com/photo-1517649763962-0c623066013b?w=400"),
                TournamentTestOption("img23", "Фестивали", "Культурные мероприятия", "https://images.unsplash.com/photo-1492684223066-81342ee5ff30?w=400"),
                TournamentTestOption("img24", "Ретро", "Винтажная атмосфера", "https://images.unsplash.com/photo-1507914464562-6ff4f296e258?w=400"),
                TournamentTestOption("img25", "Футуризм", "Концепты будущего", "https://images.unsplash.com/photo-1535376472810-5d229c65da09?w=400"),
                TournamentTestOption("img26", "Медитация", "Практики осознанности", "https://images.unsplash.com/photo-1533365550721-64b022e0e8e9?w=400"),
                TournamentTestOption("img27", "Фотография", "Профессиональные камеры", "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=400"),
                TournamentTestOption("img28", "Дождь", "Атмосферные капли", "https://images.unsplash.com/photo-1534274988757-a28bf1a57c17?w=400"),
                TournamentTestOption("img29", "Рассвет", "Утренние краски", "https://images.unsplash.com/photo-1498550744921-75f79806b8a7?w=400"),
                TournamentTestOption("img30", "Космос", "Звезды и галактики", "https://images.unsplash.com/photo-1462331940025-496dfbfc7564?w=400"),
                TournamentTestOption("img31", "Подводный мир", "Обитатели глубин", "https://images.unsplash.com/photo-1506929562872-bb421503ef21?w=400"),
                TournamentTestOption("img32", "Городская жизнь", "Повседневные моменты", "https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?w=400")
            )
        )
    }
}