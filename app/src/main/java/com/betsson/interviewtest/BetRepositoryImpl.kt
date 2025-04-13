import com.betsson.interviewtest.Bet
import com.betsson.interviewtest.BetRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BetRepositoryImpl(private val betsJson: String) : BetRepository {

    private val gson = Gson()

    override fun fetchBets(): List<Bet> {
        return try {
            val type = object : TypeToken<List<Bet>>() {}.type
            gson.fromJson<List<Bet>>(betsJson, type).orEmpty()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
