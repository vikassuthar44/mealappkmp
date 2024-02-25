import UIKit
import SwiftUI
import ComposeApp
//import shared

struct ComposeView: UIViewControllerRepresentable {
    /*private let userDefaultsSettingsRepository: SettingsRepository = SettingsRepository(settings: NSUserDefaultsSettings(delegate: UserDefaults.standard))
        
        private let keychainSettingsRepository: SettingsRepository = SettingsRepository(settings: KeychainSettings(service: "Settings Demo"))
    
    @State
      var settingsRepository: SettingsRepository
    
    init() {
        settingsRepository = userDefaultsSettingsRepository
    }*/
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()//settingsRepository)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}



