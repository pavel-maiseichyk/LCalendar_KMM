//
//  Colors.swift
//  iosApp
//
//  Created by Paul on 02/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension Color {
    init(hex: Int64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double ((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
    
    private static let colors = AppColors()
    static let iron = Color(hex: colors.Iron)
    static let cameo = Color(hex: colors.Cameo)
    static let perano = Color(hex: colors.Perano)
    static let sindbad = Color(hex: colors.Sindbad)
    static let sidecar = Color(hex: colors.Sidecar)
    static let wildSand = Color(hex: colors.WildSand)
    static let cornflowerLilac = Color(hex: colors.Cornflower_Lilac)
    
    static let primaryColor = Color(light: .sidecar, dark: .sidecar)
    static let secondaryColor = Color(light: .cameo, dark: .cameo)
    static let backgroundColor = Color(light: .iron, dark: .iron)
    static let surfaceColor = Color(light: .wildSand, dark: .wildSand)
    static let pastMeetingColor = Color(light: .perano, dark: .perano)
    static let futureMeetingColor = Color(light: .sindbad, dark: .sindbad)
    static let specialColor = Color(light: .cornflowerLilac, dark: .cornflowerLilac)
    static let onColor = Color(light: .black, dark: .black)
}

private extension Color {
    init(light: Self, dark: Self) {
        self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(dark)))
    }
}

private extension UIColor {
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
            case .light, .unspecified:
                return light
            case .dark:
                return dark
            @unknown default:
                return light
            }
        }
    }
}
