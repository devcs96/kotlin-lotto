package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoBall
import lotto.domain.LottoStore
import lotto.domain.Lottos
import lotto.domain.RandomLottoGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val lottoStore = LottoStore(RandomLottoGenerator())

    val money = InputView.askLottoBuyMoney()
    val manualLottoBuyNumber = InputView.askManualLottoBuyNumber()
    val manualLottos = Lottos(
        InputView.askManualLotto(manualLottoBuyNumber)
            .map { Lotto.of(it) }
    )
    val randomLottos = lottoStore.buy(money, manualLottoBuyNumber)
    val totalLottos = manualLottos + randomLottos

    OutputView.printLottoBuyStatus(manualLottoBuyNumber, totalLottos.size() - manualLottoBuyNumber)
    OutputView.printLottos(totalLottos)
    val winningLotto = Lotto.of(
        InputView.askWinningLottoBall()
    )

    val bonusBall = LottoBall(InputView.askBonusBall())
    if (winningLotto.containsBall(bonusBall)) {
        throw IllegalArgumentException("보너스볼은 이미 추첨된 번호가 될 수 없습니다.")
    }
    val rewards = totalLottos.matchNumbers(winningLotto, bonusBall)
    OutputView.printRewards(rewards, rewards.calculateProfit(money))
}
