Game Slot Simulator
這是一個用於模擬老虎機遊戲邏輯的專案，主要包含不同遊戲觸發規則、支付表、隨機數生成器等模組。專案中已經實現了基於 JUnit 的單元測試，以確保每個組件的正確性和穩定性。

目錄結構
以下是主要的 Java 類和其功能說明：

DBC.java: 定義了合約消息。

FreeGameTriggeringRules.java: 定義了免費遊戲觸發規則的接口。

GameFlow.java: 基本遊戲恢復邏輯。

GongXiFaCaiTriggeringRules.java: 另一個免費遊戲觸發規則的接口。

MasterPiecePayTable.java: 重新命名支付表。

Memento.java: 基本遊戲恢復邏輯。

NativeRandomNumberGenerator.java: 提取隨機數生成接口。

PayTable.java: 提取支付表接口。

PreConditionViolateException.java: 定義了合約消息異常。

RandomNumberGenerator.java: 提取隨機數生成接口。

Reel.java: 基本遊戲恢復邏輯。

Reels.java: 基本遊戲恢復邏輯。

Screen.java: 將方法移動到 Screen 類。

SlotKingPayTable.java: 重新命名支付表。

SlotScoreCalculator.java: 免費遊戲觸發規則接口。

SpinResult.java: 提取方法。

WrongModeException.java: 添加了 slotsimulator 測試。

單元測試
專案已經設置好單元測試，這些測試基於 JUnit 框架，涵蓋了各種遊戲邏輯和異常處理情況。測試可以幫助你確保各個模組的功能按預期運行，並在開發過程中發現潛在的錯誤。

測試類別
SlotSimulatorTest: 測試遊戲模擬器的核心邏輯。

RandomNumberGeneratorTest: 測試隨機數生成器的正確性。

PayTableTest: 測試支付表的邏輯。
