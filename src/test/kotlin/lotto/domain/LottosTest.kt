package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottosTest {

    @Test
    fun `상금 리스트를 반환합니다`() {
        val lottos = Lottos(
            listOf(
                Lotto.with(listOf(1, 2, 3, 4, 5, 6)),
                Lotto.with(listOf(1, 2, 3, 4, 5, 49))
            )
        )
        val winnigLotto = Lotto.with(listOf(1, 2, 3, 4, 5, 49))

        assertThat(lottos.matchNumbers(winnigLotto)).containsExactly(Reward.SECOND_RANK, Reward.FIRST_RANK)
    }
}
