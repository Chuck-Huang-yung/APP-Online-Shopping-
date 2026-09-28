# 🛒 智慧家電與電子產品商城 APP 
**(Smart Electronics E-Commerce App)**

![Android](https://img.shields.io/badge/Android-Kotlin-3DDC84?logo=android&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-Build_Tool-02303A?logo=gradle&logoColor=white)

---

## 💡 專案簡介
> 本專案為一款專為「智慧家電與電子產品」設計的行動端商城 APP。系統具備完整的電商瀏覽體驗，並實作了核心的**購物車與結帳邏輯**。透過此專案，展現了 Android 原生開發中對於複雜 UI 狀態管理、資料傳遞以及購物車生命週期的掌握能力。

---

## 📱 實際操作頁面 - 商品瀏覽、購物車結帳頁面

<img width="303" alt="image" src="https://github.com/user-attachments/assets/54be91f5-7f15-42f7-9e26-c5ad6256fc96" />
<img width="309" alt="image" src="https://github.com/user-attachments/assets/fe4382e9-cc20-41ba-a900-d6af81f0bbe7" />

---

## ✨ 核心功能亮點 (Core Features)

* **🛍️ 商品展示與分類：** 專為電子產品設計的商品型錄，支援圖文列表展示，讓使用者快速瀏覽家電規格。
* **🛒 購物車狀態管理：** 實作購物車核心邏輯，包含商品加入/移除、數量增減、以及動態小計金額計算。
* **💳 模擬結帳流程：** 從購物車結算到訂單確認，提供流暢的端到端 (End-to-End) 模擬交易體驗。
* **📱 原生流暢 UI：** 採用 Android 原生元件開發，確保畫面滑動流暢與良好的使用者互動體驗。

---

## 🛠️ 技術架構與系統設計 (Tech Stack & Architecture)

* **開發語言：** Kotlin / Java (Android SDK)
* **架構元件：** Activity / Fragment 生命週期管理
* **UI 實作：** RecyclerView (商品列表高效率渲染), 原生 XML 佈局設計
* **建置工具：** Gradle (Kotlin DSL)

---

## 📁 專案核心目錄導覽 (Directory Structure)

```text
APP-Online-Shopping/
├── app/                        # 應用程式主模組 (Application Module)
│   ├── src/main/java/          # 核心程式碼 (Activity, Fragment, Adapter 等邏輯實作)
│   ├── src/main/res/           # 資源檔案 (XML 佈局設計, 圖片資產, 字串與顏色定義)
│   └── build.gradle.kts        # App 模組層級 Gradle 建置腳本 (依賴庫設定)
├── gradle/                     # Gradle Wrapper 設定檔與環境
├── build.gradle.kts            # 專案層級 (Project-level) Gradle 建置腳本
├── settings.gradle.kts         # 專案模組註冊與全域依賴管理設定
├── gradle.properties           # Gradle 專案環境變數與效能設定參數
├── gradlew / gradlew.bat       # Gradle 命令列執行腳本 (Mac/Linux 與 Windows)
├── .gitignore                  # Git 版本控制忽略清單
└── README.md                   # 專案說明文件

---

## 🚀 學習與開發重點
在開發此購物車系統的過程中，主要克服了以下技術挑戰：
1. **跨頁面資料傳遞：** 確保商品詳情頁與購物車之間的資料同步，避免狀態不一致。
2. **RecyclerView 效能優化：** 處理大量商品圖文載入時的滑動順暢度。
3. **商業邏輯封裝：** 將金額計算、數量防呆邏輯獨立封裝，提升程式碼可維護性。
